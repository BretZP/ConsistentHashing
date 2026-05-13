import java.io.File;
import java.util.Scanner;

public class NameServerDriver {
    public static void main(String[] args) {
        NameServer nameServer = new NameServer();
        File fileConfig = new File(args[0]);

        nameServer.mapInit(fileConfig);
        Scanner scanner = new Scanner(System.in);
        Router router = new Router(nameServer);
        UserCommandHandler userCommandHandler = new UserCommandHandler(nameServer, router);

        while (true) {
            System.out.println("Enter command> ");
            String command = scanner.nextLine();
            userCommandHandler.handleCommand(command);
            
        }
        




    }



}