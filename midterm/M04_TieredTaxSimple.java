
import java.util.*;

public class M04_TieredTaxSimple {

    // 計算單筆收入的稅額
    static int calcTax(int income) {
        int tax = 0;

        // 區間一：0 ~ 120000，5%
        int tier1 = Math.min(income, 120000);
        tax += tier1 * 5 / 100;
        if (income <= 120000) {
            return tax;
        }

        // 區間二：120001 ~ 500000，12%
        int tier2 = Math.min(income, 500000) - 120000;
        tax += tier2 * 12 / 100;
        if (income <= 500000) {
            return tax;
        }

        // 區間三：500001 ~ 1000000，20%
        int tier3 = Math.min(income, 1000000) - 500000;
        tax += tier3 * 20 / 100;
        if (income <= 1000000) {
            return tax;
        }

        // 區間四：1000001 以上，30%
        int tier4 = income - 1000000;
        tax += tier4 * 30 / 100;

        return tax;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = 0;

        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            int tax = calcTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }

        int avg = total / n;
        System.out.println("Average: " + avg);
    }
}
/*
Time Complexity：O(n)
說明：計算稅額 calcTax
最多 4 段計算，固定次數 → O(1)
處理 n 筆收入，單筆 O(1)，共 n 筆 → O(n)
 */
