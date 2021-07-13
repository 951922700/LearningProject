package ArrayAndString.Array;

public class array03 {
    /**
     * 零矩阵
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出：
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2：
     *
     * 输入：
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出：
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     *
     */
    //思路一：开辟两个一维数组做标记
    //思路二：用首行首列做标记
    public void setZeroes(int[][] matrix) {
        boolean isFirstRowZero=false;
        boolean isFirstColZero=false;

        //判断行是否有0
        for (int i=0;i<matrix[0].length;i++){
            if(matrix[0][i]==0){
                isFirstRowZero=true;
                break;
            }
        }
        //判断列是否有0
        for (int i=0;i<matrix.length;i++){
            if(matrix[i][0]==0){
                isFirstColZero=true;
                break;
            }
        }

        //遍历非一行非一列元素
        for (int i=1;i<matrix.length;i++)
            for (int j=1;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }

        //填0操作
        for (int i=1;i<matrix.length;i++)
            for (int j=1;j<matrix[0].length;j++){
                if(matrix[0][j]==0||matrix[i][0]==0)
                    matrix[i][j]=0;
            }

        for (int i=0;i<matrix.length;i++)
            if (isFirstColZero)
                matrix[i][0]=0;

        for(int j = 0; j < matrix[0].length; j++)
            if (isFirstRowZero)
                matrix[0][j] = 0;

    }

    public static void main(String[] args) {
        int j=0,k=0;
        for (int i=0;i<3;i++){
                j++;
        }
        for (int i=0;i<3;++i){
            k++;
        }
        System.out.println(j+" "+k);
    }
}
