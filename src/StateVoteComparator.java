import java.util.Comparator;
/**
 * This class compares states electoral votes and ranks them from there
 * @author zaknilsen
 *
 */
public class StateVoteComparator implements Comparator<State> {
    // should sort a list of State constants by their electoral votes
    /**
     * This method compare two states votes and determines which one is higher
     * and which one is lower so that states can be sorted.
     * @param vote1 State object that will be compared to other
     * @param vote2 State object that will be compared to other
     * @return int based on whether state has more(1), less(-1) or same(0) number
     * of electoral votes as the other.  
     */
    @Override
    public int compare(State vote1, State vote2) {
        if (vote1.getVotes() > vote2.getVotes()) {
            return -1;
        } else if (vote1.getVotes() < vote2.getVotes()) {
            return 1;
        } 
        return 0;
    }

}
