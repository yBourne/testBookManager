package com.ybourne.project.model.exceptions;

import sun.rmi.runtime.Log;

public class LoginException extends RuntimeException {
    public LoginException() { super(); }

    public LoginException(String message){ super(message); }

    public LoginException(String message, Throwable cause) { super(message, cause); }

    public LoginException(Throwable cause) { super(cause); }
}
