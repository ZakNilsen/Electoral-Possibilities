import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;
/**
 * Class extends RecursiveAction and uses this to create multiple threads
 * to compute and calculate answer
 * @author zaknilsen
 *
 */
public class VoteFinder extends RecursiveAction {
    // use fork join stuff, and implementing recursive action 
    /** Election to be computed */
    private Election startState;
    /** Total solutions computed */
    private ArrayList<Election> solutionList;
    /** Determines if solution is found or not */
    private boolean solutionFind;
    /** Target for red votes user is trying to find */
    private int redTarget;
    
    
    public VoteFinder(Election start, int target) {
        //might need to create election, or take in election, not sure yet
        startState = start;
        redTarget = target;
        solutionList = new ArrayList<Election>();
        
    }
    
    /**
     * Method to determine whether solution is found or not
     * @return boolean of true or false
     */
    public boolean isSolutionFound() {
        return false;
    }
    
    /**
     * This method grabs the solutionList
     * @return ArrayList<Election> of the solutionList
     */
    public ArrayList<Election> getSolutionList() {
        return solutionList;    
    }
    
    /**
     * This method computes all solutions to find the redTarget that
     * is set by user by creating multiple threads and then joining them
     * together to find the final result.
     */
    @Override
    public void compute() {
        ArrayList<Election> elections;
        if (startState.getUnassignedStates().size() < 12) {
            elections = Election.findTargetElections(startState, redTarget);
            solutionList.addAll(0, elections);
        } else {
            Election electionCopy = new Election(startState);
            Election electionCopy2 = new Election(startState);
            
            VoteFinder left = new VoteFinder(electionCopy, redTarget);
            electionCopy.addBlueState(startState.getUnassignedStates().get(0));
            
            VoteFinder right = new VoteFinder(electionCopy2, redTarget);
            electionCopy2.addRedState(startState.getUnassignedStates().get(0));

            left.fork();
            right.compute();
            left.join();
            
            solutionList.addAll(0, left.solutionList);
            solutionList.addAll(0, right.solutionList);
            
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
