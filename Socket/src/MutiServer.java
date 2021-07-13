import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
public class MutiServer extends JFrame implements ActionListener{
    JButton connection,computer;
    JTextField inputA,inputB,inputC;
    JTextArea showResult;
    Socket socket=null;
    DataInputStream in=null;
    DataOutputStream out=null;
    Thread thread;
    MutiServer(){
        connection=new JButton("连接A");
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
        setBounds(100,100,360,310);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        MutiServer server1=new MutiServer();

    }
    public void actionPerformed(ActionEvent e){
        ServerSocket server=null;


        if(e.getSource()==connection){
            try{  server=new ServerSocket(4331);
            }
            catch(IOException e1){
                System.out.println("正在连接");   //ServerSocket对象不能重复创建
            }
            try{
                socket=server.accept();
                System.out.println("A的地址:"+socket.getInetAddress());
            }
            catch (IOException e1){
                System.out.println("正在等待A");
            }
            try{
                in=new DataInputStream(socket.getInputStream());
                out=new DataOutputStream(socket.getOutputStream());
                System.out.println("1");
            }
            catch(IOException e2){
            }
            if(socket!=null)
            {
                thread=new Thread();
                System.out.println(""+socket.isConnected());

            }

            //为每个客户启动一个专门的线程

            try{


                in=new DataInputStream(socket.getInputStream());
                out=new DataOutputStream(socket.getOutputStream());
                computer.setEnabled(true);
                System.out.println("3");
                thread.start();

            }


            catch (IOException ee){
                System.out.println(ee);
                socket=new Socket();
            }

            if(e.getSource()==computer){
                try{  System.out.println("10");
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
    }
    public void run(){
        while(true)
        {
            try{
                Scanner scanner = new Scanner(in);
                System.out.println("B:" + scanner.nextLine());

            }
            catch(Exception e2){
                showResult.setText("与A已断开");
                computer.setEnabled(false);
                break;
            }
        }
    }


}