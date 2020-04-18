package com.avatarduel.view.event;

public interface EventListener {
    void update(Event eventType, Object value) throws Exception;
}
