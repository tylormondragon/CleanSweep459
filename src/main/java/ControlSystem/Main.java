
package main.java.ControlSystem;

import main.java.ControlSystem.ControlSystemClient;
import main.java.ControlSystem.Power;
import main.java.ControlSystem.Vacuum;
import main.java.SensorSimulator.SensorObject;
import main.java.SensorSimulator.SensorSimulator;
import main.java.SensorSimulator.SensorSimulatorServer;

import java.io.IOException;

import main.java.Logger;

public class Main {
public static void main(String[] args) throws ClassNotFoundException, IOException {
	//Adding a test comment for pull request github setup testing
	int[][] room = new int[3][3];
	Power power = new Power(250);
	Logger logger = Logger.getInstance();
	Logger.logInfo("SE 459");
	Logger.logInfo("Group 2");
	Logger.logInfo("This is our Clean Sweep Vacuum ");
	Vacuum vacuum = new Vacuum(room,power);

	
	//SensorObject nearestCharge = sensorSimulator.GetNearestChargeStation(new int[]{6,3});
	//System.out.println("The nearest charging station coordinate is : " + nearestCharge.getCoordinate());


	}
}
