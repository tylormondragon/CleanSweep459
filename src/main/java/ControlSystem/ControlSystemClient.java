package main.java.ControlSystem;

// Java I/O and networking libs
import main.java.SensorSimulator.SensorObject;

import java.io.*;
import java.net.*;

public class ControlSystemClient {

    public ControlSystemClient () {}

    public SensorObject getSensorObject(String coordinate) throws ClassNotFoundException{
        Socket socket;
        final String hostname = "localhost";
        final int port = 9999;

        ObjectInputStream input;
        PrintStream output;
        SensorObject foundSensorObject = null;

        try {
            // open connection to server on port#: 9999
            socket = new Socket(hostname, port);

            // open input/output streams for socket to server
            input = new ObjectInputStream(socket.getInputStream());
            output = new PrintStream((socket.getOutputStream()));

            output.println(coordinate);
            output.flush();

            foundSensorObject = (SensorObject) input.readObject();
            socket.close();
        }
        catch (IOException exception) {
            System.out.println("Socket error");
            exception.printStackTrace();
        }
        return foundSensorObject;
    }
}