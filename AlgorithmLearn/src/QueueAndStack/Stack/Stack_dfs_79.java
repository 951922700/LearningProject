package QueueAndStack.Stack;

public class Stack_dfs_79 {
    /**
     * 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * 回溯+floodfill
     * 前面对个别情况的单独剪枝会快很多
     */
    boolean isHave=false;
    public boolean exist(char[][] board, String word) {
        int rows=board.length;
        int cols=board[0].length;
        if (rows==0)
            return false;
        boolean [][]visited=new boolean[rows][cols];
        char words[]=word.toCharArray();
        //for循环找到第一个匹配的字母
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (board[i][j]==words[0]){
                    visited[i][j]=true;
                    dfs(board,words,i,j,1,visited);
                    visited[i][j]=false;
                    //循环出来如果isHave为Ture结束循环
                    if (isHave)
                        break;
                }

            }
        }
        return isHave;
    }

    //index表示现在匹配的下标
    public void dfs(char[][] board,char[] words,int x,int y,int index,boolean[][] visited){
        if (index==words.length){
            isHave=true;
            return;
        }

        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        for (int k=0;k<4;k++){
            int newx=x+direction[k][0];
            int newy=y+direction[k][1];
            if (notOver(newx,newy,board)&&!visited[newx][newy]&&board[newx][newy]==words[index]){
                visited[newx][newy]=true;
                dfs(board,words,newx,newy,index+1,visited);
                visited[newx][newy]=false;
            }
        }
    }

    public boolean notOver(int x,int y,char[][] board){
        int rows=board.length;
        int cols=board[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }

    public static void main(String[] args) {
        Stack_dfs_79 s=new Stack_dfs_79();
        char[][] board=new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String str="SEE";
        s.exist(board,str);
    }
}
