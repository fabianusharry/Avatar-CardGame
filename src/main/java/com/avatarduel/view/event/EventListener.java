package com.avatarduel.view.event;

/**
 * EventListener that listen to specific Event
 */
public interface EventListener {
    void update(Event eventType, Object value) throws Exception;
}
