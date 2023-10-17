/* Component: Business Services
** Module Name: Tax Calculator
** Author: Gabrielle Cornelius
** Inputs: paramlist string: Tax year, Status, Gross
** Process Description: Modifies ParamList to concatenate
   taxYear and Status into taxYear-Status-File.
** Output: taxYear-Status-File
 */
import java.io.*;
import java.util.*;
public class TaxCalculator {
    public static void main(String[] args) throws IOException, InterruptedException {
        //accepts string from command line
        String paramlist = args[0];
        //Splits string into year, status, and income
	    String[] sep = paramlist.split(",");
	    String year = sep[0];
        String status = sep[1].toUpperCase();
	    double income = Double.parseDouble(sep[2]);
	    boolean exist = false;
	    String txt = "";
	    //created an object wih all the files in it
        File file = new File("tax.txt");
        //Combined the year and status so we know which file to open
        String statYear =  year + status;
        //Reads in the tax.txt file
        Scanner fileReader = new Scanner(file);

        //Reads through each line in tax.txt
        while(fileReader.hasNextLine()){
            String line = fileReader.nextLine();
                int comma = line.indexOf(',');
                //checks to see if the file name matches any names in tax.txt
                if (statYear.equals(line.substring(0, comma))) {
                    //gets the name of the file we need to open
                    txt = line.substring(comma + 1).trim();
                    exist = true;
			/*calls taxCalculator to calculate the taxes owed passing in the needed tax
            file name and the income of the user*/
                    rate = String.format("%.2f",taxCalculator(txt,income));

                }
                
        }
        //if the file does not exist send to error
        if(!exist){
            Process p = Runtime.getRuntime().exec("java -jar ServiceBroker.jar"+ "404,Error");
            BufferedReader ErrorStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String Error = ErrorStream.readLine().trim();
            System.out.println(Error);
            args[0]="404, Error";
            args[1]="TB";
            System.exit(0);
        }


        else{
		//Makes the string that is returned to TB
            String fileReturn = txt +","+ rate +",TB";
            Process p2 = Runtime.getRuntime().exec("java -jar ServiceBroker.jar "+fileReturn);
            p2.waitFor();
            InputStream input = p2.getInputStream();
            byte[] by =new byte[input.available()];
            input.read(by,0,by.length);
            String output = (new String(by));
            System.out.println(output.trim());
            System.out.println(rate);
        }
    }

    //calculates the taxes owed using the required taxFile name and the users income
    public static void taxCalculator(String txt, double gross) throws FileNotFoundException {
            float perc;
            double rate =0;
            //Creates an object of the tax file
            File file2 = new File(txt);
            //Opens and reads in the tax file
            Scanner fileReader2 = new Scanner(file2);
            //Reads through each line
            while (fileReader2.hasNextLine()) {
                String line = fileReader2.nextLine();
                //comma is where the string separates the information
                int comma = line.indexOf(',');
                //Gets the income from the file
                String amount = line.substring(0, comma);
                //gets the percent that needs to be taxed from the file
                float parseFloat = Float.parseFloat(line.substring(comma + 1).trim());
                //Calculates the tax rate for incomes over the 2nd highest income
                if(amount.equals("Over")){
                    perc = parseFloat;
                    rate = perc*gross;
                }
                //Calculates the tax rate
                else if (gross <= Double.parseDouble(amount)){
                    perc = parseFloat;
                    rate = perc * gross;
                }
            }
            fileReader2.close();
            //Rounds to the nearest cent
            System.out.printf("%.2f",rate);

    }

}
