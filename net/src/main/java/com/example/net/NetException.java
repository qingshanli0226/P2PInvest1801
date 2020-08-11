package com.example.net;

import androidx.annotation.Nullable;

public class NetException extends Exception {
    String code;
    String message;

    public NetException(String code, String message) {
        super(message);
        this.setCode(code);
        this.setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
