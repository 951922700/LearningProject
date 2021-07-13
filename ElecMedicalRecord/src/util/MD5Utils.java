package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


/**
 * MD5生成文件校验值
 * @author liuyazhuang
 *
 */
public class MD5Utils {

    //主方法，开始调用
    public static void main(String[] args) throws IOException {

        System.out.println("请输入文件路径:");//测试路径C:\Users\Administrator.USER-20170113VG\Desktop\111.txt： 文件内容为abc
        Scanner input=new Scanner(System.in);
        String filePath=input.next();
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("不存在");
        }
        String md5 = getFileMD5String(file);

//      String md5 = getMD5String("abc");

        System.out.println("加密后产生的md5码:" + md5 );
        System.out.println("此时可以修改文件测试修改前后Md5是否不同(修改后输入任意字符再回车)");
        input.next();
        String newMd5 = getFileMD5String(file);
        System.out.println("修改后产生的md5码:" + newMd5 );
        System.out.println("是否一致："+md5.equals(newMd5));
        //file.
        byte[] test=new byte[]{0,1,1,0,0,0,0,1,0,1,1,0,0,0,1,0,0,1,1,0,0,0,1,1,1,0,0,0};
        String hex=bufferToHex(test);
        System.out.println(hex);
    }

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符
     *apache校验下载的文件的正确性用的就是默认的这个组合
     */
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    protected static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(MD5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            nsaex.printStackTrace();
        }
    }

    /**
     * 生成字符串的md5校验值
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * 判断字符串的md5校验码是否与一个已知的md5码相匹配
     *
     * @param password
     *            要校验的字符串
     * @param md5PwdStr
     *            已知的md5校验码
     * @return
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * 生成文件的md5校验值
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>>
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }


}