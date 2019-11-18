package com.cleansweep.SensorSimulator;

import java.io.*;

public class mainSensorSimulatorServer {
    public static void main(String[] args) {

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
