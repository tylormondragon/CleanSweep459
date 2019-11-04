package main.java.ControlSystem;

import main.java.SensorSimulator.SensorObject;
import sun.management.Sensor;

import java.util.ArrayList;

public class Power {

	Double power;

	//This takes in two floor tiles, gets the floor type and calculates movement power to be deducted from the main power source
	public Double calculateMovementPower(SensorObject tile1, SensorObject tile2) {
		Double power = 0.0;
		String floortype1 = tile1.getFloorType();
		String floortype2 = tile2.getFloorType();
		ArrayList<String> floorTypes = new ArrayList<>();
		ArrayList<Double> floorValues = new ArrayList<>();

		floorTypes.add(floortype1);
		floorTypes.add(floortype2);
		for(String floor: floorTypes) {
			switch(floor) {
				case "Bare Floor":
					floorValues.add(1.0);
				case "Low Pile":
					floorValues.add(2.0);
				case "High Pile":
					floorValues.add(3.0);
			}
		}
		for(Double num: floorValues) {
			power += num;
		}
		power = power /2.0;

		return power;
	}

	public Double calculateCleaningPower(SensorObject currentTile) {
		return currentTile.getDirtValue();
	}

	private int getPowerValue() {
		int num = 8;
		return num;
	}

	public Power(Double power) {
		this.power = power;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return "Power [power=" + power + "]";
	}
	

}
