package QueueAndStack.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class queue_bfs_200 {
    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     *
     *
     * 示例 1：
     *
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     *
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     */
    int rows=0;
    int cols=0;
    public  int numIslands(char[][] grid) {
        rows=grid.length;
        if (rows==0) return 0;
        cols=grid[0].length;
        int nums=0;//岛屿数量

        Queue<Integer> queue=new LinkedList<>();
        boolean marked[][]=new boolean[rows][cols];
        //上下左右
        int [][] directions=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (!marked[i][j]&&grid[i][j]=='1'){
                    //没有被标记且是大陆
                    nums++;
                    marked[i][j]=true;
                    queue.offer(i*cols+j);
                }

                while(!queue.isEmpty()){
                    int cur=queue.poll();
                    int curx=cur/cols;
                    int cury=cur%cols;
                    for (int k=0;k<4;k++){
                        int newx=curx+directions[k][0];
                        int newy=cury+directions[k][1];
                        if (notOver(newx,newy)&&!marked[newx][newy]&&grid[newx][newy]=='1'){
                            queue.offer(newx*cols+newy);
                            marked[newx][newy]=true;
                        }
                    }
                }
            }
        }
        return nums;
    }

    public boolean notOver(int x,int y){
        return x>=0&&x<rows&&y>=0&&y<cols;
    }

    public static void main(String[] args) {
        queue_bfs_200 q=new queue_bfs_200();
        char [][]grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(q.numIslands(grid));
    }
}
