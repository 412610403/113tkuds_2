
import java.util.*;

public class LC17_PhoneCombos_CSShift {

    private static final String[] KEYS = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private static void backtrack(List<String> res, StringBuilder sb, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = KEYS[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(res, sb, digits, idx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        List<String> combos = letterCombinations(digits);
        for (String s : combos) {
            System.out.println(s);
        }
    }
}
