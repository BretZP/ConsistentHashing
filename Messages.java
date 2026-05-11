import java.io.Serializable;


public class Messages implements Serializable {
    public enum MessageType {
        ENTRY, EXIT, INSERT, DELETE, LOOK_UP;
    }

    MessageType message; 


    public void setType (MessageType msg) {
        this.message = msg;
    }
    
    public MessageType getType() {    
        return message;
    }


}
