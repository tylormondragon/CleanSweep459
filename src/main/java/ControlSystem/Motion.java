package main.java.ControlSystem;
import main.java.Logger;

import java.util.ArrayList;
import java.util.List;

public class Motion {

	private LookUpLocation lookUp;
	private Vacuum v;
	int row;
	int column;
	private int[] currentPosition;
	private int[] newPosition;
	private int[] homePosition;
	private Power power;
	private List <Object> VisitedLocations_List = new ArrayList<>();
	private List <Object> notVisitedLocations_List = new ArrayList<>();
	private List <Object> nextLocations_List = new ArrayList<>();
	private List <Object> notVisitedLocations = new ArrayList<>(); // List of all the untouched floor coordinates.

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
		this.move();
	}

	public void visitedLocations(int [] Position) { // GOOD!
		int x = Position[0];
		int y = Position[1];
		int[] oldPosition = new int [] {x, y};
		VisitedLocations_List.add(oldPosition);
	}
	public List<Object> getVisitedLocations() { //GOOD!
		//this.visitedLocations();
		return VisitedLocations_List;
	}

	public List<Object> allPossibleCoordinates(){
		int x =0; int y =0;
		for ( x = 0; x < 10 ; x++){
			for ( y = 0; y < 10 ; y++){
				int [] coordinate = new int [] {x, y};
				notVisitedLocations_List.add(coordinate);
			}
		}
		return notVisitedLocations_List;
	}

	public List<Object> getUnvisitedLocation(int[] position) { //removes the current position from the not visited list.

		//notVisitedLocations_List.stream().filter(value -> value.).forEach();
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

	public int[] nextCoordinate(int [] position){ // keep it
		nextLocations_List.clear();
		this.nextLocations_List = lookUp.getNextPossible(position);
		int value = nextLocations_List.size();
		int direction = (int) (value * Math.random());
		newPosition = (int[]) nextLocations_List.get(direction);
		return newPosition;
	}
	public void move(){
		do {
			newPosition = nextCoordinate(currentPosition);

			int x = newPosition[0] - currentPosition[0];
			int y = newPosition[1] - currentPosition[1];

			if (x == 0 && y == 1) {
				currentPosition = newPosition;
				Logger.logInfo("Moving up! At coordinate:" + currentPosition[0] + ", " + currentPosition[1]);
				if (alreadyVisited(currentPosition)) {
					Logger.logInfo("Already cleaned: " + currentPosition[0] + ", " + currentPosition[1]);
				} else {
					visitedLocations(currentPosition);
					getUnvisitedLocation(currentPosition);
				}
			} else if (x == 0 && y == -1) {
				currentPosition = newPosition;
				Logger.logInfo("Moving down!At coordinate:" + currentPosition[0] + ", " + currentPosition[1]);
				if (alreadyVisited(currentPosition)) {
					Logger.logInfo("Already cleaned: " + currentPosition[0] + ", " + currentPosition[1]);
				} else {
					visitedLocations(currentPosition);
					getUnvisitedLocation(currentPosition);
				}
			} else if (x == 1 && y == 0) {
				currentPosition = newPosition;
				Logger.logInfo("Moving right!At coordinate:" + currentPosition[0] + ", " + currentPosition[1]);
				if (alreadyVisited(currentPosition)) {
					Logger.logInfo("Already cleaned: " + currentPosition[0] + ", " + currentPosition[1]);
				} else {
					visitedLocations(currentPosition);
					getUnvisitedLocation(currentPosition);
				}
			} else if (x == -1 && y == 0) {
				currentPosition = newPosition;
				Logger.logInfo("Moving left!At coordinate:" + currentPosition[0] + ", " + currentPosition[1]);
				if (alreadyVisited(currentPosition)) {
					Logger.logInfo("Already cleaned: " + currentPosition[0] + ", " + currentPosition[1]);
				} else {
					visitedLocations(currentPosition); //adding the current position to the visited
					getUnvisitedLocation(currentPosition);
				}
			} else {
				//currentPosition = homePosition;
				visitedLocations(currentPosition);
				nextCoordinate(currentPosition);
				getUnvisitedLocation(currentPosition);

				//return;
			}
		} while (! (notVisitedLocations_List.isEmpty() ));
		Logger.logInfo("You Got this");

	}

	private boolean alreadyVisited(int [] position) {
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
