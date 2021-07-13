package QueueAndStack.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class queue_bfs_994 {
    /**
     * 在给定的网格中，每个单元格可以有以下三个值之一：
     *
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
     *
     * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
     *
     * 注意：
     * 当有腐烂橘子但是最后不能全部腐烂才返回-1
     *
     */
    public int orangesRotting(int[][] grid) {
      int rows=grid.length;
      int cols=grid[0].length;
      int good=0;
      int[][] directions=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
      Queue<Integer> queue=new LinkedList<>();
      //boolean[][] visited=new boolean[rows][cols];
      //先把所有腐烂橘子入队
      for (int i=0;i<rows;i++){
          for (int j=0;j<cols;j++){
              if (grid[i][j]==2){
                  queue.add(i*cols+j);
                  //visited[i][j]=true;
              }
              if (grid[i][j]==1)
                  good++;
          }
      }
      //没有烂橘子但是有好橘子应该返回-1
      if (queue.size()==0&&good!=0)
          return -1;
      //没有烂橘子也没有好橘子(没有好橘子···) 0分钟时就没有新鲜橘子了
      if (good==0)
          return 0;
      int step=0;
      while (!queue.isEmpty()){
          int size=queue.size();
          for (int i=0;i<size;i++){
              int temp=queue.poll();
              int tempx=temp/cols;
              int tempy=temp%cols;
              for (int[] direction:directions) {
                  int newx=tempx+direction[0];
                  int newy=tempy+direction[1];
                  if (notOver(newx,newy,grid)&&grid[newx][newy]==1){
                      //是新鲜橘子 感染 且新鲜橘子-1
                      grid[newx][newy]=2;
                      queue.add(newx*cols+newy);
                      good--;
                  }
              }
          }
          step++;
      }
      //有烂橘子的情况下  扩散完后如果还有新鲜橘子说明为-1
       /* for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]==1){
                    return -1;
                }
            }
        }*/
       if (good!=0)
           return -1;
      //等于0的话 说明一开始就是只有腐烂的  否则应该是step-1 最后一轮扩散的时候已经全是腐烂的了
      return step==0?step:step-1;
    }

    public static boolean notOver(int x,int y,int[][] grid){
        int rows=grid.length;
        int cols=grid[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }
}
