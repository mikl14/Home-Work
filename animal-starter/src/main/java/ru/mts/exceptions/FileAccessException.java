package ru.mts.exceptions;

import java.io.IOException;

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
