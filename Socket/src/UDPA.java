import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

public class UDPA extends JFrame implements Runnable, ActionListener {
    JTextField outMessage=new JTextField(12);
    JTextArea inMessage=new JTextArea(12,20);
    JButton b=new JButton("发送数据");
    public UDPA(){
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
        Thread thread=new Thread(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
        thread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {//点击button发送数据
        byte b[]=outMessage.getText().trim().getBytes();
        try{
            InetAddress address=Inet4Address.getByName("10.5.163.210");
            DatagramPacket data=new DatagramPacket(b,b.length,address,1234);
            DatagramSocket mail=new DatagramSocket();
            mail.send(data);
            outMessage.setText(null);
        }catch (Exception ee){
            ee.printStackTrace();
        }
    }

    @Override
    public void run() {
        DatagramPacket pack=null;
        DatagramSocket mail=null;
        byte b[]=new byte[8192];
        try{
            pack=new DatagramPacket(b,b.length);
            mail=new DatagramSocket(5678);
        }catch (Exception ee){
            throw new RuntimeException(ee);
        }
        while(true){
            try{
                mail.receive(pack);
                String message=new String(pack.getData(),0,pack.getLength());
                inMessage.append("收到数据来自:"+pack.getAddress());
                inMessage.append("\n收到数据是:"+message+"\n");
                inMessage.setCaretPosition(inMessage.getText().length());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UDPA();
    }
}
