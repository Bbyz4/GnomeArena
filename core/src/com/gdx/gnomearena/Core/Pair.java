package com.gdx.gnomearena.Core;

public class  Pair<T1,T2> {
    T1 first;
    T2 second;
    public Pair(T1 first, T2 second) {
        this.first=first;
        this.second=second;
    }
    public T1 getKey() {
        return first;
    }
    public T2 getValue() {
        return second;
    }
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
