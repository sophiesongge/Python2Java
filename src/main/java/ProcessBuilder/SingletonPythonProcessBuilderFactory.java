package ProcessBuilder;

/**
 * @author Sophie Song
 * @since 02/10/2017
 */
public class SingletonPythonProcessBuilderFactory implements PythonProcessBuilderFactory{
    private static SingletonPythonProcessBuilderFactory ourInstance = new SingletonPythonProcessBuilderFactory();

    public static SingletonPythonProcessBuilderFactory getInstance() {
        return ourInstance;
    }

    private SingletonPythonProcessBuilderFactory() {
    }

    @Override
    public ProcessBuilder getProcessBuilder(String... command) {
        return new ProcessBuilder(command);
    }
}
