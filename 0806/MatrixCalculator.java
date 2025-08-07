
public class MatrixCalculator {

    public static int[][] addMatrices(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            System.out.println("錯誤：矩陣維度不同，無法相加");
            return null;
        }

        int rows = a.length, cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            System.out.println("錯誤：矩陣無法相乘");
            return null;
        }

        int rows = a.length, cols = b[0].length, n = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) {
                    max = val;
                }
            }
        }
        return max;
    }

    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val < min) {
                    min = val;
                }
            }
        }
        return min;
    }

    public static void printMatrix(int[][] matrix, String title) {
        System.out.println(title);
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== 矩陣運算器測試 ===\n");

        int[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
        int[][] matrixB = {{7, 8, 9}, {10, 11, 12}};
        int[][] matrixC = {{1, 2}, {3, 4}, {5, 6}};

        printMatrix(matrixA, "矩陣 A (2x3):");
        printMatrix(matrixB, "矩陣 B (2x3):");
        printMatrix(matrixC, "矩陣 C (3x2):");

        System.out.println("1. 矩陣加法 (A + B):");
        int[][] sum = addMatrices(matrixA, matrixB);
        if (sum != null) {
            printMatrix(sum, "結果:");
        }

        System.out.println("2. 矩陣乘法 (A × C):");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        if (product != null) {
            printMatrix(product, "結果:");
        }

        System.out.println("3. 矩陣轉置:");
        printMatrix(transposeMatrix(matrixA), "A 轉置:");
        printMatrix(transposeMatrix(matrixC), "C 轉置:");

        System.out.println("4. 尋找最大值和最小值:");
        System.out.println("矩陣 A 最大值: " + findMax(matrixA));
        System.out.println("矩陣 A 最小值: " + findMin(matrixA));
        System.out.println("矩陣 C 最大值: " + findMax(matrixC));
        System.out.println("矩陣 C 最小值: " + findMin(matrixC));
    }

}
