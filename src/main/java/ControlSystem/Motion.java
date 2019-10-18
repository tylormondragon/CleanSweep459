package main.java.ControlSystem;
import main.java.ControlSystem.Vacuum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Motion {
	
	private LookUpLocation lookUp;
	private Vacuum v;
	int row;
	int column;
	int[] position;
	int[] visitedPosition;
	int[] currentPosition;
	int[] newPosition;
	int[] homePosition;
	List <Object> VisitedLocations_List = new ArrayList<>();
	List <Object> nextLocations_List = new ArrayList<>();
	
	public Motion(LookUpLocation lookUp) {
		// TODO Auto-generated constructor stub
		this.row = lookUp.row;
		this.column = lookUp.column;
		this.position = lookUp.position;
		this.currentPosition = lookUp.currentPosition;
		this.nextLocations_List = lookUp.nextLocations_List;
		this.lookUp = lookUp;
		homePosition = new int [] {0,0};
		this.move();
	}
 
	
	public void visitedLocations(int [] Position) {
		int x = Position[0];
		int y = Position[1];
		int[] oldPosition = new int [] {x, y};
		VisitedLocations_List.add(oldPosition);
	}
	public List<Object> getVisitedLocations(int [] Position) {
		this.visitedLocations(Position);
		return VisitedLocations_List;
	}
	public List<Object> nextPossibleLocations () {
		lookUp.getNextPossible(currentPosition);
		return nextLocations_List;
	}
	
	public List<Object> getNextLocation() {
				
		this.nextPossibleLocations();

		this.getVisitedLocations(currentPosition);
		for (int v = 0; v< VisitedLocations_List.size(); v++ ) {
		     	int [] visited = (int[]) VisitedLocations_List.get(v);
		       
		   	for (int n = 0; n< nextLocations_List.size(); n++ ) {
			       int [] next = (int[]) nextLocations_List.get(n);
			       if (next[0] == visited[0] && next[1] == visited[1]) {
			    	   nextLocations_List.remove(n);
			       }
				}
		} 
		return nextLocations_List;
	}
	
	public void goHome() {
		System.out.println("The room is clean! ");
		System.out.println("It is time to go back to my charging station.");
		currentPosition = new int [] {0,0};
	}
	
	public void move() {

		do {
			this.getNextLocation();
			int value = nextLocations_List.size();
			 int direction = (int) (value * Math.random());
		newPosition = (int[]) nextLocations_List.get(direction);
		  int x = newPosition[0] - currentPosition[0];
		  int y = newPosition[1] - currentPosition[1];
		  nextLocations_List.clear();
		  if (x == 0 && y == 1) {
			  System.out.print("Moving up!");
		  }
		  else if (x == 0 && y == -1) {
			  System.out.print("Moving down!");
		  }
		  else if (x == 1 && y == 0) {
			  System.out.print("Moving right!");
		  }
		  else if (x == -1 && y == 0) {
			  System.out.print("Moving left!");
		  }
		  else {
			  currentPosition = homePosition;
			  return;
		  }
		  currentPosition = newPosition;
		  nextLocations_List.clear();
			this.getNextLocation();

		  System.out.println(" Clean Sweep is at location: "+ currentPosition[0] + "," + currentPosition[1]);
		} while (! (nextLocations_List.isEmpty() ));
		
		goHome();
	}
}
