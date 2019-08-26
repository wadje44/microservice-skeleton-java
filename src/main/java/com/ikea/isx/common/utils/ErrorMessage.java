package com.ikea.isx.common.utils;

/* Basic error message with only one attribute.
 We can extend it from error or add fields like error code too for proper use.*/
public class ErrorMessage {

    private String message;

    public ErrorMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}