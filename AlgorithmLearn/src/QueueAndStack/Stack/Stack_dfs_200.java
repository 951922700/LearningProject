package QueueAndStack.Stack;

public class Stack_dfs_200 {

    public int numIslands(char[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        boolean marked[][]=new boolean[rows][cols];

        int nums=0;
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (!marked[i][j]&&grid[i][j]=='1'){
                  nums++;
                  dfs(grid,marked,i,j);
                }
            }
        }
        return nums;
    }

    public void dfs(char grid[][],boolean marked[][],int x,int y){
        //只为标记
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        for (int k=0;k<4;k++){
            int newx=x+direction[k][0];
            int newy=y+direction[k][1];
            if (notOver(grid,newx,newy)&&!marked[newx][newy]&&grid[newx][newy]=='1'){
                marked[newx][newy]=true;
                dfs(grid,marked,newx,newy);
            }
        }
    }
    public boolean notOver(char grid[][],int x,int y){
        int rows=grid.length;
        int cols=grid[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }
}
