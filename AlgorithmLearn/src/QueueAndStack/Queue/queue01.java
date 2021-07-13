package QueueAndStack.Queue;

/**
 * 200.岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
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
 *
 */
public class queue01 {
    /*int rows=0;
    int cols=0;
    //广度优先搜索
    public int numIslands(char[][] grid) {
        rows=grid.length;
        if (rows==0) return 0;
        cols=grid[0].length;
        int nums=0;//岛屿数量  即广搜次数
        *//**
         *               x-1,y
         *    x,y-1       x,y      x,y+1
         *              x+1,y
         *       遍历顺序  上 下 左 右
         *//*
        int[][] directions=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        boolean[][] marked=new boolean[rows][cols];//标记已经放进队列的
        Queue<Integer> queue=new LinkedList<>();//用int存数组位置  i*cols+j    i=value/cols  j=value%cols  这样会按照0 1 2 一次存储

        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                //如果没标记且为1  进入队列
                if (!marked[i][j]&&grid[i][j]=='1'){
                    queue.offer(i*cols+j);
                    marked[i][j]=true;
                    nums++;//岛屿数加1
                }
                //如果队列不为空进行广度搜索
                while(!queue.isEmpty()){
                    //出队列
                    int cur=queue.poll();
                    //获取行列下标
                    int curx=cur/cols;
                    int cury=cur%cols;
                    //遍历四个方向
                    for (int k=0;k<4;k++){
                        int newx=curx+directions[k][0];
                        int newy=cury+directions[k][1];
                        //这里需要判断数组是否越界 是否被标记 是否为1
                        if (notOver(newx,newy)&&!marked[newx][newy]&&grid[newx][newy]=='1'){
                            //加入队列
                            queue.offer(newx*cols+newy);
                            marked[newx][newy]=true;
                        }
                    }
                }

            }
        }
        return nums;
    }*/

    //深度优先搜索
    int rows=0,cols=0,nums=0;
    boolean[][] marked;
    int[][] directions=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    char[][] grid;
    public int numIslands(char[][] grid) {
        this.grid=grid;
        rows=grid.length;
        if (rows==0) return 0;
        cols=grid[0].length;
        marked=new boolean[rows][cols];
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                //没被标记且为1  进行深度搜索
                if (!marked[i][j]&&grid[i][j]=='1'){
                    nums++;
                    dfs(i,j);
                }
            }
        }
        return nums;
    }

    public void dfs(int x,int y){
        marked[x][y]=true;
        for (int k=0;k<4;k++){
            int newx=x+directions[k][0];
            int newy=y+directions[k][1];
            //这里需要判断数组是否越界 是否被标记 是否为1
            if (notOver(newx,newy)&&!marked[newx][newy]&&grid[newx][newy]=='1'){
                //进行深度搜索
                dfs(newx,newy);
            }
        }
    }

    public boolean notOver(int x,int y){
        return x>=0&&x<rows&&y>=0&&y<cols;
    }
}
