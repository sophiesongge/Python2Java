import java.util.ArrayList;
import java.util.List;

import py4j.GatewayServer;

/**
 * @author Sophie Song
 * @since 13/10/2017
 */
public class ListenerApplication {

    List<ExampleListener> listeners = new ArrayList<>();

    public void registerListener(ExampleListener listener) {
        listeners.add(listener);
    }

    public void notifyAllListeners() {
        for (ExampleListener listener : listeners) {
            Object returnValue = listener.notify(this);
            System.out.println(returnValue);
        }
    }

    @Override
    public String toString() {
        return "<ListenerApplication> instance";
    }

    public static void main(String[] args) {
        ListenerApplication application = new ListenerApplication();
        GatewayServer server = new GatewayServer(application);
        server.start(true);
    }

}
