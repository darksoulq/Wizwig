package me.darksoul.Engine.Events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private static final Map<Class<? extends Event>, List<ListenerWrapper>> listeners = new HashMap<>();

    public static <T extends Event> void registerListener(Class<T> eventType, EventListener listener, EventPriority priority) {
        List<ListenerWrapper> eventListeners = listeners
                .computeIfAbsent(eventType, k -> new ArrayList<>());
        eventListeners.add(new ListenerWrapper(listener, priority));

        eventListeners.sort((l1, l2) -> l2.priority.compareTo(l1.priority));
    }

    public static <T extends Event> void unregisterListener(Class<T> eventType, EventListener listener) {
        List<ListenerWrapper> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.removeIf(wrapper -> wrapper.listener.equals(listener));
        }
    }

    public static void dispatchEvent(Event event) {
        List<ListenerWrapper> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null)
            for (ListenerWrapper wrapper : eventListeners) {
                if (event instanceof Cancellable && ((Cancellable) event).isCancelled()) {
                    break;
                }
                wrapper.listener.onEvent(event);

                if (event instanceof Cancellable && ((Cancellable) event).isCancelled()) {
                    break;
                }
            }
    }

    private static class ListenerWrapper {
        EventListener listener;
        EventPriority priority;

        ListenerWrapper(EventListener listener, EventPriority priority) {
            this.listener = listener;
            this.priority = priority;
        }
    }
}
