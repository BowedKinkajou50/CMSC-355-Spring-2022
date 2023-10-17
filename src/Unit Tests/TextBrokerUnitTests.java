package Sprint2;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JUnitTestTB {
    /*
    Checks whether it can take in a language file and
    successfully output the correct word in correlation
    (chat)
     */
    @Test
    public void testMainMethod() throws FileNotFoundException {
        File file = new File("french.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",cat";
        TextBroker.main(arguments);
    }
    /*
    Checks whether it can deal with a
    word not inside the language file
     */
    @Test
    public void testMainMethod2() throws FileNotFoundException {
        File file = new File("french.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",hate";
        TextBroker.main(arguments);
    }
    /*
    Checks whether it can deal with an unavailable language file
     */
    @Test
    public void testMainMethod3() throws FileNotFoundException {
        File file = new File("french2.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",cat";
        TextBroker.main(arguments);
    }
    /*
    Checks whether it can successfully pull the right tax bracket
     */
    @Test
    public void testMainMethod4() throws FileNotFoundException {
        File file = new File("2021S.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",300000,>=";
        TextBroker.main(arguments);
        //should be 0.35
    }
    /*
    Checks whether it can successfully print the "over" tax bracket
     */
    @Test
    public void testMainMethod5() throws FileNotFoundException {
        File file = new File("2021S.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",600000,>=";
        TextBroker.main(arguments);
    }
    /*
    Checks whether it can see that the tax file is not real
     */
    @Test
    public void testMainMethod6() throws FileNotFoundException {
        File file = new File("2021SS.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",300000, >=";
        TextBroker.main(arguments);
    }
    /*
    Checks whether it can pull the right string according to error code
     */
    @Test
    public void testMainMethod7() throws FileNotFoundException {
        File file = new File("msgEng.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",404";
        TextBroker.main(arguments);
    }
    /*
    Checks to see if module can see that the file is not real
     */
    @Test
    public void testMainMethod8() throws FileNotFoundException {
        File file = new File("msgEng3.txt");
        String filename = file.getName();
        String[] arguments = new String[1];
        arguments[0] = filename + ",903";
        TextBroker.main(arguments);
    }
}
