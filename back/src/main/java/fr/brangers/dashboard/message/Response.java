package fr.brangers.dashboard.message;

import fr.brangers.dashboard.service.Data;

public class Response implements IResponse{
    private String message;
    private String status;

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getStatus() {
        return status;
    }

}
