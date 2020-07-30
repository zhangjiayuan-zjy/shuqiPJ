package WebSocket;

public class ResultMessage {
    private String fromName;
    private Object message;
    private boolean system;

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return  message;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }
    public boolean getSystem() {
        return system;
    }
}
