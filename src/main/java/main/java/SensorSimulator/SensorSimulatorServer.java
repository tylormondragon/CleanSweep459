package main.java.SensorSimulator;

// Java I/O and networking libs
import java.io.*;
import java.net.*;

public class SensorSimulatorServer{
    SensorSimulator sensorSimulatorObject;

    public SensorSimulatorServer(SensorSimulator sensorSimulatorObject) { this.sensorSimulatorObject = sensorSimulatorObject; }

    public void StartSensorSimulatorServer() throws IOException{
        int port = 9999;
        int queue_len = 6;
        Socket socket;

        // create socket listening on port#: 9999 + queue length of 6
        ServerSocket serverSocket = new ServerSocket(port, queue_len);
        System.out.println("SensorSimulatorServer, listening on port 9999.\n" );

        // accept incoming coming connections and then pass them along to the worker class
        while (true) {
            socket = serverSocket.accept();
            new Worker(socket, sensorSimulatorObject).run();
        }
    }
}

class Worker extends Thread {
    Socket socket;
    SensorSimulator sensorSimulatorObject;

    Worker(Socket socket, SensorSimulator sensorSimulatorObject) {
        this.socket = socket;
        this.sensorSimulatorObject = sensorSimulatorObject;
    }

    public void run() {
        // receive input/output from the opened socket
        ObjectOutputStream output = null;
        BufferedReader input = null;

        // Try and open streams with given socket
        try {
            output = new ObjectOutputStream((socket.getOutputStream()));
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            try {
                // read line from input
                String coordinate;
                coordinate = input.readLine();

                SensorSimulatorSearch(coordinate, output);

            } catch (IOException exception) {
                System.out.println("Server read error");
                exception.printStackTrace();
            }
            // close socket after returning remote address or catching an exception
            socket.close();
        } catch (IOException ioexception) {
            System.out.println(ioexception);
        }
    }

    public void SensorSimulatorSearch(String coordinate, ObjectOutputStream output){
        try {
            System.out.println("Looking up: " + coordinate);
            // check if we can resolve the name to get details about the hostname and IP address
            // if it doesn't exist, an exception will be thrown back at the client

            SensorObject foundSensorObject = sensorSimulatorObject.SensorObjectSearch(coordinate);
            output.writeObject(foundSensorObject);

        } catch (IOException exception) {
            System.out.println("Failed to find coordinate: " + coordinate);
        }
    }
}
