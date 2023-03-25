package com.company.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {
    // TODO -- Fill in your code here
    public static final int IN = 1;
    public static final int OUT = -1;
    public static final int CLOSED = 0;

    private int mSwing;

    public boolean setSwing(int direction) {
        if (direction == IN || direction == OUT) {
            mSwing = direction;
            return true;
        } else {
            return false;
        }

    }

    public boolean open(int direction) {
        if (setSwing(direction)) {
            return true;
        } else {
            return false;
        }
    }

    public void close() {
        mSwing = CLOSED;
    }

    public int getSwingDirection() {
        return mSwing;
    }

    public int thru(int count) {
        switch (mSwing) {
            case IN:
                return count;
            case OUT:
                return -count;
            case CLOSED:
                return 0;
            default:
                return 0;
        }
    }

    public String toString() {
        switch (mSwing) {
            case IN:
                return "This gate is open and swings to enter the pen only";
            case OUT:
                return "This gate is open and swings to exit the pen only";
            case CLOSED:
                return "This gate is closed";
            default:
                return "This gate has an invalid swing direction";
        }
    }
}
