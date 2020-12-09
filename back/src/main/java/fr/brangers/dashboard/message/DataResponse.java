package fr.brangers.dashboard.message;

import fr.brangers.dashboard.service.Data;

public class DataResponse implements IResponse{
    private String message;
    private String status;
    private Object data;

    public DataResponse(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
