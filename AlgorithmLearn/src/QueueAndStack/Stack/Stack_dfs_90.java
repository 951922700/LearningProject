package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack_dfs_90 {
    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(nums,0);
        return ans;
    }

    public void dfs(int[]  nums,int begin){
        ans.add(new ArrayList<>(path));
        if (begin==nums.length)
            return;
        for (int i=begin;i<nums.length;i++){
            //同一层相同只会是前面的真子集
            if (i>begin&&nums[i]==nums[i-1])
                continue;
            path.add(nums[i]);
            dfs(nums,i+1);
            path.remove(path.size()-1);
        }
    }
}
