import java.io.File;
import java.util.Scanner;

public class BootServer extends BaseNode {

    @Override
    public void start() {
        throw new UnsupportedOperationException("start");
    }

    @Override
    public void handleEntry(Messages msg) {
        // check for index out of bounds
        // SenderID , SenderPort, SenderIP
        String[] contentParts = msg.content.split(" ");
        if (contentParts.length < 3) {
            System.err.println("Invalid message format from sender");
            return;
        } else {
            int senderId = Integer.parseInt(contentParts[0]);
            int senderPort = Integer.parseInt(contentParts[1]);
            String senderIp = contentParts[2];
            if (this.ownKey(senderId)) {
                if (this.predecessorId == this.ID) {
                    this.predecessorId = senderId;
                    this.predecessorPort = senderPort;
                    this.predecessorIp = senderIp;
                    this.successorId = senderId;
                    this.successorPort = senderPort;
                    this.successorIp = senderIp;


                    Messages updateSucessor = new Messages("UPDATE_SUCCESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", this.predecessorPort, updateSucessor);
                    Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", this.successorPort, updatePredecessor);
                    Messages entryResponse = new Messages("ENTRY_ACK", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, entryResponse);
                } else {
                    // old predecessor updates its succesor to new node
                    Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", this.predecessorId + " " + this.predecessorPort + " " + this.predecessorIp);
                    this.networkHandler.sendMessage("localhost", senderPort, updatePredecessor);
                    Messages updateSuccessor = new Messages("UPDATE_SUCCESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, updateSuccessor);
                    this.predecessorId = senderId;
                    this.predecessorPort = senderPort;
                    this.predecessorIp = senderIp;
                }

            } else {
                msg.setMessage("SENDING_ENTRY");
                this.networkHandler.sendMessage("localhost", this.successorPort, msg);
            }
        }
        

    }

    @Override
    public void handleExit(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleExit'");
    }

    @Override
    public void handleInsert(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInsert'");
    }

    @Override
    public void handleDelete(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleDelete'");
    }

    @Override
    public void handleLookUP(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleLookUP'");
    }

 

    @Override
    public void mapInit(File configFile) {
        try {
            Scanner scanner = new Scanner(configFile);
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i == 0) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.ID = Integer.parseInt(parts[0]);
                    i++;
                } else if (i == 1) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.Port = Integer.parseInt(parts[0]);
                    successorPort = this.Port; // Initially points to itself
                    predecessorPort = this.Port; // Initially points to itself
                    i++;
                } else {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    int key = Integer.parseInt(parts[0]);
                    String value = parts[1];
                    this.storeKeys.put(key, value);
                    i++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleEntryACK(Messages msg) {
        System.out.println("Invalid for this type of node");
        
    }

    @Override
    public void handleSendingData(Messages msg) {
        System.out.println("Invalid for this type of node");
    }

    @Deprecated
    public void handleSendingEntry(Messages msg) {
        System.out.println("sending entry");
    }

    @Override 
    public void handleNameACK(Messages msg) {
        System.out.println("Received nameACK");
        String[] contentParts = msg.content.split(" ");
        successorId = Integer.parseInt(contentParts[0]);
        successorPort = Integer.parseInt(contentParts[1]);
        successorIp = contentParts[2];
        predecessorId = Integer.parseInt(contentParts[3]);
        predecessorPort = Integer.parseInt(contentParts[4]);
        predecessorIp = contentParts[5];
        String senderIp = contentParts[6]; // when we change ip from localhost to correct
        int senderPort = Integer.parseInt(contentParts[7]);
        int senderId = Integer.parseInt(contentParts[8]);
        Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", predecessorId + " " + predecessorPort + " " + predecessorIp);
        this.networkHandler.sendMessage("localhost", senderPort, updatePredecessor);
        Messages updateSuccessor = new Messages("UPDATE_SUCCESSOR", successorId + " " + successorPort + " " + successorIp);
        this.networkHandler.sendMessage("localhost", senderPort, updateSuccessor);
        Messages entryResponse = new Messages("ENTRY_ACK", this.ID + " " + this.Port + " " + this.IP);
        this.networkHandler.sendMessage("localhost", senderPort, entryResponse);
        // this means a name server has the id of the node entering
        // we send to new node who owns that id (new successor) and who is their
        // new predecessor
    }
   

    
}
