
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        Map<Character, Integer> last = new HashMap<>();
        int left = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last.containsKey(c)) {
                left = Math.max(left, last.get(c) + 1);
            }
            last.put(c, i);
            ans = Math.max(ans, i - left + 1);
        }
        System.out.println(ans);
    }
}

/*解題思路：
利用滑動視窗：右指針往右走，若遇到重複字元，左指針移到該字元上次出現位置+1。
每次更新當前視窗長度，取最大值。
時間 O(n)，空間 O(k)，k 為字元種類數。
 */
