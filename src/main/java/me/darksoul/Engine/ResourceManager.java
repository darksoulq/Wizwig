package me.darksoul.Engine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

public class ResourceManager {

    private static final Map<Integer, Object> resources = new ConcurrentHashMap<>();

    public static void addResource(int id, Object resource) {
        if (resource == null) {
            ErrorLogger.error("ResourceManager", "Attempted to add a null resource for ID: " + id);
            throw new IllegalArgumentException("Resource cannot be null for ID: " + id);
        }
        resources.put(id, resource);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getResource(int id, Class<T> type) {
        AtomicReference<T> resourceRef = new AtomicReference<>();

        resources.computeIfPresent(id, (key, resource) -> {
            if (!type.isInstance(resource)) {
                ErrorLogger.error("ResourceManager", "Resource type mismatch for ID: " + id);
                throw new ClassCastException("Resource ID: " + id + " is not of expected type " + type.getName());
            }
            resourceRef.set((T) resource);
            return resource;
        });

        if (resourceRef.get() == null) {
            ErrorLogger.warning("ResourceManager", "Resource not found for ID: " + id);
            throw new IllegalArgumentException("Resource not found for ID: " + id);
        }

        return resourceRef.get();
    }

    public static void removeResource(int id) {
        if (resources.remove(id) == null) {
            ErrorLogger.warning("ResourceManager", "Attempted to remove non-existing resource for ID: " + id);
        }
    }

    public static void clear() {
        resources.clear();
    }
}
