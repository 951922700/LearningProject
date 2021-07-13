package LanQiaoBei;

import java.util.Scanner;

public class lan_9_f {
    /**
     * 给定三个整数数组
     * A = [A1, A2, ... AN], 
     * B = [B1, B2, ... BN], 
     * C = [C1, C2, ... CN]，
     * 请你统计有多少个三元组(i, j, k) 满足：
     *
     * 1. 1 <= i, j, k <= N  
     * 2. Ai < Bj < Ck  
     *
     * 【输入格式】
     * 第一行包含一个整数N。
     * 第二行包含N个整数A1, A2, ... AN。
     * 第三行包含N个整数B1, B2, ... BN。
     * 第四行包含N个整数C1, C2, ... CN。
     *
     * 对于30%的数据，1 <= N <= 100  
     * 对于60%的数据，1 <= N <= 1000 
     * 对于100%的数据，1 <= N <= 100000 0 <= Ai, Bi, Ci <= 100000 
     *
     * 【输出格式】
     * 一个整数表示答案
     *
     * 【输入样例】
     * 3
     * 1 1 1
     * 2 2 2
     * 3 3 3
     *
     * 【输出样例】
     * 27 
     *
     * 资源约定：
     * 峰值内存消耗（含虚拟机） < 256M
     * CPU消耗  < 1000ms
     */

    /**
     * 优化思路  在第三层用一个二分查找到满足C[i]>B[i]的点  然后后面的点全是满足的直接+长度
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();

        int[] A=new int[n];
        int[] B=new int[n];
        int[] C=new int[n];

        for (int i=0;i<n;i++)
            A[i]=scan.nextInt();
        for (int i=0;i<n;i++)
            B[i]=scan.nextInt();
        for (int i=0;i<n;i++)
            C[i]=scan.nextInt();
        int num=0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                int l=0,r=n-1;
                while(l<r){
                    int mid=l+(r-l)/2;
                    if (C[mid]<=B[i])
                        l=mid+1;
                    else
                        r=mid;
                }
                num+=n-l;// 1 2 3如果是2  那就是长度3-索引位置1=2后面有两个数
            }
        }

        System.out.println(num);
    }
}
