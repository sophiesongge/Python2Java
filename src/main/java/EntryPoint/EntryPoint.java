package EntryPoint;

import java.util.HashMap;
import java.util.Map;

import Context.Variables;
import py4j.GatewayServer;

/**
 * @author Sophie Song
 * @since 27/09/2017
 */
public class EntryPoint {
    /**
     * Singleton
     */
    private static EntryPoint ourInstance = new EntryPoint();

    private Map variabels = new HashMap();

    private EntryPoint() {}

    public static EntryPoint getInstance() {
        return ourInstance;
    }

    //private Variables variables = Variables.getInstance();

    public Map getVariables(){
        return variabels;
    }

    //Start gateway server
    public void gateWayServerStart(){
        GatewayServer gatewayServer = new GatewayServer(this.getInstance(), 25335);
        gatewayServer.start();
        System.out.println("GateWay Server Started");
    }

    //Stop gateway server
    public void gateWayServerStop(){
        GatewayServer gatewayServer = new GatewayServer(this.getInstance());
        gatewayServer.shutdown();
        System.out.println("GateWay Server Stopped");
    }

}
