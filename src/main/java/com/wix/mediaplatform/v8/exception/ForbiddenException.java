package com.wix.mediaplatform.v8.exception;

public class ForbiddenException extends MediaPlatformException {
    public ForbiddenException() {
        super("Forbidden", 403);
    }
}
