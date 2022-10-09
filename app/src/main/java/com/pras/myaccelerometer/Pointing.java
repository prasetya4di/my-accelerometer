package com.pras.myaccelerometer;

enum Pointing {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    static Pointing parse(float x, float y) {
        // Pointing up or down
        if (x > -4 && x < 4) {
            if (y > 0) {
                return Pointing.UP;
            } else {
                return Pointing.DOWN;
            }
        }

        // Pointing left or right
        if (x < 0) {
            return Pointing.RIGHT;
        } else if (x > 0) {
            return Pointing.LEFT;
        }
        return null;
    }
}
