package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack_dfs_47 {
    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * 示例 2：
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean used[]=new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums,0,used);
        return ans;
    }
    public void dfs(int nums[],int depth,boolean used[]){
        if (depth==nums.length){
            ans.add(new ArrayList<>(path));
        }
        for (int i=0;i<nums.length;i++){
            //如果没有被用过加入序列中
            if (!used[i]){
                //如果这个数和上一个数一样 而且上一个数已经被释放了  说明即将重复之前的排序 进行剪枝
                if (i>0&&nums[i]==nums[i-1]&&!used[i-1])
                    continue;
                path.add(nums[i]);
                used[i]=true;
                dfs(nums,depth+1,used);
                //递归结束  将这个数字释放
                used[i]=false;
                path.remove(path.size()-1);
            }
        }
    }
}
