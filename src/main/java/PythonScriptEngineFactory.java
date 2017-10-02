import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;

import Utils.PythonVersionGetter;


/**
 * @author Sophie Song
 * @since 29/09/2017
 */
public class PythonScriptEngineFactory implements ScriptEngineFactory{
    private static final Map<String, String> PARAMETERS = new HashMap<>();

    static{
        String pythonEngineVersion = new PythonVersionGetter().getPythonVersion("python3");
        PARAMETERS.put(ScriptEngine.NAME, "python");
        PARAMETERS.put(ScriptEngine.ENGINE, "python");
        PARAMETERS.put(ScriptEngine.ENGINE_VERSION, pythonEngineVersion);
        PARAMETERS.put(ScriptEngine.LANGUAGE, "python");
        PARAMETERS.put(ScriptEngine.LANGUAGE_VERSION, pythonEngineVersion);
    }

    @Override
    public String getEngineName() {
        return PARAMETERS.get(ScriptEngine.NAME);
    }

    @Override
    public String getEngineVersion() {
        return PARAMETERS.get(ScriptEngine.ENGINE_VERSION);
    }

    @Override
    public List<String> getExtensions() {
        return Arrays.asList("py");
    }

    @Override
    public List<String> getMimeTypes() {
        return Collections.singletonList("applecation/python");
    }

    @Override
    public List<String> getNames() {
        return Arrays.asList(PARAMETERS.get(ScriptEngine.NAME), "python", "Python");
    }

    @Override
    public String getLanguageName() {
        return PARAMETERS.get(ScriptEngine.LANGUAGE);
    }

    @Override
    public String getLanguageVersion() {
        return PARAMETERS.get(ScriptEngine.LANGUAGE_VERSION);
    }

    @Override
    public Object getParameter(String key) {
        return PARAMETERS.get(key);
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        String methodCall = m + " ";
        for (String arg : args) {
            methodCall += arg + " ";
        }
        return methodCall;
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return toDisplay;
    }

    @Override
    public String getProgram(String... statements) {
        return statements[0];
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new PythonScriptEngine();
    }
}
