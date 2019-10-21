//package main.java.ControlSystem;
import main.java.ControlSystem.ControlSystemClient;
import main.java.ControlSystem.Vacuum;
import main.java.Logger;
import main.java.SensorSimulator.SensorObject;
import main.java.SensorSimulator.SensorSimulator;
import main.java.SensorSimulator.SensorSimulatorServer;

import java.io.IOException;

public class Main {
public static void main(String[] args) throws ClassNotFoundException, IOException {
	int[][] room = new int[3][3];
	Logger logger = Logger.getInstance();
	Logger.logInfo("SE 459");
	Logger.logInfo("Group 2");
	Logger.logInfo("This is our Clean Sweep Vacuum ");
	Vacuum vacuum = new Vacuum(room);

	SensorSimulator sensorSimulator = new SensorSimulator();
	String userDir = System.getProperty("user.dir");
	//sensorSimulator.SensorRead("C:\\Users\\Raquib Talukder\\Desktop\\se459\\jsonReader\\floorPlan.json");
	sensorSimulator.SensorRead(userDir + "/floorPlan.json");
	SensorSimulatorServer newServer = new SensorSimulatorServer(sensorSimulator);
	newServer.StartSensorSimulatorServer();

	ControlSystemClient client = new ControlSystemClient();
	SensorObject foundObject = client.getSensorObject("(0,0)");
	System.out.println(foundObject.getCoordinate());
	System.out.println(foundObject.getIsChargingStation());

	foundObject = client.getSensorObject("(0,9)");
	System.out.println(foundObject.getCoordinate());
	System.out.println(foundObject.getIsChargingStation());

	foundObject = client.getSensorObject("(9,1)");
	System.out.println(foundObject.getCoordinate());
	System.out.println(foundObject.getIsChargingStation());

	foundObject = client.getSensorObject("(4,3)");
	System.out.println(foundObject.getCoordinate());
	System.out.println(foundObject.getIsChargingStation());

	}
}
