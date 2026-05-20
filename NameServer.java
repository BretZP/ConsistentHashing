import java.io.File;
import java.util.Scanner;

public class NameServer extends BaseNode {
    

    @Override
    public void start() {

    }
    
    @Override
    public void handleEntry(Messages msg) {
        msg.content = this.ID + " " + this.Port + " " + this.IP;
        this.networkHandler.sendMessage(this.BootStrapIP, this.BootStrapPort, msg);
    }

 
    @Override
    public void handleExit(Messages msg) {
        throw new UnsupportedOperationException("Unimplemented method 'handleExit'");
    }

    @Override
    public void handleInsert(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInsert'");
    }

    @Override
    public void handleDelete(Messages msg) {
         throw new UnsupportedOperationException("Unimplemented method 'handleDelete'");
    }

    @Override
    public void handleLookUP(Messages msg) {
        throw new UnsupportedOperationException("Unimplemented method 'handleLookUP'");
    }

  
    @Override
    public void mapInit(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int i = 0;
            while(scanner.hasNextLine()) {

                if (i == 0) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.ID = Integer.parseInt(parts[0]);
                    i++;

                } if (i == 1) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.Port = Integer.parseInt(parts[0]);
                    successorPort = this.Port;
                    predecessorPort = this.Port;
                    successorId = this.ID;
                    predecessorId = this.ID;
                    i++;


                } else {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.BootStrapIP = parts[0];
                    this.BootStrapPort = Integer.parseInt(parts[1]);
                    i++;
                }

                
            }

            scanner.close();
            } catch (Exception e) {
                System.err.println(e);
            }

        }

    public void handleEntryACK(Messages msg) {
        String content[] = msg.content.split(" ");
        Messages dataRequest = new Messages("DATA_REQUEST", this.ID + " " + this.Port + " " + this.IP);
        this.networkHandler.sendMessage("localhost", this.successorPort, dataRequest);


    }

}

