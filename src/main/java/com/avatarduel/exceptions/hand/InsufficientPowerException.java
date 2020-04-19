package com.avatarduel.exceptions.hand;

import com.avatarduel.model.card.Element;

/**
 * Exception saat mencoba memanggil kartu namun power tidak cukup
 */
public class InsufficientPowerException extends HandOperationException {
    public InsufficientPowerException(Element type) {
        super("Power "+type.toString()+" tidak cukup");
    }
}
