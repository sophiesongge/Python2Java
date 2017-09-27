package Context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class Variables {
    private static Variables ourInstance = new Variables();

    public static Variables getInstance() {
        return ourInstance;
    }

    private Variables() {
    }

    private Map<String, String> variables = new HashMap<>();


    public Map<String, String> getWorkflowVariables() {
        this.insertVariables("test0", "abc");
        this.insertVariables("test1", "def");
        return variables;
    }

    public void insertVariables(String key, String value){
        this.variables.put(key, value);
    }
}
