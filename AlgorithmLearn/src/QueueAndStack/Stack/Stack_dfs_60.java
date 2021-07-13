package QueueAndStack.Stack;

import java.util.ArrayList;
import java.util.List;

public class Stack_dfs_60 {
    /**
     * 60. 排列序列
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     *
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 3, k = 3
     * 输出："213"
     * 示例 2：
     *
     * 输入：n = 4, k = 9
     * 输出："2314"
     * 示例 3：
     *
     * 输入：n = 3, k = 1
     * 输出："123"
     */
    //List<List<Integer>> ans=new ArrayList<>();
    /*int num=0; //num为第几个
    List<Integer> path=new ArrayList<>();
    boolean isFind=false;//用于结束递归
    public String getPermutation(int n, int k) {
        boolean used[]=new boolean[n+1];
        dfs(n,0,k,used);
        StringBuilder sb=new StringBuilder();
        for (Integer cur: path) {
            sb.append(cur);
        }
        System.out.println(sb);
        return sb.toString();
    }

    public void dfs(int n,int depth,int k,boolean[] used){
        if (depth==n){
            //depth执行一次才加1 当depth等于n则为最长子集之一
            //ans.add(new ArrayList<>(path));
            num++;
            if (num==k){
                //此时path为答案，让递归结束
                isFind=true;//找到答案
            }
            return;
        }
        for (int i=1;i<=n;i++){
            if (isFind)
                return;
            if (!used[i]){
                //若这个数没被使用  则加进list中
                depth++;
                path.add(i);
                used[i]=true;
                dfs(n,depth,k,used);
                //可能递归结束出来 答案找到了
                if (isFind)
                    return;
                used[i]=false;
                path.remove(path.size()-1);
                depth--;
            }
        }
    }*/
    int[] factorial;
    StringBuilder path=new StringBuilder();
    int n,k;
    public String getPermutation(int n, int k) {
        this.n=n;
        this.k=k;
        boolean[] used=new boolean[n+1];
        getFact();
        dfs(0,used);
        return path.toString();
    }

    public void dfs(int index,boolean[] used){
        if (index==n){
            return;//结束递归
        }
        int curF=factorial[n-1-index];//剩余可选数排成的排列数  比如当前及1开头 剩下321  那就是有3！阶层的数
        for (int i=1;i<=n;i++){
            if (!used[i]){
                if (k>curF){
                    //要求的序列数大于当前开头能组成的数  则不在这个开头的序列中 直接剪枝
                    k-=curF;
                    continue;
                }
                //否则 就在这个开头组成的序列中
                used[i]=true;
                path.append(i);
                dfs(index+1,used);
                //不回溯  直接到叶子节点
                return;//不需要再遍历了
            }
        }
    }

    public void getFact(){
        factorial=new int[n+1];
        factorial[0]=1;
        for (int i=1;i<=n;i++){
            factorial[i]=i*factorial[i-1];
        }
    }
    public static void main(String[] args) {

    }
}
