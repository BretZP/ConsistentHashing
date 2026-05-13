import java.io.File;
import java.util.Scanner;

public class BootServer extends BaseNode {

    @Override
    public void start() {
        throw new UnsupportedOperationException("start");
    }

    @Override
    public void handleEntry(Messages msg) {
        this.networkHandler.listenMessage();

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
    public void handleStatus(Messages msg) {
        System.out.println("Router is working");
        System.out.println("ID: " + this.ID);
        System.out.println("Port: " + this.Port);
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

    
}
