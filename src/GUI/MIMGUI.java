package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import MIM.CallMIM;

public class MIMGUI extends JFrame {
    private JPanel StartPanel;
    private JTabbedPane Servers;
    private JPanel MenInTheMiddle;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox SSLCheckBox1;
    private JCheckBox SSLCheckBox2;
    private JButton openCertIn;
    private JButton openCertOut;
    private JButton startMIMButton;
    private String filePath;

    public MIMGUI() {
        this.setContentPane(StartPanel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textField1.setEnabled(false);
        openCertIn.setEnabled(false);
        textField3.setEnabled(false);
        openCertOut.setEnabled(false);

        openCertIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser certificateFile = new JFileChooser();
                certificateFile.setCurrentDirectory(new File("D:\\"));
                certificateFile.showOpenDialog(certificateFile);
                filePath = certificateFile.getSelectedFile().getAbsolutePath();
                textField1.setText(filePath);
            }
        });
        openCertOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser certificateFile = new JFileChooser();
                certificateFile.setCurrentDirectory(new File("D:\\"));
                certificateFile.showOpenDialog(certificateFile);
                filePath = certificateFile.getSelectedFile().getAbsolutePath();
                textField1.setText(filePath);
            }
        });
        SSLCheckBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setEnabled(SSLCheckBox1.isSelected());
                openCertIn.setEnabled(SSLCheckBox1.isSelected());
            }
        });
        SSLCheckBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setEnabled(SSLCheckBox2.isSelected());
                openCertOut.setEnabled(SSLCheckBox2.isSelected());
            }
        });
        startMIMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SSLCheckBox1.isSelected() && SSLCheckBox2.isSelected())
                    new CallMIM(textField1.getText(), textField3.getText(), "qwerty", Integer.parseInt(textField2.getText()), textField4.getText(), Integer.parseInt(textField5.getText()));
                else if (SSLCheckBox1.isSelected())
                    new CallMIM(textField1.getText(), "qwerty", Integer.parseInt(textField2.getText()), textField4.getText(), Integer.parseInt(textField5.getText()));
                else if (SSLCheckBox2.isSelected())
                    new CallMIM(textField3.getText(), Integer.parseInt(textField2.getText()), textField4.getText(), Integer.parseInt(textField5.getText()));
                else
                    new CallMIM(Integer.parseInt(textField2.getText()), textField4.getText(), Integer.parseInt(textField5.getText()));
            }
        });
    }
}