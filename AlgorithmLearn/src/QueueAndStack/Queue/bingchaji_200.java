package QueueAndStack.Queue;

public class bingchaji_200 {
    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
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
     */
    class UnionFind{
        char[][] grid;
        int rows,cols;
        int []parent;//父节点
        int []rank;
        int count;//岛屿数量

        UnionFind(char[][] grid){
            this.grid=grid;
            rows=grid.length;
            cols=grid[0].length;
            count=0;//如果是1 count+1
            parent=new int[rows*cols];
            rank=new int[rows*cols];
            for (int i=0;i<rows;i++){
                for (int j=0;j<cols;j++){
                    if (grid[i][j]=='1'){
                        count++;
                        parent[i*cols+j]=i*cols+j;
                    }
                }
            }
        }

        //路径压缩
        public int find(int x){
            return parent[x]==x?x:(parent[x]=find(parent[x]));
        }

        //秩合并
        public void merge(int i,int j){
            int x=find(i);
            int y=find(j);
            if (x==y) return;//防止重复count--
            if (rank[x]>=rank[y]){
                //深的当根节点
                parent[y]=x;
            }else{
                parent[x]=y;
            }
            if (rank[x]==rank[y]&&x!=y){
                rank[x]++;
            }
            count--;
        }

        public int getCount(){
            return count;
        }

        public boolean notOver(int x,int y){
            return x>=0&&x<rows&&y>=0&&y<cols;
        }
    }
    public int numIslands(char[][] grid) {
        UnionFind unionFind=new UnionFind(grid);
        int rows=grid.length;
        int cols=grid[0].length;
        //遍历整个数组  是1 则把他和附近的1合并 最后置为零  值执行右下快1ms
        int [][] directions=new int[][]{{1,0},{0,1}};
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]=='1'){

                    for (int k=0;k<2;k++){
                        int newx=i+directions[k][0];
                        int newy=j+directions[k][1];
                        if (unionFind.notOver(newx,newy)&&grid[newx][newy]=='1'){
                            //如果1的上下左右也是1 合并
                            unionFind.merge(i*cols+j,newx*cols+newy);
                        }
                    }
                }
            }
        }
        return unionFind.getCount();
    }


}
