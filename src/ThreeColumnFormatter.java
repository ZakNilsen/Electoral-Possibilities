
import java.util.Scanner;

/**
 * Class to allow easy printing of data in three-column format.
 * 
 * @author Prof. Scott Yilek for use in CISC 230
 *
 */
public class ThreeColumnFormatter {
    
    /** How wide each column should be, in characters */
    private int columnWidth;
    /** A single String for the entire column one, so likely containing many newline characters */
    private String columnOne;
    /** A single String for the entire column two, so likely containing many newline characters */
    private String columnTwo;
    /** A single String for the entire column three, so likely containing many newline characters */
    private String columnThree;
    
  
    
    /** Constructor taking all of the information needed to construct a three-column String
     * 
     * @param one The left column's multi-line String
     * @param two The middle column's multi-line String
     * @param three The right column's multi-line String
     * @param width How wide in characters each column should be in the final String
     */
    public ThreeColumnFormatter(String one, String two, String three, int width) {
        columnOne = one;
        columnTwo = two;
        columnThree = three;
        columnWidth = width;

    }
    
    /** Method to combine the three columns into a single String
     * 
     * @return A single string with all three columns of information included and spaced appropriately
     */
    public String getString() {
        // Scanner can be used on Strings, not just files or input streams
        Scanner left = new Scanner(columnOne);
        Scanner middle = new Scanner(columnTwo);
        Scanner right = new Scanner(columnThree);
        String retString="";
        
        // some columns might have more lines, so continue as long as any of them have more
        while (left.hasNextLine() || middle.hasNextLine() || right.hasNextLine()) {
            String leftLine;
            String middleLine;
            String rightLine;
            // if a column is out of lines, just print empty string in its place
            if (left.hasNextLine()) {
                leftLine = left.nextLine();
            } else {
                leftLine = "";
            }
            if (middle.hasNextLine()) {
                middleLine = middle.nextLine();
            } else {
                middleLine = "";
            }
            if (right.hasNextLine()) {
                rightLine = right.nextLine();
            } else {
                rightLine = "";
            }
            
            retString += String.format("%-"+columnWidth+"."+columnWidth+"s  %-"+columnWidth+"."+columnWidth+"s  %-"+columnWidth+"."+columnWidth+"s %n", leftLine, middleLine, rightLine);
        }
        return retString;
               
    }

}