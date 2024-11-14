import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class socketManager extends Thread {
    private Socket socket;
    private OutputStream out;
    private InputStream inp;
    private socketManager otherSocket;
    private volatile boolean running = true;

    public socketManager(Socket socket, int i) throws Exception {
        this.socket = socket;
        out = socket.getOutputStream();
        inp = socket.getInputStream();
        out.write(i);
    }

    @Override
    public void run() {
        try {
            int c;
            while (running && !socket.isClosed()) {
                while ((c = inp.read()) != -1) {
                    if (otherSocket != null && otherSocket.isRunning()) {
                        synchronized (otherSocket) {
                            otherSocket.out.write(c);
                            otherSocket.out.flush();
                        }
                    }
                }
            }
        } catch (IOException e) {
        } finally {
            cleanup();
        }
    }

    public void setOtherSocket(socketManager socket) {
        this.otherSocket = socket;
    }

    public boolean isRunning() {
        return running;
    }


    private void cleanup() {
        try {
            if (inp != null) inp.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
