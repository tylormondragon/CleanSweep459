package com.cleansweep.ControlSystem;

import com.cleansweep.SensorSimulator.SensorObject;

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
					break;
				case "Low Pile":
					floorValues.add(2.0);
					break;
				case "High Pile":
					floorValues.add(3.0);
					break;
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
