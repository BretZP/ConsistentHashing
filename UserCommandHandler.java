public class UserCommandHandler {
    
    BaseNode node;
    Router router;

    public UserCommandHandler(BaseNode node, Router router) {
        this.node = node;
        this.router = router;
    }

    public Messages handleCommand(String command) {
        String commandParts[] = command.split(" ");
        String action = commandParts[0];
        Messages msg = action.valueOf(action);

        return msg;


    }   

}

