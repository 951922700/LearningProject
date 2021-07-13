package BinarySearn;

public class binary06_154 {
    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 请找出其中最小的元素。
     *
     * 注意数组中可能存在重复的元素。
     *
     * 示例 1：
     *
     * 输入: [1,3,5]
     * 输出: 1
     * 示例 2：
     *
     * 输入: [2,2,2,0,1]
     * 输出: 0
     */
    public int findMin(int[] nums) {
        int len=nums.length;
        int left=0,right=len-1;

        while(left<right){
            int mid=left+(right-left)/2;
            //3 4 5 1 2
            if (nums[mid]>nums[right]){
                //中间大于右边界  左边一定不存在最小值  且中间值也一定不是最小值
                left=mid+1;
            }else if(nums[mid]<nums[right]){
                //12345  67345 中间小于右边界
                //且有可能中间就是最小值
                right=mid;
            }else{
                //中间数等于右边界  把右边界-1 因为中间数和右边界相等所以不会漏判

                right--;
            }
        }
        return nums[left];
    }
}
