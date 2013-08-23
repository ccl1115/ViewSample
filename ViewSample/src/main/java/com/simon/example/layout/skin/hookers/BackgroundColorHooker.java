package com.simon.example.layout.skin.hookers;

import android.util.TypedValue;
import android.view.View;

import com.simon.example.layout.skin.Hooker;
import com.simon.example.layout.skin.HookerType;

/**
* @author Simon Yu
*/
public class BackgroundColorHooker implements Hooker {

    @Override
    public HookerType hookType() {
        return HookerType.LITERAL_COLOR;
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
        return new Apply() {
            @Override
            public void apply(View view, TypedValue value) {
                view.setBackgroundColor(value.data);
            }
        };
    }
}
