
import java.util.*;

public class AdvancedStringRecursion {

    public static List<String> generatePermutations(String str) {
        List<String> result = new ArrayList<>();
        generatePermutationsHelper(str.toCharArray(), 0, result);
        return result;
    }

    private static void generatePermutationsHelper(char[] chars, int index, List<String> result) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            generatePermutationsHelper(chars, index + 1, result);
            swap(chars, index, i); // 回溯
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static boolean stringMatch(String text, String pattern) {
        return stringMatchHelper(text, pattern, 0, 0);
    }

    private static boolean stringMatchHelper(String text, String pattern, int textIndex, int patternIndex) {
        if (patternIndex == pattern.length()) {
            return true;
        }

        if (textIndex == text.length()) {
            return false;
        }

        if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
            return stringMatchHelper(text, pattern, textIndex + 1, patternIndex + 1);
        }

        return stringMatchHelper(text, pattern, textIndex + 1, 0);
    }

    public static String removeDuplicates(String str) {
        if (str.length() <= 1) {
            return str;
        }

        char firstChar = str.charAt(0);
        String restString = str.substring(1);

        String processedRest = removeDuplicates(restString);

        if (processedRest.indexOf(firstChar) != -1) {
            return processedRest;
        } else {
            return firstChar + processedRest;
        }
    }

    public static List<String> generateSubstrings(String str) {
        List<String> result = new ArrayList<>();
        generateSubstringsHelper(str, 0, "", result);
        return result;
    }

    private static void generateSubstringsHelper(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                result.add(current);
            }
            return;
        }

        generateSubstringsHelper(str, index + 1, current, result);

        generateSubstringsHelper(str, index + 1, current + str.charAt(index), result);
    }

    public static List<String> generateContinuousSubstrings(String str) {
        List<String> result = new ArrayList<>();
        generateContinuousHelper(str, 0, result);
        return result;
    }

    private static void generateContinuousHelper(String str, int start, List<String> result) {
        if (start >= str.length()) {
            return;
        }

        for (int end = start + 1; end <= str.length(); end++) {
            result.add(str.substring(start, end));
        }

        generateContinuousHelper(str, start + 1, result);
    }

    public static List<Integer> findAllMatches(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        findAllMatchesHelper(text, pattern, 0, matches);
        return matches;
    }

    private static void findAllMatchesHelper(String text, String pattern, int start, List<Integer> matches) {
        if (start > text.length() - pattern.length()) {
            return;
        }

        if (text.substring(start, start + pattern.length()).equals(pattern)) {
            matches.add(start);
        }

        findAllMatchesHelper(text, pattern, start + 1, matches);
    }

    public static void main(String[] args) {
        System.out.println("=== 字串遞迴處理進階 ===\n");

        System.out.println("1. 字串排列組合:");
        String testStr1 = "ABC";
        List<String> permutations = generatePermutations(testStr1);
        System.out.printf("字串 \"%s\" 的所有排列 (%d 個):\n", testStr1, permutations.size());
        for (String perm : permutations) {
            System.out.print(perm + " ");
        }
        System.out.println("\n");

        System.out.println("2. 字串匹配演算法:");
        String text = "Hello World Programming";
        String[] patterns = {"World", "Program", "Java", "Hello"};

        for (String pattern : patterns) {
            boolean found = stringMatch(text, pattern);
            System.out.printf("在 \"%s\" 中尋找 \"%s\": %s\n", text, pattern, found ? "找到" : "未找到");

            if (found) {
                List<Integer> positions = findAllMatches(text, pattern);
                System.out.printf("  位置: %s\n", positions);
            }
        }
        System.out.println();

        System.out.println("3. 移除重複字符:");
        String[] testStrings = {"programming", "hello", "aabbcc", "abcabc"};

        for (String str : testStrings) {
            String result = removeDuplicates(str);
            System.out.printf("\"%s\" -> \"%s\"\n", str, result);
        }
        System.out.println();

        System.out.println("4. 子字串組合:");
        String testStr2 = "abc";

        List<String> allSubstrings = generateSubstrings(testStr2);
        System.out.printf("字串 \"%s\" 的所有子序列 (%d 個):\n", testStr2, allSubstrings.size());
        Collections.sort(allSubstrings, (a, b) -> {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            }
            return a.compareTo(b);
        });
        for (String substr : allSubstrings) {
            System.out.print("\"" + substr + "\" ");
        }
        System.out.println();

        List<String> continuousSubstrings = generateContinuousSubstrings(testStr2);
        System.out.printf("字串 \"%s\" 的所有連續子字串 (%d 個):\n", testStr2, continuousSubstrings.size());
        for (String substr : continuousSubstrings) {
            System.out.print("\"" + substr + "\" ");
        }
        System.out.println("\n");

        System.out.println("5. 複雜範例測試:");
        String complexStr = "ABCD";
        System.out.printf("字串 \"%s\" 的統計資訊:\n", complexStr);

        List<String> complexPerms = generatePermutations(complexStr);
        System.out.printf("排列數量: %d (應為 %d!)\n", complexPerms.size(), factorial(complexStr.length()));

        List<String> complexSubs = generateSubstrings(complexStr);
        System.out.printf("子序列數量: %d (應為 2^%d - 1)\n", complexSubs.size(), complexStr.length());

        List<String> complexCont = generateContinuousSubstrings(complexStr);
        System.out.printf("連續子字串數量: %d\n", complexCont.size());

        // 6. 效能測試
        System.out.println("\n=== 效能測試 ===");
        String perfTest = "ABCDE";

        long startTime = System.nanoTime();
        List<String> perfPerms = generatePermutations(perfTest);
        long permTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        String perfRemoved = removeDuplicates("aabbccddee");
        long removeTime = System.nanoTime() - startTime;

        System.out.printf("生成 \"%s\" 的 %d 個排列耗時: %.2f ms\n",
                perfTest, perfPerms.size(), permTime / 1_000_000.0);
        System.out.printf("移除重複字符耗時: %.2f ms\n", removeTime / 1_000_000.0);
    }

    private static int factorial(int n) {
        return n <= 1 ? 1 : n * factorial(n - 1);
    }

}
