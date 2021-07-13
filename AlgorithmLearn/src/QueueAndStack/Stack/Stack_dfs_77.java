package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_77 {
    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    List<List<Integer>> ans=new ArrayList<>();
    List<Integer> path=new ArrayList<>();

    //n  1 2 3...n
    public List<List<Integer>> combine(int n, int k) {
        dfs(n,1,0,k);
        return ans;
    }

    public void dfs(int n,int begin,int depth,int k){
        if (depth==k){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i=begin;i<=n;i++){
            depth++;
            path.add(i);
            dfs(n,i+1,depth,k);
            path.remove(path.size()-1);
            depth--;
        }
    }

    public static void main(String[] args) {
        Stack_dfs_77 s=new Stack_dfs_77();
        s.combine(4,2);
        System.out.println(s.ans);
    }
}
