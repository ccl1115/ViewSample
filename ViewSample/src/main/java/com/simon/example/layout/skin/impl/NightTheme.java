package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookSet;

/**
 * @author Simon Yu
 */
public class NightTheme extends BaseHookSet {

    public static final String NAME = "night";

    @Override
    public String getPrefix() {
        return NAME;
    }

    @Override
    public String getNamespace() {
        return "http://schemas.tieba.baidu.com/android/skin/night";
    }
}
