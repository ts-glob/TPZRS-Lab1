package Server;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public Server(String keyStore, int port, String pass) {
        System.setProperty("javax.net.ssl.keyStore", keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        ServerSocket serverSocket = null;
        try {
            serverSocket = ((SSLServerSocketFactory) SSLServerSocketFactory.getDefault()).createServerSocket(port);
            while (true) new ServerThread(serverSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Server(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = (ServerSocketFactory.getDefault()).createServerSocket(port);
            while (true) new ServerThread(serverSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}