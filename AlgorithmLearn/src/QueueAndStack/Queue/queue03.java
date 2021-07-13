package QueueAndStack.Queue;

import java.util.*;

/**
 * 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class queue03 {
    /*
        1.动态规划 用一个一维数组去存储 每个n(1 2 3````)的最小组成个数
        动态转换方程 dp[i]=min(dp[i],dp[i-j*j]+1)
     */
    public int numSquares(int n) {
        int []dp=new int[n+1];

        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        //先计算<=n的平方数
        int maxNum_index=(int)Math.sqrt(n);
        int []square_num=new int[maxNum_index+1];
        for (int i=1;i<=maxNum_index;i++)
            square_num[i]=i*i;
        for (int i=1;i<=n;i++){
            //i-平方数大于等于0 就比较dp[i] 和dp[i-j*j]+1谁更小
            for (int j=1;j<=maxNum_index;j++){
                if (i-square_num[j]<0)
                    break;
                dp[i]=Math.min(dp[i],dp[i-square_num[j]]+1);
            }
        }
        return dp[n];
    }
    //暴力解法  递归   可以不用set的
    public int numSquares1(int n) {
        //先计算<=n的平方数
        int maxNum_index=(int)Math.sqrt(n);
        Set<Integer>square_num=new LinkedHashSet<>();
        for (int i=1;i<=maxNum_index;i++){
            square_num.add(i*i);
        }
    return minNumSquares(n,square_num,new HashMap<Integer,Integer>());
    }
    //HashSet遍历是不按顺序的   可以用一个HashMap来把过程中的解存下来
    public int minNumSquares(Integer n, Set<Integer> square_num, HashMap<Integer, Integer> map){
        //如果n本身就是完全平方数的一个  直接返回1
        if (square_num.contains(n)) return 1;
        //如果在map中（存放n的最小解）有n 则不用再重复递归了
        if (map.containsKey(n)) return map.get(n);
        if (n==0) return 0;
        int minNum=Integer.MAX_VALUE;//n本身最大的一个组合就是n个1
        for (Integer k:square_num){
            if (n<k) break;
            int newMin=minNumSquares(n-k,square_num,map)+1;
            minNum=Math.min(minNum,newMin);
        }
        map.put(n,minNum);
        return minNum;
    }

    //BFS   一课树 一层层往下扩散  中间有可能出现一样的节点当出现一样的节点的时候不进行扩散
    public int numSquares2(int n) {
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();//记录过程的值
        queue.add(n);
        visited.add(n);
        int num=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            num++;
            for (int i=0;i<size;i++){
                int cur=queue.poll();
                //if (cur==n) return num;如果当前节点是完全平方数 那么直接返回num 没有设置map 懒得放了
                //子扩散
                for (int j=1;cur-j*j>=0;j++){
                    int next=cur-j*j;
                    if (next==0) return num;//等于0说明 在这一层就是最短组合了
                    if (!visited.contains(next)){
                        //如果这个节点还没有加入队列过
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        queue03 queue03=new queue03();
        System.out.println(queue03.numSquares2(43));
        System.out.println(queue03.numSquares(43));
    }
}
