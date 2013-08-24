package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookSet;
import com.simon.example.layout.skin.hookers.BackgroundHook;

/**
 * @author Simon Yu
 */
public abstract class BaseHookSet extends HookSet {

    public BaseHookSet() {
        put("background", new BackgroundHook());
    }

}
