import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import EntryPoint.EntryPoint;
import ScriptEngine.PythonScriptWriter;
import ScriptEngine.PythonCommandCreator;
/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class test {

    private static EntryPoint entryPoint = EntryPoint.getInstance();
    private static PythonScriptWriter psw = new PythonScriptWriter();
    private static PythonCommandCreator pcc = new PythonCommandCreator();

    private final static String fileContent = "import numpy as np\n" +
            "a = np.arange(15).reshape(3, 5)\n" +
            "print(a)\n" +
            "print(variables[\"test0\"]+\" test success\")";

    public static void main(String[] args) throws IOException {
        entryPoint.gateWayServerStart();
        File pythonFile = psw.writeFileToDisk(fileContent);
        String command = pcc.createPythonExecutionCommand(pythonFile, "python3");
        System.out.println(command);

        try{
            Process process = Runtime.getRuntime().exec(command);
            InputStream fis = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            if(process.exitValue() == 0){
                System.out.println("Python ScriptEngine Execution OK");
            }else{
                System.out.println("Python ScriptEngine Execution ERROR");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            entryPoint.gateWayServerStop();
            System.exit(-1);
        }

    }
}