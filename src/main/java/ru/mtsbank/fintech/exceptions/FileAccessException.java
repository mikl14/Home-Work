package ru.mtsbank.fintech.exceptions;

public class FileAccessException extends RuntimeException {
    public FileAccessException()
    {
        super();
    }

    public FileAccessException(String message)
    {
        super(message);
    }
}
