package LanQiaoBei;

public class lan_9_b {
    /**
     *方格计数
     * 半径1000
     * 先求第一象限（第二象限用左上角）
     * 以右上角为一个方格
     */
    public static void main(String[] args) {
        int d=1000,ans=0;
        for(int i=1;i<=d;i++)
            for(int j=1;j<=d;j++)
                if(i*i+j*j<=d*d)
                    ans++;
        System.out.println(ans*4);
    }

}
