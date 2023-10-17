import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import java.util.*;

/**************************************************************
 * Service Broker
 *
 * This service broker module receives a parameter list in args[0] and a service code in args[1].
 * It looks up the corresponding filepath to the service code in Service.txt. It then calls that
 * file and passes along parameter list as args[0].
 *
 * If Service.txt is not found, or if the correct service is not found within Service.txt, Service
 * Broker will call Error module and pass the appropriate error code in args[0].
 *
 * Tyler Benson
 * CMSC355
 * Last Revised 4/21/2022
 *
 *
 *
 *
 *
 **************************************************************/
public class ServiceBroker {
    public static void main(String args[]) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);
        File file = new File("Service.txt");

        try{
            /******************************************
            *Read Service.txt into a list line by line
             ******************************************/
            Scanner fileReader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (fileReader.hasNextLine()){
                list.add(fileReader.nextLine());
                //System.out.println("Line read\n");
            }
            //System.out.println("Service.txt read successfully\n");
            /***********************************************************************************************
            *If any element of the list contains the service code in args[1], save that element to filePath and update flag
             *********************************************************************************************/
            String filePath = null;
            boolean serviceFoundFlag = false;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).contains(args[1])){
                    filePath = list.get(i);
                    serviceFoundFlag = true;
                    //System.out.println("Service looked up successfully\n");
                    //System.out.println("Filepath: " + filePath + "\n");

                }
            }

            /******************************************************************************
             * If the filepath is found, isolate just the filepath and call that service and
             * pass along paramlist as args[0]. Read back called function standard input and print it
             *****************************************************************************/
            if(serviceFoundFlag) {
                //take only the part of the string after the first comma
                filePath = filePath.substring(filePath.indexOf(",")+1);
                filePath = filePath.replaceAll("\"", "");


                Process proc2 = Runtime.getRuntime().exec(filePath + " " + args[0]);

                BufferedReader inStream = null;

                // read from the called program's standard output stream
                try
                {
                    inStream = new BufferedReader(new InputStreamReader(proc2.getInputStream()));
                    System.out.println(inStream.readLine());
                }
                catch(IOException e)
                {
                    System.err.println("Error on inStream.readLine()");
                    e.printStackTrace();
                }
            }
            /**************************************************************************
             * If the service filepath is not found, call Error module with code 703. Read back called
             * function standard input and print it
             **************************************************************************/
            else{
                Process proc = Runtime.getRuntime().exec("java -jar Error.jar" + " 703");
                BufferedReader inStream = null;

                // read from the called program's standard output stream
                try
                {
                    inStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    System.out.println(inStream.readLine());
                }
                catch(IOException e)
                {
                    System.err.println("Error on inStream.readLine()");
                    e.printStackTrace();
                }
            }
        }
        /*************************************************************************************************************
        //If Service broker cannot find Service.txt, call Error and give it error code 404. Store whatever String is
         returned in output and then return output.
         *************************************************************************************************************/
        catch (FileNotFoundException e){
            System.out.println("catch");
           Process proc = Runtime.getRuntime().exec("java -jar Error.jar" + " 404");

            BufferedReader inStream = null;

            // read from the called program's standard output stream
            try
            {
                inStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                System.out.println(inStream.readLine());
            }
            catch(IOException f)
            {
                System.err.println("Error on inStream.readLine()");
                e.printStackTrace();
            }
        }
    }
}
