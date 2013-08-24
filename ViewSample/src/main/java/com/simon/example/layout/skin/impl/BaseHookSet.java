package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookSet;
import com.simon.example.layout.skin.hookers.BackgroundHook;
import com.simon.example.layout.skin.hookers.TextColorHook;
import com.simon.example.layout.skin.hookers.TextHook;

/**
 * @author Simon Yu
 */
public abstract class BaseHookSet extends HookSet {

    public BaseHookSet() {
        put("background", new BackgroundHook());
        put("textColor", new TextColorHook());
        put("text", new TextHook());
    }

}
