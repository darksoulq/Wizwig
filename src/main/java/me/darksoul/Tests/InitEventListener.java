package me.darksoul.Tests;

import me.darksoul.Engine.Events.Event;
import me.darksoul.Engine.Events.EventListener;
import me.darksoul.Engine.Events.EventPriority;

public class InitEventListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        if (event instanceof InitEvent) {
            ((InitEvent) event).setCancelled(true);
            System.out.println(((InitEvent) event).getMessage());
        }
    }

    @Override
    public EventPriority getPriority() {
        return EventPriority.NORMAL;
    }
}
