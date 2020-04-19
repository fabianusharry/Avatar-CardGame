package com.avatarduel.view.event;

import java.util.*;

/**
 * EventManager for managing event in AvatarDuel
 */
public class EventManager {
    Map<Event, List<EventListener>> listeners = new HashMap<>();

    /**
     * Create new EventManager with one or many operations
     * @param operations event operations
     */
    public EventManager(Event... operations) {
        for (Event operation : operations) {
            if (!this.listeners.containsKey(operation)) {
                this.listeners.put(operation, new ArrayList<>());
            }
        }
    }

    /**
     * Add event to EventManager
     * @param operation event
     */
    public void addEvent(Event operation) {
        if (!this.listeners.containsKey(operation)) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * add listener as subscriber to a specific event
     * @param eventType event
     * @param listener listener or subscriber
     */
    public void subscribe(Event eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Notify value to all subscriber in specific eventType
     * @param eventType event
     * @param value object
     * @throws Exception exception when update in listener fails
     */
    public void notify(Event eventType, Object value) throws Exception {
        if (value != null) {
            List<EventListener> users = listeners.get(eventType);
            for (EventListener listener : users) {
                listener.update(eventType, value);
            }
        }
    }
}
