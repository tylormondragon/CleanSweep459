package main.java.SensorSimulator;

public class SensorObject {

    String coordinate;
    String floorType;
    String roomType;
    Boolean isDirty;
    Boolean isChargingStation;
    Boolean isStairs;
    Boolean isWallUp;
    Boolean isWallLeft;
    Boolean isWallRight;
    Boolean isWallDown;
    Boolean isDoorUp;
    Boolean isDoorLeft;
    Boolean isDoorRight;
    Boolean isDoorDown;

    public SensorObject ( String coordinate, String floorType, String roomType, Boolean isDirty,
                          Boolean isChargingStation, Boolean isStairs, Boolean isWallUp, Boolean isWallLeft,
                          Boolean isWallRight, Boolean isWallDown, Boolean isDoorUp, Boolean isDoorLeft,
                          Boolean isDoorRight, Boolean isDoorDown) {

        this.coordinate = coordinate;
        this.floorType = floorType;
        this.roomType = roomType;
        this.isDirty = isDirty;
        this.isChargingStation = isChargingStation;
        this.isStairs = isStairs;
        this.isWallUp = isWallUp;
        this.isWallLeft = isWallLeft;
        this.isWallRight = isWallRight;
        this.isWallDown = isWallDown;
        this.isDoorUp = isDoorUp;
        this.isDoorLeft = isDoorLeft;
        this.isDoorRight = isDoorRight;
        this.isDoorDown = isDoorDown;
    }

    public String getCoordinate(){
        return coordinate;
    }

    public String getFloorType(){
        return floorType;
    }

    public String getRoomType(){
        return roomType;
    }

    public Boolean getIsDirty(){
        return isDirty;
    }

    public Boolean getIsChargingStation(){
        return isChargingStation;
    }

    public Boolean getIsStairs(){
        return isStairs;
    }

    public Boolean getIsWallUp(){
        return isWallUp;
    }

    public Boolean getIsWallLeft(){
        return isWallLeft;
    }

    public Boolean getIsWallRight(){
        return isWallRight;
    }

    public Boolean getIsWallDown(){
        return isWallDown;
    }

    public Boolean getIsDoorUp(){
        return isDoorUp;
    }

    public Boolean getIsDoorLeft(){
        return isDoorLeft;
    }

    public Boolean getIsDoorRight(){
        return isDoorRight;
    }

    public Boolean getIsDoorDown(){
        return isDoorDown;
    }
}
