/*****************************************************************************************************************
 * TextBroker Module
 * Component: Utility Services
 * ---------------------------------------------------------------------------------------------------------------
 * Function:  Opens file given by translator, tax, or error module and find the corresponding match to their needs
 *----------------------------------------------------------------------------------------------------------------
 *    Input:   Parameters - TxtFile, word/number, >= [for comparison]
 *    Output:  Return – String containing a word or number
 *----------------------------------------------------------------------------------------------------------------
 *    Author: Mandy Lin
 *    Review:
 *    Version 04/21/2022   CMSC 355
 *****************************************************************************************************************/
package Sprint2;
import java.io.*;
import java.util.*;
public class TextBroker {
    public static void main (String[] args) throws FileNotFoundException {
        String param = args[0];
        String[] array = param.split(",");
        String lookup = array[1]; //word or number
        String filegiven = array[0]; //file given
        String out = "";
        String line = "";
        boolean flag = false;
        File file = new File(filegiven);
        boolean correctFile = isFile(file); //checks for validity of file
        if (!correctFile) {
            flag = true;
            out = "404"; //not found
        }
        else {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine() && !flag) {
                line = scan.nextLine();
                String[] numArray = line.split(",");
                if (array[array.length - 1].contains(">=")) {
                    /*********************************************************
                     made to find a value greater than or equal to given value
                     *********************************************************/
                    for (int i = 0; i < numArray.length - 1; i = i + 2) {
                        if (!(numArray[i].equals("Over"))) {
                            if (Double.parseDouble(numArray[i]) >= Double.parseDouble(lookup)) {
                                flag = true;
                                out = numArray[i + 1];
                            }
                        }
                        else if (numArray[i].equals("Over")) {
                            flag = true;
                            out = numArray[i + 1];
                        }
                    }
                }
                else {
                    /***********************************************************************************************
                     made to find a matching term/number, just for safety, everything becomes lowercase, if possible
                     ***********************************************************************************************/
                    if (line.toLowerCase().contains(lookup.toLowerCase())) {
                        flag = true;
                        int comma = line.indexOf(",");
                        out = (line.substring(comma + 1));
                    }
                    else {
                        out = "813"; //word not found
                        flag = true;
                    }
                }
            }
            scan.close();
        }
        System.out.print(out);
    }
    /**************************
     Checks whether file is real
     **************************/
    public static boolean isFile (File tryFile) {
        return tryFile.exists();
    }
}
