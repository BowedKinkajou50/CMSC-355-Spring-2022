/* *************************************************************************************
 *   Translator Module
 *
 * Component: Task Manager
 * *********************************************************************************** *
 * Function:  Translates given word into specified language.
 *
 *----------------------------------------------------------------------------------------------------------------------------------------
 *    Input:   Parameters – Language, Word
 *
 *    Output:  Return – Translated Word
 *
 *----------------------------------------------------------------------------------------------------------------------------------------
 *    Author: Even Pham
 *    Review:
 *    Version 04/22/2022   CMSC 355
 * *********************************************************************************** */
import java.io.*;
import java.util.Scanner;
public class Translator2
{

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] param = args[0].split(","); //splits the param list into an array for ease of access.
        //param list contains of (language,English_word)
        String language = param[0].toLowerCase(); // Labeling the language part of the array
        String word = param[1]; //labeling the english word of the array
        String textFile = "";
        //flagLanguage is true when a line in the translator.txt has that language, and it has a language file
        boolean flagLanguage = false;
        File file2 = new File("translator.txt");
        Scanner file = new Scanner(file2);                //translator.txt has all the languages inside
        while(file.hasNextLine() && flagLanguage == false)//search through the translator.txt to find the language
        {
            String line = file.nextLine();
            int comma = line.indexOf(',');
            if((line.toLowerCase()).contains(language))
            {
                textFile = (line.substring(comma+1)).trim();
                flagLanguage = true;
            }
        }
        file.close();



        //if the languague isn't found go to error
        if(flagLanguage == false){
            Process proc2 = Runtime.getRuntime().exec("java -jar ServiceBroker.jar" + " 805,Error");
            BufferedReader ErrorInStream = null;
            ErrorInStream = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
            String Error = ErrorInStream.readLine().trim();
            System.out.println(Error);
        }
        else{
            //build the string to pass through to the textbroker through servicebroker.
             String passThrough = textFile +"," + word+",TB";
             //when testing , Process proc = Runtime.getRuntime().exec("java -jar stub3.jar " + passThrough);
            Process proc = Runtime.getRuntime().exec("java -jar ServiceBroker.jar " + passThrough);

            BufferedReader inStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String translated = inStream.readLine();
            translated = translated.trim();

            //if the Textbroker finds an error then this would go to Error
            if(isNumeric(translated)){
                Process proc3 = Runtime.getRuntime().exec("java -jar ServiceBroker.jar"+" "+
                        translated+",Error");

                BufferedReader ErrorStringReader = null;
                ErrorStringReader = new BufferedReader(new InputStreamReader(proc3.getInputStream()));
                String ErrorString = ErrorStringReader.readLine();
                ErrorString = ErrorString.trim();
                proc3.waitFor();
                System.out.println(ErrorString);
            }
            else
             System.out.println(translated);
        }
    }

    //see if a String is a number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}