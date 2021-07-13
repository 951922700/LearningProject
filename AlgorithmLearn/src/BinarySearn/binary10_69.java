package BinarySearn;

public class binary10_69 {
    /**
     * 69. x 的平方根
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */

    //中间数取左边还是右边要看 left和right怎么取值  否则会不断循环
    public int mySqrt(int x) {
        long left=0,right=x;
        while(left<right){
            long mid=left+(right-left+1)/2;
            if (mid*mid<=x){
                //这个时候有可能是满足k的平方<x 的最大k
                left=mid;
            }else{
                right=mid-1;
            }
        }
        return (int)left;
    }
}
