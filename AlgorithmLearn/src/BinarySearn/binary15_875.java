package BinarySearn;
public class binary15_875 {
    /**
     * 875. 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     *
     *
     *
     * 示例 1：
     *
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     * 示例 2：
     *
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 示例 3：
     *
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     */
    public int minEatingSpeed(int[] piles, int h) {
        //用速度作为二分查找的核心  最小是每次吃一根 最大速度肯定是最大数量
        int left=1,right=0;
        for (int num:piles) {
            right=Math.max(num,right);
        }

        while(left<right){
            int mid=left+(right-left)/2;

            //当速度为mid时 要几个小时
            int H=calHour(piles,mid);

            if (H>h){
                //如果时间大于规定时间  那么速度低了
                left=mid+1;
            }else{
                //时间小于等于规定时间 让速度尽可能往左边靠
                right=mid;
            }
        }
        return left;
    }

    public static int calHour(int[] piles,int k){
        int h=0;
        for (int num:piles) {
            h+=(num+k-1)/k;//向上取整
        }
        return h;
    }
}
