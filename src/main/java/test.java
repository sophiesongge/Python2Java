import java.io.File;
import java.io.IOException;

import ScriptEngine.PythonScriptExecutor;
import ScriptEngine.PythonScriptWriter;
import ScriptEngine.PythonCommandCreator;
/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class test {

    private static PythonScriptWriter psw = new PythonScriptWriter();
    private static PythonCommandCreator pcc = new PythonCommandCreator();
    private static PythonScriptExecutor pse = new PythonScriptExecutor();

    private final static String fileContent = "import numpy as np\n" +
            "a = np.arange(15).reshape(3, 5)\n" +
            "print(a)\n" +
            "print(variables[\"test0\"]+\" test success\")";

    public static void main(String[] args) throws IOException {
        File pythonFile = psw.writeFileToDisk(fileContent);
        String command = pcc.createPythonExecutionCommand(pythonFile, "python3");
        System.out.println(command);

        pse.pythonScriptExecutor(command);
    }
}