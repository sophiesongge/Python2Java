package ProcessBuilder;

/**
 * @author Sophie Song
 * @since 02/10/2017
 */
public interface PythonProcessBuilderFactory {
    ProcessBuilder getProcessBuilder(String... command);
}
