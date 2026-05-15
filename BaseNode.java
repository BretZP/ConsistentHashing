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

}


