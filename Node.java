import java.io.*;

public interface Node {
    void handleEntry(Messages msg);
    void handleExit(Messages msg);
    void handleInsert(Messages msg);
    void handleDelete(Messages msg);
    void handleLookUP(Messages msg);
    void handleStatus(Messages msg);
    void handleUpdateSuccessor(Messages msg);
    void handleUpdatePredecessor(Messages msg);
    void handleDataRequest(Messages msg);
    void handleEntryACK(Messages msg);


    
    boolean ownKey(int key);     
    void start();
    void mapInit(File file);
    
}