/**
 * Class: <class_number> - <description>
 * Author: Raquib Talukder
 **/

package main.java.ControlSystem;

import main.java.SensorSimulator.SensorObject;

public class mainControlSystemClient {
    public static void main(String[] args) {
        ControlSystemClient client = new ControlSystemClient();
        SensorObject foundObject;
        try {
            foundObject = client.getSensorObject("(0,0)");
            System.out.println(foundObject.getCoordinate());
            System.out.println(foundObject.getIsChargingStation());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * System.out.println(foundObject.getCoordinate());
         * System.out.println(foundObject.getIsChargingStation());
         */

        try {
            foundObject = client.getSensorObject("(0,9)");
            System.out.println(foundObject.getCoordinate());
            System.out.println(foundObject.getIsChargingStation());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            foundObject = client.getSensorObject("(9,1)");
            System.out.println(foundObject.getCoordinate());
            System.out.println(foundObject.getIsChargingStation());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            foundObject = client.getSensorObject("(4,3)");
            System.out.println(foundObject.getCoordinate());
            System.out.println(foundObject.getIsChargingStation());

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
