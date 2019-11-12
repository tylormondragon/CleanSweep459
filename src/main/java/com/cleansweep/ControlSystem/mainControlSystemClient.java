/**
 * Class: <class_number> - <description>
 * Author: Raquib Talukder
 **/

package com.cleansweep.ControlSystem;

import main.java.Logger;

public class mainControlSystemClient {

    public static Power power;
    public static void main(String[] args) {
        ControlSystemClient client = new ControlSystemClient();

        int[][] room = new int[10][10];
        power = new Power(250.0);
        Logger logger = Logger.getInstance();
        Logger.logInfo("SE 459");
        Logger.logInfo("Group 2");
        Logger.logInfo("This is our Clean Sweep Vacuum ");
        Vacuum vacuum = new Vacuum(room,power);


//        SensorObject nearestCharge = sensorSimulator.GetNearestChargeStation(new int[]{6,3});
//        System.out.println("The nearest charging station coordinate is : " + nearestCharge.getCoordinate());
//        SensorObject foundObject;
//        try {
//            foundObject = client.getSensorObject("(0,0)");
//            System.out.println(foundObject.getCoordinate());
//            System.out.println(foundObject.getIsChargingStation());
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        /*
//         * System.out.println(foundObject.getCoordinate());
//         * System.out.println(foundObject.getIsChargingStation());
//         */
//
//        try {
//            foundObject = client.getSensorObject("(0,9)");
//            System.out.println(foundObject.getCoordinate());
//            System.out.println(foundObject.getIsChargingStation());
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//        try {
//            foundObject = client.getSensorObject("(9,1)");
//            System.out.println(foundObject.getCoordinate());
//            System.out.println(foundObject.getIsChargingStation());
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//        try {
//            foundObject = client.getSensorObject("(4,3)");
//            System.out.println(foundObject.getCoordinate());
//            System.out.println(foundObject.getIsChargingStation());
//
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}
