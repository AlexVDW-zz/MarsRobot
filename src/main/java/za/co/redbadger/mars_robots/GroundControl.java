package za.co.redbadger.mars_robots;

import java.util.ArrayList;
import java.util.List;

//Class to represent the ground control system that manages the grid and tracks lost robots
//The ground control system is responsible for keeping track of the maximum x and y coordinates of the grid
//It also keeps track of the positions where robots have been lost, so that future robots can
//avoid those positions and not get lost themselves
public class GroundControl{
    //maximum x and y coordinates of the grid
    private int maxX;
    private int maxY;
    //List to keep track of the positions where robots have been lost
    private List<int[]> smells;

    public GroundControl() {
    }
    
    //public accessor for maxX
    public int getMaxX(){
        return maxX;
    }

    //public accessor for maxY
    public int getMaxY(){
        return maxY;
    }
    
    public GroundControl(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
        this.smells = new ArrayList<>();
    }
    
    //method to signal that a robot has been lost at a specific position
    //this method adds the position to the list of smells, so that future robots can avoid
    //getting lost at the same position
    public void signalLost(int x, int y){
        this.smells.add(new int[]{x, y});
    }
    
    //method to check if a specific position has a smell, indicating that a robot has been lost there before
    //this method checks the list of smells to see if the position is present
    //if the position is present, it returns true, indicating that a robot has been lost
    //if the position is not present, it returns false, indicating that no robot has been lost there
    public boolean checkSmell(int x, int y){
        //iterate through the list of smells and check if the position is present
        for(int[] smell : this.smells){
            //if the position is present, return true
            //x = smell[0] and y = smell[1] are the coordinates of the position to check
            if(smell[0] == x && smell[1] == y)
                return true;
        }
        return false;//if the position is not present, return false
    }
}