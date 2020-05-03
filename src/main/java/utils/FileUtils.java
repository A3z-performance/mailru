package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static String getPassword(String filePath)
    {
        String sCurrentLine = "no password";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            sCurrentLine = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return sCurrentLine;
    }
}
