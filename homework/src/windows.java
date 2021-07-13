import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class windows{
    public static void main(String args[]){
        new MyWin();
    }
}
class MyWin extends JFrame implements ActionListener,MouseListener{
    JMenuBar menubar;
    JMenu menu;
    JMenuItem item1,item2;
    JTabbedPane p;
    JTextField count;
    JTextField location;
    JButton button[];//lll
    ImageIcon icon[];
    int i=0;
    String imageName[]={"Chris Evans/1.jpg",
            "Chris Evans/2.jpg",
            "Chris Evans/3.jpg",
            "Chris Evans/4.jpg",
            "Chris Evans/5.jpg"};
    public MyWin(){
        //addMouseListener(this);
        count=new JTextField(10);
        count.addActionListener(this);
        location=new JTextField(10);
        location.addActionListener(this);
        add(count,BorderLayout.NORTH);
        add(location,BorderLayout.SOUTH);
        setBounds(100,100,1000,800);
        setLocation(800,200);
        getContentPane().setBackground(Color.blue);
        setVisible(true);
        menubar=new JMenuBar();
        menu=new JMenu("文件");
        item1=new JMenuItem("打开",new ImageIcon("open.gif"));
        item1.setAccelerator(KeyStroke.getKeyStroke('O'));
        item2=new JMenuItem("保存",new ImageIcon("save.gif"));
        item2.setAccelerator(KeyStroke.getKeyStroke('S'));
        menu.add(item1);
        menu.add(item2);
        menubar.add(menu);
        setJMenuBar(menubar);
        validate();
        icon=new ImageIcon[imageName.length];

        for(int i=0;i<icon.length;i++){
            icon[i]=new ImageIcon(imageName[i]);
        }

        button=new JButton[icon.length];//lll
        p=new JTabbedPane(JTabbedPane.LEFT);
        for(int i=0;i<icon.length;i++){
            int m=i+1;
            button[i]=new JButton(icon[i]);//lll
            button[i].addMouseListener(this);//lll
            p.add("观看第"+m+"个图片 ",button[i]);//lll
        }
        //p.add("看文字",new JButton("这就是JTabbedPane"));
        p.validate();
        add(p,BorderLayout.CENTER);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }
    public void actionPerformed(ActionEvent e){
        String text=count.getText();
        if(text.equals("1")) p.setSelectedIndex(Integer.parseInt(text)-1);
        else if(text.equals("2")) p.setSelectedIndex(Integer.parseInt(text)-1);
        else if(text.equals("3")) p.setSelectedIndex(Integer.parseInt(text)-1);
        else if(text.equals("4")) p.setSelectedIndex(Integer.parseInt(text)-1);
        else if(text.equals("5")) p.setSelectedIndex(Integer.parseInt(text)-1);
    }
    public void mousePressed(MouseEvent e){
        location.setText(""+e.getX()+","+e.getY());
    }
    public void mouseExited(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
    }
}
