import EntryPoint.EntryPoint;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class test {

    private static EntryPoint entryPoint = EntryPoint.getInstance();

    public static void main(String[] args){
        entryPoint.gateWayServerStart();
    }

}
