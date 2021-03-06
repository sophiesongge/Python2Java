import java.util.ArrayList;
import java.util.List;

import py4j.GatewayServer;

public class StackEntryPoint {
    private Stack stack;

    public StackEntryPoint() {
        stack = new Stack();
        stack.push("Initial Item");
        stack.push("1");
    }

    public Stack getStack() {
        return stack;
    }

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
        GatewayServer server = new GatewayServer(new StackEntryPoint(), 25335);
        server.start(true);
        System.out.println("Gateway Server Started");
    }
}
