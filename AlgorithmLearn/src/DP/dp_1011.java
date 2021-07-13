package DP;

public class dp_1011 {
    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：2
     * 示例 2：
     *
     * 输入：n = 7
     * 输出：21
     * 示例 3：
     *
     * 输入：n = 0
     * 输出：1
     */
    /**
     * 空间优化
     * f(n)=f(n-1)+f(n-2)  跳1和跳2的方法数之和
     * 初始化
     * f(0)=1,f(1)=1
     */
    public int numWays(int n) {
        //n为0 或者1 都是1
        if (n<2)
            return 1;
        int l=1,r=1,cur=0;
        for (int i=2;i<=n;i++){
            cur=(l+r)%1000000007;
            l=r;
            r=cur;
        }

        return cur;
    }
}
