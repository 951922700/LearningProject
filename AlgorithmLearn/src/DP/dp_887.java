package DP;

public class dp_887 {
    /**
     *887. 鸡蛋掉落
     * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
     *
     * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
     * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
     *
     * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？  最坏情况
     */

    public static void main(String[] args) {
        System.out.println(superEggDrop(3,1000));
    }

    public static int superEggDrop(int k, int n) {
        int[][] memory=new int[k+1][n+1];
        return dp(memory,k,n);
    }
    public static int dp(int[][] memory,int k,int n){
        if (k==1) return n;//鸡蛋只有一个 只能一层一层试
        if (n==0) return 0;//0层就不用
        int time=n;
        if (memory[k][n]!=0)
            return memory[k][n];
        for (int i=1;i<=n;i++){
            //因为我们要求的是最坏情况下扔鸡蛋的次数，所以鸡蛋在第 i 层楼碎没碎，取决于那种情况的结果更大
            int min=Math.max(dp(memory,k-1,i-1),dp(memory,k,n-i))+1;//在第i层扔了一次 也就是操作了一次
            // n-i是因为这层楼扔过了没碎的话就可以向上继续扔，也就是相当于下一次是个(n-(i+1))+1层高的楼
            time=Math.min(time,min);
        }
        memory[k][n]=time;
        return time;
    }
}
