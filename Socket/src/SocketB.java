import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SocketB  implements Runnable {

    ServerSocket server=null;
    Socket you=null;
    public SocketB(){
        Thread thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                server=new ServerSocket(5678);//建立接受端口号为4331信息的套接字
            } catch (IOException e) {
                System.out.println("正在监听");
            }
            try {
                you=server.accept();//获取连接
            } catch (IOException e) {
                System.out.println("正在等待客户：");
            }
            if(you!=null){
                ServerThread thread= new ServerThread(you);
                Thread thread1=new Thread(thread);
                thread1.start();
            }
            else continue;
        }

    }

    public static void main(String[] args) {
        new SocketB();
    }
}

class ServerThread extends JFrame implements Runnable,ActionListener {
    JTextField outMessage=new JTextField(12);
    JTextArea inMessage=new JTextArea(12,20);
    JButton b=new JButton("发送数据");
    Socket socket;
    DataOutputStream out=null;
    DataInputStream in=null;
    String s=null;
    ServerThread(Socket t){//初始化与客户的输入输出
        socket=t;
        try {
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        setName("I am B");
        setBounds(350,100,320,200);
        setVisible(true);
        JPanel p=new JPanel();
        b.addActionListener(this);
        p.add(outMessage);
        p.add(b);
        Container con=getContentPane();
        con.add(new JScrollPane(inMessage),BorderLayout.CENTER);
        con.add(p,BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }
    @Override
    public void run(){
        while(true){
            try {
                String str=in.readUTF();
                InetAddress address=socket.getInetAddress();//获取信息来源机的套接字对象
                inMessage.append("收到数据来自："+address.getAddress());//输出对应的ip地址
                inMessage.append("\n收到数据是:"+str+"\n");
                inMessage.setCaretPosition(inMessage.getText().length());
                /*System.out.println("收到数据来自："+address.getAddress().toString());
                System.out.println("收到数据是:"+str);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b){
            try {
                String str=outMessage.getText().trim();
                out.writeUTF(str);
            } catch (Exception ex) {
                inMessage.append("\n还没有获取到连接\n");
            }
        }
    }
}
