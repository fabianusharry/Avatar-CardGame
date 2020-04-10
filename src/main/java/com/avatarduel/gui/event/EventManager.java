package com.avatarduel.gui.event;

import java.io.IOException;
import java.util.*;

public class EventManager {
    Map<Event, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Event... operations) {
        for (Event operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(Event eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(Event eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(Event eventType, Object value) throws IOException {
        if (value != null) {
            List<EventListener> users = listeners.get(eventType);
            for (EventListener listener : users) {
                listener.update(eventType, value);
            }
        }
    }
}
