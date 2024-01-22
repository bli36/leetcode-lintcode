import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SparseMatrixMultiplicationSolution {

        /**
         * @param A: a sparse matrix
         * @param B: a sparse matrix
         * @return: the result of A * B
         */
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
        int resultRow = A.length;
        int resultCol = B[0].length;
        int n = B.length;
        int p = A[0].length;
        int[][] result = new int[resultRow][resultCol];
        //pre process the multiplicand
        List<List<Integer>> countNoneZeroCol = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            countNoneZeroCol.add(new ArrayList<>());
            for (int j = 0; j < resultCol; j++) {
                if (B[i][j] != 0) {
                    countNoneZeroCol.get(i).add(j);
                }
            }
        }
        System.out.println(Arrays.deepToString(countNoneZeroCol.toArray()));

        for (int i = 0; i < resultRow; i++) {
            for (int j = 0; j < p; j++) {
                if (A[i][j] == 0) {
                    continue;
                }

                for (int m : countNoneZeroCol.get(j)) {
                    result[i][m] += A[i][j] * B[j][m];
                }

            }

        }
        return result;
    }
    public static void main(String[] args) {
        int[][] A = new int[][] {{0,1},{0,0},{0,1}};
        int[][] B = new int[][] {{1,0}, {1,0}};
        SparseMatrixMultiplicationSolution s = new SparseMatrixMultiplicationSolution();
        int[][] result = s.multiply(A, B); // Display the string.
        System.out.println("What");
        System.out.println(Arrays.deepToString(result));
    }

}


