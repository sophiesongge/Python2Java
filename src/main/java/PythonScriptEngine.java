import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Map;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import ProcessBuilder.Utils.PythonProcessBuilderUtilities;
import ScriptEngine.PythonScriptWriter;
import ScriptEngine.PythonCommandCreator;
import ProcessBuilder.SingletonPythonProcessBuilderFactory;
import Bindings.PythonBindingsAdder;

/**
 * @author Sophie Song
 * @since 02/10/2017
 */
public class PythonScriptEngine extends AbstractScriptEngine {

    public static final String EXIT_VALUE_BINDING_NAME = "EXIT_VALUE";

    public static final String VARIABLES_BINDING_NAME = "variables";

    private PythonProcessBuilderUtilities processBuilderUtilities = new PythonProcessBuilderUtilities();

    private PythonScriptWriter pythonScriptWriter = new PythonScriptWriter();

    private PythonCommandCreator pythonCommandCreator = new PythonCommandCreator();

    private PythonBindingsAdder pythonBindingsAdder = new PythonBindingsAdder();

    public PythonScriptEngine() {

    }


    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {

        File pythonFile = null;

        try {
            pythonFile = pythonScriptWriter.writeFileToDisk(script);
        }catch (IOException e) {
            //TODO add logs
            System.out.println("Failed to write content to python file: "+e);
        }

        //Create Python Command
        //TODO change here to automatically choose python version
        String pythonCommand = pythonCommandCreator.createPythonExecutionCommand(pythonFile, "python3");

        //Create a process builder
        ProcessBuilder processBuilder = SingletonPythonProcessBuilderFactory.getInstance().getProcessBuilder(pythonCommand);

        //Use process builder environment and fill it with environment variables
        Map<String, String> variableMap = processBuilder.environment();

        //Add Bindings as environment varibales
        pythonBindingsAdder.addBindingsToMap(context.getBindings(ScriptContext.ENGINE_SCOPE), variableMap);

        Process process = null;

        try{
            //Start process
            process = processBuilder.start();

            //Attach streams
            processBuilderUtilities.attachStreamsToProcess(process, context.getWriter(), context.getErrorWriter(), context.getReader());

            //Wait for process to exit
            int exitValue = process.waitFor();

            if(context.getBindings(ScriptContext.ENGINE_SCOPE).containsKey(VARIABLES_BINDING_NAME)) {
                Map<String, Serializable> variables = (Map<String, Serializable>) context.getBindings(ScriptContext.ENGINE_SCOPE).get(VARIABLES_BINDING_NAME);
                variables.put(EXIT_VALUE_BINDING_NAME, exitValue);
            }

            context.getBindings(ScriptContext.ENGINE_SCOPE).put(EXIT_VALUE_BINDING_NAME, exitValue);

            if(exitValue != 0) {
                throw new ScriptException("Python process execution has failed with exit code " + exitValue);
            }
            return exitValue;

        } catch (IOException e) {
           throw new ScriptException("Check if Python is installed properly. Failed to execut Python with exception: " + e);
        } catch (InterruptedException e1) {
            //TODO add logs
            //e1.printStackTrace();
            if(process != null) {
                process.destroy();
            }
        } finally {
            if(process != null) {
                try{
                    process.waitFor();
                } catch (InterruptedException e) {
                    //TODO add logs "Python execution was not finished correctly after the interruption. " + e.getMessage();
                }
            }

            //Delete pythonFile
            if(pythonFile != null) {
                boolean deleted = pythonFile.delete();
                if(!deleted) {
                    //TODO add logs "File: " + pythonFile.getAbsolutePath() + " was not deleted."
                }
            }
        }
        return null;
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {

        StringWriter stringWriter = new StringWriter();

        try {
            PythonProcessBuilderUtilities.pipe(reader, stringWriter);
        } catch (IOException e) {
            //TODO add logs
            //log.warn("Filed to convert Reader into StringWriter. Not possible to execute Python script.");
            //log.debug("Filed to convert Reader into StringWriter. Not possible to execute Python script.", e);
        }

        return eval(stringWriter.toString(), context);
    }
    @Override
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return new PythonScriptEngineFactory();
    }
}
