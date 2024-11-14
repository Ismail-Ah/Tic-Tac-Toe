import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        clientManager cli = new clientManager(socket);
        cli.start();
    }
}
