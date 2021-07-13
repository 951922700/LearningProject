package ArrayAndString.Array;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 *  
 *
 * 示例:
 *
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 *
 * 输出:  [1,2,4,7,5,3,6,8,9]
 *
 */
public class array04 {
        public int[] findDiagonalOrder(int[][] matrix) {

            // Check for empty matrices
            if (matrix == null || matrix.length == 0) {
                return new int[0];
            }

            // Variables to track the size of the matrix
            int N = matrix.length;
            int M = matrix[0].length;

            // The two arrays as explained in the algorithm
            int[] result = new int[N*M];
            int k = 0;
            ArrayList<Integer> intermediate = new ArrayList<Integer>();

            // We have to go over all the elements in the first
            // row and the last column to cover all possible diagonals
            for (int d = 0; d < N + M - 1; d++) {

                // Clear the intermediate array every time we start
                // to process another diagonal
                intermediate.clear();

                // We need to figure out the "head" of this diagonal
                // The elements in the first row and the last column
                // are the respective heads.
                int r = d < M ? 0 : d - M + 1;
                int c = d < M ? d : M - 1;

                // Iterate until one of the indices goes out of scope
                // Take note of the index math to go down the diagonal
                while (r < N && c > -1) {

                    intermediate.add(matrix[r][c]);
                    ++r;
                    --c;
                }

                // Reverse even numbered diagonals. The
                // article says we have to reverse odd
                // numbered articles but here, the numbering
                // is starting from 0 :P
                if (d % 2 == 0) {
                    Collections.reverse(intermediate);
                }

                for (int i = 0; i < intermediate.size(); i++) {
                    result[k++] = intermediate.get(i);
                }
            }
            return result;
        }


    public int[] findDiagonalOrder1(int[][] matrix) {
            //空情况
            if(matrix==null||matrix.length==0)
                return new int[0];

            //记录行列
            int N=matrix.length;
            int M=matrix[0].length;

            //存放结果数组
            int [] result=new int[N*M];

            //记录result存入位置
            int k=0;

            //中间数组，存放一次对角线读入结果，用Collection.reserve可以对list进行翻转
            ArrayList<Integer> intermediate=new ArrayList<Integer>();

            for (int i=0;i<N+M-1;i++){
                //确定每一次遍历的对角线的初始位置的行列下标
                int r=i<M?0:i-(M-1);
                int c=i<M?i:M-1;

                //清空内存
                intermediate.clear();

                while(r<N&&c>-1){
                    intermediate.add(matrix[r][c]);
                    r++;
                    c--;
                }

                //奇数
                if(i%2==0)
                    Collections.reverse(intermediate);

                for (int j=0;j<intermediate.size();j++)
                    result[k++]=intermediate.get(j);
            }
            return result;
    }
}
