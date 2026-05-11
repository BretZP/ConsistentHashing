import java.io.*;
import java.util.*;
import java.net.*;

public class nserver {
    private TreeMap<Integer, String> localKeyData;
    private int id;
    private int port;
    private int successorId = 0;
    private int successorPort = 0;
    private int predecessorId = 0;
    private int predecessorPort = 0;

    private String bserverIp;
    private int bserverPort;

    private Socket socket = null;


    // This code together with nconfig_example.txt and bnserver.java is a starting point for 
    // this project. Thie code can be run with nconfig_example.txt as an instance of a Name Server
    // If BootStrap server is running, an instance of this server can use the commmand
    // "Entry" to enter the hash ring.
    // Commands:
    // Entry
    // Exit
    // This code takes nconfig_example.txt as the only parameter.
    BufferedInputStream ninput = null;
    PrintWriter noutput = null;


    public nserver(String configFilePath) {
        this.localKeyData = new TreeMap<Integer, String>();
        //Set up localKeyData and init variables
        File configFile = new File(configFilePath);
        mapInit(configFile);

    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java nserver <config_file_path>");
            return;
        }
        nserver server = new nserver(args[0]);
        Scanner scanner = new Scanner(System.in);

        while (true) {

            if (scanner.hasNextLine()) {
                String commandLine = scanner.nextLine();
                String[] commandParts = commandLine.split(" ");
                String command = commandParts[0];
                if (command.equalsIgnoreCase("Entry")) {
                    server.connectToBootstrap();
                    server.entry(server.socket);
                } else if (command.equalsIgnoreCase("Exit")) {
    
                }

            }

        }
    }

   private void mapInit(File configFile) {
        try {
            Scanner scanner = new Scanner(configFile);
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i == 0) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.id = Integer.parseInt(parts[0]);
                    i++;
                } else if (i == 1) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.port = Integer.parseInt(parts[0]);
                    this.successorPort = port;
                    this.predecessorPort = port;
                    i++;
                } else {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" ");
                    this.bserverIp = parts[0];
                    this.bserverPort = Integer.parseInt(parts[1]);
                    i++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // helper to initiate connection to bootstrap + streams setup
    // Message: "Entry <id> <port>"
    private void connectToBootstrap() {
         try {
            this.socket = new Socket(bserverIp, bserverPort);
            System.out.println("Connected to bnserver at " + bserverIp + ":" + bserverPort);
            this.ninput = new BufferedInputStream(socket.getInputStream());
            this.noutput = new PrintWriter(socket.getOutputStream(), true);  
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // helper to send entry message to bootstrap
    private void entry(Socket socket) {
        String entryMessage = "Entry " + id + " " + port;
        noutput.println(entryMessage);
        
    }

}