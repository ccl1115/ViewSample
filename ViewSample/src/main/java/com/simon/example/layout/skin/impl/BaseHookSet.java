package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookSet;
import com.simon.example.layout.skin.hooks.BackgroundHook;
import com.simon.example.layout.skin.hooks.TextColorHook;
import com.simon.example.layout.skin.hooks.TextHook;
import com.simon.example.layout.skin.hooks.VisibilityHook;

/**
 * @author Simon Yu
 */
abstract class BaseHookSet extends HookSet {

    BaseHookSet() {
        put("background", new BackgroundHook());
        put("textColor", new TextColorHook());
        put("text", new TextHook());
        put("visibility", new VisibilityHook());
    }

}
