package WebSocket;

public class Message {
    private String toName;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getMessage() {
        return message;
    }

    public String getToName() {
        return toName;
    }
}
