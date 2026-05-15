import java.io.Serializable;


public class Messages implements Serializable {
    public enum MessageType {
        ENTRY, EXIT, INSERT, DELETE, LOOK_UP, STATUS, UPDATE_SUCCESSOR;
    }

    MessageType message; 
    String content;

    public Messages(String msg, String content) {
        try {
            this.message = MessageType.valueOf(msg);
            this.content = content;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid command: " + msg);
        }
    }

    public MessageType getType() {    
        return message;
    }


}
