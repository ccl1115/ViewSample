package com.simon.example.layout.skin.hookers;

import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.simon.example.layout.skin.Hook;

/**
* @author Simon Yu
*/
public class BackgroundHook implements Hook {

    public static final Apply APPLY = new Apply() {
        @Override
        public void to(View view, TypedValue value) {
            switch (value.type) {
                case TypedValue.TYPE_REFERENCE:
                    view.setBackgroundColor(view.getResources().getColor(value.data));
                    break;
                case TypedValue.TYPE_INT_COLOR_ARGB4:
                case TypedValue.TYPE_INT_COLOR_RGB4:
                case TypedValue.TYPE_INT_COLOR_ARGB8:
                case TypedValue.TYPE_INT_COLOR_RGB8:
                    Log.d("APPLY", "color = " + Integer.toHexString(value.data));
                    view.setBackgroundColor(value.data);
                    break;
            }
        }
    };

    @Override
    public int hookType() {
        // computed from HookType.REFERENCE_ID | HookType.COLOR
        return 6;
    }

    @Override
    public String hookName() {
        return "background";
    }

    @Override
    public boolean shouldHook(View view, TypedValue value) {
        return true;
    }

    @Override
    public Apply getApply() {
        return APPLY;
    }
}
