package com.cleansweep.ControlSystem;
import com.cleansweep.Logger;
import java.util.ArrayList;
import java.util.List;

public class Motion {
	//Lets see if we have conflicting code

	private LookUpLocation lookUp;
	private Vacuum v;
	int row;
	int column;
	static int[] currentPosition;
	int[] newPosition = new int [] {0,0};
	int[] previousPosition = new int [] {0,0};
	private int[] homePosition;
	private Power power;
	private VacuumBag vacuumBag;

	static List <int[]> VisitedLocations_List = new ArrayList<>();
	private List <Object> notVisitedLocations_List = new ArrayList<>();
	private List <Object> nextLocations_List = new ArrayList<>();
	private List <Object> notVisitedLocations = new ArrayList<>();// List of all the untouched floor coordinates.
	int counter = 0;

	public Motion(LookUpLocation lookUp, Power power) {
		// TODO Auto-generated constructor stub
		this.row = lookUp.row;
		this.column = lookUp.column;
		this.currentPosition = lookUp.currentPosition;

		this.lookUp = lookUp;
		this.power = power;
		homePosition = new int [] {0,0};
		VisitedLocations_List.add(homePosition);
		this.notVisitedLocations = allPossibleCoordinates();
		vacuumBag = new VacuumBag(0.0);
		this.move();
	}

	public void visitedLocations(int [] Position) { // GOOD!
		int x = Position[0];
		int y = Position[1];
		int[] oldPosition = new int [] {x, y};
		VisitedLocations_List.add(oldPosition);
	}
	public static List<int []> getVisitedLocations() { //GOOD!
		//this.visitedLocations();
		return VisitedLocations_List;
	}
	public static int[] getCurrentPosition(){
		return currentPosition;
	}

	public List<Object> allPossibleCoordinates(){
		int x =0; int y =0;
		for ( x = 0; x < 9 ; x++){
			for ( y = 0; y < 9 ; y++){
				int [] coordinate = new int [] {x, y};
				notVisitedLocations_List.add(coordinate);
			}
		}
		return notVisitedLocations_List;
	}

	public List<Object> getUnvisitedLocation(int[] position) { //removes the current position from the not visited list.

		for (int v = 0; v < notVisitedLocations.size(); v++) {
			int[] visited = (int[]) notVisitedLocations.get(v);
			if (position[0] == visited[0] && position[1] == visited[1]) {
				notVisitedLocations.remove(v);
			}
		}
		return notVisitedLocations_List;
	}

	public void goHome() {
		Logger.logInfo("The room is clean!");
		Logger.logInfo("It is time to go back to my charging station.");
		currentPosition = new int [] {0,0};
	}

	public int[] nextCoordinate(int [] position){ // this method gives the vacuum next coordinate to move to
		nextLocations_List.clear();
		this.nextLocations_List = lookUp.getNextPossible(position);

		for (int n = 0; n < nextLocations_List.size(); n++) {
			//this loop removes the current position from the list
			int[] next = (int[]) nextLocations_List.get(n);
			if (position[0] == next[0] && position[0]!=5
					&& position[1] == next[1] && position[1]!=1) {
				nextLocations_List.remove(n);
			}
		}
		for (int n = 0; n < nextLocations_List.size(); n++) {
			//this loop removes the previous position from the list
			int[] next = (int[]) nextLocations_List.get(n);
			if (position[0] == next[0] && position[0]!=5
					&& position[1] == next[1] && position[1]!=1) {
				nextLocations_List.remove(n);
			}
		}
		int value = nextLocations_List.size();
		int direction = (int) (value * Math.random());
		newPosition = (int[]) nextLocations_List.get(direction);
		return newPosition;
	}

	public void move(){
		do {
			visitedLocations(currentPosition);
			getUnvisitedLocation(currentPosition);

			if (currentPosition[0] != previousPosition[0] && currentPosition[1] != previousPosition[1])
				previousPosition = currentPosition;

			newPosition = nextCoordinate(currentPosition);

			int x = newPosition[0] - currentPosition[0];
			int y = newPosition[1] - currentPosition[1];

			System.out.println("Minimum power to go home: " + ChargingStation.WorstCaseScenarioToChargingStation());

			if (x == 0 && y == 1) { //MOVE UP
				MovingUp movingUp = new MovingUp(this, currentPosition, newPosition, power, vacuumBag);
				currentPosition = movingUp.getCurrentPosition();
				//previousPosition = movingUp.getPreviousPosition();
			}

			else if (x == 0 && y == -1) { //MOVE DOWN
				MovingDown movingDown = new MovingDown(this, currentPosition, newPosition, power, vacuumBag);
				currentPosition = movingDown.getCurrentPosition();
				//previousPosition = movingDown.getPreviousPosition();
			}
			else if (x == 1 && y == 0) { //MOVE RIGHT
				MovingRight movingRight = new MovingRight(this, currentPosition, newPosition, power, vacuumBag);
				currentPosition = movingRight.getCurrentPosition();
				//previousPosition = movingRight.getPreviousPosition();
			}
			else if (x == -1 && y == 0) { // MOVE LEFT
				MovingLeft movingLeft = new MovingLeft(this, currentPosition, newPosition, power, vacuumBag);
				currentPosition = movingLeft.getCurrentPosition();
				//previousPosition = movingLeft.getPreviousPosition();
			}
			else {
				newPosition = nextCoordinate(currentPosition);
			}
			//newPosition = nextCoordinate(currentPosition);
		} while (! (notVisitedLocations_List.size() < 20 ));
		Logger.logInfo("DONE!");
	}

	public boolean alreadyVisited(int [] position) {
		VisitedLocations_List = getVisitedLocations();
		boolean value = false;
		for (int v = 0; v < VisitedLocations_List.size(); v++) {
			int[] visited = (int[]) VisitedLocations_List.get(v);
			if (position[0] == visited[0] && position[1] == visited[1]) {
				value = true; break;
			} else value = false;
		}return value;
	}
}
