package za.co.redbadger.mars_robots;

//split the cardinal directions into an enum to link them to their corresponding degrees and abbreviations
//degrees are used to calculate the new direction when turning left or right
//right turn = +90 degrees, left turn = -90 degrees
//if the degrees go below 0 or above 360, we can use modulo to wrap around to the other side of the compass

//Enum to represent the cardinal directions and their corresponding degrees and abbreviations
//West = 0 degrees, North = 90 degrees, East = 180 degrees, South = 270 degrees
public enum CardinalDirection{
    W('W', 0),N('N', 90),E('E', 180),S('S',270);
    
    //member variable for abbreviation
    private final char abbreviation;

    //member variable for degrees
    private final int degrees;
    
    public int getDegrees(){
        return degrees;
    }
    
    public char getAbbreviation(){
        return abbreviation;
    }
    
    //static helper methods to get the cardinal direction by abbreviation or degrees
    public static CardinalDirection getByAbbreviation(char abbreviation){
        switch(abbreviation){
          case 'W' -> {
              return W;
            }
          case 'N' -> {
              return N;
            }
          case 'E' -> {
              return E;
            }
          case 'S' -> {
              return S;
            }
      }
      return N; // throw Exception
    }
    
    //static helper methods to get the cardinal direction by abbreviation or degrees
    public static CardinalDirection getByDegrees(int degrees){
        switch(degrees){
          case 0 -> {
              return W;
            }
          case 90 -> {
              return N;
            }
          case 180 -> {
              return E;
            }
          case 270 -> {
              return S;
            }
      }
      return N; // throw Exception
    }
    
    //Constructor to set the abbreviation and degrees for each cardinal direction
    CardinalDirection(char abbreviation, int degrees){
        this.abbreviation = abbreviation;
        this.degrees = degrees;
    }
}