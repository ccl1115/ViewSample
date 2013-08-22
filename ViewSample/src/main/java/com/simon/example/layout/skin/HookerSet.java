package com.simon.example.layout.skin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 钩子套件
 * @author yulu02
 */
public abstract class HookerSet implements List<Hooker> {

    private List<Hooker> mHookers = new ArrayList<Hooker>();

    public abstract String getPrefix();

    public abstract String getNamespace();

    @Override
    public void add(int i, Hooker hooker) {
        mHookers.add(i, hooker);
    }

    @Override
    public boolean add(Hooker hooker) {
        return mHookers.add(hooker);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Hooker> hookers) {
        return mHookers.addAll(i, hookers);
    }

    @Override
    public boolean addAll(Collection<? extends Hooker> hookers) {
        return mHookers.addAll(hookers);
    }

    @Override
    public void clear() {
        mHookers.clear();
    }

    @Override
    public boolean contains(Object o) {
        return mHookers.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> objects) {
        return mHookers.containsAll(objects);
    }

    @Override
    public Hooker get(int i) {
        return mHookers.get(i);
    }

    @Override
    public int indexOf(Object o) {
        return mHookers.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return mHookers.isEmpty();
    }

    @Override
    public Iterator<Hooker> iterator() {
        return mHookers.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return mHookers.lastIndexOf(o);
    }

    @Override
    public ListIterator<Hooker> listIterator() {
        return mHookers.listIterator();
    }

    @Override
    public ListIterator<Hooker> listIterator(int i) {
        return mHookers.listIterator(i);
    }

    @Override
    public Hooker remove(int i) {
        return mHookers.remove(i);
    }

    @Override
    public boolean remove(Object o) {
        return mHookers.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> objects) {
        return mHookers.removeAll(objects);
    }

    @Override
    public boolean retainAll(Collection<?> objects) {
        return mHookers.retainAll(objects);
    }

    @Override
    public Hooker set(int i, Hooker hooker) {
        return mHookers.set(i, hooker);
    }

    @Override
    public int size() {
        return mHookers.size();
    }

    @Override
    public List<Hooker> subList(int i, int i2) {
        return mHookers.subList(i, i2);
    }

    @Override
    public Object[] toArray() {
        return mHookers.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return mHookers.toArray(ts);
    }
}
