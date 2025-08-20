
import java.util.*;

public class M06_PalindromeClean {

    static boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    // 回文檢測（雙指標）
    static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !isLetter(s.charAt(left))) {
                left++;
            }
            while (left < right && !isLetter(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
