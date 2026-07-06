package za.co.redbadger.mars_robots;

public enum CardinalDirection{
    W('W', 0),N('N', 90),E('E', 180),S('S',270);
    
    private final char abbreviation;
    private final int degrees;
    
    public int getDegrees(){
        return degrees;
    }
    
    public char getAbbreviation(){
        return abbreviation;
    }
    
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
    
    CardinalDirection(char abbreviation, int degrees){
        this.abbreviation = abbreviation;
        this.degrees = degrees;
    }
}