package QueueAndStack.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stack_dfs_377 {
    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 题目数据保证答案符合 32 位整数范围。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3], target = 4
     * 输出：7
     * 解释：
     * 所有可能的组合为：
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     * 请注意，顺序不同的序列被视作不同的组合。
     * 示例 2：
     *
     * 输入：nums = [9], target = 3
     * 输出：0
     */

    public int combinationSum4(int[] nums, int target) {
        return dfs(nums,target);
    }

    //用之前排序的递归会超时
   /* public void dfs(int[] nums,int target){
        if (target==0){
            //这是一种组合
            num++;
            return;
        }
        //这里允许取重复的值
        for (int i=0;i<nums.length;i++){
            if (target-nums[i]<0)
                return;

            dfs(nums,target-nums[i]);
        }
    }*/
    /**
     * 此递归思路是求target为某个值时总的组合数
     */
   Map<Integer,Integer> map=new HashMap<>();
   public int dfs(int[] nums,int target){
       if (target==0){
           return 1;
       }
       int count=0;
       //元素能重复用 每次都从下标0开始
       for (int i=0;i<nums.length;i++){
           if (target-nums[i]>=0){
               if (map.containsKey(target-nums[i])){
                   count+=map.get(target-nums[i]);
               }else
                count+=dfs(nums,target-nums[i]);
           }
       }
       map.put(target,count);
       return count;
   }
}
