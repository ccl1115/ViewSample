package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookerSet;

/**
 * @author Simon Yu
 */
public class NightHookerSet extends HookerSet {

    @Override
    public String getPrefix() {
        return "night";
    }

    @Override
    public String getNamespace() {
        return "http://schemas.tieba.baidu.com/android/skin/night";
    }
}
