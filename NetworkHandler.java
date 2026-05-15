import java.io.DataOutput;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class NetworkHandler {
    Messages message;
    int ip;
    int port;
    Router router;
    
    
    public NetworkHandler(Router router, int port) {
        this.router = router;
        this.port = port;
    }

    // maybe ip as well
    public void sendMessage(String host, int port, Messages msg) {
        try {
            Socket sender = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            System.err.println("Error sending message");
        }

    }

    public void readMessage(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Messages msg = (Messages) in.readObject();
            router.route(msg);
        } catch (IOException e) {
            System.err.println("Error IO");
        } catch (ClassNotFoundException e) {
            System.err.println("Error ClassNot");
        }


    }


    public Socket listenMessage(ServerSocket serverSocket) {
        try {
            Socket listener = new Socket();
            listener = serverSocket.accept();
            System.out.println(listener.getLocalAddress());
            return listener;
        } catch (IOException e) {
            System.err.println("Error listening for message");
        }
        return null;
    }

    
}
