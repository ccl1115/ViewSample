package com.simon.example.layout.skin.impl;

/**
 * @author Simon Yu
 */
public class NightSkin extends BaseHookSet {

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
