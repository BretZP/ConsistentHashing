import java.util.*;


 public abstract class BaseNode implements Node {
    public TreeMap<Integer, String> storeKeys = new TreeMap<>();
    public int predecessorId;
    public int predecessorPort;
    public int predecessorIP;
    public int successorPort;
    public int successorId;
    public int successorIP;
    public int Port;
    public int ID;
    public int IP;

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


