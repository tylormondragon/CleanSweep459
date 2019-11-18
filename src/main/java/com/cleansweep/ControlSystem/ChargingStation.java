package com.cleansweep.ControlSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Sources Used: https://www.techiedelight.com/find-shortest-path-in-maze/ (1)
 **/

// methods and concepts used from source (1)
public class ChargingStation {
    static final int M = 10;
    static final int N = 10;
    static int[] chargingStationCoordinates = new int[2];
    static int xChargingStation;
    static int yChargingStation;
    static int [][]visited = new int[10][10];

    public  ChargingStation(){}

    public static void setChargingStation(String newChargingStationCoordinates){
        String[] splitCoordinates = newChargingStationCoordinates.split(",");
        int x = Integer.parseInt(splitCoordinates[0].replaceAll("\\(", ""));
        int y = Integer.parseInt(splitCoordinates[1].replaceAll("\\)", ""));

        chargingStationCoordinates[0] = x;
        chargingStationCoordinates[1] = y;
        xChargingStation = x;
        yChargingStation = y;
    }

    public static int[] getChargingStationCoordinate() {
        return chargingStationCoordinates;
    }

    public static int getChargingStationXCoordinate() {
        return xChargingStation;
    }

    public static int getChargingStationYCoordinate() {
        return yChargingStation;
    }

    // methods and concepts used from source (1)
    // Check if it is possible to go to (x, y) from current position. The
    // function returns false if the cell has value 0 or already visited
    private static boolean isSafe(int mat[][], int visited[][], int x, int y){
        return !(mat[x][y] == 0 || visited[x][y] != 0);
    }

    // methods and concepts used from source (1)
    // if not a valid position, return false
    private static boolean isValid(int x, int y) {
        return (x < M && y < N && x >= 0 && y >= 0);
    }

    // Find Shortest Possible Route in a Matrix mat from source cell (0, 0)
    // to destination cell (x, y)

    // 'min_dist' stores length of longest path from source to destination
    // found so far and 'dist' maintains length of path from source cell to
    // the current cell (i, j)

    public static int findShortestPathPlusPower(int mat[][], int visited[][],
                                                int i, int j, int x, int y, int min_dist, int dist){
        // if destination is found, update min_dist
        if (i == x && j == y){
            return Integer.min(dist, min_dist);
        }

        // set (i, j) cell as visited
        visited[i][j] = 1;

        // go to bottom cell
        if (isValid(i + 1, j) && isSafe(mat, visited, i + 1, j)){
            min_dist = findShortestPathPlusPower(mat, visited, i + 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to right cell
        if (isValid(i, j + 1) && isSafe(mat, visited, i, j + 1)){
            min_dist = findShortestPathPlusPower(mat, visited, i, j + 1, x, y,
                    min_dist, dist + 1);
        }

        // go to top cell
        if (isValid(i - 1, j) && isSafe(mat, visited, i - 1, j)){
            min_dist = findShortestPathPlusPower(mat, visited, i - 1, j, x, y,
                    min_dist, dist + 1);
        }

        // go to left cell
        if (isValid(i, j - 1) && isSafe(mat, visited, i, j - 1)){
            min_dist = findShortestPathPlusPower(mat, visited, i, j - 1, x, y,
                    min_dist, dist + 1);
        }

        // Backtrack - Remove (i, j) from visited matrix
        visited[i][j] = 0;

        return min_dist;
    }

    public static int WorstCaseScenarioToChargingStation(){
        for(int[] visitedCoordinate : Motion.VisitedLocations_List){
            int x = visitedCoordinate[0];
            int y = visitedCoordinate[1];
            visited[x][y] = 1;
        }

        int[][] notVisited = new int[M][N];
        int[] currentPosition = Motion.getCurrentPosition();
        int currentX = currentPosition[0];
        int currentY = currentPosition[1];

        int minimumPowerNeeded =
                findShortestPathPlusPower(visited, notVisited, ChargingStation.getChargingStationXCoordinate(),
                                          ChargingStation.getChargingStationYCoordinate(), currentX, currentY, Integer.MAX_VALUE, 0);
        return minimumPowerNeeded*3;
    }
}