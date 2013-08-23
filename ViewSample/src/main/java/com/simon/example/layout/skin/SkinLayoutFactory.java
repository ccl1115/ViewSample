package com.simon.example.layout.skin;

import android.content.Context;
import android.util.AttributeSet;
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

/**
 * 布局钩子
 *
 * @author yulu02
 */
public class SkinLayoutFactory implements LayoutInflater.Factory {
    private static final String TAG = "SkinLayoutFactory";

    private static final String SKIN_NAMESPACE = "skin";

    private HookerSet mHookerSet;

    private static final String LOAD_PREFIX = "android.widget.";

    private Map<String, Constructor<? extends View>> mConstructors;

    public SkinLayoutFactory(Context context) {
        mConstructors = new HashMap<String, Constructor<? extends View>>();

    }

    public static class ValueInfo {
        public TypedValue mTypedValue;
        public Hooker.Apply mApply;

        public ValueInfo(TypedValue typedValue, Hooker.Apply apply) {
            mTypedValue = typedValue;
            mApply = apply;
        }
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
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
                Log.d(TAG, "attribute " + i + " : " + attrs.getAttributeName(i));
            }
        }

        if (mHookerSet != null) {
            final List<ValueInfo> infos = new ArrayList<ValueInfo>();
            for (Hooker hooker : mHookerSet) {
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "try hook name: " + hooker.hookName());
                }
                final String value = attrs.getAttributeValue(mHookerSet.getNamespace(), hooker.hookName());
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "try hook value: " + value);
                }
                if (value == null) {
                    continue;
                }
                TypedValue tv = null;
                switch (hooker.hookType()) {
                    case LITERAL_COLOR: {
                        tv = TypedValueParser.parseLiteralColor(value);
                        break;
                    }
                    case LITERAL_STRING: {
                        break;
                    }
                    case REFERENCE_ID: {
                        break;
                    }
                    case LITERAL_DIMENSION:
                        break;
                    case LITERAL_INTEGER:
                        break;
                    case LITERAL_FLOAT:
                        tv = TypedValueParser.parseFloat(value);
                        break;
                }
                if (tv == null) return null;
                if (hooker.shouldHook(view, tv)) {
                    infos.add(new ValueInfo(tv, hooker.getApply()));
                }
            }

            if (infos.size() > 0) {
                ViewTagger.setTag(view, R.id.skin_hooker, infos);
            }
        }

        return view;
    }

    void setHookerSet(HookerSet hookers) {
        mHookerSet = hookers;
    }

}
