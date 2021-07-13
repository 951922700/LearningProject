package QueueAndStack.Queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class queue_bfs_1162 {
    /**
     * 1162. 地图分析
     * 你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。
     * 其中 0 代表海洋，1 代表陆地，请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。
     *
     * 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
     *
     * 如果网格上只有陆地或者海洋，请返回 -1。
     */

    static int max=-1;

    /**
     * 时间复杂度：该算法最多执行n的平方bfs，每次bfs最多需要遍历n的平方-1所以时间复杂度
     * 为n的4次方
     * 空间复杂度：O（n的平方）
     */
    /*public static int maxDistance(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]==0){
                    //是海洋就算一下
                    max=Math.max(max,bfs(i,j,grid));
                }
            }
        }
        return max;
    }

    //每次遍历完更新最大值
    public static int bfs(int x,int y,int[][] grid){
        int rows=grid.length;
        int cols=grid[0].length;
        int[][] direction =new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
        boolean [][]visited=new boolean[rows][cols];
        Queue<Integer> queue=new ArrayDeque<>();
        queue.offer(x*cols+y);
        visited[x][y]=true;
        while (!queue.isEmpty()){
            int temp=queue.poll();
            int tempx=temp/cols;
            int tempy=temp%cols;

            for (int k=0;k<4;k++){
                int newx=tempx+direction[k][0];
                int newy=tempy+direction[k][1];
                if (notOver(newx,newy,grid)&&!visited[newx][newy]){
                    //在这里提前判断 就不放进去了
                    if (grid[newx][newy]==1){
                        //如果是陆地那么 这个陆地一定是最近的陆地
                        int tmax=Math.abs(newx-x)+Math.abs(newy-y);
                        //max=Math.max(max,tmax);
                        //结束方法
                        return tmax;
                    }
                    //没越界且没被访问过
                    visited[newx][newy]=true;
                    queue.offer(newx*cols+newy);
                }
            }
        }
        return -1;
    }*/

    public static boolean notOver(int x,int y,int[][] grid){
        int rows=grid.length;
        int cols=grid[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }


    /**
     * 海洋单元格到离它最近的陆地单元格的距离是最大的
     * 如果想用陆地扩散海洋的方式求
     * 理解两点：
     * 1.【海洋离陆地单元是最大的】  从陆地开始扩散 当扩散完海洋时那个海洋与陆地的距离肯定是最远的
     * 2.【陆地是最接近海洋的】 因为只有最接近这个海洋的陆地才能优先扩散到它
     * 所以结合1 2 就满足  最后一个扩散到的海洋  扩散的次数为从陆地扩散的距离  也即时离他最近的陆地
     * 时间：n平方
     * 空间：n平方
     */
    public static int maxDistance(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;

        int[][] directions =new int[][]{{0,-1},{0,1},{1,0},{-1,0}};
        boolean [][]visited=new boolean[rows][cols];
        Queue<Integer> queue=new ArrayDeque<>();
        //将陆地放入队列准备多源扩散
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]==1)
                    queue.offer(i*cols+j);
            }
        }
        if (rows==0||rows*cols==queue.size()){
            //空数组或者全是陆地
            return -1;
        }
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
                    if (notOver(newx,newy,grid)&&!visited[newx][newy]&&grid[newx][newy]==0){
                        //是海洋扩散
                        queue.offer(newx*cols+newy);
                        visited[newx][newy]=true;
                    }
                }
            }
            step++;//一层扩散结束+1
        }
        return step-1;//最后一层没东西扩散  但是依旧+1了所以这里要-1
    }

    public static void main(String[] args) {
        int[][] grid=new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0}};
//        System.out.println(maxDistance(grid));
    }
}
