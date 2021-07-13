package QueueAndStack.Queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class queue_bfs_279 {
    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
     * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；
     * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 12
     * 输出：3
     * 解释：12 = 4 + 4 + 4
     * 示例 2：
     *
     * 输入：n = 13
     * 输出：2
     * 解释：13 = 4 + 9
     */

    public int numSquares(int n) {
        Queue<Integer> queue=new LinkedList<>();
        Set<Integer> visited=new HashSet<>();
        queue.offer(n);
        visited.add(n);
        int step=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for (int i=0;i<size;i++){
                int cur=queue.poll();
                if (cur==0)
                    return step;
                for (int j=1;j*j<=cur;j++){
                    //弹出的这个数  一个一个减去完全平方数  要判断是否之前已经在队列中
                    int temp=cur-j*j;
                    if (!visited.contains(temp)){
                        queue.offer(temp);
                        visited.add(temp);
                    }

                }
            }
            step++;
        }
        return -1;
    }
}
