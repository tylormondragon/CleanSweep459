package com.cleansweep;

import com.cleansweep.ControlSystem.*;
import com.cleansweep.SensorSimulator.SensorSimulator;
import com.cleansweep.SensorSimulator.SensorSimulatorServer;
import com.cleansweep.SensorSimulator.mainSensorSimulatorServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CleanSweepTesting {
    final int[] startingPosition = {1,1};

    @BeforeAll
    public void StartSensorSimulatorServer(){
        SensorSimulator sensorSimulator = new SensorSimulator();
        BufferedReader br = new BufferedReader(new InputStreamReader(mainSensorSimulatorServer.class.getResourceAsStream("/floorPlan.json")));

        sensorSimulator.SensorRead(br);

        SensorSimulatorServer newServer = new SensorSimulatorServer(sensorSimulator);

        try {
            newServer.StartSensorSimulatorServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void SensorSimulatorExceptionsTest(){
        SensorSimulator sensorTest = Mockito.mock(SensorSimulator.class);
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

        Mockito.doThrow(FileNotFoundException.class).when(sensorTest).SensorRead(bufferedReader);
        Mockito.doThrow(IOException.class).when(sensorTest).SensorRead(bufferedReader);
        Mockito.doThrow(ParseException.class).when(sensorTest).SensorRead(bufferedReader);
    }

    @Test
    public void SensorSimulatorServerExceptionsTest() {
        SensorSimulatorServer sensorServerTest = Mockito.mock(SensorSimulatorServer.class);

        try {
            Mockito.doThrow(IOException.class).when(sensorServerTest).StartSensorSimulatorServer();
        }
        catch (IOException exception) {
            System.out.println("Testing for nested IO exception");
        }
    }

    @Test
    public void ControlSystemClientExceptionsTest(){
        ControlSystemClient controlSystemTest = Mockito.mock(ControlSystemClient.class);

        try {
            Mockito.doThrow(IOException.class).when(controlSystemTest).getSensorObject(null);
        }
        catch (ClassNotFoundException exception){
            System.out.println("Testing for nested ClassNotFoundException");
        }
    }

    @Test
    public void MovingUpTest(){
        Motion motionTest = Mockito.mock(Motion.class);
        Power power = new Power(250.0);
        int[] newPosition = {1,2};

        MovingUp up = new MovingUp(motionTest, startingPosition, newPosition, power);
        assertEquals(newPosition, up.getCurrentPosition());
    }

    @Test
    public void MovingDownTest(){
        Motion motionTest = Mockito.mock(Motion.class);
        Power power = new Power(250.0);
        int[] newPosition = {1,0};

        MovingDown down = new MovingDown(motionTest, startingPosition, newPosition, power);
        assertEquals(newPosition, down.getCurrentPosition());
    }

    @Test
    public void MovingLeftTest(){
        Motion motionTest = Mockito.mock(Motion.class);
        Power power = new Power(250.0);
        int[] newPosition = {0,1};

        MovingLeft left = new MovingLeft(motionTest, startingPosition, newPosition, power);
        assertEquals(newPosition, left.getCurrentPosition());
    }

    @Test
    public void MovingRightTest(){
        Motion motionTest = Mockito.mock(Motion.class);
        Power power = new Power(250.0);
        int[] newPosition = {2,1};

        MovingRight right = new MovingRight(motionTest, startingPosition, newPosition, power);
        assertEquals(newPosition, right.getCurrentPosition());
    }
}
