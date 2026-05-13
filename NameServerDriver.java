import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.*;

public class NameServerDriver {
    public static void main(String[] args) {
        NameServer nameServer = new NameServer();
        File fileConfig = new File(args[0]);
        nameServer.mapInit(fileConfig);

        
        Scanner scanner = new Scanner(System.in);
        Router router = new Router(nameServer);
        UserCommandHandler userCommandHandler = new UserCommandHandler(nameServer, router);
        NetworkHandler networkHandler = new NetworkHandler(router, nameServer.Port);
        nameServer.networkHandler = networkHandler;

        while (true) {
            System.out.println("Enter command> ");
            String command = scanner.nextLine();
            userCommandHandler.handleCommand(command);
            
        }
        




    }



}