package QueueAndStack.Stack;

public class Stack_offer10 {
    /**
     * 剑指 Offer 10- I. 斐波那契数列
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     *
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     */

    public int fib(int n) {
        int num[]=new int[n+1];
        return f(n,num);
    }

    public static int f(int n,int num[]){
        if (n==0||n==1)
            return n;
        if (num[n]!=0)
            return num[n];
        int left=f(n-1,num);
        int right=f(n-2,num);
        num[n]=(left+right)% 1000000007;
        return num[n];
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int fib1(int n){
        int a=0,b=1,sum=0;
        for (int i=0;i<n;i++){
            sum=a+b;
            a=b;
            b=sum;
        }
        return a;
    }
}
