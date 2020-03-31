package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MailUtils {
    public static boolean checkMailMsg(String msg, String containerMsg){
        return msg.contains(containerMsg);
    }

    public static String currentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
