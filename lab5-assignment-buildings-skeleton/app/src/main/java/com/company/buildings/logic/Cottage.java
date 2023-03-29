package com.company.buildings.logic;

/**
 * This is the cottage class file.  It is a subclass of House.
 */
public class Cottage 
       extends House {
       
    // TODO - Put your code here.
    private final boolean mSecondFloor;

    public Cottage(int dimension, int lotLength, int lotWidth) {
        super(dimension, dimension, lotLength, lotWidth);
        this.mSecondFloor = false;
    }

    public Cottage(int dimension, int lotLength, int lotWidth, String owner, boolean secondFloor) {
        super(dimension, dimension, lotLength, lotWidth, owner);
        this.mSecondFloor = secondFloor;
    }

    public boolean hasSecondFloor() {
        return mSecondFloor;
    }

    public String toString() {
        String result = "";

        if (super.getOwner() != null) {
            result += super.getOwner();
        } else {
            result += "n/a";
        }

        if (super.hasPool()) {
            result += "; has a pool";
        }

        if (super.calcLotArea() / super.calcBuildingArea() > 5) {
            result += "; has a big open space";
        }

        if (mSecondFloor) {
            result += "; is a two story cottage";
        } else {
            result += "; is a cottage";
        }

        return result;
    }
}

