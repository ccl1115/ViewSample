package com.simon.example.layout.skin;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.simon.example.layout.R;
import com.simon.example.layout.skin.impl.DayTheme;
import com.simon.example.layout.skin.impl.NightTheme;

import java.util.List;
import java.util.Stack;

import static com.simon.example.layout.skin.SkinInflatorFactory.ValueInfo;

/**
 * 皮肤服务，替代SkinManager
 * @author yulu02
 */
public class SkinService {

    public static SkinInflatorFactory sSkinInflatorFactory;

    public synchronized static SkinInflatorFactory getFactory(Context context) {
        if (sSkinInflatorFactory == null) {
            sSkinInflatorFactory = new SkinInflatorFactory(context);
            sSkinInflatorFactory.addHookSet(new DayTheme());
            sSkinInflatorFactory.addHookSet(new NightTheme());
        }
        return sSkinInflatorFactory;
    }

    private static String mTheme;

    public static String getTheme() {
        return mTheme;
    }

    public static void applyTheme(Activity activity) {
        mTheme = activity.getSharedPreferences("default", Context.MODE_PRIVATE).getString("skin", DayTheme.NAME);
        applyViews(activity.findViewById(android.R.id.content));
    }

    public static void applyTheme(Activity activity, String theme) {
        mTheme = theme;
        activity.getSharedPreferences("default", Context.MODE_PRIVATE).edit().putString("skin", mTheme).apply();
        applyViews(activity.findViewById(android.R.id.content));
    }

    private static void applyViews(View root) {
        if (mTheme == null) return;

        Stack<View> stack = new Stack<View>();
        stack.push(root);

        while (!stack.isEmpty()) {
            View v = stack.pop();

            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                final int count = vg.getChildCount();
                for (int i = 0; i < count; i++) {
                    stack.push(vg.getChildAt(i));
                }
            } else {
                List<ValueInfo> list = (List<ValueInfo>) ViewTagger.getTag(v, R.id.skin_hooker);

                if (list == null) {
                    continue;
                }

                for (ValueInfo info : list) {
                    if (mTheme.equals(info.theme)) info.apply.to(v, info.typedValue);
                }
            }
        }
    }
}
