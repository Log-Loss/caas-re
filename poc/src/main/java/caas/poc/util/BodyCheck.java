package caas.poc.util;

import java.util.Map;

public class BodyCheck {
    public static Boolean check(Map body, String... args) {
        for (String arg : args) {
            if (!body.containsKey(arg))
                return false;
        }
        return true;
    }
}
