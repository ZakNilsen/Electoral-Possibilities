
/**
 * This enum holds all the states and D.C. as well, each containing a 
 * String name, and the number of electoral votes that state has. Also
 * determines whether each state is correct, as if one name does not match
 * with any of the names here, the static method should throw an exception
 * so that no unreal states or other values can get through.
 * @author zaknilsen
 *
 */
public enum State {
    ALABAMA("Alabama",9),
    ALASKA("Alaska",3),
    ARIZONA("Arizona",11),
    ARKANSAS("Arkansas",6),
    CALIFORNIA("California",55),
    COLORADO("Colorado",9),
    CONNECTICUT("Connecticut",7),
    DELAWARE("Delaware",3),
    FLORIDA("Florida",29),
    GEORGIA("Georgia",16),
    HAWAII("Hawaii",4),
    IDAHO("Idaho",4),
    ILLINOIS("Illinois",20),
    INDIANA("Indiana",11),
    IOWA("Iowa",6),
    KANSAS("Kansas",6),
    KENTUCKY("Kentucky",8),
    LOUISIANA("Louisiana",8),
    MAINE("Maine",4),
    MARYLAND("Maryland",10),
    MASSACHUSETTS("Massachusetts",11),
    MICHIGAN("Michigan",16),
    MINNESOTA("Minnesota",10),
    MISSISSIPPI("Mississippi",6),
    MISSOURI("Missouri",10),
    MONTANA("Montana",3),
    NEBRASKA("Nebraska",5),
    NEVADA("Nevada",6),
    NEW_HAMPSHIRE("New Hampshire",4),
    NEW_JERSEY("New Jersey",14),
    NEW_MEXICO("New Mexico",5),
    NEW_YORK("New York",29),
    NORTH_CAROLINA("North Carolina",15),
    NORTH_DAKOTA("North Dakota",3),
    OHIO("Ohio",18),
    OKLAHOMA("Oklahoma",7),
    OREGON("Oregon",7),
    PENNSYLVANIA("Pennsylvania",20),
    RHODE_ISLAND("Rhode Island",4),
    SOUTH_CAROLINA("South Carolina",9),
    SOUTH_DAKOTA("South Dakota",3),
    TENNESSEE("Tennessee",11),
    TEXAS("Texas",38),
    UTAH("Utah",6),
    VERMONT("Vermont",3),
    VIRGINIA("Virginia",13),
    WASHINGTON("Washington",12),
    WEST_VIRGINIA("West Virginia",5),
    WISCONSIN("Wisconsin",10),
    WYOMING("Wyoming",3),
    DISTRICT_OF_COLUMBIA("District of Columbia",3);
    
    /** name of state */
    private String name;
    /** Electoral votes that state has */
    private int votes;
    
    //constructor
    private State(String name, int delegates) {
        this.name = name;
        votes = delegates;
        
    }
    /**
     * Takes in String of name to determine if that name matches with
     * states listed here, and if it does not it should throw an exception
     * as that state does not exist, or it is spelled wrong.
     * @param name is String that should be a state name, and if it is not
     * exception will be thrown.
     * @return State object that should match one of enums listed above
     */
    static State fromStateName(String name) {
        // should not crash if state spelled wrong
        for (State current : State.values()) {
            if (current.getName().equals(name)) {
                return current;
            }
        }
        throw new IllegalArgumentException();
    }
    /**
     * Gives number of electoral votes state has
     * @return int of electoral votes state has
     */
    int getVotes() {  
        return votes;
    }
    
    /**
     * Gets name of state
     * @return String of state name
     */
    String getName()  {
        return name; 
    }
    
    /**
     * Gives String of state object with the name and number of electoral
     * votes that state has.
     * @return String of state name with votes for user to see
     */
    @Override
    public String toString() {
        String finalString = "";
        finalString = finalString + name + "(" + votes + ")";
        return finalString;
        
    }


    

}

