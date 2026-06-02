import java.io.Serializable;


public class Messages implements Serializable {
    public enum MessageType {
        SENDING_ENTRY,
        ENTRY, 
        EXIT, 
        INSERT, 
        DELETE, 
        LOOKUP, 
        STATUS, 
        UPDATE_SUCCESSOR, 
        UPDATE_PREDECESSOR,
        DATA_REQUEST,
        ENTRY_ACK,
        SENDING_DATA,
        NAME_ACK,
        KEY_FOUND,
        SENDING_EXIT
        ;
    }

    MessageType message; 
    String content;

    public Messages(String msg, String content) {
        try {
            this.message = MessageType.valueOf(msg);
            this.content = content;

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid command (Messages): " + msg);
        }
    }


    public void setMessage(String msg) {
        this.message = MessageType.valueOf(msg);

    }
    public MessageType getType() {    
        return message;
    }


}
