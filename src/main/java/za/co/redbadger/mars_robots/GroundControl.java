package za.co.redbadger.mars_robots;

import java.util.ArrayList;
import java.util.List;

public class GroundControl{
    private int maxX;
    private int maxY;
    private List<int[]> smells;

    public GroundControl() {
    }
    
    public int getMaxX(){
        return maxX;
    }
    
    public int getMaxY(){
        return maxY;
    }
    
    public GroundControl(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
        this.smells = new ArrayList<>();
    }
    
    public void signalLost(int x, int y){
        this.smells.add(new int[]{x, y});
    }
    
    public boolean checkSmell(int x, int y){
        for(int[] smell : this.smells){
            if(smell[0] == x && smell[1] == y)
                return true;
        }
        return false;
    }
}