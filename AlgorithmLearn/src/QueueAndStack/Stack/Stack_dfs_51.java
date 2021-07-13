package QueueAndStack.Stack;

import java.util.*;

public class Stack_dfs_51 {
    /**
     * 51. N 皇后
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     *
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * 2n皇后其实是类似的
     * 思路是先对黑皇后进行搜索
     * 到row==n的时候进入白皇后递归
     * 白皇后的row==n 再进行答案处理  但是白皇后要注意 不要放在黑皇后已经放的位置可以用queens[row]是否等于i判断
     * 因为queens[row]记录了某一行所放置黑皇后的列位置
     */
    List<List<String>> ans=new ArrayList<>();
    Set<Integer> colums=new HashSet<>();//记录已经放置的皇后列位置
    Set<Integer> diagonal1=new HashSet<>();//记录左上角到右下角斜线  行-列差值相等
    Set<Integer> diagonal2=new HashSet<>();//记录右上角到左下角斜线  行+列和相等
    public List<List<String>> solveNQueens(int n) {
        int queens[]=new int[n];//记录每个皇后所在行 列
        Arrays.fill(queens,-1);
        dfs(queens,n,0);
        return ans;
    }

    //以行为递归  需要对列和两个斜角做判断
    public void dfs(int queens[],int n,int row){
        if (row==n){
            //退出递归
            ans.add(getBoard(queens,n));
            return;
        }else {
            for (int i=0;i<n;i++){
                if (colums.contains(i))
                    continue;
                int dia1=row-i;
                if (diagonal1.contains(dia1))
                    continue;
                int dia2=row+i;
                if (diagonal2.contains(dia2))
                    continue;

                //此时可以放皇后
                queens[row]=i;
                colums.add(i);
                diagonal1.add(dia1);
                diagonal2.add(dia2);
                //继续找下一行的值
                dfs(queens,n,row+1);
                //回溯
                colums.remove(i);
                diagonal1.remove(dia1);
                diagonal2.remove(dia2);
            }
        }
    }

    public List<String> getBoard(int queens[],int n){
        List<String> temp=new ArrayList<>();
        for (int i=0;i<n;i++){
            char chs[]=new char[n];//第i行
            Arrays.fill(chs,'.');
            chs[queens[i]]='Q';
            temp.add(new String(chs));
        }
        return temp;
    }
}
