import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
/**
 * This is where every election gets presented in a neat fashion, files read
 * and where the user puts in their target input and what method they want to use
 * to find an election that meets their target.
 * @author zaknilsen
 *
 */
public class ElectionDriver {

    public static void main(String[] args) {
        //  should handle error exceptions and should not crash
        /*
         * No negative electoral votes
         * Misspelling States in files
         * -just some examples
         */
        
        //sequential code is already written, just need VoteFinder for Fork/Join
        Scanner keyboard = new Scanner(System.in);
        String target;
        String method;
        Election election;
        List<String> list = null;
        List<String> list2 = null;
        ArrayList<Election> electionList;
        VoteFinder vote;
        int parseTarget;
        long startTime;
        long endTime;
        System.out.println("Welcome to Election Central!");
        System.out.println("Based on the files provided, the current electoral vote status is ");
        
        election = new Election();
        
        try {
            list = Files.readAllLines(Paths.get("red.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File reading error");
            return;
        }
        
        try {
            list2 = Files.readAllLines(Paths.get("blue.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File reading error");
            return;
        }
        
        
        try {
            election = Election.fromStateNameLists(list, list2);
        } catch (IllegalArgumentException e) {
            System.out.println("**Problem with States in file**");
            return;
        } catch (NullPointerException e) {
            System.out.println("**Problem with States in file**");
            return;
        }
        
        
        //display 3 column format of electoral vote status
        System.out.println(election.toString());
        
        System.out.println("What is your target number of RED electoral votes: ");
        target = keyboard.nextLine();
        
        try {
            parseTarget = Integer.parseInt(target);
            if (parseTarget < 0 || parseTarget > 538) {
                System.out.println("Invalid target number");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid target number");
            return;
        }
        
        
        
        System.out.println("Which method do you want to test? (FJ or Seq) ");
        
        method  = keyboard.nextLine();
        
        if (method.equals("FJ")) {
            //VoteFinder
            vote = new VoteFinder(election, parseTarget);
            startTime = System.currentTimeMillis();
            try {
                ForkJoinPool.commonPool().invoke(vote);
            } catch(IndexOutOfBoundsException e) {
                System.out.println("*Problem forking*");
                return;
            }
            endTime = System.currentTimeMillis();
            //need to figure out what to use for VoteFinder
            System.out.println("Found " + vote.getSolutionList().size() +  " solutions");
            System.out.println("in " + (endTime - startTime)/1000.0 + " seconds");
            System.out.println("One example solution: ");
            
            try {
                System.out.println(vote.getSolutionList().get(0).toString());
            } catch(IndexOutOfBoundsException e) {
                System.out.println("No answer");
                return;
            }
        } else if (method.equals("Seq")) {
            
            startTime = System.currentTimeMillis();
            try {
                electionList = Election.findTargetElections(election, parseTarget);
            } catch (NullPointerException e) {
                //empty ArrayList
                System.out.println("Problem executing code");
                return;
            } 
            
            endTime = System.currentTimeMillis();
            //find solutions
            System.out.println("Found " + electionList.size() +  " solutions");
            System.out.println("in " + (endTime - startTime)/1000.0 + " seconds");
            System.out.println("One example solution: ");
            
            try {
                System.out.println(electionList.get(0).toString());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No answer");
                return;
            }
            
        } else {
            System.out.println("Invalid option");
            //not valid option
            return;
        }
        
    }

}
