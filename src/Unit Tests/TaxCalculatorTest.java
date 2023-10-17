import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TaxCalculatorTest {
    /*
    Tests the 2020S.txt file
     */
    @Test
    public void testMainMethod() throws IOException, InterruptedException {
        File file = new File("2020S.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = "2020,S,40000";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2020S.txt file
     */
    @Test
    public void testMainMethod2() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2020,S,10";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2020J.txt file
     */
    @Test
    public void testMainMethod3() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2020,J,40000";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2020H.txt file
     */
    @Test
    public void testMainMethod4() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2020,H,40000";
        TaxCalculator.main(arguments);
        //should be 0.35
    }
    /*
    Tests the 2021S.txt file
     */
    @Test
    public void testMainMethod5() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2021,S,40000";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2021J.txt file
     */
    @Test
    public void testMainMethod6() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2021,J,40000";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2021H.txt file
     */
    @Test
    public void testMainMethod7() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2021,H,40000";
        TaxCalculator.main(arguments);
    }
    /*
    Tests the 2021S.txt file
     */
    @Test
    public void testMainMethod8() throws IOException, InterruptedException {
        String[] arguments = new String[1];
        arguments[0] = "2021,S,400";
        TaxCalculator.main(arguments);
    }
}
