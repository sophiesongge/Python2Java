package ScriptEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class PythonCommandCreator {

    private static final String PYTHON2_COMMAND = "python";

    private static final String PYTHON3_COMMAND = "python3";

    /**
     * This method is used to create a bash command which executes a python script with a given python file
     * @param pythonFile
     * @param pythonVersion the version of Python (either 2 or 3)
     * @return A String which contains the command
     */
    public String createPythonExecutionCommand(File pythonFile, String pythonVersion) {
        StringBuffer sb = new StringBuffer();

        //Add Python Command
        if(pythonVersion.equals("python3".toLowerCase())) {
            sb.append(PYTHON3_COMMAND);
            sb.append(" ");
        }else{
            sb.append(PYTHON2_COMMAND);
            sb.append(" ");
        }

        //Add the file path
        sb.append(pythonFile.getPath());

        return sb.toString();
    }

}
