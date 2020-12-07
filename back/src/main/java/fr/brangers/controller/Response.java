package fr.brangers.controller;

public class Response {
    private Service service;
    private String message;

    public Response(Service service, String message) {
        this.service = service;
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public String getMessage() {
        return message;
    }
}
