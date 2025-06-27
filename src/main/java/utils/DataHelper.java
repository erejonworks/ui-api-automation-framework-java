package utils;

public class DataHelper {
    public static String getRandomEmail() {
        return "user" + System.currentTimeMillis() + "@mail.com";
    }
}
