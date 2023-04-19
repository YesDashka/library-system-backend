package org.example.exception;

import java.io.IOException;

public class PaymentFailedException extends IOException {

    public PaymentFailedException(String message) {
        super(message);
    }

}
