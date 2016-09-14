package com.rssreader.core.services.exceptions;

public class RssItemExistsException extends RuntimeException {
    public RssItemExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RssItemExistsException(String message) {
        super(message);
    }

    public RssItemExistsException() {
        super();
    }
}
