

public class Router {
    private final Node node;

    public Router (Node node) {
        this.node = node;
    }

    public void route(Messages msg) {
        switch (msg.getType()) {
            case ENTRY -> node.handleEntry(msg);
            case EXIT -> node.handleExit(msg);
            case INSERT -> node.handleInsert(msg);
            case DELETE -> node.handleDelete(msg);
            case LOOK_UP -> node.handleLookUP(msg);
        }



    }
 
}
