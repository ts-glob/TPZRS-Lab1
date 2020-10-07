package GUI;

import Server.Server;
import javax.net.ssl.SSLServerSocketFactory;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.ServerSocket;

public class ServerGUI extends JFrame {
    private JPanel startPanel;
    private JTabbedPane Servers;
    private JPanel Server;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox SSLCheckBox;
    private JButton openFileButton;
    private JButton startServerButton;
    private String filePath;

    public ServerGUI() {
        this.setContentPane(startPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textField1.setEnabled(false);
        openFileButton.setEnabled(false);

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser certificateFile = new JFileChooser();
                certificateFile.setCurrentDirectory(new File("D:\\"));
                certificateFile.showOpenDialog(certificateFile);
                filePath = certificateFile.getSelectedFile().getAbsolutePath();
                textField1.setText(filePath);
            }
        });
        SSLCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setEnabled(SSLCheckBox.isSelected());
                openFileButton.setEnabled(SSLCheckBox.isSelected());
            }
        });
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SSLCheckBox.isSelected())
                    new Server(textField1.getText(), Integer.parseInt(textField2.getText()), "qwerty");
                else
                    new Server(Integer.parseInt(textField2.getText()));
            }
        });
    }
}

