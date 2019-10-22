package main.java.ControlSystem;

public class Power {
	
	float power;

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
