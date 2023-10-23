

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is where the Election object gets created and properly assigns
 * where each state should be designated. This class also determines whether
 * once the Sequential code goes through the possibilities of what the user
 * decided their target to be, when the program has reached its goal and
 * the program terminates. Program also prevents each different ArrayList
 * in a neat and orderly column fashion using ThreeColumnFormatter class
 * in the toString.
 * 
 * @author zaknilsen
 *
 */
public class Election {
    /** This is where red states are stored */
    private ArrayList<State> redStates;
    /** This is where blue states are stored */
    private ArrayList<State> blueStates;
    /** This is where unassigned states are stored */
    private ArrayList<State> unassignedStates;

    // this is sequential code
    // make sure there are no duplicate states or missing states, always sums to 538
    // toString should use ThreeColumnFormatter to print 
    // second constructor will return copy, and some methods will also return copy of array/ arrayList
    // make sure if state is added it is removed so it is not added to anything else
    
    public Election() {
        redStates  = new ArrayList<State>();
        blueStates = new ArrayList<State>();
        unassignedStates = new ArrayList<State>();
        for (State current : State.values()) {
            unassignedStates.add(current);   
        }
        
        if (redStates.size() + blueStates.size() + unassignedStates.size() != 51) {
            throw new IllegalArgumentException();
        }
        
        
    }
    
    public Election(Election election) {
        redStates = election.getRedStates();
        blueStates = election.getBlueStates();
        unassignedStates = election.getUnassignedStates();
        
    }
    
    /** This method finds all completed elections with the red votes equal to a
     * a particular target and starting from a particular starting state. You 
     * should not modify this method.
     *
     * @param startState An in-progress Election with some states possibly already assigned to red and blue
     * @param redTarget The number of red votes our returned completed elections should have
     * @return An ArrayList of finished Elections that are consistent with the start state and have redTarget red votes
     */
    public static ArrayList<Election> findTargetElections(Election startState, int redTarget) {
        ArrayList<State> unassigned = startState.getUnassignedStates();
        ArrayList<Election> matching = new ArrayList<Election>();
        int numberLeft = unassigned.size();
        long subsets = (long)Math.pow(2.0, numberLeft);
        for (long i = 0; i < subsets; i++) {
            Election trial = new Election(startState);
            for (int j = 0; j < unassigned.size(); j++) {
                // a one bit in spot j means red, a zero bit means blue
                if (((i>>j)&1L)==1L) {
                    trial.addRedState(unassigned.get(j));
                } else {
                    trial.addBlueState(unassigned.get(j));
                }
            }
            if (trial.getRedVotes()==redTarget) {
                // the finished election matches our target vote count 
                matching.add(trial);
            }
        }
        return matching;
    }
    
    /**
     * This class takes in two lists read from files and then adds them
     * to the correct ArrayLists where they will be stored
     * @param list Red states file
     * @param list2 Blue states file
     * @return Election object of red and blue states that are added in
     */
    public static Election fromStateNameLists(List<String> list, List<String> list2) {
        Election election;
        election = new Election();
        
        for (String currentLine : list) {
            election.addRedState(State.fromStateName(currentLine));
        }
        
        for (String currentLine : list2) {
            election.addBlueState(State.fromStateName(currentLine));
        }
        
        if (election.getBlueVotes() + election.getRedVotes() + election.getUnassignedVotes() != 538) {
            throw new IllegalArgumentException();
        }
        
        // Unsure whether to use this constructor or not
        return election;
    }
    /**
     * Says that program is over when the unassigned states reaches 0
     * @return boolean determined by whether program is over or not
     */
    public boolean isOver() {
        if (unassignedStates.size() == 0) {
            return true;
        }
        return false;
    }
    /**
     * This method adds red states and removes that state from any other
     * ArrayList so it does not double up.
     * @param state object that will be added to ArrayList
     */
    public void addRedState(State state) {
        redStates.add(state);
        blueStates.remove(state);
        unassignedStates.remove(state);
    }
    /**
     * This method adds blue states and removes that state from any other
     * ArrayList so it does not double up.
     * @param state object that will be added to ArrayList
     */
    public void addBlueState(State state) {
        blueStates.add(state);
        unassignedStates.remove(state);
        redStates.remove(state);
    }
    /**
     * This method adds unassigned states and removes that state from any other
     * ArrayList so it does not double up.
     * @param state object that will be added to ArrayList
     */
    public void unassignState(State state) {
        unassignedStates.add(state);
        blueStates.remove(state);
        redStates.remove(state);
    }
    /**
     * This method gets total amount of votes from states in red ArrayList
     * @return int of total number of votes
     */
    public int getRedVotes() {
        int votes = 0;
        for (int i = 0; i < redStates.size(); i++) {
            votes = votes + redStates.get(i).getVotes();
        }
        return votes;
        
    }
    
