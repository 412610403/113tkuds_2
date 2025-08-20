
import java.util.*;

public class M05_GCD_LCM_Recursive {

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long lcm(long a, long b, long g) {
        return (a / g) * b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = lcm(a, b, g);

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
 Time Complexity：O(log(min(a, b)))
 說明：每次遞迴將參數縮小，最多約 log(min(a, b)) 次遞迴。
 */
