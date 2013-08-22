package com.simon.example.layout.skin;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;

import com.simon.example.layout.R;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 布局钩子
 *
 * @author yulu02
 */
public class SkinLayoutFactory implements LayoutInflater.Factory {
    private static final String TAG = "com.baidu.adp.lib.skin.SkinLayoutHook";

    private static final String SKIN_NAMESPACE = "skin";

    private HookerSet mHookerSet;

    private static final String[] LOAD_PREFIX = {
            "android.widget",
            "android.webkit"
    };

    private static Map<String, Constructor> sConstructors;

    static {
        sConstructors = new HashMap<String, Constructor>();

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.d(TAG, "view name = " + name);
        View view;
        try {
            Class c = context.getClassLoader().loadClass(name);
            Constructor constructor = c.getConstructor(Context.class, AttributeSet.class);
            view = (View) constructor.newInstance(context, attrs);
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

        Log.d("SkinLayoutHook", "view create");

        if (mHookerSet != null) {
            for (Hooker hooker : mHookerSet) {
                switch (hooker.hookType()) {
                    case LITERAL_COLOR: {
                        final String value = attrs.getAttributeValue(mHookerSet.getNamespace(), hooker.hookName());
                        TypedValue tv = new TypedValue();
                        tv.data = parseColor(value);
                        Log.d("SkinLayoutHook", "hooked color value: " + value);
                        if (hooker.onHook(view, tv)) {
                            ViewTagger.setTag(view, R.id.skin_hooker, hooker.getApply(view));
                        }
                    }
                }
            }
        }

        return view;
    }

    private void setHookerSet(HookerSet hookers) {
        mHookerSet = hookers;
    }

    public int parseColor(String color) {
        if (color.startsWith("#")) {
            try {
                return Integer.valueOf(color.substring(1, color.length()));
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }
}
