import MatlabInJava.MatlabInJava;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ManInTheMiddle extends Thread{
    Socket socket;
    private final String HOST;
    private final int PORT;
    ManInTheMiddle(Socket socket,String host,int port) {
        this.PORT = port;
        this.HOST = host;
        this.socket = socket;
    }
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            BufferedImage bufferedImage = ImageIO.read(bufferedInputStream);
            bufferedInputStream.close();
            socket.close();
            ImageIO.write(bufferedImage,"jpg",new File("kartinka.jpg"));
            MatlabInJava matlabInJava = new MatlabInJava();
            System.out.println("test");
            matlabInJava.addNoiseToImage("kartinka.jpg",0.3);
            connectToServer();
            sendImage("kartinka.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void connectToServer()
    {
        try {
            socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendImage(String imagePATH) {
        try
        {
            ImageIcon imageIcon = new ImageIcon(imagePATH);
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.createGraphics();
            graphics.drawImage(image,0,0,null);
            graphics.dispose();
            ImageIO.write(bufferedImage,"jpg",bufferedOutputStream);
            bufferedOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
