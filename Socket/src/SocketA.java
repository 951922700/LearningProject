import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SocketA extends JFrame implements Runnable, ActionListener {
    JTextField outMessage=new JTextField(12);
    JTextArea inMessage=new JTextArea(12,20);
    JButton b=new JButton("发送数据");
    Socket mySocket;
    DataInputStream in=null;
    DataOutputStream out=null;
    public SocketA(){
        super("I AM ChatA");
        setSize(320,200);
        setVisible(true);
        JPanel p=new JPanel();
        b.addActionListener(this);
        p.add(outMessage);
        p.add(b);
        Container con=getContentPane();
        con.add(new JScrollPane(inMessage),BorderLayout.CENTER);
        con.add(p,BorderLayout.NORTH);
        /**
         * 初始化Socket，以及输入输出流
         */
        try {
            mySocket=new Socket("127.0.0.1",5678);//给localhost的服务器地址以端口4331发送连接请求
            in=new DataInputStream(mySocket.getInputStream());
            out=new DataOutputStream(mySocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread thread=new Thread(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {//点击button发送数据
        try {
            String str=outMessage.getText().trim();
            out.writeUTF(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                String str=in.readUTF();//阻塞
                InetAddress address=mySocket.getInetAddress();//获取信息来源机的套接字对象
                inMessage.append("收到数据来自："+address.getAddress().toString());//输出对应的ip地址
                inMessage.append("\n收到数据是:"+str+"\n");
                inMessage.setCaretPosition(inMessage.getText().length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SocketA();
    }
}
