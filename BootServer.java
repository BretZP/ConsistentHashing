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
                  

                    Messages updateSucessor = new Messages("UPDATE_SUCCESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, updateSucessor);
                    Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, updatePredecessor);
                    Messages entryResponse = new Messages("ENTRY_ACK", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, entryResponse);
                } else {
                    // old predecessor updates its succesor to new node
                    
                    //
                    Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", this.predecessorId + " " + this.predecessorPort + " " + this.predecessorIp);
                    this.networkHandler.sendMessage("localhost", senderPort, updatePredecessor);
                    //
                    Messages updateSuccessor = new Messages("UPDATE_SUCCESSOR", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, updateSuccessor);
                    Messages entryResponse = new Messages("ENTRY_ACK", this.ID + " " + this.Port + " " + this.IP);
                    this.networkHandler.sendMessage("localhost", senderPort, entryResponse);
            
                }

            } else {
                msg.setMessage("SENDING_ENTRY");
                this.networkHandler.sendMessage("localhost", this.successorPort, msg);
            }
        }
        

    }

    @Override
    public void handleExit(Messages msg) {
        System.out.println("receiving exit message");
        String[] content = msg.content.split(" ");
        int exitId = Integer.parseInt(content[0]);
        int exitPort = Integer.parseInt(content[1]);
        String ip = content[2];

        if (exitId == this.successorId) {
            msg.setMessage("SENDING_EXIT");
            int newSuccessorId = Integer.parseInt(content[3]);
            int newSuccessorPort = Integer.parseInt(content[4]);
            String newSuccessorIp = content[5];
            this.networkHandler.sendMessage("localhost", successorPort, msg);
            this.successorId = newSuccessorId;
            this.successorPort = newSuccessorPort;
            this.successorIp = newSuccessorIp;
            // We might be missing some ACK to request data 
        } else {
            System.out.println("It is not successor so we send it forward");
            msg.setMessage("SENDING_EXIT");
            this.networkHandler.sendMessage("localhost", successorPort, msg);
            
        }

    }

    @Override
    public void handleSendingExit(Messages msg) {
        String[] content = msg.content.split(" ");
        int exitId = Integer.parseInt(content[0]);

        if (exitId == predecessorId) {
            int newPredecessorId = Integer.parseInt(content[6]);
            int newPredecessorPort = Integer.parseInt(content[7]);
            String newPredecessorIp = content[8];
            this.predecessorId = newPredecessorId;
            this.predecessorPort = newPredecessorPort;
            this.predecessorIp = newPredecessorIp;
          
        } else {
            System.out.println("SendingExit reached bootserver without finding predecessor");
        }

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
        String[] content = msg.content.split(" ");
        int key = Integer.parseInt(content[0]);
       
        if (ownKey(key) == true) {
            Object value = key;
            String value1 = storeKeys.get(value);
            if (value1 == null) {
               System.out.println("Value not found for key " + key);
                
            } else {
                 System.out.println("Key is found at" + 
                " " + this.ID + " " + "value" + " " +
                value1
                );
            }
        
        }   else {
            System.out.println("key not in this machine");
            networkHandler.sendMessage("localhost", successorPort, msg);
        }
        
    }
    
    @Override
    public void handleKeyFound(Messages msg) {
        String[] content = msg.content.split(" ");
        int key = Integer.parseInt(content[0]);
        int id = Integer.parseInt(content[1]);
        String value = content[2];
        System.out.println("Key found at server " + id + " " 
            + "with value " + " " + value);
    
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
        int newSuccessorId = Integer.parseInt(contentParts[0]); //23
        int newSuccessorPort = Integer.parseInt(contentParts[1]);
        String newSuccessorIp = contentParts[2];
        int newPredecessorId = Integer.parseInt(contentParts[3]); // 0
        int newPredecessorPort = Integer.parseInt(contentParts[4]);
        String newPredecessorIp = contentParts[5]; 
        String senderIp = contentParts[6]; // when we change ip from localhost to correct
        int senderPort = Integer.parseInt(contentParts[7]);
        int senderId = Integer.parseInt(contentParts[8]);
        Messages updatePredecessor = new Messages("UPDATE_PREDECESSOR", newPredecessorId + " " + newPredecessorPort + " " + newPredecessorIp);
        this.networkHandler.sendMessage("localhost", senderPort, updatePredecessor);
        Messages updateSuccessor = new Messages("UPDATE_SUCCESSOR", newSuccessorId + " " + newSuccessorPort + " " + newSuccessorIp);
        this.networkHandler.sendMessage("localhost", senderPort, updateSuccessor);
        Messages entryResponse = new Messages("ENTRY_ACK", this.ID + " " + this.Port + " " + this.IP);
        this.networkHandler.sendMessage("localhost", senderPort, entryResponse);
        // this means a name server has the id of the node entering
        // we send to new node who owns that id (new successor) and who is their
        // new predecessor
    }
   

    
}
