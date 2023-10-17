import java.io.*;

public class TranslatorStub{
    //This stub3 is used to see if the other modules can access the string data from other modules.
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] passThrough = new String[2];
        passThrough[0] = "java -jar Translator.jar french,cat";
        Process proc = Runtime.getRuntime().exec(passThrough);
        BufferedReader inStream = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String translated = inStream.readLine();
        translated = translated.trim();
        System.out.println(translated);
        String data = "This is a line of text inside the file coming out of TranslatorStub";
        System.out.print(data);
    }
}