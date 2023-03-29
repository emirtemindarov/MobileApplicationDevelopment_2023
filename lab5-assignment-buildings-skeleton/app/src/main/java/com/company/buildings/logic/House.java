package com.company.buildings.logic;

/**
 * This is the House class file that extends Building.
 */
public class House 
       extends Building {
       
    // TODO - Put your code here.
    private String mOwner;
    private boolean mPool;

    public House(int length, int width, int lotLength, int lotWidth) {
        super(length, width, lotLength, lotWidth);
        mOwner = null;
        mPool = false;

    }

    public House(int length, int width, int lotLength, int lotWidth, String owner) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = false;
    }

    public House(int length, int width, int lotLength, int lotWidth, String owner, boolean pool) {
        super(length, width, lotLength, lotWidth);
        mOwner = owner;
        mPool = pool;
    }

    public String getOwner() {
        return mOwner;
    }

    public boolean hasPool() {
        return mPool;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public void setPool(boolean pool) {
        mPool = pool;
    }

    public int calcBuildingArea() {
        return super.calcBuildingArea();
    }

    public String toString() {
        String result = "";

        if (mOwner != null) {
            result += mOwner;
        } else {
            result += "n/a";
        }

        if (mPool) {
            result += "; has a pool";
        }

        if (super.calcLotArea() / super.calcBuildingArea() > 5) {
            result += "; has a big open space";
        }

        return result;
    }

    public boolean equals(House other) {
        if (super.calcBuildingArea() == other.calcBuildingArea() &&
                this.mPool == other.mPool) {
            return true;
        } else {
            return false;
        }
    }
}
