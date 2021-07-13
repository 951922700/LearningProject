package QueueAndStack.Queue;

import java.util.*;

public class queue_bfs_1619 {
    /**
     * 面试题 16.19. 水域大小
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
     * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
     * 池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     *
     * 示例：
     *
     * 输入：
     * [
     *   [0,2,1,0],
     *   [0,1,0,1],
     *   [1,1,0,1],
     *   [0,1,0,1]
     * ]
     * 输出： [1,2,4]
     *
     * 学习要点：
     * //todo List<Interger>转int[]数组
     *         return  pond.stream().mapToInt(Integer::valueOf).toArray();
     */
    public int[] pondSizes(int[][] land) {
        int rows=land.length;
        int cols=land[0].length;
        boolean marked[][]=new boolean[rows][cols];
        Queue<Integer> queue=new LinkedList<>();
        int[] pond=new int[rows*cols];
        int index=0;
       /* Set<Integer> ponds=new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });*/
        /**上下左右   ＋四个角
         *  x-1,y+1  x,y+1  x+1,y+1
         *  x-1,y    x,y    x+1,y
         *  x-1,y-1  x,y-1  x+1,y-1
         */
        int[][] direction=new int[][]{{0,1},{0,-1},{-1,0},{1,0},{-1,1},{1,1},{-1,-1},{1,-1}};
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                int tempArea=0;
                if (!marked[i][j]&&land[i][j]==0){
                    queue.offer(i*cols+j);
                    marked[i][j]=true;
                }
                while (!queue.isEmpty()){
                    int cur = queue.poll();
                    tempArea++;
                    int curx=cur/cols;
                    int cury=cur%cols;
                    for (int k=0;k<8;k++){
                        int newx=curx+direction[k][0];
                        int newy=cury+direction[k][1];
                        if (notOver(land,newx,newy)&&!marked[newx][newy]&&land[newx][newy]==0){
                            queue.offer(newx*cols+newy);
                            marked[newx][newy]=true;
                        }
                    }
                }
                //循环出来一个岛屿扩散完毕 将岛屿面积加入pond
                if (tempArea!=0)
                pond[index++]=tempArea;
            }
        }
        //此时的index为岛屿数量
        pond=Arrays.copyOf(pond,index);//截取多余的部分

        Arrays.sort(pond);
        return  pond;
    }

    public boolean notOver(int[][] board,int x,int y){
        int rows=board.length;
        int cols=board[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }

    public static void main(String[] args) {
        queue_bfs_1619 q=new queue_bfs_1619();
        int[][] pond=new int[][]{
                {0,2,1,0},
                {0,1,0,1},
                {1,1,0,1},
                {0,1,0,1}
        };
        q.pondSizes(pond);
    }
}
