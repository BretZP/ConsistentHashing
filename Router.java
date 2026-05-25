import java.net.Socket;

public class Router {
    private final Node node;

    public Router (Node node) {
        this.node = node;
    }

    public void route(Messages msg) {
        if (msg.getType() == null) {
            System.err.println("Message type is null");
        } else {
            switch (msg.getType()) {
                case ENTRY -> node.handleEntry(msg);
                case EXIT -> node.handleExit(msg);
                case INSERT -> node.handleInsert(msg);
                case DELETE -> node.handleDelete(msg);
                case LOOK_UP -> node.handleLookUP(msg);
                case STATUS -> node.handleStatus(msg);
                case UPDATE_SUCCESSOR -> node.handleUpdateSuccessor(msg);
                case UPDATE_PREDECESSOR -> node.handleUpdatePredecessor(msg);
                case ENTRY_ACK -> node.handleEntryACK(msg);
                case DATA_REQUEST -> node.handleDataRequest(msg);
                case SENDING_DATA -> node.handleSendingData(msg);
            }
        }

    }
 
}
