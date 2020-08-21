package com.example.net.http;

public class NetBusinessException extends Exception  {
    private String Code;
    private String Message;



    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public NetBusinessException(String code, String message) {
        super(message);
        Code = code;
        Message = message;
    }
}
