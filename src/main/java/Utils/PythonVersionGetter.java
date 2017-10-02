package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Sophie Song
 * @since 02/10/2017
 */
public class PythonVersionGetter {

    public static final String PYTHON_VERSION_IF_NOT_INSTALLED = "Could not determine version";

    private String PYTHON_VERSION_COMMAND = " --version"; // this command is needed to retrieve only specific string with python version


    /**
     * Retrieves the Python version
     *
     * @return The currently installed version return by the python command or an string indicates that the version could not be determined.
     */
    public String getPythonVersion(String python2or3) {

        String result = PYTHON_VERSION_IF_NOT_INSTALLED; //Default error string for result if version recovery fails

        if(python2or3.toLowerCase().equals("python3")){
            PYTHON_VERSION_COMMAND = "python3" + PYTHON_VERSION_COMMAND;
        } else {
            PYTHON_VERSION_COMMAND = "python2" + PYTHON_VERSION_COMMAND;
        }

        try{
            Process process = Runtime.getRuntime().exec(PYTHON_VERSION_COMMAND);

            //To show the result
            InputStream fis = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                //TODO rewrite the restul
                result = line;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
