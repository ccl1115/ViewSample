package com.simon.example.layout.skin;

import android.util.TypedValue;
import android.view.View;

/**
 * 指向Xml文件中的控件的钩子
 * @author yulu02
*/
public interface Hooker {

    HookerType hookType();

    String hookName();

    boolean shouldHook(View view, TypedValue value);

    Apply getApply();

    public interface Apply {
        void apply(View view, TypedValue value);
    }
}
