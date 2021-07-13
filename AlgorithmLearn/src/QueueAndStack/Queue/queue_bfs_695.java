package QueueAndStack.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class queue_bfs_695 {
    /**
     * 695. 岛屿的最大面积
     * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
     *
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
     * 这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     */
    public int maxAreaOfIsland(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        boolean marked[][]=new boolean[rows][cols];
        Queue<Integer> queue=new LinkedList<>();
        int maxArea=0;
        int[][] direction=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                int tempArea=0;
                if (!marked[i][j]&&grid[i][j]==1){
                    queue.offer(i*cols+j);
                    marked[i][j]=true;
                }
                while (!queue.isEmpty()){
                    int cur = queue.poll();
                    tempArea++;
                    int curx=cur/cols;
                    int cury=cur%cols;
                    for (int k=0;k<4;k++){
                        int newx=curx+direction[k][0];
                        int newy=cury+direction[k][1];
                        if (notOver(grid,newx,newy)&&!marked[newx][newy]&&grid[newx][newy]==1){
                            queue.offer(newx*cols+newy);
                            marked[newx][newy]=true;
                        }
                    }
                }
                //循环出来一个岛屿扩散完毕
                maxArea=Math.max(tempArea,maxArea);
            }
        }
        return maxArea;
    }

    public boolean notOver(int[][] board,int x,int y){
        int rows=board.length;
        int cols=board[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }
}
