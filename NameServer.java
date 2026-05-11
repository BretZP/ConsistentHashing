import java.io.File;
import java.util.Scanner;

public class NameServer extends BaseNode {
    

    @Override
    public void start() {

    }
    
    @Override
    public void handleEntry(Messages msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleEntry'");
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

            for (i = 0; i < 2; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (i == 0) {
                    this.ID = Integer.parseInt(parts[0]);
                } else if (i == 1) {
                    this.Port = Integer.parseInt(parts[0]);
                    this.successorPort = this.Port;
                    this.predecessorPort = this.Port;
                } else {
                    this.BootStrapIP = parts[0];
                    this.BootStrapPort = Integer.parseInt(parts[1]);

                }

                }
            } catch (Exception e) {
                System.err.println(e);
            }

        }
}

