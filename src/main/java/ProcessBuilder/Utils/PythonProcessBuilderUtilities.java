package ProcessBuilder.Utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * @author Sophie Song
 * @since 02/10/2017
 */
public class PythonProcessBuilderUtilities {

    /**
     * Creates a thread which will constantly pipe data, only active when new data is available, from a source to an attached sink. After
     * reaching the end of the source stream the @Thread will silently be destroyed.
     *
     * @param source       Data source.
     * @param attachedSink Data sink.
     */
    private void attachToInputStream(final Reader source, final Writer attachedSink) {
        new Thread() {
            public void run() {
                try {
                    pipe(source, attachedSink);
                } catch (IOException ignored) {
                    //The exception is ignored as for native scripts
                }
            }
        }.start();
    }

    /**
     * Pipes all data from a reader (source) to a writer (sink) until an I/O execution occurs or the
     * end of the source is reached.
     *
     * @param from Source of data.
     * @param to   Sink of data.
     * @throws IOException
     */
    public static void pipe(Reader from, Writer to) throws IOException {
        char[] buff = new char[1024];
        int n = from.read(buff);
        while (n != -1) {
            to.write(buff, 0, n);
            to.flush();
            n = from.read(buff);
        }
        from.close();
    }

    /**
     * Attaches standard and error writer as well as input to a process.
     *
     * @param process       Process which to attach Output, Error and Input to.
     * @param processOutput A data sink for the process standard output. If null nothing will ne attached.
     * @param processError  A data sink for the process' error output. If null nothing will be attached.
     * @param processInput  A data source to be streamed to the process. If null nothing will be attached.
     */
    public void attachStreamsToProcess(Process process, Writer processOutput, Writer processError,
                                       Reader processInput) {
        if (processOutput != null) {
            // Attach to std output
            attachToInputStream(new InputStreamReader(process.getInputStream()), processOutput);
        }

        if (processError != null) {
            // Attach error output
            attachToInputStream(new InputStreamReader(process.getErrorStream()), processError);
        }

        if (processInput != null) {
            // Attach process input
            attachToInputStream(processInput, new OutputStreamWriter(process.getOutputStream()));
        }
    }

}
