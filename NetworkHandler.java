import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkHandler {
    Messages message;
    int ip;
    int port;
    Router router;
    ServerSocket serverSocket;
    

    public NetworkHandler(Router router, int port) {
        this.router = router;
        this.port = port;
    }

    // maybe ip as well
    public void sendMessage(String host, int port, Messages msg) {
        try {
            Socket sender = new Socket(host, port);
            System.out.println(sender.getLocalAddress());
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    public void listenMessage() {
        try {
            Socket listener = new Socket();
            listener = serverSocket.accept();
            System.out.println(listener.getLocalAddress());


        } catch (IOException e) {
            System.err.println(e);
        }
    }

    
}