    /**
     * This method gets total amount of votes from states in blue ArrayList
     * @return int of total number of votes
     */
    public int getBlueVotes() {
        int votes = 0;
        for (int i = 0; i < blueStates.size(); i++) {
            votes = votes + blueStates.get(i).getVotes();
        }
        return votes; 
    }
    
    /**
     * This method gets total amount of votes from states in unassigned ArrayList
     * @return int of total number of votes
     */
    public int getUnassignedVotes() {
        int votes = 0;
        for (int i = 0; i < unassignedStates.size(); i++) {
            votes = votes + unassignedStates.get(i).getVotes();
        }
        return votes;
    }
    /**
     * Creates copy of blue states ArrayList
     * @return ArrayList<State> Copy of blue states ArrayList
     */
    public ArrayList<State> getBlueStates() {
        ArrayList<State> copy = new ArrayList<State>(blueStates);
        return copy;
        
    }
    /**
     * Creates copy of red states ArrayList
     * @return ArrayList<State> Copy of red states ArrayList
     */
    public ArrayList<State> getRedStates() {
        ArrayList<State> copy = new ArrayList<State>(redStates);
        return copy;
        
    }
    /**
     * Creates copy of unassigned states ArrayList
     * @return ArrayList<State> Copy of unassigned states ArrayList
     */
    public ArrayList<State> getUnassignedStates() {
        ArrayList<State> copy = new ArrayList<State>(unassignedStates);
        return copy;
        
    }
    /**
     * Election is formatted properly into three column format, that is easy
     * to understand for user, and to print out a clear understanding of what
     * is going on in the election.
     * @return String of Election object in three column format
     */
    public String toString() {
        ThreeColumnFormatter columns;
        String red;
        String blue;
        String undecided;
        int characterLength;
        String finalString = "Red " + getRedVotes() + "\t\t\t\t\t" + "Blue " + getBlueVotes() + "\t\t\t\t" + "Undecided " + getUnassignedVotes();
        finalString = finalString + "\n\n";
        red = stateListToString(getRedStates());
        blue = stateListToString(getBlueStates());
        undecided = stateListToString(getUnassignedStates());
        characterLength = 38;
        
        columns = new ThreeColumnFormatter(red, blue, undecided, characterLength);
        finalString = finalString + columns.getString();
                
        return finalString;
        
    }
    
    /**
     * This is private method where states are sorted and where each
     * individual column is created to be sent to the toString method
     * @param states ArrayList that is taken in
     * @return String of individual column of states ArrayList
     */
    private String stateListToString(ArrayList<State> states) {
        String stateList = "";
        StateVoteComparator comparator;
        comparator = new StateVoteComparator();
        Collections.sort(states, comparator);
        for (int i = 0; i < states.size(); i++) {
            //stateList = stateList + states.get(i).getName() + "(" + states.get(i).getVotes() + ")";
            stateList = stateList + states.get(i).toString() + "\n";    
        }
        Collections.sort(states);
        return stateList;
        
    }
    
    
    
    
    
}
