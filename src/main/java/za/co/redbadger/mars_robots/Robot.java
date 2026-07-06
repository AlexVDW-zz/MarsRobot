package za.co.redbadger.mars_robots;

public class Robot{
    private int x;
    private int y;
    private int degrees;
    private final GroundControl groundcontrol;
    private boolean lost;
    
    public Robot(int x, int y, char direction, GroundControl groundcontrol){
        this.x = x;
        this.y = y;
        this.groundcontrol = groundcontrol;
        this.lost = false;
        this.degrees = CardinalDirection.getByAbbreviation(direction).getDegrees();
    }
    
    public void rotateLeft(){
        degrees -= 90;
            if(degrees < 0){
                degrees += 360;
            }
    }
    
    public void rotateRight(){
        degrees += 90;
            if(degrees >= 360){
                degrees -= 360;
            }
    }
    
    public void moveForwardOnEdge(){
        if(!groundcontrol.checkSmell(x, y)){
            lost = true;
            groundcontrol.signalLost(this.x, this.y);
        }
    }
    
    public void moveForward(){
        switch (degrees) {
            case 0 -> {
                if(x == 0) {
                    moveForwardOnEdge();
                }else{
                    x--;
                }
            }
            case 90 -> {
                if(y == groundcontrol.getMaxY()) {
                    moveForwardOnEdge();
                }else{
                    y++;
                }
            }
            case 180 -> {
                if(x == groundcontrol.getMaxX()) {
                    moveForwardOnEdge();
                }else{
                    x++;
                }
            }
            case 270 -> {
                if(y == 0) {
                    moveForwardOnEdge();
                }else{
                    y--;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        String toString = x + " " + y + " " + CardinalDirection.getByDegrees(degrees).getAbbreviation();
        if(lost)
            toString += " LOST";
        return toString;
    }
  
  public String process(String instructions){
        OUTER:
        for (char c : instructions.toCharArray()) {
            switch (c) {
                case 'L' -> rotateLeft();
                //System.out.println(c + " | " + this.toString());
                case 'R' -> rotateRight();
                //System.out.println(c + " | " + this.toString());
                case 'F' -> {
                    moveForward();
                    //System.out.println(c + " | " + this.toString());
                    if (lost) {
                        break OUTER;
                    }
                }
                default -> {
                    //throw error
                }
            }
        }
    return this.toString();
  }
}