package com.simon.example.layout.skin.hookers;

import android.util.TypedValue;
import android.view.View;

import com.simon.example.layout.skin.Hooker;
import com.simon.example.layout.skin.HookerType;

/**
* @author Simon Yu
*/
public class BackgroundHooker implements Hooker {

    public static final Apply APPLY = new Apply() {
        @Override
        public void apply(View view, TypedValue value) {
            view.setBackgroundColor(value.data);
        }
    };

    @Override
    public int hookType() {
        return HookerType.COLOR | HookerType.REFERENCE_ID;
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
