import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.*;


public class ChatB extends JFrame implements Runnable, ActionListener {
    JTextField outMessage = new JTextField(12);
    JTextField add = new JTextField(12);
    JTextField port = new JTextField(6);
    JTextArea inMessage = new JTextArea(12, 20);
    JButton b = new JButton("发送数据");
    ServerSocket server = null;

    ChatB() {
        super("I AM ChatB");
        setBounds(350,100,320,200);
        setVisible(true);
        JPanel p = new JPanel();
        outMessage.setText("input message");
        add.setText("127.0.0.1");
        port.setText("5678");
        b.addActionListener(this);
        p.add(outMessage);
        p.add(add);
        p.add(port);
        p.add(b);
        Container con = getContentPane();
        con.add(new JScrollPane(inMessage), BorderLayout.CENTER);
        con.add(p, BorderLayout.NORTH);
        Thread thread = new Thread(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        thread.start(); // 线程负责接收数据
    }

    public void actionPerformed(ActionEvent event) {// 单击按钮发送数据
        System.out.println("执行了");
        Socket mySocket = null;
        DataOutputStream out = null;
        String address=add.getText();
        String por=port.getText();
        byte b[] = outMessage.getText().trim().getBytes();
        String s = new String(b);
        try {
            mySocket = new Socket(address, Integer.parseInt(por));
            out = new DataOutputStream(mySocket.getOutputStream());
            out.writeUTF(s);
            outMessage.setText(null);
        } catch (Exception e) {
        }
    }

    public void run() {
        Socket recive = null; // 接收数据
        DataInputStream in = null;
        try {
            server = new ServerSocket(1234);
        } catch (IOException e) {
        }
        try {
            while (true) {
                try {
                    recive = server.accept();
                } catch (IOException e) {
                    System.out.println("正在等待客户");
                }
                in = new DataInputStream(recive.getInputStream());
                String s = in.readUTF();
                inMessage.append("收到数据来自：" + recive.getInetAddress());
                inMessage.append("\n收到数据是：" + s + "\n");
                inMessage.setCaretPosition(inMessage.getText().length());
            }
        } catch (IOException e) {
        }

    }

    public static void main(String args[]) {
        new ChatB();
    }
}