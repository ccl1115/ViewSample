package com.simon.example.layout.skin.hooks;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.simon.example.layout.skin.Hook;

/**
 * @author Simon Yu
 */
public class TextColorHook implements Hook {

    private static final Apply APPLY = new Apply() {

        @Override
        public void to(View view, TypedValue value) {
            if (!(view instanceof TextView)) {
                return;
            }
            TextView tv = (TextView) view;
            switch (value.type) {
                case TypedValue.TYPE_INT_COLOR_ARGB4:
                case TypedValue.TYPE_INT_COLOR_RGB4:
                case TypedValue.TYPE_INT_COLOR_ARGB8:
                case TypedValue.TYPE_INT_COLOR_RGB8:
                    tv.setTextColor(value.data);
                    break;
                case TypedValue.TYPE_REFERENCE:
                    tv.setTextColor(view.getResources().getColor(value.data));
                    break;
            }
        }
    };

    @Override
    public int hookType() {
        // computed from HookType.REFERENCE_ID | HookType.COLOR;
        return 6;
    }

    @Override
    public String hookName() {
        return "textColor";
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
