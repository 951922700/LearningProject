package DP;

public class dp_0801 {
    /**
     * 面试题 08.01. 三步问题
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，
     * 计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     *
     * 示例1:
     *
     *  输入：n = 3
     *  输出：4
     *  说明: 有四种走法
     * 示例2:
     *
     *  输入：n = 5
     *  输出：13
     */
    /**
     * f(n)=f(n-1)+f(n-2)+f(n-3)
     * f(0)=1  f(1)=1  f(2)=2;
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        if (n==0||n==1)
            return 1;
        if (n==2)
            return 2;
        int l=1,mid=1,r=2,cur=0;
        for (int i=3;i<=n;i++){
            cur=((l+mid)%1000000007+r)%1000000007;
            l=mid;
            mid=r;
            r=cur;
        }
        return cur;
    }
}
