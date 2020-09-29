import java.net.Socket;
import java.io.OutputStream;
import java.io.DataOutputStream;


public class Server {
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("127.0.0.1", 5000);
        String message1 = "Лёха, принимай!";
        OutputStream ostream = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ostream);
        dos.writeBytes(message1);
        dos.close();
        ostream.close();
        sock.close();
    }
}
