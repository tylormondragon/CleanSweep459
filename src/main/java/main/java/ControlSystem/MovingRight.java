package main.java.ControlSystem;
import main.java.Logger;
import main.java.SensorSimulator.SensorObject;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class MovingRight {
    private Motion motion;
    private int[] currentPosition;
    private int[] newPosition;
    private Power power;

    public MovingRight(Motion motion, int[] currentPosition, int[] newPosition, Power power) {
        this.motion = motion;
        this.currentPosition = currentPosition;
        this.newPosition = newPosition;
        this.power = power;
        SensorObject beforeMove;
        SensorObject afterMove;
        ControlSystemClient client = new ControlSystemClient();

        int currentx = this.currentPosition[0];
        int currenty = this.currentPosition[1];

        int newx = this.newPosition[0];
        int newy = this.newPosition[1];

        try {
            beforeMove = client.getSensorObject("(" + currentx + "," + currenty + ")");
            beforeMove.getCoordinate();
            afterMove = client.getSensorObject("(" + newx + "," + newy + ")");
            afterMove.getCoordinate();

            if (beforeMove.getIsWallUp()) { // Can't move
                if (this.motion.alreadyVisited(this.newPosition)) {
                } else {
                    Logger.logInfo("Wall detected. Unable to move RIGHT.");
                    this.motion.visitedLocations(this.newPosition);
                    this.motion.getUnvisitedLocation(this.currentPosition);
                }
            } else if (beforeMove.getIsStairs()) { //Can't move
                Logger.logInfo("Stairs detected. Unable to move RIGHT.");
                this.motion.visitedLocations(this.newPosition);
                this.motion.getUnvisitedLocation(this.currentPosition);
            } else if (beforeMove.getIsDoorUp()) { // Move through the door
                this.currentPosition = this.newPosition;
                Logger.logInfo("Moving through the doors RIGHT.");
                this.motion.visitedLocations(this.newPosition);
                this.motion.getUnvisitedLocation(this.newPosition);
            } else if (afterMove.getRoomType() == "Bathroom") {
                if (this.motion.alreadyVisited(this.newPosition)) {
                } else {
                    Logger.logInfo("Bathroom detected. Unable to move RIGHT.");
                    this.motion.visitedLocations(this.newPosition);
                    this.motion.getUnvisitedLocation(this.currentPosition);
                }
            } else { // Can Move
                this.currentPosition = this.newPosition;
                if (this.power.getPower() < 75) {
                    Logger.logInfo("\n RUNNING LOW ON POWER.");
                    if (afterMove.getIsChargingStation()) {
                        Logger.logInfo("\t NOW CHARGING...");
                        pause(2);
                        this.power.setPower(250.0);
                        Logger.logInfo("\t FULLY CHARGED " + this.power.getPower() + " UNITS");
                    }
                }

                double deductPower = this.power.calculateMovementPower(beforeMove, afterMove);
                double p = this.power.power - deductPower; //deducts the power
                this.power.setPower(p);
                double powerRemaining = this.power.getPower();
                Logger.logInfo("Moving RIGHT!");

                // check if visited before
                if (this.motion.alreadyVisited(this.currentPosition)) {
                    Logger.logInfo("\t Again at: " + newx + ", " + newy);
                    pause(1);
                    this.motion.getUnvisitedLocation(this.currentPosition);
                } else {
                    Logger.logInfo("Currently at: " + newx + ", " + newy);
                    pause(1);
                    Logger.logInfo("\t Power remaining after moving: " + powerRemaining);
                    pause(1);
                    String roomType = afterMove.getRoomType();
                    String floorType = afterMove.getFloorType();
                    Logger.logInfo("\t Room type: " + roomType);
                    pause(1);
                    Logger.logInfo("\t Floor Type: " + floorType);
                    pause(1);
                    this.motion.visitedLocations(this.currentPosition);
                    this.motion.getUnvisitedLocation(this.currentPosition);

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
                        Logger.logInfo("\t Power remaining after cleaning: " + powerRemaining);
                    } else {
                        Logger.logInfo("\t NO dirt detected.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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