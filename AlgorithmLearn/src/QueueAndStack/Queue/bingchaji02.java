package QueueAndStack.Queue;

/**
 * 岛屿数量
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
public class bingchaji02 {
    class UnionFind{
        int count=0;//岛屿数量  也即连通分支数
        int [] parent;//父节点
        int [] rank;//秩
        //页可以用一个boolean存是否合并过
        public UnionFind(char [][] grid){
            //初始化函数
            //m 行 n 列
            int m=grid.length,n=grid[0].length;
            parent=new int[m*n];
            rank=new int[m*n];
            for (int i=0;i<m;i++){
                for (int j=0;j<n;j++){
                    //是陆地才存 且连通分支数加1
                    if (grid[i][j]=='1'){
                        parent[i*n+j]=i*n+j;
                        count++;
                    }
                    rank[i*n+j]=0;//初始深度设置为0
                }
            }
        }

        public int find(int x){
            return x==parent[x]?x:(parent[x]=find(parent[x]));
        }

        public void merge(int x,int y){
            //找到x y根节点
            int rootx=find(x);
            int rooty=find(y);
            if (rootx!=rooty){
                if (rank[rootx]>rank[rooty]){
                    parent[rooty]=rootx;
                }else if(rank[rootx]<rank[rooty]){
                    parent[rootx]=rooty;
                }else{
                    parent[rooty]=rootx;
                    rank[rootx]++;
                }
                count--;
            }
        }
        public int getCount(){
            return this.count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid.length==0||grid==null) return 0;
        int m=grid.length;
        int n=grid[0].length;
        UnionFind unionFind=new UnionFind(grid);
        //只需遍历右 和 下
        int[][] directions=new int[][]{{1,0},{0,1}};
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]=='1'){
                    //因为只遍历右和下 所以不用设置为0
                    for (int k=0;k<2;k++){
                        int newx=i+directions[k][0];
                        int newy=j+directions[k][1];
                        if (newx<m&&newy<n&&grid[newx][newy]=='1')
                        unionFind.merge(i*n+j,newx*n+newy);
                    }
                }
            }
        }
        return unionFind.getCount();
    }

    public static void main(String[] args) {
        char[][] grid={{'1','1','0','0','0'},{'1','1','0','0','0'}
                        ,{'0','0','1','0','0'},{'0','0','0','1','1'}};
        bingchaji02 bingchaji02=new bingchaji02();
        bingchaji02.numIslands(grid);
    }
}
