import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket sock = new ServerSocket(8000)) {
            socketManager socket1 = null;
            socketManager socket2 = null;
            int i = 1;
            
            while (true) {
                Socket socket = sock.accept();
                if (socket != null) {
                    if (i == 1) {
                        socket1 = new socketManager(socket, i);
                        socket1.start();
                        i++;
                    } else {
                        socket2 = new socketManager(socket, i);
                        socket1.setOtherSocket(socket2); 
                        socket2.setOtherSocket(socket1);
                        socket2.start();
                        i = 1; 
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
}
}
}