package me.darksoul.Engine.Events;

public interface Cancellable {
    boolean isCancelled();
    void setCancelled(boolean cancelled);
}
