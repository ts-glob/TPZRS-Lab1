package MIM;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

public class CallMIM {
    public CallMIM(String trustStore, String keyStore, String pass, int portIn, String localHost, int portOut) {
        System.setProperty("javax.net.ssl.trustStore", trustStore);
        System.setProperty("javax.net.ssl.keyStore", keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        ServerSocket serverSocket = null;
        try {
            serverSocket = ((SSLServerSocketFactory) SSLServerSocketFactory.getDefault()).createServerSocket(portIn);
            while (true) new ManInTheMiddle(serverSocket.accept(), localHost, portOut, true).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CallMIM(String keyStore, String pass, int portIn, String localHost, int portOut) {
        System.setProperty("javax.net.ssl.keyStore", keyStore);
        System.setProperty("javax.net.ssl.keyStorePassword", pass);
        ServerSocket serverSocket = null;
        try {
            serverSocket = ((SSLServerSocketFactory) SSLServerSocketFactory.getDefault()).createServerSocket(portIn);
            while (true) new ManInTheMiddle(serverSocket.accept(), localHost, portOut, false).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CallMIM(String trustStore, int portIn, String localHost, int portOut) {
        System.setProperty("javax.net.ssl.trustStore", trustStore);
        ServerSocket serverSocket = null;
        try {
            serverSocket = (ServerSocketFactory.getDefault()).createServerSocket(portIn);
            while (true) new ManInTheMiddle(serverSocket.accept(), localHost, portOut, true).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CallMIM(int portIn, String localHost, int portOut) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = (ServerSocketFactory.getDefault()).createServerSocket(portIn);
            while (true) new ManInTheMiddle(serverSocket.accept(), localHost, portOut, false).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}