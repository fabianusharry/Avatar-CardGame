package com.avatarduel.exceptions.hand;

import com.avatarduel.model.card.Element;

public class InsufficientPowerException extends HandOperationException {
    public InsufficientPowerException(Element type) {
        super("Power "+type.toString()+" tidak cukup");
    }
}
