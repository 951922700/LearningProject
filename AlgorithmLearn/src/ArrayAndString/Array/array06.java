package ArrayAndString.Array;

/**
 * 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 */
public class array06 {

    //可以暴力
    /*
        改进的二分法
        4567 21  寻找中间位置mid
        mid>nums[0] 说明mid在右边
        mid<nums[0] 说明mid在左边
        当nums[mid]>nums[mid+1]  return mid+1
        当nums[mid-1]>nums[mid] return mid
     */
    public int findMin(int[] nums) {
        int len=nums.length;
        if (len==1) return nums[0];
        int left=0,right=len-1;
        //判断有木有翻转
        if (nums[0]<nums[right]) return nums[0];

        while(left<=right){
            int mid=(right-left)/2+left;
            if (nums[mid]>nums[mid+1])
                return nums[mid+1];
            if(nums[mid-1]>nums[mid])
                return nums[mid];

            if (nums[mid]>nums[0])
                left=mid+1;
           else
                right=mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(Math.pow(19,5)%119);
        double temp=Math.pow(66,77);
        System.out.println(temp);
        System.out.println(temp%119);
    }
}
