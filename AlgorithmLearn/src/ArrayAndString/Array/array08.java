package ArrayAndString.Array;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 */
public class array08 {
    //错误
/*    public void moveZeroes(int[] nums) {
        int head=0,last=nums.length-1;

            while(head>=last){
                //将last指针指向后面一个非0元素
                while (head>=last){
                    if (nums[last]==0) {
                        last--;
                        break;
                    }
                }
                if (nums[head]==0){
                    int temp=nums[last];
                    nums[last]=nums[head];
                    nums[head]=temp;
                    head++;
                    last--;
                }else head++;
            }
        for (int temp:nums) {
            System.out.print(temp+" ");
        }

    }*/
    public void moveZeroes(int[] nums) {
        int fast=0,slow=0;
        while (fast<nums.length){
            if (nums[fast]==0&&nums[slow]==0)
                fast++;
            else if(nums[fast]!=0&&nums[slow]==0){
                nums[slow]=nums[fast];
                nums[fast]=0;
                slow++;
            }else if(nums[fast]!=0&&nums[slow]!=0){
                slow++;
                fast++;
            }else if(nums[fast]==0&&nums[slow]!=0){
                slow++;
                fast++;
            }
        }
        System.out.println("1");
    }

    /**
     *思路是  直接用快指针去遍历元素  当快指针发现一个不等于0的时候  那么就放到slow指针内容上并且slow前进1
     * 最后再把slow后面的全部为0即可
     */
/*    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt++] = nums[i];
            }
        }
        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // We just need to fill remaining array with 0's.
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }*/


    public static void main(String[] args) {
        array08 array08=new array08();
        array08.moveZeroes(new int[]{0,1,0,3,12});
    }
}
