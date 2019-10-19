import main.java.ControlSystem.Vacuum;
import main.java.Logger;

public class Main {
public static void main(String[] args) {
	int[][] room = new int[3][3];
	Logger logger = Logger.getInstance();
	Logger.logInfo("SE 459");
	Logger.logInfo("Group 2");
	Logger.logInfo("This is our Clean Sweep Vacuum ");
	Vacuum vacuum = new Vacuum(room);
	}
}
