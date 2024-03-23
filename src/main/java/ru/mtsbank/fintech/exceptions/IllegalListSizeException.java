package ru.mtsbank.fintech.exceptions;

import java.io.IOException;

public class IllegalListSizeException extends IOException {

    public IllegalListSizeException() {
        super();
    }

    public IllegalListSizeException(String s) {
        super(s);
    }

}
