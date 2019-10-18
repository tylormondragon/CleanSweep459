
package main.java.ControlSystem;

import java.util.ArrayList;
import java.util.List;

public class LookUpLocation {

	private Vacuum v;
	int row;
	int column;
	int[] position;
	int[] currentPosition;
	int x;
	int y;
	
	List <Object> nextLocations_List = new ArrayList<>();
	
	public LookUpLocation(Vacuum vacuum, int[] position, int i, int j) {
		// TODO Auto-generated constructor stub
		this.v = vacuum;
		this.position = vacuum.position;
		row = i;
		column = j;
		currentPosition = vacuum.getCurrentLocation();
		
		this.getNextPossible(currentPosition);
		System.out.println("Clean Sweep 2");
		Motion motion = new Motion (this);
		for (int n = 0; n< nextLocations_List.size(); n++ ) {
	       int [] pos = (int[]) nextLocations_List.get(n);
	       System.out.println(pos[0] + ", " + pos[1]);
		}
		//System.out.println(currentPosition[0] + ", " + currentPosition[1]);

	}
	
	public  int[] Up ( int r, int c) {
		y = r + 1;
		x = c;
		v.setRow(y);
		v.setColumn(x);
		row = v.getRow();
		column = v.getColumn();
		int[] nextPosition = new int [] {column, row};
		nextLocations_List.add(nextPosition);
		return nextPosition;
	}
	public  int[] Down (int r, int c) {
		y = r - 1;
		x = c;
		v.setRow(y);
		v.setColumn(x);
		row = v.getRow();
		column = v.getColumn();
		int[] nextPosition = new int [] {column, row};
		nextLocations_List.add(nextPosition);
		return nextPosition;
	}
	public  int[] Right (int r, int c) {
		y = r;
		x = c + 1;
		v.setRow(y);
		v.setColumn(x);
		row = v.getRow();
		column = v.getColumn();
		int[] nextPosition = new int [] {column, row};
		nextLocations_List.add(nextPosition);
		return nextPosition;
	}
	public  int[] Left (int r, int c) {
		y = r ;
		x = c - 1;
		v.setRow(y);
		v.setColumn(x);
		row = v.getRow();
		column = v.getColumn();
		int[] nextPosition = new int [] {column, row};
		nextLocations_List.add(nextPosition);
		return nextPosition;
	}
	public List<Object> getNextPossible(int [] currentPosition) {
		int r = currentPosition[1];
		int c = currentPosition[0];
		this.Up(r, c);
		this.Down(r, c);
		this.Right(r, c);
		this.Left(r, c);
		return nextLocations_List;
	}
	
	//new Motion().move();

}

