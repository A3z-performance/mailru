package utils;

public class MailUtils {
    public static boolean checkMailMsg(String msg, String containerMsg){
        if(msg.length() <= 100){
            return msg.equals(containerMsg);
        } else {
            return msg.substring(0, 100).equals(containerMsg);
        }
    }
}
