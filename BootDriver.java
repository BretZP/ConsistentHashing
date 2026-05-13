import java.lang.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.*;


public class BootDriver {
    public static void main(String[] args) {
        BootServer bootStrap = new BootServer();
        File  bootfile = new File(args[0]);
           
        bootStrap.mapInit(bootfile);

        Router router = new Router(bootStrap);
        UserCommandHandler userCommandHandler = new UserCommandHandler(bootStrap, router);
        Scanner scanner = new Scanner(System.in);
        NetworkHandler networkHandler = new NetworkHandler(router, bootStrap.Port);
        bootStrap.networkHandler = networkHandler;
        networkHandler.listenMessage();

        while (true) {
            System.out.println("enter command: ");
            String command = scanner.nextLine();
            userCommandHandler.handleCommand(command);
        }

        

    }
}
