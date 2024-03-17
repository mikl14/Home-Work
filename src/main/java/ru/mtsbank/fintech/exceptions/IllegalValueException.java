package ru.mtsbank.fintech.exceptions;

public class IllegalValueException extends IllegalArgumentException {
    public IllegalValueException() {
        super();
    }

    public IllegalValueException(String str) {
        super(str);
    }
}
