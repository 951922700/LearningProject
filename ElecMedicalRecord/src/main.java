import entity.Patient;
import util.AESUtil;
import util.ConvertUtil;
import util.DSAUtils;
import util.MD5Utils;

import javax.management.MBeanRegistration;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class main {
        //1.创建病人病例  同时生成病人AES密钥并保存
        //2.查看病历信息 输入病人id AES密钥进行查看信息--1--   --2--
        //3.医生输入病历 也需要输入病人AES密码展示病历信息再进行内容修改，在修改前的内容加上行分隔符--revised time--
        //修改完，医生输入自己私密钥对新增内容进行数字签名，写入文件，文件从对分隔符（当前修改次数+1）
        //医生的私密yao应该是提前定义好的，提前把密钥弄出来存进doctor数组
        //之前的内容进行md5加密，对比数据库（在这里模拟数据库将md5放入patient类中）中的md5再进行判断 id+md5.txt文件存md5
        //4.校验某部分内容是否是某医生进行签名的   输入修改次数，医生密码  定位当前信息，寻找下方医生的数字签名进行比对
        //对生成的md5再进行aes加密 固定一个公私密钥

        //保存文件路径
        private static String filePath="E:/user";//存用户基本账号信息目录
        private static String md5Path="E:/user/md5";//存用户md5目录
        private static String contentPath="E:/user/context";//存病历目录 1A289
        //全局AES密钥  对MD5进行加密解密
        private static String globalAesPass="1604414720";

        public static void main(String[] args) {
            //addPatient();
            //viewInfo();
            //doctorInput();
            Scanner in=new Scanner(System.in);
            while(true){
                showMenu();
                int flag=in.nextInt();
                switch (flag){
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        viewInfo();
                        break;
                    case 3:
                        doctorInput();
                        break;
                    default:
                        System.out.println("\n输入错误，请重新输入\n");
                }
            }
        }

        public static void showMenu(){
            System.out.println("\n\n------         电子病历系统  ------");
            System.out.println("------  王野 何定炜 朱舟明 蓝永龙  ------");
            System.out.println("1.创建病历");
            System.out.println("2.查看病历内容");
            System.out.println("3.输入新病历内容");
            System.out.println("\n请输入1,2,3选择你想使用的功能\n");
        }

    /**
     * 创建病历要向user文件添加用户身份证和密钥记录作为存档
     * 创建病人病历
     * 1.输入基本信息到类中
     * 2.字符串进行拼接  aes进行加密             输出到身份证号命名的文件中
     * 3.对字符串进行md5加密 再对md5进行aes加密  存入user+id.txt文件中
     * 4.将id和生成的密码                       存入user.txt文件中
     */
    public static void addPatient(){
        //信息输入
        Patient patient=new Patient();
        Scanner in=new Scanner(System.in);
        System.out.println("--正在创建病人病历，请按提示输入如下内容--");
        System.out.println("请输入身份证号：");
        patient.setId(in.nextLine());
        File t = new File(contentPath+"/"+patient.getId()+".txt");
        if(t.exists())
        {
            //病历已存在
            System.out.println("\n病历已存在，创建失败\n");
            return ;
        }
        System.out.println("请输入姓名：");
        patient.setName(in.nextLine());
        System.out.println("请输入性别：");
        patient.setSex(in.nextLine());
        System.out.println("请输入出生年月日：");
        patient.setBirthDay(in.nextLine());
        System.out.println("请输入您的血型：");
        patient.setBloodType(in.nextLine());
        System.out.println("是否有药物过敏：（有请填写 具体过敏药物，没有填写 无）");
        patient.setDrugAllergy(in.nextLine());
        //字符串拼接
        StringBuilder sb=new StringBuilder();
        sb.append("----基本信息----\n");
        sb.append("身份证号:"+patient.getId()+"\n");
        sb.append("姓名:"+patient.getName()+"\n");
        sb.append("性别:"+patient.getSex()+"\n");
        sb.append("出生日期:"+patient.getBirthDay()+"\n");
        sb.append("血型:"+patient.getBloodType()+"\n");
        sb.append("药物过敏情况:"+patient.getDrugAllergy()+"\n");
        System.out.println(sb.toString());
        sb.append("\n----既往病史----\n");
        //分别对拼接的字符串进行md5加密以及aes加密
        String password= AESUtil.generateKeyString();//生成AES随机密码
        System.out.println("文档创建中······\n");
        System.out.println("您的密码是"+password);
        byte encrypt[]=AESUtil.encrypt(sb.toString(),password);//这是密文  存入文件时要化成16进制

        String MD5Str= MD5Utils.getMD5String(sb.toString());//
        MD5Str= ConvertUtil.parseByte2HexStr(AESUtil.encrypt(MD5Str,globalAesPass));//对MD5再进行aes加密  并且把加密结果以16进制存储
//        System.out.println(new String(encrypt));
//        System.out.println("尝试解密");
//        System.out.println(new String(AESUtil.decrypt(encrypt,password)));

        //创建user文件   将id 密钥  插入文件 "E:/user"
        try{
            File file = new File(filePath);
            FileWriter fw = null;
            BufferedWriter bw = null;
            if (!file.exists()){
                file.mkdirs();//目录不存在创建目录
            }
            file=new File(filePath+"/user.txt");
            if (!file.exists())
                file.createNewFile();//文件不存在再创建文件

            //此时文件已经创建 在这里对用户信息进行读写
            fw=new FileWriter(file,true);
            bw=new BufferedWriter(fw);
           //对用户信息进行字符串拼接 以身份证、密码为顺序进行存放
            StringBuilder temp=new StringBuilder();
            temp.append(patient.getId()+"\n");
            temp.append(password+"\n");
            //temp.append(MD5Str+"\n");//将经过AES加密的MD5写入user文件
            bw.write(temp.toString());//写入文件中
            bw.close();
            fw.close();


        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        //创建身份证命名文件  将AES加密后的密文写入  这个文件在 "E:/user/context"
        try{
            File file = new File(contentPath);
            FileWriter fw = null;
            BufferedWriter bw = null;
            if (!file.exists()){
                file.mkdirs();//目录不存在创建目录
            }
            file=new File(contentPath+"/"+patient.getId()+".txt");
            if (!file.exists())
                file.createNewFile();//再创建文件

            fw=new FileWriter(file);
            bw=new BufferedWriter(fw);
            bw.write(ConvertUtil.parseByte2HexStr(encrypt));//将密文以16进制形式写入文件中
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        //创建user+身份证命名文件  将本次经过AES加密的md5码写入文件  这个文件在 "E:/user/md5"
        try{
            File file = new File(md5Path);
            FileWriter fw = null;
            BufferedWriter bw = null;
            if (!file.exists()){
                file.mkdirs();//目录不存在创建目录
            }
            file=new File(md5Path+"/user"+patient.getId()+".txt");
            if (!file.exists())
                file.createNewFile();//再创建文件

            fw=new FileWriter(file);
            bw=new BufferedWriter(fw);
            bw.write(MD5Str);//将密文写入文件中
            bw.close();
            fw.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("病历创建完成");
    }


    /**
     * 查看病历信息
     * 输入id 和密钥
     */
    public static void viewInfo(){
        File file=null;
        System.out.println("\n--查看病历--\n");
        Scanner in=new Scanner(System.in);
        while(true){
            System.out.println("请输入您的身份证号：");
            String id=in.nextLine();
            file=new File(contentPath+"/"+id+".txt");
            if (!file.exists())
                System.out.println("此病历不存在，请重新输入id");
            else
                break;
        }
        System.out.println("请输入密钥：");
        String password=in.nextLine();//假设密钥不会输入错误
        System.out.println("\n正在开始解析文件·····\n");
        try {
            //先把内容读出来，再解密
            FileReader fr=new FileReader(file);
            BufferedReader bf=new BufferedReader(fr);
            StringBuilder sb=new StringBuilder();
            String str=null;
            while( (str=bf.readLine())!=null){
                sb.append(str);
            }
            byte[] content=AESUtil.decrypt(ConvertUtil.parseHexStr2Byte(sb.toString()),password);
            System.out.println(new String(content));
            bf.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 医生输入病历
     * 输入：病人id，密钥+医生签名密钥
     * 如何模拟医生修改之前的病历内容？
     * 假设我们软件给医生填写内容的时候医生是在一个富文本框进行修改的，也就是说之前的内容医生可能会修改
     * 在我们现在这个程序中只能输入最新数据，所以我们在后台模拟医生将之前的内容作了修改，
     * 并在医生确认输入完毕的时候检查此时之前内容的md5与所存的md5的区别
     * 如果md5不一致则拒绝医生内容的写入并退出病历编写程序
     * （如果是在富文本框下我们可以 以旧内容在文本框最后占的行数为标记区分旧文本与新文本
     * 在新文本输入完毕后，以这个标志对上面的旧内容进行md5加密再执行上述操作即可）
     * 1.获取原本的明文内容
     * 2.医生对病历内容进行输入  确认输入后进行md5内容比对
     * 3.医生输入自己的私密钥  程序对输入内容进行数字签名
     * 4.将数字签名加到输入内容中，连接旧内容进行aes加密存入content中
     */
    public static void doctorInput(){
        Scanner in=new Scanner(System.in);
        String oldStr=null;//旧内容
        System.out.println("\n--填写病历中--\n");
        //获取原本明文内容
        File file=null;
        String id=null;
        while(true){
            System.out.println("请输入病人身份ID：");
            id=in.nextLine();
            file=new File(contentPath+"/"+id+".txt");
            if (!file.exists())
                System.out.println("此病历不存在，请重新输入id");
            else
                break;
        }
        System.out.println("请输入密钥：");
        String password=in.nextLine();//假设密钥不会输入错误
        try {
            //先把内容读出来，再解密
            FileReader fr=new FileReader(file);
            BufferedReader bf=new BufferedReader(fr);
            StringBuilder sb=new StringBuilder();
            String str=null;
            while( (str=bf.readLine())!=null){
                sb.append(str);
            }
            byte[] content=AESUtil.decrypt(ConvertUtil.parseHexStr2Byte(sb.toString()),password);
            oldStr=new String(content);
            //oldStr=oldStr+"1";//可以在这里修改模拟医生修改前面内容
            //System.out.println(new String(content));
            bf.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n病人文档解析成功，下面开始病历输入\n");
        //输入病历内容  发病症状、诊断、药物、日期、医生签名
        System.out.println("发病症状：");
        String situation=in.nextLine();
        System.out.println("诊断：");
        String diagnosis=in.nextLine();
        System.out.println("药物：");
        String drug=in.nextLine();
        System.out.println("医生建议：");
        String suggest=in.nextLine();
        System.out.println("您的姓名:");
        String doctorName=in.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = formatter.format(new Date());//获取当前时间
        //字符拼接
        StringBuilder sb=new StringBuilder();
        sb.append("\n-- "+dateString+" --\n");
        sb.append("时间:"+dateString+"\n");
        sb.append("发病症状："+situation+"\n");
        sb.append("诊断："+diagnosis+"\n");
        sb.append("药物："+drug+"\n");
        sb.append("建议："+suggest+"\n");
        sb.append("医生姓名："+doctorName+"\n");

        //获取存好的md5信息并  对此刻对应的旧内容生成md5码 进行对比
        boolean isSuccessful=true;//校验是否成功
        file=new File(md5Path+"/user"+id+".txt");
        try {
            //先把内容读出来，再解密
            FileReader fr=new FileReader(file);
            BufferedReader bf=new BufferedReader(fr);
            StringBuilder builder=new StringBuilder();
            String str=null;
            while( (str=bf.readLine())!=null){
                builder.append(str);
            }
            byte[] content=AESUtil.decrypt(ConvertUtil.parseHexStr2Byte(builder.toString()),globalAesPass);
            String oldMd5=new String(content);//这是存进文档的md5
            String newMd5=MD5Utils.getMD5String(oldStr);
            if (newMd5.equals(oldMd5))
                System.out.println("\n文件校验成功，旧信息未被修改");
            else{
                System.out.println("\n文件校验失败，此次病历输入无效，请重新操作");
                isSuccessful=false;
            }
            //System.out.println(new String(content));
            bf.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isSuccessful){
            try {
                //成功则执行如下操作
                System.out.println("\n请输入您的密钥，并确认此次修改：");
                String doctorPass=in.nextLine();
                //对输入内容进行签名
                DSAUtils util=new DSAUtils();
                System.out.println("输入内容为：");
                System.out.println(sb.toString());

                System.out.println("\n正在进行数字签名······");
                String sign=util.sign(sb.toString(),doctorPass);
                sb.append("数字签名："+sign+"\n");
                System.out.println("数字签名成功："+sign);

                System.out.println("\n正在生成MD55文件······");
                //新内容连接到旧内容后面
                StringBuilder old=new StringBuilder(oldStr);
                old.append(sb.toString());
                //System.out.println(old);
                String newMd5=MD5Utils.getMD5String(old.toString());//新的md5还得AEs加密切变成16进制才能写入文件
                String hexMd5=ConvertUtil.parseByte2HexStr(AESUtil.encrypt(newMd5,globalAesPass));//用全局密码加密
                //写入文件
                File temp=new File(md5Path+"/user"+id+".txt");
                FileWriter fw=new FileWriter(temp);//会覆盖
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write(hexMd5);
                bw.close();//关闭流才会写入到文件中
                fw.close();
                //对内容进行AES加密 变16进制写入文件
                System.out.println("正在加密文件······");
                temp=new File(contentPath+"/"+id+".txt");
                fw=new FileWriter(temp);
                bw=new BufferedWriter(fw);
                String hexAes=ConvertUtil.parseByte2HexStr(AESUtil.encrypt(old.toString(),password));//用用户密码进行加密
                bw.write(hexAes);
                System.out.println("\n加密成功，病历写入完成\n");
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
