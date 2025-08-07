
public class RecursiveMathCalculator {

    public static long combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        if (k > n) {
            return 0;
        }

        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static long catalan(int n) {
        if (n <= 1) {
            return 1;
        }

        // 遞迴公式：C(n) = Σ(C(i) × C(n-1-i))
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    public static long hanoi(int n) {
        if (n == 1) {
            return 1;
        }

        return 2 * hanoi(n - 1) + 1;
    }

    public static boolean isPalindrome(int number) {
        String str = String.valueOf(number);
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String str, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        return isPalindromeHelper(str, left + 1, right - 1);
    }

    public static void showCombinationProcess(int n, int k) {
        System.out.printf("計算 C(%d, %d):\n", n, k);
        for (int i = 0; i <= Math.min(k, n - k); i++) {
            long result = combination(n, i);
            System.out.printf("C(%d, %d) = %d\n", n, i, result);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== 遞迴數學計算器 ===\n");

        System.out.println("1. 組合數計算:");
        System.out.printf("C(5, 2) = %d\n", combination(5, 2));
        System.out.printf("C(10, 3) = %d\n", combination(10, 3));
        System.out.printf("C(8, 4) = %d\n", combination(8, 4));
        System.out.println();

        System.out.println("2. 卡塔蘭數計算:");
        for (int i = 0; i <= 6; i++) {
            System.out.printf("C(%d) = %d\n", i, catalan(i));
        }
        System.out.println();

        System.out.println("3. 漢諾塔移動步數:");
        for (int i = 1; i <= 8; i++) {
            System.out.printf("漢諾塔 %d 層: %d 步\n", i, hanoi(i));
        }
        System.out.println();

        System.out.println("4. 回文數判斷:");
        int[] testNumbers = {121, 12321, 123, 1234321, 12345, 1};
        for (int num : testNumbers) {
            System.out.printf("%d 是回文數: %s\n", num, isPalindrome(num));
        }
        System.out.println();

        System.out.println("5. 詳細計算過程:");
        showCombinationProcess(6, 3);

        System.out.println("\n卡塔蘭數應用：");
        System.out.println("C(3) = 5 代表 3 對括號的合法組合有 5 種");
        System.out.println("如：((())), (()()), (())(), ()(()), ()()()");

        System.out.println("\n漢諾塔公式驗證：");
        int n = 4;
        long steps = hanoi(n);
        long formula = (long) Math.pow(2, n) - 1;
        System.out.printf("遞迴計算: %d 層 = %d 步\n", n, steps);
        System.out.printf("公式計算: 2^%d - 1 = %d 步\n", n, formula);
        System.out.printf("結果一致: %s\n", steps == formula);
    }

}
