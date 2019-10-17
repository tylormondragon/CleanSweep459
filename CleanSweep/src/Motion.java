import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Motion {
	
	private LookUpLocation lookUp;
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
		VisitedLocations_List.add(homePosition);
		//System.out.println(currentPosition[0] + ", " + currentPosition[1]);

		//int [] posi = (int[]) nextLocations_List.get(0);
		//System.out.println(currentPosition[0] + ", " + currentPosition[1]);
		this.move();
	}
	//a;lksdjfl;kasdjf
	//;kajsdflk;aj
	//;alskdjf;lj 
	
	public void visitedLocations(int [] currentPosition) {
		VisitedLocations_List.add(currentPosition);
	}
	public List<Object> getVisitedLocations() {
		this.visitedLocations(currentPosition);
		return VisitedLocations_List;
	}
	public List<Object> nextPossibleLocation () {
		this.getVisitedLocations();
		lookUp.getNextPossible(currentPosition);
		//nextLocations_List.removeAll(VisitedLocations_List);
		return nextLocations_List;
	}
	
	public void goHome() {
		System.out.println("I have visited all the places. It is time to go back to home location.");
		 int [] pos = (int[]) VisitedLocations_List.get(0);
		 int [] posi = (int[]) nextLocations_List.get(0);
		       System.out.println(pos[0] + ", " + pos[1]);
		       System.out.println(posi[0] + ", " + posi[1]);
		//for (int n = 0; n< VisitedLocations_List.size(); n++ ) {
		 //      int [] pos = (int[]) VisitedLocations_List.get(n);
		  //     System.out.println(pos[0] + ", " + pos[1]);
			//}
		currentPosition = new int [] {0,0};
	}
	public void move() {
		//this.nextPossibleLocation();
		this.visitedLocations(currentPosition);
		  nextLocations_List.removeAll(VisitedLocations_List);

		while (! (nextLocations_List.isEmpty() )) {
			int value = nextLocations_List.size();
			 int direction = (int) (value * Math.random());
		newPosition = (int[]) nextLocations_List.get(direction);
		  int x = newPosition[1] - currentPosition[1];
		  int y = newPosition[0] - currentPosition[0];
		  
		  if (x == 0 && y == 1) {
			  System.out.print("Moving up!");
		  }
		  if (x == 0 && y == -1) {
			  System.out.print("Moving down!");
		  }
		  if (x == 1 && y == 0) {
			  System.out.print("Moving right!");
		  }
		  if (x == -1 && y == 0) {
			  System.out.print("Moving left!");
		  }
		  
		  currentPosition = newPosition;
		  System.out.println("Clean Sweep is at location :"+ currentPosition[0] + "," + currentPosition[1]);
		  //nextLocations_List.removeAll(VisitedLocations_List);
		  nextLocations_List.removeAll(nextLocations_List); 
		  this.nextPossibleLocation();
		}
		  ;
		System.out.print("Clean ");
		goHome();
	}
}
