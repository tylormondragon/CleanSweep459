package com.cleansweep.ControlSystem;

import com.cleansweep.Logger;

public class mainControlSystemClient {

    public static Power power;
    public static void main(String[] args) {
        ControlSystemClient client = new ControlSystemClient();

        int[][] room = new int[10][10];
        power = new Power(250.0);
        Logger logger = Logger.getInstance();
        Logger.logInfo("SE 459");
        Logger.logInfo("Group 2");
        Logger.logInfo("This is our Clean Sweep Vacuum ");
        Vacuum vacuum = new Vacuum(room, power);
    }
}
