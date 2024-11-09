package me.darksoul.Engine.Events;

public interface EventListener {
    void onEvent(Event event);

    EventPriority getPriority();
}