package ScriptEngine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class PythonScriptWriter {
    //Extension
    public static final String PYTHON_FILE_EXTENSION = ".py";

    public File writeFileToDisk(String fileContent) throws IOException {
        File pythonTempFile = null;
        try {
            pythonTempFile = File.createTempFile("jsr223-cpython-", PYTHON_FILE_EXTENSION);
        } catch (IOException e) {
            throw new IOException("Unable to create python temp file. " + e);
        }

        // Write python script file to disk
        Writer pythonScriptFileWriter = new FileWriter(pythonTempFile);
        pythonScriptFileWriter.write("from py4j.java_gateway import JavaGateway"+"\n");
        pythonScriptFileWriter.write("gateway = JavaGateway()"+"\n");
        pythonScriptFileWriter.write("variables = gateway.entry_point.getVariables()"+"\n");
        pythonScriptFileWriter.write(fileContent);
        pythonScriptFileWriter.close();

        return pythonTempFile;
    }
}
