package main.java.SensorSimulator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SensorSimulator {
    public ArrayList<SensorObject> sensorObjectArrayList = new ArrayList<>();

    public SensorObject SensorObjectSearch(String coordinate){

        SensorObject foundSensorObject = null;

        for (SensorObject sensorObject : sensorObjectArrayList){
            if((sensorObject.coordinate).equals(coordinate)){
                foundSensorObject = sensorObject;
            }
        }
        return foundSensorObject;
    }
    
    public SensorObject GetNearestChargeStation(int[] position)
    {
        SensorObject foundSensorObject = null;
        int minDistance = Integer.MAX_VALUE;
        for(SensorObject sensorObject:sensorObjectArrayList)
        {
            if(sensorObject.isChargingStation == false)
                continue;
            String coordinate = sensorObject.coordinate;
            int[] coordinatePostion = new int[] { Integer.parseInt(coordinate.substring(1,2)),Integer.parseInt(coordinate.substring(3,4))};
            int distance = Math.abs(position[0]-coordinatePostion[0]) + Math.abs(position[1]-coordinatePostion[1]);
            if(minDistance > distance) {
                minDistance = distance;
                foundSensorObject = sensorObject;
            }
        }
        return foundSensorObject;

    }

    public void SensorObjectArrayListAdd(SensorObject sensorObject){
        sensorObjectArrayList.add(sensorObject);
    }

    public void SensorRead(String fileName){
        JSONParser parser = new JSONParser();

        try{
            JSONArray fileObject = (JSONArray) parser.parse(new FileReader(fileName));

            for (Object fileObjects : fileObject){

                JSONObject jsonObject = (JSONObject) fileObjects;

                String coordinate = (String) jsonObject.get("coordinate");
                String floorType = (String) jsonObject.get("floorType");
                String roomType = (String) jsonObject.get("roomType");
                Boolean isDirty = Boolean.valueOf((String) jsonObject.get("isDirty"));
                double dirtValue = Double.valueOf((String) jsonObject.get("dirtValue"));
                Boolean isChargingStation = Boolean.valueOf((String) jsonObject.get("isChargingStation"));
                Boolean isStairs = Boolean.valueOf((String) jsonObject.get("isStairs"));
                Boolean isWallUp = Boolean.valueOf((String) jsonObject.get("isWallUp"));
                Boolean isWallLeft = Boolean.valueOf((String) jsonObject.get("isWallLeft"));
                Boolean isWallRight = Boolean.valueOf((String) jsonObject.get("isWallRight"));
                Boolean isWallDown = Boolean.valueOf((String) jsonObject.get("isWallDown"));
                Boolean isDoorUp = Boolean.valueOf((String) jsonObject.get("isDoorUp"));
                Boolean isDoorLeft = Boolean.valueOf((String) jsonObject.get("isDoorLeft"));
                Boolean isDoorRight = Boolean.valueOf((String) jsonObject.get("isDoorRight"));
                Boolean isDoorDown = Boolean.valueOf((String) jsonObject.get("isDoorDown"));

                SensorObject newObject = new SensorObject(  coordinate, floorType, roomType, isDirty,dirtValue,
                        isChargingStation, isStairs, isWallUp, isWallLeft,
                        isWallRight, isWallDown, isDoorUp, isDoorLeft,
                        isDoorRight, isDoorDown);

                SensorObjectArrayListAdd(newObject);
            }

        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException exception){
            exception.printStackTrace();
        } catch (ParseException excpetion){
            excpetion.printStackTrace();
        }
    }
}
