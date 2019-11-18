package com.cleansweep.ControlSystem;

import com.cleansweep.SensorSimulator.SensorObject;
import com.cleansweep.Logger;

import java.util.concurrent.TimeUnit;

public class MovingRight {
    private Motion motion;
    private int[] currentPosition;// CURRENT POSITION
    private int[] newPosition; // RANDOMLY SELECTED POSITION
    private Power power;
    private VacuumBag vacuumBag;
    private SensorObject beforeMove;
    private SensorObject afterMove;
    int newx;
    int newy;
    int currentx;
    int currenty;

    public MovingRight(Motion motion, int[] currentPosition, int[] newPosition, Power power, VacuumBag vacuumBag) {
        this.motion = motion;
        this.currentPosition = currentPosition;
        this.newPosition = newPosition;
        this.power = power;
        this.vacuumBag = vacuumBag;

        ControlSystemClient client = new ControlSystemClient();

        currentx = this.currentPosition[0];
        currenty = this.currentPosition[1];

        newx = this.newPosition[0];
        newy = this.newPosition[1];

        try {
            beforeMove = client.getSensorObject("(" + currentx + "," + currenty + ")");
            beforeMove.getCoordinate();
            afterMove = client.getSensorObject("(" + newx + "," + newy + ")");
            //afterMove.getCoordinate();

            if (beforeMove.getIsWallRight()) { // Can't move
                Logger.logInfo("Wall detected. Unable to move RIGHT.");
                if (afterMove.getRoomType().equals("Bathroom")) {
                    this.motion.visitedLocations(this.newPosition);
                } else if (afterMove.getRoomType().equals("Closet")) {
                    this.motion.visitedLocations(this.newPosition);
                }
            } else if (afterMove.getIsStairs()) { //Can't move
                Logger.logInfo("Stairs detected. Unable to move RIGHT.");
                this.motion.visitedLocations(this.newPosition);
            } else if (afterMove.getRoomType().equals("Bathroom")) {
                Logger.logInfo("Bathroom detected. Unable to move RIGHT.");
                this.motion.visitedLocations(this.newPosition);
            } else if (afterMove.getRoomType().equals("Closet")) {
                Logger.logInfo("Closet detected. Unable to move RIGHT.");
                this.motion.visitedLocations(this.newPosition);
            } else if (afterMove.getIsChargingStation()) {
                this.motion.visitedLocations(this.newPosition);
            } else if (beforeMove.getIsDoorRight()) { // Move through the door
                Logger.logInfo("Moving through the doors RIGHT.");
                canMove();
            } else { // Can Move
                canMove();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void canMove() {
        this.currentPosition = this.newPosition;

        double deductPower = this.power.calculateMovementPower(beforeMove, afterMove);
        double p = this.power.power - deductPower; //deducts the power
        this.power.setPower(p);
        double powerRemaining = this.power.getPower();
        Logger.logInfo("Moving RIGHT!");

        // check if visited before
        if (this.motion.alreadyVisited(this.currentPosition)) {
            Logger.logInfo("\t Again at: " + newx + ", " + newy);
            pause(1);
            Logger.logInfo("\t Power remaining after moving: " + powerRemaining);
            pause(1);
        } else {
            Logger.logInfo("Currently at: " + newx + ", " + newy);
            pause(1);
            Logger.logInfo("\t Power remaining after moving: " + powerRemaining);
            String roomType = afterMove.getRoomType();
            String floorType = afterMove.getFloorType();
            Logger.logInfo("\t Room type: " + roomType);
            pause(1);
            Logger.logInfo("\t Floor Type: " + floorType);
            pause(1);
            this.motion.visitedLocations(this.currentPosition);

            if (afterMove.getIsDirty()) {
                deductPower = this.power.calculateCleaningPower(afterMove);
                p = this.power.power - deductPower; //deducts the power
                this.power.setPower(p);
                powerRemaining = this.power.getPower();
                double dirt = afterMove.getDirtValue();
                Logger.logInfo("\t " + dirt + " UNITS of dirt detected.");
                pause(1);
                Logger.logInfo("\t \t Now cleaning");
                pause(1);
                vacuumBag.addDirtToBag(dirt);
                double dirtInVacuumBag = vacuumBag.getBag();
                Logger.logInfo("\tVacuum Bag:"+ dirtInVacuumBag + " UNITS.");
                Logger.logInfo("\t Power remaining after cleaning: " + powerRemaining);
            } else {
                Logger.logInfo("\t NO dirt detected.");
            }
        }
    }

    private void pause(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

}