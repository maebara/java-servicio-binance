package com.sergio.binance.util;


import java.util.TreeSet;

public class BoundedTreeSet<T extends Comparable> {
    private TreeSet<T> treeSet;
    private int limit;
    private int size;

    public BoundedTreeSet(int limit) {
        this.treeSet = new TreeSet<>();
        this.limit = limit;
        size = 0;
    }

    public void add(T obj) {
        if (treeSet.size() == limit) {
            if (treeSet.last().compareTo(obj) > 0) {
                treeSet.pollLast();
            } else {
                return;
            }
        }

        treeSet.add(obj);
    }

    public TreeSet<T> getTreeSet() {
        return treeSet;
    }
}
