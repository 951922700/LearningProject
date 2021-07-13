import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public static void main(String[] args) {
        WindowBox windowBox=new WindowBox();
    }
}
class WindowBox extends JFrame implements ActionListener{
    Box baseBox,boxV1,boxV2;
    JTextField jTextField1;
    JTextField jTextField2;
    JButton jButton;
    WindowBox(){
        jTextField1=new JTextField(16);
        jTextField2=new JTextField(16);
        jButton=new JButton("确认输入");

        boxV1=Box.createVerticalBox();
        boxV1.add(new JLabel("输入要转置的数字"));
        boxV1.add(Box.createVerticalStrut(9));
        boxV1.add(new JLabel("结果"));
        boxV1.add(Box.createVerticalStrut(9));

        boxV2=Box.createVerticalBox();
        boxV2.add(jTextField1);
        boxV2.add(Box.createVerticalStrut(9));
        boxV2.add(jTextField2);
        boxV2.add(Box.createVerticalStrut(9));

        baseBox=Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxV2);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(jButton);

        jTextField1.addActionListener(this);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取文本的数字内容字符串
                String num=jTextField1.getText();
                //System.out.println(num+"空白吗");
                //将123.123以.分离成两个字符串
                String []list=num.split("\\.");
                //System.out.println(list[0]+"\n有东西？\n");
                String result="";
                for (int i=0;i<list.length;i++)
                {
                    if(i==1) result+=".";
                    result=result+new StringBuilder(list[i]).reverse().toString();
                }

                System.out.println(result);
                jTextField2.setText(result);
            }
        });
        setLayout(new FlowLayout());
        add(baseBox);
        validate();
        setBounds(120,125,600,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取文本的数字内容字符串
        String num=jTextField1.getText();
        //将123.123以.分离成两个字符串
        String []list=num.split("\\.");

        String result="";
        for (int i=0;i<list.length;i++)
        {
            if(i==1) result+=".";
            result=result+new StringBuilder(list[i]).reverse().toString();
        }

        System.out.println(result);
        jTextField2.setText(result);
    }
}


