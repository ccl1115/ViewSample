package com.simon.example.layout.skin.impl;

import com.simon.example.layout.skin.HookerSet;

/**
 * @author Simon Yu
 */
public class DayHookSet extends HookerSet {
    @Override
    public String getPrefix() {
        return "day";
    }

    @Override
    public String getNamespace() {
        return "http://schemas.tieba.baidu.com/android/skin/day";
    }
}
