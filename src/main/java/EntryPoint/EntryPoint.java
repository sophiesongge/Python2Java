package EntryPoint;

import java.util.Map;

import Context.Variables;
import py4j.GatewayServer;

/**
 * @author ActiveEon Team
 * @since 27/09/2017
 */
public class EntryPoint {
    /**
     * Singleton
     */
    private static EntryPoint ourInstance = new EntryPoint();

    private EntryPoint() {}

    public static EntryPoint getInstance() {
        return ourInstance;
    }

    private Variables variables = Variables.getInstance();

    public Map getVariables(){
        return variables.getWorkflowVariables();
    }

    public void gateWayServerStart(){
        GatewayServer gatewayServer = new GatewayServer(this.getInstance());
        gatewayServer.start();
        System.out.println("GateWay Server Started");
    }

    public void gateWayServerStop(){
        GatewayServer gatewayServer = new GatewayServer(this.getInstance());
        gatewayServer.shutdown();
        System.out.println("GateWay Server Stopped");
    }

}
