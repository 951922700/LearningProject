package LanQiaoBei;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class lan_9_i {
    /**
     * 全球变暖
     *
     * 你有一张某海域NxN像素的照片，"."表示海洋、"#"表示陆地，如下所示：
     *
     * .......
     * .##....
     * .##....
     * ....##.
     * ..####.
     * ...###.
     * .......
     *
     * 其中"上下左右"四个方向上连在一起的一片陆地组成一座岛屿。例如上图就有2座岛屿。  
     *
     * 由于全球变暖导致了海面上升，科学家预测未来几十年，岛屿边缘一个像素的范围会被海水淹没。具
     * 体来说如果一块陆地像素与海洋相邻(上下左右四个相邻像素中有海洋)，它就会被淹没。  
     *
     * 例如上图中的海域未来会变成如下样子：
     *
     * .......
     * .......
     * .......
     * .......
     * ....#..
     * .......
     * .......
     *
     * 请你计算：依照科学家的预测，照片中有多少岛屿会被完全淹没。  
     *
     * 【输入格式】
     * 第一行包含一个整数N。  (1 <= N <= 1000)  
     * 以下N行N列代表一张海域照片。  
     *
     * 照片保证第1行、第1列、第N行、第N列的像素都是海洋。  
     *
     * 【输出格式】
     * 一个整数表示答案。
     *
     * 计算前后岛屿数量 <0说明没有被淹没
     */
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        scan.nextLine();//吸收回车
        char [][]ch=new char[n][n];
        int oldN,newN;//旧数量  新数量
        for (int i=0;i<n;i++)
            ch[i]=scan.nextLine().toCharArray();
        oldN=bfs(n,ch);
        /**
         *         x+y
         */
        //上下左右
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        //预处理 把靠近水的弄掉
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (ch[i][j]=='#'){
                    //看上下左右是否有水
                    for (int k=0;k<4;k++){
                        int newx=i+direction[k][0];
                        int newy=j+direction[k][1];
                        if (notOver(ch,i,j)&&ch[newx][newy]=='.')
                        {
                            //没越界而且是水的话 淹没该陆地
                            ch[i][j]='*';
                            break;//接下来不用再看有没有水了
                        }
                    }
                }
            }
        }

        //将*变回.
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (ch[i][j]=='*'){
                    ch[i][j]='.';
                }
            }
        }
        newN=bfs(n,ch);

        System.out.println(oldN-newN<0?0:oldN-newN);
    }

    public static boolean notOver(char [][]ch,int x,int y){
        int rows=ch.length;
        int cols=ch[0].length;
        return x>=0&&x<rows&&y>=0&&y<cols;
    }

    public static int bfs(int n,char[][]ch){
        //bfs 计算连通数量
        Queue<Integer> queue=new LinkedList<>();
        boolean marked[][]=new boolean[n][n];
        int direction[][]=new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        int nums=0;
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++) {
                if (!marked[i][j]&&ch[i][j]=='#'){
                    nums++;
                    marked[i][j]=true;
                    queue.offer(i*n+j);
                }

                while(!queue.isEmpty()){
                    int cur=queue.poll();
                    int x=cur/n;
                    int y=cur%n;
                    for (int k=0;k<4;k++){
                        int newx=x+direction[k][0];
                        int newy=y+direction[k][1];
                        if (notOver(ch,newx,newy)&&!marked[newx][newy]&&ch[newx][newy]=='#'){
                            marked[newx][newy]=true;
                            queue.offer(newx*n+newy);
                        }
                    }
                }

            }
        }
        return nums;
    }
}
