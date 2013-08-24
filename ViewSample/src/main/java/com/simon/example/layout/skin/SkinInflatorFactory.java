package com.simon.example.layout.skin;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import com.simon.example.layout.BuildConfig;
import com.simon.example.layout.R;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.simon.example.layout.skin.HookType.BOOLEAN;
import static com.simon.example.layout.skin.HookType.COLOR;
import static com.simon.example.layout.skin.HookType.DIMENSION;
import static com.simon.example.layout.skin.HookType.FLOAT;
import static com.simon.example.layout.skin.HookType.INTEGER;
import static com.simon.example.layout.skin.HookType.REFERENCE_ID;
import static com.simon.example.layout.skin.HookType.STRING;

/**
 * 布局钩子
 *
 * @author yulu02
 */
public class SkinInflatorFactory implements LayoutInflater.Factory {
    private static final String TAG = "SkinInflatorFactory";

    private HookSet[] mHookSets;

    private static final String LOAD_PREFIX = "android.widget.";

    private Map<String, Constructor<? extends View>> mConstructors;

    private DisplayMetrics mDisplayMetrics;
    private Resources mRes;

    public SkinInflatorFactory(Context context) {
        mConstructors = new HashMap<String, Constructor<? extends View>>();
        mDisplayMetrics = context.getResources().getDisplayMetrics();
        mRes = context.getResources();
    }

    /**
     * Use to bind a typed value to an applier
     */
    public static class ValueInfo {
        public String theme;
        public TypedValue typedValue;
        public Hook.Apply apply;

        public ValueInfo(String theme, TypedValue typedValue, Hook.Apply apply) {
            this.theme = theme;
            this.typedValue = typedValue;
            this.apply = apply;
        }
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        long now;
        if (BuildConfig.DEBUG) {
            now = SystemClock.uptimeMillis();
        }
        View view = null;
        try {
            Constructor<? extends View> constructor;
            Class<? extends View> c;
            constructor = mConstructors.get(name);
            if (constructor == null) {
                ClassLoader classLoader = context.getClassLoader();
                if (classLoader != null) {
                    c = classLoader.loadClass(name.contains(".") ? name : LOAD_PREFIX + name).asSubclass(View.class);
                    constructor = c.getConstructor(Context.class, AttributeSet.class);
                    mConstructors.put(name, constructor);
                    view = constructor.newInstance(context, attrs);
                } else {
                    return null;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "view name = " + name);
            final int count = attrs.getAttributeCount();
            for (int i = 0; i < count; i++) {
                Log.v(TAG, "attribute " + i + " : " + attrs.getAttributeName(i) + ":" + attrs.getAttributeValue(i));
            }
        }

        if (mHookSets == null || mHookSets.length == 0) return view;

        final List<ValueInfo> infos = new ArrayList<ValueInfo>();

        for (HookSet hookSet : mHookSets) {
            Log.d(TAG, "hook set : " + hookSet.getPrefix());
            for (Hook hook : hookSet.values()) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "try hook name: " + hook.hookName());
                }
                final String value = attrs.getAttributeValue(hookSet.getNamespace(), hook.hookName());
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "try hook value: " + value);
                }
                if (value == null) {
                    continue;
                }
                TypedValue tv = null;
                final int hookType = hook.hookType();
                if ((hookType & REFERENCE_ID) == REFERENCE_ID) {
                    tv = TypedValueParser.parseReference(value, mRes);
                }
                if (tv == null) {
                    if ((hookType & COLOR) == COLOR) {
                        tv = TypedValueParser.parseColor(value);
                    } else if ((hookType & STRING) == STRING) {
                        tv = TypedValueParser.parseString(value);
                    } else if ((hookType & FLOAT) == FLOAT) {
                        tv = TypedValueParser.parseFloat(value);
                    } else if ((hookType & INTEGER) == INTEGER) {
                        tv = TypedValueParser.parseInt(value);
                    } else if ((hookType & BOOLEAN) == BOOLEAN) {
                        tv = TypedValueParser.parseInt(value);
                    } else if ((hookType & DIMENSION) == DIMENSION) {
                        tv = TypedValueParser.parseDimension(value, mDisplayMetrics);
                    }
                }
                if (tv == null) continue;

                if (BuildConfig.DEBUG) {
                    Log.i(TAG, "parsed TypedValue = " + tv.toString());
                }

                if (hook.shouldHook(view, tv)) {
                    infos.add(new ValueInfo(hookSet.getPrefix(), tv, hook.getApply()));
                }
            }
        }

        if (infos.size() > 0) {
            ViewTagger.setTag(view, R.id.skin_hooker, infos);
        }

        if (BuildConfig.DEBUG) {
            now = SystemClock.uptimeMillis() - now;
            Log.d(TAG, "inflate view time = " + now);
        }

        return view;
    }

    void addHookSet(HookSet hookSet) {
        if (mHookSets == null) {
            HookSet[] n = new HookSet[1];
            n[0] = hookSet;
            mHookSets = n;
        } else {
            HookSet[] n = new HookSet[mHookSets.length + 1];
            System.arraycopy(mHookSets, 0, n, 0, mHookSets.length);
            n[mHookSets.length] = hookSet;
            mHookSets = n;
        }
    }

}
