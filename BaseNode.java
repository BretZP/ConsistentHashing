import java.util.*;


 public abstract class BaseNode implements Node {
    public TreeMap<Integer, String> storeKeys = new TreeMap<>();
    public int predecessorId;
    public int predecessorPort;
    public String predecessorIp;
    public int successorPort;
    public int successorId;
    public String successorIp;
    public int Port;
    public int ID;
    public String IP;

    public String BootStrapIP;
    public int BootStrapPort;

    public NetworkHandler networkHandler;
    public Router router;


    public void setRouter(Router router) {
        this.router = router;
    }

    public void setNetworkHandler(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
    }

    public boolean ownKey(int key) {


        if (predecessorId == ID) {
            return true; // 1 node in ring
        }

        if (predecessorId < ID) { // regular case
            if (key > predecessorId && key <= ID) { // key belongs to this node
                return true;
            } else {
                return false;
            }

        } else if (predecessorId > ID) { // ring case
            if (key > predecessorId || key <= ID) { // key either 0 or belongs to ranges
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    public void handleUpdateSuccessor(Messages msg) {
        // Id, port, ip
        System.out.println("update succ receive");
        System.out.println(msg.content);
        String content[] = msg.content.split(" ");
        this.successorId = Integer.parseInt(content[0]);
        this.successorPort = Integer.parseInt(content[1]);
        this.successorIp = content[2];
        

    }

    @Override
    public void handleUpdatePredecessor(Messages msg) {
        System.out.println("Pred update");
        String content[] = msg.content.split(" ");
        this.predecessorId = Integer.parseInt(content[0]);
        this.predecessorPort = Integer.parseInt(content[1]);
        this.predecessorIp = content[2];

    }

    @Override
    public void handleStatus(Messages msg) {
        System.out.println("Router is working");
        System.out.println("ID: " + this.ID);
        System.out.println("Port: " + this.Port);
        System.out.println("Predecessor ID: " + this.predecessorId);
        System.out.println("Successor ID: " + this.successorId);
    }

    @Override
    public void handleDataRequest(Messages msg) {
        // data is requested by new predeccessor, we use an iterator to dynamically remove data from the TreeMap
        System.out.println("data request received");
        Messages sending_data = new Messages("SENDING_DATA", "");
        Iterator<Map.Entry<Integer, String>> iterator = storeKeys.entrySet().iterator();
        String[] content = msg.content.split(" ");
        int id = Integer.parseInt(content[0]);
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() > id || entry.getKey() == 0) {
                System.out.println("We do not send" + entry.getKey());
                break;
                
            } else {
                // building the string content for the message 
                sending_data.content += entry.getKey() + " " + entry.getValue();
                System.out.println("Sending" + " " + entry.getKey());
                iterator.remove();
                networkHandler.sendMessage("localhost", this.predecessorPort, sending_data); 
            }



        }




    }


}


