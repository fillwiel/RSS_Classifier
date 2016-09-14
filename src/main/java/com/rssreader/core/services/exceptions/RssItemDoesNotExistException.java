package com.rssreader.core.services.exceptions;

public class RssItemDoesNotExistException extends RuntimeException {
    public RssItemDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public RssItemDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RssItemDoesNotExistException(String message) {
        super(message);
    }

    public RssItemDoesNotExistException() {
    }
}
