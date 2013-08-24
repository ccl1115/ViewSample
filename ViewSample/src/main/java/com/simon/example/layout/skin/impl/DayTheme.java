package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookSet;

/**
 * @author Simon Yu
 */
public class DayTheme extends BaseHookSet {

    public static final String NAME = "day";

    @Override
    public String getPrefix() {
        return NAME;
    }

    @Override
    public String getNamespace() {
        return "http://schemas.tieba.baidu.com/android/skin/day";
    }
}
