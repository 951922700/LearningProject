import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
public class ClientFrame extends JFrame
        implements Runnable,ActionListener{
    JButton connection,computer;
    JTextField inputA,inputB,inputC;
    JTextArea showResult;
    Socket socket=null;
    DataInputStream in=null;
    DataOutputStream out=null;
    Thread thread;
    public ClientFrame(){
        socket=new Socket();               //待连接的套接字
        connection=new JButton("连接B");
        computer=new JButton("发送");
        computer.setEnabled(false);         //没有和服务器连接之前，该按钮不可用
        inputC=new JTextField(" ",12);
        Box boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("输入对话"));
        Box boxV2=Box.createVerticalBox();
        boxV2.add(inputC);
        Box baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(boxV2);
        Container con=getContentPane();
        con.setLayout(new FlowLayout());
        showResult=new JTextArea(8,18);
        con.add(connection);
        con.add(baseBox);
        con.add(computer);
        con.add(new JScrollPane(showResult));
        computer.addActionListener(this);
        connection.addActionListener(this);
        thread = new Thread(this);
        setBounds(100,100,360,310);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void run(){
        while(true){

            try{  char string1;
                Scanner scanner = new Scanner(in); //堵塞状态，除非读取到信息
                showResult.append("\nB:\n"+scanner.nextLine());

            }
            catch(Exception e3){
                showResult.setText("与B已断开");
                computer.setEnabled(false);
                break;
            }
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==connection){
            try{  if(socket.isConnected()) {}
            else{
                System.out.println("1");
                InetAddress address=InetAddress.getByName("127.0.0.1");
                InetSocketAddress socketAddress=new InetSocketAddress(address,4331);
                socket.connect(socketAddress);
                in=new DataInputStream(socket.getInputStream());
                out=new DataOutputStream(socket.getOutputStream());
                computer.setEnabled(true);
                thread.start();
            }
            }
            catch (IOException ee){
                System.out.println(ee);
                socket=new Socket();
            }
        }
        if(e.getSource()==computer){
            try{
                String c=inputC.getText();
                System.out.println(""+c);

                PrintStream printStream = new PrintStream(out);
                printStream.println(""+c);
                printStream.flush();



            }
            catch(Exception ee){
                inputA.setText("请输入字符");
            }
        }
    }
    public static void main(String args[]){
        ClientFrame win=new ClientFrame();
    }
}