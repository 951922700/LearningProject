package util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.*;
import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class DSAUtils {
    private DSAPublicKey dsaPublicKey;//公钥
    private DSAPrivateKey dsaPrivateKey;//私钥
    private byte[] res;//签名后产生的字节数组
    private static String filePath="E:/user/doctor";//医生密钥地址  密钥文件命名 publicKey+password.key 私密钥同

    //初始化throws Exception
    public DSAUtils(){
       /* KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        this.dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();*/
        //System.out.println(dsaPrivateKey.getFormat());

    }

    public  DSAPublicKey getPublicKey(KeyPair keyPair){
        return (DSAPublicKey) keyPair.getPublic();
    }

    public  DSAPrivateKey getPrivateKey(KeyPair keyPair){
        return (DSAPrivateKey) keyPair.getPrivate();
    }

    public void setDsaPublicKey(DSAPublicKey dsaPublicKey) {
        this.dsaPublicKey = dsaPublicKey;
    }

    public void setDsaPrivateKey(DSAPrivateKey dsaPrivateKey) {
        this.dsaPrivateKey = dsaPrivateKey;
    }

    /**
     * 执行签名
     * 输入医生密码 读取对应私钥文件对象 对明文进行数字签名并返回签名
     * @param src
     * @param password
     * @throws Exception
     */
    public  String  sign(String src,String password)throws Exception{
        ObjectInputStream in_pub=new ObjectInputStream(new FileInputStream(filePath+"/privateKey"+password+".key"));
        PrivateKey privateKey = (PrivateKey) in_pub.readObject();
        /*PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(this.dsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);*/
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        this.res = signature.sign();
        //System.out.println("签名：" + bytesToHexString(res));
        return bytesToHexString(res);
    }

    /**
     * 未实现
     * 输入明文，签名以及，医生密码获取的公钥来进行数字签名认证
     * res要转换成字节  而且是要16进制转2进制
     * @param src
     * @return
     * @throws Exception
     */
    public  boolean verify(String src,String res,String password) throws Exception
    {
        ObjectInputStream in_pub=new ObjectInputStream(new FileInputStream(filePath+"/publicKey"+password+".key"));
        PublicKey publicKey = (PublicKey) in_pub.readObject();
        //3.验证签名
/*        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(this.dsaPublicKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);*/
        Signature signature = Signature.getInstance("SHA1withDSA");
        signature.initVerify(publicKey);
        signature.update(src.getBytes());
        boolean bool = signature.verify(this.res);
        System.out.println("验证：" + bool);
        return bool;
    }

    //byte转16位进制
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 生成秘钥对写入到文件  文件命名为publicKey+医生自己定义的密码（唯一）.key
     * 修改密码即可生成对应医生的公钥密钥
     * @return
     */
    public static boolean getKeyPairs() {
        try {
            File file=new File(filePath);
            if (!file.exists()){
                file.mkdirs();//目录不存在创建目录
            }
            //初始化秘钥管理器
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            //获取秘钥对
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            //直接写入公钥
            ObjectOutputStream out_pub = new ObjectOutputStream(new FileOutputStream(filePath+"/publicKey951922700.key"));
            out_pub.writeObject(publicKey);
            out_pub.close();
            System.out.println("生成的公钥内容为_____:\n " + publicKey);
            //直接写入私钥
            ObjectOutputStream out_pri = new ObjectOutputStream(new FileOutputStream(filePath+"/privateKey951922700.key"));
            out_pri.writeObject(privateKey);
            out_pri.close();
            System.out.println("生成的私钥内容为_____:\n " + privateKey);
            System.out.println("\n生成密钥对成功...");
            return true;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return false;
        }
    }

        //测试
    public static void main(String[] args) throws Exception {
//        DSAUtils util=new DSAUtils();
       /* util.sign("bbb");
        util.verify("bbb");*/
        //如何将DSA私密钥和公密钥

        getKeyPairs();
       /* System.out.println(util.sign("hhh", "943033940"));
        System.out.println(util.verify("hhh","11","943033940"));*/
    }
}