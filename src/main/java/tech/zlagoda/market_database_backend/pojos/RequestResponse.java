package tech.zlagoda.market_database_backend.pojos;

public class RequestResponse {
    public RequestResponse(String id, boolean success, String error) {
        this.id = id;
        this.success = success;
        this.error = error;
    }

    private String id;
    private boolean success;
    private String error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
