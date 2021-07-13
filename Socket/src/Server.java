import java.io.*;
import java.net.*;
public class Server{
    public static void main(String args[]){
        ServerSocket server=null;
        Socket you=null;
        DataOutputStream out=null;
        DataInputStream  in=null;
        try{  server=new ServerSocket(4331);
        }
        catch(IOException e1){
            System.out.println("ERRO:"+e1);
        }
        try{  you=server.accept();
            in=new DataInputStream(you.getInputStream());
            out=new DataOutputStream(you.getOutputStream());
            while(true){
                int m=0;
                m=in.readInt();
                //通过使用in读取客户写入输出流的信息。堵塞状态，除非读取到信息
                out.writeUTF("你说的数对应的字符是:"+(char)m);
                System.out.println("服务器收到:"+m);
                Thread.sleep(500);
            }
        }
        catch(IOException e){
            System.out.println(""+e);
        }
        catch(InterruptedException e){}
    }
}
