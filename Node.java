

public interface Node {
    void handleEntry(Messages msg);
    void handleExit(Messages msg);
    void handleInsert(Messages msg);
    void handleDelete(Messages msg);
    void handleLookUP(Messages msg);
    
    boolean ownKey(int key);     
    void start();
    
}