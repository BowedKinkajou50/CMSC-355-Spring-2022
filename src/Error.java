import java.io.*;
import java.util.Scanner;
/*************************************************************************************
 * Error Message Module
 * Component: Utility Layer
 *************************************************************************************
 * Function:
 *      The Service Broker (SB) will have called this error module, providing the
 *      error message code as args[0].
 *
 *      This error module will, in turn, open Option.txt to identify the error
 *      language specified. This module will set the service code to "TB" (Text
 *      Broker), and will call the SB with a paramList (containing the respective
 *      error language file name and error message code) and the service code ("TB").
 *------------------------------------------------------------------------------------
 * Main method input:
 *      String errorMessageCode as args[0] from the Service Broker
 * Output:
 *      Calls Service Broker with paramList (error language file, error code) and
 *          serviceCode ("TB").
 *      Sends back an output string to the Service Broker.
 *------------------------------------------------------------------------------------
 * @author: Mariam Topchyan
 * @version 4/21/2022        CMSC 355
 *************************************************************************************/
public class Error {

   public static void main(String[] args) throws InterruptedException, IOException {

       /**-----------------------------------------------------------------------
        * Declare variable:
        *   errorMessageCode - Error message code passed in by the Service Broker
        *-----------------------------------------------------------------------*/
       String errorMessageCode = args[0];       // Obtain the error message code from the first argument passed in by the Service Broker

       /**---------------------------------------------------------------------
        * Declare variable:
        *    serviceCode - Will tell the Service Broker which module to call;
        *                  Set to "TB" so that the SB will call the Text Broker.
        *---------------------------------------------------------------------*/
       String serviceCode = "TB";

       /**-----------------------------------------------------
        * Open Option.txt to identify error language specified
        *-----------------------------------------------------*/
       File optionFile = new File("Option.txt");       // Create new File object
       Scanner input = new Scanner(optionFile);        // Create new Scanner
       String optionString = "";
       if (input.hasNextLine()) {
            optionString = input.nextLine();           // Read in the first line of Option.txt
       }
       String[] stringArray = optionString.split(",");    // Separate the line into an array of strings, split by comma

       /**-----------------------------------------------------------------------------------------------------------------
        * Declare variable:
        *   errorFileName - represents the language-specific error message file name in Option.txt (i.e. "MSGEnglish.txt")
        *-----------------------------------------------------------------------------------------------------------------*/
       String errorFileName = "";
       if (stringArray[0].equals("Msg")) {
            errorFileName = stringArray[1];                          // Set errorFileName to the file name in Option.txt
            errorFileName = errorFileName.replaceAll("\"", "");      // Get rid of quotation marks around the file name
       }

       /**------------------------------------------------------------------------------------
        * Declare variable:
        *   paramList - String containing the error message file name and error message code;
        *               Will be passed into the Service Broker, alongside serviceCode.
        *-------------------------------------------------------------------------------------*/
       String paramList = errorFileName + "," + errorMessageCode;

       /**--------------------------------------------------------------------
        *                               * UNIT TEST *
        * Checks to see if paramList was compiled into a String correctly.
        * If args[0] (errorMessageCode) == "404", paramList will be:
        *   "MSGEnglish.txt,404"
        *-------------------------------------------------------------------*/
       System.out.println("paramList test: " + paramList);

       try {

           /**-------------------------------------------------------------------------------
            *                          * UNIT TEST *
            * Tests to see if Error module can reach a dummy Service Broker.
            * ServiceBrokerUnitTest.jar located in same directory as Error.jar when testing
            * "MSGEnglish.txt,404" represents a possible paramList.
            * Prints canned result of "Not found"
            *------------------------------------------------------------------------------*/
           Process testProc = Runtime.getRuntime().exec("java -jar ServiceBrokerUnitTest.jar" + " " + "MSGEnglish.txt,404" + serviceCode);
           testProc.waitFor();


           /**------------------------------------------------
            * Call the SB with the paramList and serviceCode
            *------------------------------------------------*/
           Process proc = Runtime.getRuntime().exec("java -jar ServiceBroker.jar" + " " + paramList + " " + serviceCode);
           proc.waitFor();

           /**----------------------------------------------
            * Send back output string to the Service Broker
            *----------------------------------------------*/
           BufferedReader inStream = null;
           try {
               // Read from the called program's standard output stream
               inStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));  
               System.out.println(inStream.readLine());
           }
           catch (IOException e) {
               System.err.println("Error on inStream.readLine()");
               e.printStackTrace();
           }

       }
       catch (InterruptedException exception) {
           System.out.println("Connection to service broker interrupted.");
       }
    }

}

