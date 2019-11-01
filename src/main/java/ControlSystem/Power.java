package main.java.ControlSystem;

import main.java.SensorSimulator.SensorObject;

public class Power {

	private SensorObject floorTile;
	
	float power;

	public Double calculateMovementPower(String floorType1, String floorType2) {
		Double num = 8.0;
		floorType1 = floorTile.getFloorType();
		floorType2 = floorTile.getFloorType();

		return num;

	}

	public Double calculateCleaningPower(String floorType) {
		Double num = 1.0;

		return num;
	}

	private int getPowerValue() {
		int num = 8;
		return num;
	}

	public Power(float power) {
		this.power = power;
	}

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	@Override
	public String toString() {
		return "Power [power=" + power + "]";
	}
	

}
