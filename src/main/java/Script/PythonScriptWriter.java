package Script;
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

    public static final String PYTHON_FILE_PATH = "/Users/sophiesong/Documents/workspace/activeeon/test.py ";

    public File forceFileToDisk(String fileContent) throws IOException {
        File perlTempFile = null;
        try {
            perlTempFile = File.createTempFile("jsr223-cpython-", PYTHON_FILE_EXTENSION);
        } catch (IOException e) {
            throw new IOException("Unable to create perl temp file. " + e);
        }

        // Force perl script file to disk
        Writer perlScriptFileWriter = new FileWriter(perlTempFile);
        perlScriptFileWriter.write(fileContent);
        perlScriptFileWriter.close();

        return perlTempFile;
    }
}
