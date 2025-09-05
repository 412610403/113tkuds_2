
import java.util.*;

public class LC26_RemoveDuplicates_Scores {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[write - 1]) {
                nums[write++] = nums[i];
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int len = removeDuplicates(arr);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
