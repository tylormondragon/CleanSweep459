package com.cleansweep;

import com.cleansweep.ControlSystem.*;
import com.cleansweep.SensorSimulator.SensorObject;
import com.cleansweep.SensorSimulator.SensorSimulator;
import com.cleansweep.SensorSimulator.SensorSimulatorServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanSweepTest {
//    final int[] startingPosition = {1,1};
//    SensorObject startingObject;
//    SensorObject moveUpObject;
//    SensorObject moveDownObject;
//    SensorObject moveLeftObject;
//    SensorObject moveRightObject;

    /**
     *     public SensorObject ( String coordinate, String floorType, String roomType, Boolean isDirty, double dirtValue,
     *                           Boolean isChargingStation, Boolean isStairs, Boolean isWallUp, Boolean isWallLeft,
     *                           Boolean isWallRight, Boolean isWallDown, Boolean isDoorUp, Boolean isDoorLeft,
     *                           Boolean isDoorRight, Boolean isDoorDown)
     */

//    @BeforeEach
//    public void CreateSensorObjects(){
//        this.startingObject = new SensorObject("(1,1)", "Low Pile", "Guest Bedroom A", true, 2.0,false,
//                false, false, false, false, false, false, false, false, false);
//
//        this.moveUpObject = new SensorObject("(1,2)", "Low Pile", "Guest Bedroom A", true, 2.0,false,
//                false, false, false, false, false, false, false, false, false);
//
//        this.moveDownObject = new SensorObject("(1,0)", "Low Pile", "Guest Bedroom A", true, 2.0,false,
//                false, false, false, false, true, false, false, false, false);
//
//        this.moveLeftObject = new SensorObject("(0,1)", "Low Pile", "Guest Bedroom A", true, 2.0,false,
//                false, false, true, false, false, false, false, false, false);
//
//        this.moveRightObject = new SensorObject("(2,1)", "Low Pile", "Guest Bedroom A", false, 0.0,false,
//                false, false, false, false, false, false, false, false, false);
//    }

    @Test
    public void SensorSimulatorExceptionsTest(){
        SensorSimulator sensorTest = Mockito.mock(SensorSimulator.class);
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

        Mockito.doThrow(FileNotFoundException.class).when(sensorTest).SensorRead(bufferedReader);
        Mockito.doThrow(IOException.class).when(sensorTest).SensorRead(bufferedReader);
        Mockito.doThrow(ParseException.class).when(sensorTest).SensorRead(bufferedReader);
    }

    @Test
    public void SensorSimulatorServerExceptionsTest(){
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

//    @Test
//    public void MovingUpTest(){
//        Motion motionTest = Mockito.mock(Motion.class);
//        ControlSystemClient clientTest = Mockito.mock(ControlSystemClient.class);
//        Power power = new Power(250.0);
//        int[] newPosition = {1,2};
//
//        try {
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(startingObject);
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(moveUpObject);
//        }
//        catch (ClassNotFoundException exception){
//            System.out.println("Class not found exception: " + exception);
//        }
//
//        MovingUp up = new MovingUp(motionTest, startingPosition, newPosition, power);
//        assertEquals(newPosition, up.getCurrentPosition());
//    }
//
//    @Test
//    public void MovingDownTest(){
//        Motion motionTest = Mockito.mock(Motion.class);
//        ControlSystemClient clientTest = Mockito.mock(ControlSystemClient.class);
//        Power power = new Power(250.0);
//        int[] newPosition = {1,0};
//
//        try {
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(startingObject);
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(moveDownObject);
//        }
//        catch (ClassNotFoundException exception){
//            System.out.println("Class not found exception: " + exception);
//        }
//
//        MovingDown down = new MovingDown(motionTest, startingPosition, newPosition, power);
//        assertEquals(newPosition, down.getCurrentPosition());
//    }
//
//    @Test
//    public void MovingLeftTest(){
//        Motion motionTest = Mockito.mock(Motion.class);
//        ControlSystemClient clientTest = Mockito.mock(ControlSystemClient.class);
//        Power power = new Power(250.0);
//        int[] newPosition = {0,1};
//
//        try {
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(startingObject);
//            Mockito.when(clientTest.getSensorObject(null)).thenReturn(moveLeftObject);
//        }
//        catch (ClassNotFoundException exception){
//            System.out.println("Class not found exception: " + exception);
//        }
//
//        MovingLeft left = new MovingLeft(motionTest, startingPosition, newPosition, power);
//        assertEquals(newPosition, left.getCurrentPosition());
//    }
//
//    @Test
//    public void MovingRightTest(){
//        Motion motionTest = Mockito.mock(Motion.class);
//        ControlSystemClient clientTest = Mockito.mock(ControlSystemClient.class);
//        Power power = new Power(250.0);
//        int[] newPosition = {2,1};
//
//        try {
//            Mockito.when(clientTest.getSensorObject("")).thenReturn(startingObject);
//            Mockito.when(clientTest.getSensorObject("")).thenReturn(moveRightObject);
//
////            Mockito.when(startingObject.getCoordinate()).thenReturn("(1,1)");
////            Mockito.when(moveRightObject.getCoordinate()).thenReturn("(2,1)");
//
//            MovingRight right = new MovingRight(motionTest, startingPosition, newPosition, power);
//            assertEquals(newPosition, right.getCurrentPosition());
//        }
//        catch (ClassNotFoundException exception){
//            System.out.println("Class not found exception: " + exception);
//        }
//    }
}
