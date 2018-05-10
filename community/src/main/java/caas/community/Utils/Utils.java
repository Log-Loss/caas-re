package caas.community.Utils;

public class Utils {


    public static String getRandomString(int length) {

        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    public static String generateRandomId() {
        return getRandomString(10);
    }

    public static Long getCurrentTime() {
        return System.currentTimeMillis();

    }

    // test utils here
    public static void main(String args[]) {

    }
}