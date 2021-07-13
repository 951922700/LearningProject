package LanQiaoBei;

import java.math.BigInteger;

public class Common {
    public static void main(String[] args) {
        BigInteger sum=new BigInteger("0");
        BigInteger two=new BigInteger("2");
        for(int i=0;i<64;i++){
            sum.add(two.pow(i));
        }
        System.out.println(sum);
    }
}
