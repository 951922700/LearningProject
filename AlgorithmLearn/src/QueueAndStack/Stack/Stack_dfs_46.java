package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_46 {
    /**
     *46. 全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * 解决方法：
     * 回溯方法
     *
     * 时间复杂福O(nxn!)
     * 空间复杂度:O(n)
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean used[]=new boolean[nums.length];
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
                path.add(nums[i]);
                used[i]=true;
                dfs(nums,depth+1,used);
                //递归结束  将这个数字释放
                used[i]=false;
                path.remove(path.size()-1);
            }
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Stack_dfs_46 solution = new Stack_dfs_46();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }

}
