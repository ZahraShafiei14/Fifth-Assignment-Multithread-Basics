package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> tempMatrixProduct;
        List<List<Integer>> matrix_A;
        List<List<Integer>> matrix_B;
        int startRow;
        int endRow;
        int startCol;
        int endCol;

        public BlockMultiplier(List<List<Integer>> tempMatrixProduct, List<List<Integer>> matrix_A, List<List<Integer>> matrix_B, int startRow, int endRow, int startCol, int endCol) {
            this.tempMatrixProduct = tempMatrixProduct;
            this.matrix_A = matrix_A;
            this.matrix_B = matrix_B;
            this.startRow = startRow;
            this.endRow = endRow;
            this.startCol = startCol;
            this.endCol = endCol;
        }

        @Override
        public void run() {
            // multiply matrix
            for (int i = startRow; i < endRow; i++) {
                for (int j = startCol; j < endCol; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrix_A.get(0).size(); k++) {
                        sum += matrix_A.get(i).get(k) * matrix_B.get(k).get(j);
                    }
                    tempMatrixProduct.get(i).set(j, sum);
                }
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        int p = matrix_A.size(); // rows

        int r = matrix_B.get(0).size(); // cols

        //To initialize list with  zeros
        List<List<Integer>> tempMatrixProduct = new ArrayList<>(p);
        for (int i = 0; i < p; i++) {
            List<Integer> row = new ArrayList<>(r);
            for (int j = 0; j < r ; j++) {
                row.add(0);
            }
            tempMatrixProduct.add(row);
        }
        // Divide the quarters among four threads
        Thread[] threads = new Thread[4];
        threads[0] = new Thread(new BlockMultiplier(tempMatrixProduct, matrix_A , matrix_B , 0 , p / 2 , 0 , r / 2));
        threads[1] = new Thread(new BlockMultiplier(tempMatrixProduct, matrix_A , matrix_B , 0 , p / 2 , r / 2 , r));
        threads[2] = new Thread(new BlockMultiplier(tempMatrixProduct, matrix_A , matrix_B , p / 2 , p  , 0 , r / 2));
        threads[3] = new Thread(new BlockMultiplier(tempMatrixProduct, matrix_A , matrix_B , p / 2 , p , r / 2 , r));

        for (Thread thread: threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        return tempMatrixProduct;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
