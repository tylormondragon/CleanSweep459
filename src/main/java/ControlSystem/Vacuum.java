package main.java.ControlSystem;

import java.lang.reflect.Array;

public class Vacuum {
	int[][] room;
	
	int[] position = new int [2];
	
	int  i = (int) Array.get(position, 0);
	int  j = (int) Array.get(position, 1);



	public Vacuum(int[][] room2) {
		// TODO Auto-generated constructor stub
		room = room2;
		LookUpLocation lookup = new LookUpLocation(this, position, i, j);

	}
	public void initVacuum(){
		this.setColumn(0);
		this.setRow(0);
		
		System.out.println("Clean Sweep on");
	}
	public void setRow(int y) {
		// TODO Auto-generated method stub
		if (y >= 0 && y <= room.length) {
			i = y;
		position [1] = i;
		}
		else {
			if (y < 0) {
			i = y + 1;
			position [1] = i;
			}
			if (y > room.length) {
				i = y - 1;
				position [1] = i;
			}
		}
	}
	
	public int getRow() {
		return i;
	}
		
	public void setColumn(int x) {
		// TODO Auto-generated method stub
		if (x >= 0 && x <= room.length) {
			j = x;
			position [0] = j;
		} 
		else {
			if (x < 0) {
			j = x + 1;
			position [0] = j;
			}
			if (x > room.length) {
				i = x - 1;
				position [0] = j;
			}
		}
	}
	
	public int getColumn() {
		return j;
	}
	
	public void currentLocation (int r, int c) {
		r = getRow();
		c = getColumn();
		position = new int [] {r, c};
	}
	public int[] getCurrentLocation () {
		return position;
	}

}
