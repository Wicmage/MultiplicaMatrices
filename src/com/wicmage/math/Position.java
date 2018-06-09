package com.wicmage.math;

public class Position {

    private int x,y;

    public static Position create(int x, int y) {
        return new Position(x,y);
    }

    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
