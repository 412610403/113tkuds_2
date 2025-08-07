
import java.util.*;

public class RecursionVsIteration {

    public static long binomialCoefficientRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k > n) {
            return 0;
        }
        return binomialCoefficientRecursive(n - 1, k - 1) + binomialCoefficientRecursive(n - 1, k);
    }

    public static long binomialCoefficientIterative(int n, int k) {
        if (k > n - k) {
            k = n - k;
        }

        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }

    public static long arrayProductRecursive(int[] arr, int index) {
        if (index >= arr.length) {
            return 1;
        }
        return arr[index] * arrayProductRecursive(arr, index + 1);
    }

    public static long arrayProductRecursive(int[] arr) {
        return arrayProductRecursive(arr, 0);
    }

    public static long arrayProductIterative(int[] arr) {
        long result = 1;
        for (int num : arr) {
            result *= num;
        }
        return result;
    }

    public static int countVowelsRecursive(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }

        char ch = Character.toLowerCase(str.charAt(index));
        int current = isVowel(ch) ? 1 : 0;
        return current + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsRecursive(String str) {
        return countVowelsRecursive(str, 0);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isVowel(Character.toLowerCase(ch))) {
                count++;
            }
        }
        return count;
    }

    private static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }

    public static boolean isValidParenthesesRecursive(String str) {
        return checkParenthesesHelper(str, 0, 0);
    }

    private static boolean checkParenthesesHelper
}
