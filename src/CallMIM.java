import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class CallMIM {
    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "test.jks");
        System.setProperty("javax.net.ssl.keyStore", "test.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "qwerty");
        ServerSocket serverSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4444);
        while (true) new ManInTheMiddle(serverSocket.accept(),"82.179.50.54",5555).start();
    }
}
