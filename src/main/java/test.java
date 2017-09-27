import EntryPoint.EntryPoint;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class test {

    private static EntryPoint entryPoint = EntryPoint.getInstance();

    private final static String fileContent = "import numpy as np\n" +
            "a = np.arange(15).reshape(3, 5)\n" +
            "print(a)\n" +
            "print(variables[\"test0\"]+\" test success\")";

    public static void main(String[] args){
        entryPoint.gateWayServerStart();
    }

}
