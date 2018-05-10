package ac.tec.ic6821.ej3.integration.support;

import java.util.HashMap;
import java.util.Map;

public class RestUtils {

    private static Map<String, String> successMap = null;
    private static Map<String, String> failureMap = null;

    public static Map<String, String> success() {
        if (successMap == null) {
            successMap = new HashMap<String, String>();
            successMap.put("status", "success");
        }
        
        return successMap;
    }

    public static Map<String, String> failure(String message) {
        if (failureMap == null) {
            failureMap = new HashMap<String, String>();
            failureMap.put("status", "failure");
        }

        failureMap.put("message", message);

        return failureMap;
    }
}
