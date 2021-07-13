package DP;

public class dp_509 {
    /**
     * 509. 斐波那契数
     * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给你 n ，请计算 F(n) 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     */
    /**
     * 递归
     * 时间复杂度O(logN)
     * 空间O(N)
     * @param n
     * @return
     */
    int f[];
    public int fib(int n) {
        if (n==0||n==1)
            return 1;
        f=new int[n+1];
        f[0]=0;
        f[1]=1;
        return dfs(n);
    }

    public int dfs(int n){
        if (n==0||n==1)
            return n;
        int n1=f[n-1]==0?dfs(n-1):f[n-1];
        int n2=f[n-2]==0?dfs(n-2):f[n-2];
        f[n-1]=n1;
        f[n-2]=n2;
        return n1+n2;
    }

    /**
     * 动态规划
     * 甚至可以不用数组
     */
    public int fib1(int n) {
        if (n<2)
            return n;
        //int dp[]=new int[n+1];
        int l=0,r=1,cur=0;
        for (int i=2;i<=n;i++){
            cur=l+r;
            l=r;
            r=cur;
        }
        return cur;
    }
}
