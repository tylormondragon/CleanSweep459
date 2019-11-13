/**
 * Class: <class_number> - <description>
 * Author: Raquib Talukder
 **/

package com.cleansweep.SensorSimulator;

import java.io.*;

//this is a test comment
public class mainSensorSimulatorServer {
    public static void main(String[] args) throws FileNotFoundException {

        SensorSimulator sensorSimulator = new SensorSimulator();
        BufferedReader br = new BufferedReader(new InputStreamReader(mainSensorSimulatorServer.class.getResourceAsStream("/floorPlan.json")));

        sensorSimulator.SensorRead(br);

        SensorSimulatorServer newServer = new SensorSimulatorServer(sensorSimulator);

        try {
            newServer.StartSensorSimulatorServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
