package com.simon.example.layout.skin;

import com.simon.example.layout.skin.hookers.BackgroundHook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * 钩子套件
 * @author yulu02
 */
public abstract class HookSet implements Map<String, Hook> {

    private Map<String, Hook> mHooks = new HashMap<String, Hook>();

    public abstract String getPrefix();

    public abstract String getNamespace();

    @Override
    public void clear() {
        mHooks.clear();
    }

    @Override
    public boolean containsKey(Object o) {
        return mHooks.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return mHooks.containsValue(o);
    }

    @Override
    public Set<Entry<String, Hook>> entrySet() {
        return mHooks.entrySet();
    }

    @Override
    public Hook get(Object o) {
        return mHooks.get(o);
    }

    @Override
    public boolean isEmpty() {
        return mHooks.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return mHooks.keySet();
    }

    @Override
    public Hook put(String s, Hook hook) {
        return mHooks.put(s, hook);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Hook> map) {
        mHooks.putAll(map);
    }

    @Override
    public Hook remove(Object o) {
        return mHooks.remove(0);
    }

    @Override
    public int size() {
        return mHooks.size();
    }

    @Override
    public Collection<Hook> values() {
        return mHooks.values();
    }
}

