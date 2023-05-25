package com.example.ticketbooking.domain;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {

    private T payload;

    private List<String> messages = new ArrayList<>();

    public boolean isSuccess() {
        return messages.size() > 0;
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public List<String> getErrorMessages() {
        return messages;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }



}
