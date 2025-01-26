package com.cms.payload;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;

    private String request;

    public ErrorDetails(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
