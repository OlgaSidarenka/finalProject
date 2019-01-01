package com.sidarenka.alien.pool;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }
}
