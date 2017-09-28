package ScriptEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import EntryPoint.EntryPoint;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class PythonScriptExecutor {

    public void pythonScriptExecutor(String command) throws IOException{
        EntryPoint entryPoint = EntryPoint.getInstance();
        entryPoint.gateWayServerStart();
        try{
            Process process = Runtime.getRuntime().exec(command);

            //To show the result
            InputStream fis = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            //To see the execution status
            if(process.exitValue() == 0){
                System.out.println("Python ScriptEngine Execution OK");
            }else{
                System.out.println("Python ScriptEngine Execution ERROR");
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally{
            //To shutdown the gateway and exist the JVM
            entryPoint.gateWayServerStop();
            System.exit(0);
        }
    }

}
