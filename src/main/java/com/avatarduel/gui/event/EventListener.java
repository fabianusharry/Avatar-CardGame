package com.avatarduel.gui.event;

import java.io.IOException;
    
public interface EventListener {
    void update(Event eventType, Object value) throws Exception;
}
