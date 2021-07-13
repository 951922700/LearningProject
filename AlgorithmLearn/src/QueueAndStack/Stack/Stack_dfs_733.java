package QueueAndStack.Stack;

public class Stack_dfs_733 {
    /**
     * 733. 图像渲染
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     *
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     *
     * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
     *
     * 最后返回经过上色渲染后的图像。
     *
     * 示例 1:
     *
     * 输入:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * 输出: [[2,2,2],[2,2,0],[2,0,1]]
     * 解析:
     * 在图像的正中间，(坐标(sr,sc)=(1,1)),
     * 在路径上所有符合条件的像素点的颜色都被更改成2。
     * 注意，右下角的像素没有更改为2，
     * 因为它不是在上下左右四个方向上与初始点相连的像素点。
     */
/*    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int rows=image.length;
        int cols=image[0].length;
        boolean [][]visied=new boolean[rows][cols];
        visied[sr][sc]=true;
        dfs(image,sr,sc,visied);
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (visied[i][j])
                    image[i][j]=newColor;
            }
        }
        return image;
    }

    public void dfs(int[][] image,int x,int y,boolean visited[][]){
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        for (int i=0;i<4;i++){
            int newx=x+direction[i][0];
            int newy=y+direction[i][1];
            if (notOver(newx,newy,image)&&!visited[newx][newy]&&image[newx][newy]==image[x][y]){
                visited[newx][newy]=true;
                //满足条件才会进入递归
                dfs(image,newx,newy,visited);
            }
        }
    }*/

    public boolean notOver(int x,int y,int[][] image){
        int rows=image.length;
        int cols=image[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }

    //优化后击败90 50  优化前只有20
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int rows=image.length;
        int cols=image[0].length;
        //旧颜色和新颜色一样
        if(newColor==image[sr][sc])
            return image;
        boolean [][]visied=new boolean[rows][cols];
        visied[sr][sc]=true;
        dfs(image,sr,sc,image[sr][sc],newColor);
        return image;
    }

    public void dfs(int[][] image,int x,int y,int oldColor,int newColor){
        image[x][y]=newColor;
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        for (int i=0;i<4;i++){
            int newx=x+direction[i][0];
            int newy=y+direction[i][1];
            if (notOver(newx,newy,image)&&image[newx][newy]==oldColor){
                //满足条件才会进入递归
                dfs(image,newx,newy,oldColor,newColor);
            }
        }
    }
}
