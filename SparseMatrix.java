import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class SparceMat {
    private final int row;
    private final int col;
    private final List<int[]> elements;

    // constructor
    public SparceMat(int row, int col, List<int[]> element) {
        this.row = row;
        this.col = col;
        this.elements = List.copyOf(element);
    }

    // getter
    public int getRow() {
        try {
            return this.row;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public int getCol() {
        try {
            return this.col;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Calculates transpose of the matrix and returns it
     * 
     * @return transposeMat such as it is transpose of the given matrixs
     */
    public SparceMat transpose() {
        try {
            List<int[]> transeposeMat = new ArrayList<>();

            for (int[] element : elements) {
                transeposeMat.add(new int[] { element[1], element[0], element[2] });
            }

            return new SparceMat(col, row, transeposeMat);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SparceMat(row, col, elements);
        }
    }

    /**
     * Checks if the matrix is symmetric
     * 
     * @return true if matrix is symmetrix else
     *         false
     */
    public boolean isSymmetric() {
        try {

            if (this.row != this.col) {
                return false;
            }

            boolean found = false;
            for (int[] element : this.elements) {
                for (int[] el : this.elements) {
                    if (element[0] == el[1] && element[1] == el[0] && element[2] == el[2]) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Adds two matrices if row and column are same
     * 
     * @param s second matrix
     * @return added matrix that is sum of two matrices
     */
    public SparceMat add(SparceMat s) {

        try {

            if (this.row != s.row || this.col != s.col) {
                throw new IllegalArgumentException("Both matrices should have same dimention");
            }

            HashMap<String, Integer> mpp = new HashMap<>();

            List<int[]> added = new ArrayList<>();

            for (int[] el1 : this.elements) {
                mpp.put(el1[0] + "," + el1[1], el1[2]);
            }

            for (int[] el2 : s.elements) {
                mpp.merge(el2[0] + "," + el2[1], el2[2], Integer::sum);
            }

            for (Map.Entry<String, Integer> e : mpp.entrySet()) {
                String[] temp = e.getKey().split(",");
                added.add(new int[] { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), e.getValue() });
            }

            return new SparceMat(this.row, this.col, added);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SparceMat(row, col, elements);
        }
    }

    /**
     * multiply two matrices if colum of firs matrix and column of second matrix are
     * same
     * 
     * @param s second matrix
     * @return added matrix that is sum of two matrices
     */
    public SparceMat multiply(SparceMat s) {

        try {

            if (this.col != s.row) {
                throw new IllegalArgumentException("Dimention Mismatch.");
            }

            List<int[]> mulMat = new ArrayList<>();
            HashMap<String, Integer> mpp = new HashMap<>();

            for (int[] elem1 : this.elements) {
                for (int[] elem2 : s.elements) {
                    if (elem1[1] == elem2[0]) {
                        mpp.merge(elem1[0] + "," + elem2[1], (elem1[2] * elem2[2]), Integer::sum);
                    }
                }
            }

            for (Map.Entry<String, Integer> mp : mpp.entrySet()) {
                String[] temp = mp.getKey().split(",");
                mulMat.add(new int[] { Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), mp.getValue() });
            }

            return new SparceMat(row, s.col, mulMat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new SparceMat(row, col, elements);
        }
    }

    public static void printSparce(SparceMat mat) {
        try {

            int[][] arr = new int[mat.row][mat.col];

            for (int[] element : mat.elements) {
                arr[element[0]][element[1]] = element[2];
            }

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.print(arr[i][j] + ", ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

public class SparseMatrix {

    public int intScanner() {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            try {
                n = sc.nextInt();
                if (n >= 0) {
                    break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Enter integer value only");
            }
        }
        // sc.close();
        return n;
    }

    public static void main(String[] args) {
        SparseMatrix sm = new SparseMatrix();
        Scanner sc = new Scanner(System.in);

        try {

            List<int[]> ls = new ArrayList<>();
            List<int[]> ls2 = new ArrayList<>();

            System.out.println("Sparse matrix...");
            System.out.println("Enter row : ");
            int row = sm.intScanner();
            System.out.println("Enter column : ");
            int col = sm.intScanner();
            System.out.println("How many non-zero elements are there? ");
            int elem = sm.intScanner();

            for (int i = 0; i < elem; i++) {
                int[] temp = new int[3];
                System.out.println("Enter row for " + (i + 1) + " element ");
                temp[0] = sm.intScanner();
                System.out.println("Enter column for " + (i + 1) + " element ");
                temp[1] = sm.intScanner();
                System.out.println("Enter value for " + (i + 1) + " element ");
                temp[2] = sm.intScanner();

                ls.add(temp);
            }

            SparceMat m1 = new SparceMat(row, col, ls);

            while (true) {
                System.out.println("What you want to do?");
                System.out.println("1.\tTranspose");
                System.out.println("2.\tCheck Symmetry");
                System.out.println("3.\tAdd");
                System.out.println("4.\tMultiply");

                int action = sm.intScanner();

                switch (action) {
                    case 1:
                        SparceMat.printSparce(m1.transpose());
                        break;

                    case 2:
                        if (m1.isSymmetric()) {
                            System.out.println("It is symmetric");
                        } else {
                            System.out.println("It is not symmetric");
                        }
                        break;

                    case 3:
                        System.out.println("Sparse matrix 2...");
                        System.out.println("Enter row : ");
                        row = sm.intScanner();
                        System.out.println("Enter row : ");
                        col = sm.intScanner();
                        System.out.println("How many non-zero elements are there? ");
                        elem = sm.intScanner();

                        for (int i = 0; i < elem; i++) {
                            int[] temp = new int[3];
                            System.out.println("Enter row for " + (i + 1) + " element ");
                            temp[0] = sm.intScanner();
                            System.out.println("Enter column for " + (i + 1) + " element ");
                            temp[1] = sm.intScanner();
                            System.out.println("Enter value for " + (i + 1) + " element ");
                            temp[2] = sm.intScanner();

                            ls2.add(temp);
                        }

                        SparceMat m2 = new SparceMat(row, col, ls2);

                        SparceMat.printSparce(m1.add(m2));
                        break;

                    case 4:
                        System.out.println("Sparse matrix 2...");
                        System.out.println("Enter row : ");
                        row = sm.intScanner();
                        System.out.println("Enter row : ");
                        col = sm.intScanner();
                        System.out.println("How many non-zero elements are there? ");
                        elem = sm.intScanner();

                        for (int i = 0; i < elem; i++) {
                            int[] temp = new int[3];
                            System.out.println("Enter row for " + (i + 1) + " element ");
                            temp[0] = sm.intScanner();
                            System.out.println("Enter column for " + (i + 1) + " element ");
                            temp[1] = sm.intScanner();
                            System.out.println("Enter value for " + (i + 1) + " element ");
                            temp[2] = sm.intScanner();

                            ls2.add(temp);
                        }

                        m2 = new SparceMat(row, col, ls2);

                        System.out.println(m1.equals(m2));

                        SparceMat.printSparce(m1.multiply(m2));
                        break;

                    default:
                        break;
                }

                System.out.println("Press 1 if want to continue....");
                int num = sm.intScanner();

                if (num != 1) {
                    break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Input should be Integer");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }

    }
}