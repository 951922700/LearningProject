package ArrayAndString.Array;

public class array02 {
    /**
     * 旋转矩阵
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     *
     * 不占用额外内存空间能否做到？
     *
     *  
     *
     * 示例 1:
     *
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * 示例 2:
     *
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     *
     */

    //解法一
    public void rotate(int[][] matrix) {
        int[][] temp=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;++i){//多少行
            for (int j=matrix[0].length-1;j>=0;--j){//多少列
                temp[i][Math.abs(j-(matrix[0].length-1))]=matrix[j][i];
            }
        }
        for (int i=0;i<temp.length;++i)
            for (int j=0;j<temp.length;++j){
                //System.out.print(" "+temp[i][j]);
                matrix[i][j]=temp[i][j];
            }
        for (int i=0;i<temp.length;++i)
            for (int j=0;j<temp.length;++j){
                System.out.print(" "+matrix[i][j]);
                if(j==temp.length-1) System.out.print("\n");
            }
    }

    public static void main(String[] args) {
        array02 array02=new array02();
        int[][] temp={{1,2,3},{4,5,6},{7,8,9}};
        array02.rotate(temp);
    }

    //解法二   时间O(N平方)  空间O(1)
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }



}
