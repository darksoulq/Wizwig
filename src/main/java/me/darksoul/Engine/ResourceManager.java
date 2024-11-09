package me.darksoul.Engine;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private static Map<Integer, Object> resources = new HashMap<>();

    public static void addResource(int id, Object resource) {
        resources.put(id, resource);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getResource(int id, Class<T> type) {
        Object resource = resources.get(id);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found for ID: " + id);
        }
        return type.cast(resource);
    }

    public static void removeResource(int id) {
        resources.remove(id);
    }

    public static void clear() {
        resources.clear();
    }
}
