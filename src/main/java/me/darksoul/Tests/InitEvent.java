package me.darksoul.Tests;

import me.darksoul.Engine.Events.Cancellable;
import me.darksoul.Engine.Events.Event;

public class InitEvent extends Event implements Cancellable {
    private String message;
    private boolean cancelled = false;

    public InitEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
