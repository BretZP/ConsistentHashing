public class UserCommandHandler {
    
    BaseNode node;
    Router router;

    public UserCommandHandler(BaseNode node, Router router) {
        this.node = node;
        this.router = router;
    }

    public void handleCommand(String command) {
        String commandParts[] = command.split(" ");
        String content = "";
        String action = commandParts[0].toUpperCase();
        Messages msg = new Messages(action, content);
        
        router.route(msg);
    }   

}

