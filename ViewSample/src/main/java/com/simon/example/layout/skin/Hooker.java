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

    int hookAttrId();

    boolean onHook(View view, TypedValue value);

    Apply getApply(View view);

    public interface Apply {
        void apply();
    }
}
