package QueueAndStack.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class queue_bfs_130 {
    /**
     * 130. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     */
    public void solve(char[][] board) {
        //1.对四个边进行遍历  x+y   所有与边界相连的O区域都不会被填充 其他都填充
        int rows=board.length;
        int cols=board[0].length;
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        Queue<Integer> queue=new LinkedList();
        boolean marked[][]=new boolean[rows][cols];
        //其实可以一次性把边界的O全部offer进去
        //空间优化 不用marked数组  直接把扩散的O换成另一个字母  最后把O变成X  另一个字母变回O就可以
        //0行i列
        for (int i=0;i<cols;i++){
            if (board[0][i]=='O'&&!marked[0][i]){
                queue.offer(0*cols+i);
                marked[0][i]=true;
            }
            while (!queue.isEmpty()){
                int cur=queue.poll();
                int curx=cur/cols;
                int cury=cur%cols;
                for (int k=0;k<4;k++){
                    int newx=curx+direction[k][0];
                    int newy=cury+direction[k][1];
                    if (notOver(board,newx,newy)&&!marked[newx][newy]&&board[newx][newy]=='O'){
                        queue.offer(newx*cols+newy);
                        marked[newx][newy]=true;
                    }
                }
            }
        }
        //rows-1行 i列
        for (int i=0;i<cols;i++){
            if (board[rows-1][i]=='O'&&!marked[rows-1][i]){
                queue.offer((rows-1)*cols+i);
                marked[rows-1][i]=true;
            }
            while (!queue.isEmpty()){
                int cur=queue.poll();
                int curx=cur/cols;
                int cury=cur%cols;
                for (int k=0;k<4;k++){
                    int newx=curx+direction[k][0];
                    int newy=cury+direction[k][1];
                    if (notOver(board,newx,newy)&&!marked[newx][newy]&&board[newx][newy]=='O'){
                        queue.offer(newx*cols+newy);
                        marked[newx][newy]=true;
                    }
                }
            }
        }
        //i行0列
        for (int i=0;i<rows;i++){
            if (board[i][0]=='O'&&!marked[i][0]){
                queue.offer(i*cols+0);
                marked[i][0]=true;
            }
            while (!queue.isEmpty()){
                int cur=queue.poll();
                int curx=cur/cols;
                int cury=cur%cols;
                for (int k=0;k<4;k++){
                    int newx=curx+direction[k][0];
                    int newy=cury+direction[k][1];
                    if (notOver(board,newx,newy)&&!marked[newx][newy]&&board[newx][newy]=='O'){
                        queue.offer(newx*cols+newy);
                        marked[newx][newy]=true;
                    }
                }
            }
        }
        //i行cols-1列
        for (int i=0;i<rows;i++){
            if (board[i][cols-1]=='O'&&!marked[i][cols-1]){
                queue.offer(i*cols+cols-1);
                marked[i][cols-1]=true;
            }
            while (!queue.isEmpty()){
                int cur=queue.poll();
                int curx=cur/cols;
                int cury=cur%cols;
                for (int k=0;k<4;k++){
                    int newx=curx+direction[k][0];
                    int newy=cury+direction[k][1];
                    if (notOver(board,newx,newy)&&!marked[newx][newy]&&board[newx][newy]=='O'){
                        queue.offer(newx*cols+newy);
                        marked[newx][newy]=true;
                    }
                }
            }
        }
        //现在marked标记了所右不需要被X覆盖的O
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (!marked[i][j]&&board[i][j]=='O')
                    board[i][j]='X';
            }
        }
    }

    public boolean notOver(char[][] board,int x,int y){
        int rows=board.length;
        int cols=board[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }
}
