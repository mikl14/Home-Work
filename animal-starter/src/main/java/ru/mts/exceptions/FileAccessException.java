package ru.mts.exceptions;

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
