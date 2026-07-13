package za.co.redbadger.mars_robots;

//Class to represent a robot that can move on a grid and track its position and direction
//The robot can rotate left or right, move forward
//and check if it has been lost at a specific position
//The robot uses the GroundControl instance to check if it has been lost at a specific position
//The robot also uses the CardinalDirection enum to determine its current direction and to calculate its new direction when rotating left or right
//The robot can also process a string of instructions, which can contain the characters 'L', 'R', and 'F' to rotate left, rotate right, and move forward, respectively
//The robot will stop processing instructions if it gets lost at a specific position
public class Robot{
    //member variable to track the robot's current x coordinate
    private int x;
    //member variable to track the robot's current y coordinate
    private int y;
    //member variable to track the robot's current direction in degrees
    private int degrees;
    //GroundControl instance to check if the robot has been lost at a specific position
    private final GroundControl groundcontrol;
    //boolean flag to indicate if the robot has been lost
    private boolean lost;
    
    //public constructor to initialize the robot's position, direction, and ground control instance
    public Robot(int x, int y, char direction, GroundControl groundcontrol){
        this.x = x;
        this.y = y;
        this.groundcontrol = groundcontrol;
        this.lost = false;
        this.degrees = CardinalDirection.getByAbbreviation(direction).getDegrees();
    }
    
    //rotate the robot left by subtracting 90 degrees from its current direction
    //if the degrees go below 0, wrap around to the other side of the compass
    //for example, if the robot is facing North (90 degrees) and rotates left,
    //it will be facing West (0 degrees)
    //if the robot is facing West (0 degrees) and rotates left,
    //it will be facing South (270 degrees)
    public void rotateLeft(){
        degrees -= 90;
            if(degrees < 0){
                degrees += 360;
            }
    }
    
    //rotate the robot right by adding 90 degrees to its current direction
    //if the degrees go above 360, wrap around to the other side of the compass
    //for example, if the robot is facing North (90 degrees) and rotates right,
    //it will be facing East (180 degrees)
    public void rotateRight(){
        degrees += 90;
            if(degrees >= 360){
                degrees -= 360;
            }
    }
    
    //Method to handle the case when the robot is at the edge of the grid and tries to move forward
    //If the robot is at the edge of the grid and tries to move forward, it
    //will be marked as lost and the position will be signaled to the GroundControl instance
    public void moveForwardOnEdge(){
        //check if the current position has a smell, indicating that a robot has been lost there before
        if(!groundcontrol.checkSmell(x, y)){
            lost = true;
            groundcontrol.signalLost(this.x, this.y);
            //if the current position does not have a smell, signal that the robot is lost and add the position to the list of smells in the GroundControl instance
        }
    }
    
    //Method to move the robot forward in its current direction
    //The robot will check if it is at the edge of the grid before moving forward
    //If the robot is at the edge of the grid and tries to move forward, it
    //will be marked as lost and the position will be signaled to the GroundControl instance
    //If the robot is not at the edge of the grid, it will move forward in
    //its current direction by updating its x or y coordinate based on its current direction
    //The robot uses the degrees variable to determine its current direction and to calculate its new position when moving forward
    //ie if the robot is facing North (90 degrees) and moves forward, it will increment its y coordinate by 1
    //if the robot is facing East (180 degrees) and moves forward, it will increment its x coordinate by 1
    //if the robot is facing South (270 degrees) and moves forward, it will decrement its y coordinate by 1
    //if the robot is facing West (0 degrees) and moves forward, it will increment its x coordinate by 1
    public void moveForward(){
        //switch statement to determine the robot's current direction and update its position accordingly
        //0 degrees = West, 90 degrees = North, 180 degrees = East, 270 degrees = South
        switch (degrees) {
            case 0 -> {
                //move West by decrementing the x coordinate
                if(x == 0) {
                    moveForwardOnEdge();
                }else{
                    x--;
                }
            }
            case 90 -> {
                //move North by incrementing the y coordinate
                if(y == groundcontrol.getMaxY()) {
                    moveForwardOnEdge();
                }else{
                    y++;
                }
            }
            case 180 -> {
                //move East by incrementing the x coordinate
                if(x == groundcontrol.getMaxX()) {
                    moveForwardOnEdge();
                }else{
                    x++;
                }
            }
            case 270 -> {
                //move South by decrementing the y coordinate
                if(y == 0) {
                    moveForwardOnEdge();
                }else{
                    y--;
                }
            }
        }
    }
    
    //Override the toString method to return the robot's current position and direction as a string
    //The format of the string is "x y direction"
    //or "x y direction LOST" if the robot is lost
    @Override
    public String toString(){
        String toString = x + " " + y + " " + CardinalDirection.getByDegrees(degrees).getAbbreviation();
        if(lost)
            toString += " LOST";
        return toString;
    }
  
    //processes a string of instructions, which can contain the characters 'L', 'R', and 'F' to rotate left, rotate right, and move forward, respectively
    //The robot will stop processing instructions if it gets lost at a specific position
    //The method returns the final position and direction of the robot as a string, in the format "x y direction" or "x y direction LOST" if the robot is lost
    public String process(String instructions){
        OUTER:
        for (char c : instructions.toCharArray()) {
            //switch statement to process each instruction character
            //L = rotate left, R = rotate right, F = move forward
            //If the robot gets lost at a specific position, it will stop processing instructions and return its final position and direction as a string
            switch (c) {
                case 'L' -> rotateLeft();
                case 'R' -> rotateRight();
                case 'F' -> {
                    moveForward();
                    if (lost) {
                        //exit the loop if the robot is lost
                        break OUTER;
                    }
                }
                default -> {
                    throw new IllegalArgumentException("Unknown instruction: " + c);
                }
            }
        }
    //return the final position and direction of the robot as a string, in the format "x y direction" or "x y direction LOST" if the robot is lost
    return this.toString();
  }
}