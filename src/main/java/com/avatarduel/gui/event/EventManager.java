package com.avatarduel.gui.event;

import java.io.IOException;
import java.util.*;

public class EventManager {
    Map<Event, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Event... operations) {
        for (Event operation : operations) {
            if (!this.listeners.containsKey(operation)) {
                this.listeners.put(operation, new ArrayList<>());
            }
        }
    }

    public void addEvent(Event operation) {
        if (!this.listeners.containsKey(operation)) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(Event eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void notify(Event eventType, Object value) throws Exception {
        if (value != null) {
            List<EventListener> users = listeners.get(eventType);
            for (EventListener listener : users) {
                listener.update(eventType, value);
            }
        }
    }
}
