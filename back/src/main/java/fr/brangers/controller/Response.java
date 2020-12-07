package fr.brangers.controller;

import fr.brangers.service.Data;
import fr.brangers.service.Service;

public class Response {
    private Data data;
    private String message;

    public Response(Data data, String message) {
        this.data = data;
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
