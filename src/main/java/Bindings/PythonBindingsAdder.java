package Bindings;

import java.util.Collection;
import java.util.Map;

import javax.script.Bindings;

/**
 * @author Sophie Song
 * @since 03/10/2017
 */
public class PythonBindingsAdder {

    /**
     * Adds all bindings which are from type @String to the environment map. All other bindings are printed
     * with toString() to log file.
     *
     * @param bindings    Bindings which will be read and added to environment. If null,
     *                    this method will return immediately.
     * @param environment Map<String,String> which will get all Entry<String,String> from the @Bindings. If null,
     *                    this method will return immediately.
     */

    public void addBindingsToMap(Bindings bindings, Map<String, String> environment) {
        if (bindings == null || environment == null) {
            return;
        }

        for (Map.Entry<String, Object> binding : bindings.entrySet()) {
            String bindingKey = binding.getKey();
            Object bindingValue = binding.getValue();

            if (bindingValue instanceof Object[]) {
                addArrayBindingAsEnvironmentVariable(bindingKey, (Object[]) bindingValue, environment);
            } else if (bindingValue instanceof Collection) {
                addCollectionBindingAsEnvironmentVariable(bindingKey, (Collection) bindingValue, environment);
            } else if (bindingValue instanceof Map) {
                addMapBindingAsEnvironmentVariable(bindingKey, (Map<?, ?>) bindingValue, environment);
            } else {
                environment.put(bindingKey, toEmptyStringIfNull(binding.getValue()));
            }
        }
    }


    private void addMapBindingAsEnvironmentVariable(String bindingKey, Map<?, ?> bindingValue,
                                                    Map<String, String> environment) {
        for (Map.Entry<?, ?> entry : ((Map<?, ?>) bindingValue).entrySet()) {
            environment.put(bindingKey + "_" + entry.getKey(),
                    (entry.getValue() == null ? "" : toEmptyStringIfNull(entry.getValue())));
        }
    }

    private void addCollectionBindingAsEnvironmentVariable(String bindingKey, Collection bindingValue,
                                                           Map<String, String> environment) {
        Object[] bindingValueAsArray = bindingValue.toArray();
        addArrayBindingAsEnvironmentVariable(bindingKey, bindingValueAsArray, environment);
    }

    private void addArrayBindingAsEnvironmentVariable(String bindingKey, Object[] bindingValue,
                                                      Map<String, String> environment) {
        for (int i = 0; i < bindingValue.length; i++) {
            environment.put(bindingKey + "_" + i,
                    (bindingValue[i] == null ? "" : toEmptyStringIfNull(bindingValue[i].toString())));
        }
    }


    private String toEmptyStringIfNull(Object value) {
        return value == null ? "" : value.toString();
    }

}
