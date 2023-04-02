package com.company.buildings.logic;

/**
 * This is the office class file, it is a subclass of Building.
 */
public class Office 
       extends Building {
       
    // TODO - Put your code here.
    private String mBusinessName;
    private int mParkingSpaces;
    private static int sTotalOffices = 0;

    public Office(int length, int width, int lotLength, int lotWidth) {
        super(length, width, lotLength, lotWidth);
        sTotalOffices++;
    }

    public Office(int length, int width, int lotLength, int lotWidth, String businessName) {
        super(length, width, lotLength, lotWidth);
        this.mBusinessName = businessName;
        sTotalOffices++;
    }

    public Office(int length, int width, int lotLength, int lotWidth, String businessName, int parkingSpace) {
        super(length, width, lotLength, lotWidth);
        this.mBusinessName = businessName;
        this.mParkingSpaces = parkingSpace;
        sTotalOffices++;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public int getParkingSpaces() {
        return mParkingSpaces;
    }

    public void setBusinessName(String businessName) {
        mBusinessName = businessName;
    }

    public void setParkingSpaces(int parkingSpaces) {
        mParkingSpaces = parkingSpaces;
    }

    public String toString() {
        String result = "Business:";

        if (mBusinessName != null) {
            result += mBusinessName;
        } else {
            result += " unoccupied";
        }

        if (mParkingSpaces != 0) {
            result += "; has " + mParkingSpaces + " parking spaces";
        }

        result += " (total offices: " + sTotalOffices + ")";
        return result;
    }

    public boolean equals(Office other) {
        if (super.calcBuildingArea() == other.calcBuildingArea() && this.mParkingSpaces == other.mParkingSpaces) {
            return true;
        } else {
            return false;
        }
    }
}
