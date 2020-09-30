import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("user '" + bufferedReader.readLine() + "' is now connected to the server.");
            while (true) {
                printWriter.println("Server receiving the data...");
                int length = in.readInt();
                byte[] data = null;
                System.out.println("receiving " + length +" bytes...");
                if (length > 0) {
                    data = new byte[length];
                    for (int i = 0; i <= length / 10000; i++) {
                        if (length - i * 10000 > 10000) {
                            in.read(data, i * 10000, 10000);
                        } else {
                            in.read(data, i * 10000, length - i * 10000);
                        }
                        System.out.println("received data: " + (i + 1) + "\\" + ((length / 10000) + 1));
                    }
                }
                ByteArrayInputStream bis = new ByteArrayInputStream(data);
                BufferedImage bImage2 = ImageIO.read(bis);
                ImageIO.write(bImage2, "jpg", new File("output.jpg"));
                System.out.println(ANSI_GREEN + "image has been transported!" + ANSI_RESET);
                break;
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + e + ANSI_RESET);
        }
    }
}