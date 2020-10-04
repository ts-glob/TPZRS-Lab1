import javax.net.ssl.SSLServerSocketFactory;
import java.net.ServerSocket;

public class Server {
    public static void main() throws Exception {
        System.setProperty("javax.net.ssl.keyStore", "test.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "qwerty");
        ServerSocket serverSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4444);
        while (true) new ServerThread(serverSocket.accept()).start();
    }
}
