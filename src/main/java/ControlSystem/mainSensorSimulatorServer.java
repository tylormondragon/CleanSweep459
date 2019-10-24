/**
 * Class: <class_number> - <description>
 * Author: Raquib Talukder
 **/

package main.java.ControlSystem;

import main.java.SensorSimulator.SensorSimulator;
import main.java.SensorSimulator.SensorSimulatorServer;

import java.io.IOException;

public class mainSensorSimulatorServer {
    public static void main(String[] args) {

        SensorSimulator sensorSimulator = new SensorSimulator();
        String userDir = System.getProperty("user.dir");
        sensorSimulator.SensorRead(userDir + "/floorPlan.json");

        SensorSimulatorServer newServer = new SensorSimulatorServer(sensorSimulator);

        try {
            newServer.StartSensorSimulatorServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
