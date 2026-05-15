import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;


public class BootDriver {
    public static void main(String[] args) {
        BootServer bootStrap = new BootServer();
        File  bootfile = new File(args[0]);
        bootStrap.mapInit(bootfile);
        
        Router router = new Router(bootStrap);
        NetworkHandler networkHandler = new NetworkHandler(router, bootStrap.Port);
        bootStrap.setNetworkHandler(networkHandler);
        bootStrap.setRouter(router);
        UserCommandHandler userCommandHandler = new UserCommandHandler(bootStrap, router);
        

        // Server messages thread
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(bootStrap.Port)) {
            while(true) {
                Socket clientSocket = networkHandler.listenMessage(serverSocket);
                networkHandler.readMessage(clientSocket);
                
            }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }).start();

        //User command thread
        new Thread(() -> {




        }).start();

        

    }
}
