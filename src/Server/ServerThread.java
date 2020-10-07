package Server;

import MatlabInJava.MatlabInJava;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    ServerThread(Socket socket) {
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
            System.out.println("test");
            matlabInJava.cleanNoise("kartinka.jpg","median",2,1);
            System.out.println("test");
            matlabInJava.cleanNoise("kartinka.jpg","linear",1,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}