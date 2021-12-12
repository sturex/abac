package com.example.abac.permission;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AbacPermissionContainer {

    private final Map<Class<?>, ActionPermissionContainer<?>> domainPolicies = new HashMap<>();

    private static class ActionPermissionContainer<U> {

        private final Map<String, Function<AbacPermissionEnvironment<U>, Boolean>> actionPolicies = new HashMap<>();

        public Boolean evaluate(String action, AbacPermissionEnvironment<U> environment) {
            return actionPolicies.getOrDefault(action, env -> {
                        throw new RuntimeException("Not found policy for action: " + action);
                    })
                    .apply(environment);
        }

        public void put(String permission, Function<AbacPermissionEnvironment<U>, Boolean> policyFunc) {
            actionPolicies.put(permission, policyFunc);
        }
    }

    @SuppressWarnings("unchecked")
    public <U> void put(String action, Class<U> uClass, Function<AbacPermissionEnvironment<U>, Boolean> policyFunc) {
        ((ActionPermissionContainer<U>) domainPolicies.computeIfAbsent(uClass, cls -> new ActionPermissionContainer<U>()))
                .put(action, policyFunc);
    }

    @SuppressWarnings("unchecked")
    public <U> boolean evaluate(String action, AbacPermissionEnvironment<U> environment) {
        Class<?> cls = environment.getTargetObject().getClass();
        ActionPermissionContainer<U> actionPermissionContainer = (ActionPermissionContainer<U>) domainPolicies.get(cls);
        if (actionPermissionContainer != null) {
            return actionPermissionContainer.evaluate(action, environment);
        } else {
            throw new RuntimeException("Not found policies for domain: " + cls);
        }
    }
}
