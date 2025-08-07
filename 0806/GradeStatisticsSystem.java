
public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        double average = calculateAverage(grades);
        int max = findMax(grades);
        int min = findMin(grades);
        int[] gradeCounts = countGradeLevels(grades);
        int aboveAverageCount = countAboveAverage(grades, average);

        printReport(grades, average, max, min, gradeCounts, aboveAverageCount);
    }

    public static double calculateAverage(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
    }

    public static int findMax(int[] grades) {
        int max = grades[0];
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }

    public static int findMin(int[] grades) {
        int min = grades[0];
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }

    public static int[] countGradeLevels(int[] grades) {
        int[] counts = new int[5]; // A, B, C, D, F

        for (int grade : grades) {
            if (grade >= 90) {
                counts[0]++; // A
            } else if (grade >= 80) {
                counts[1]++; // B
            } else if (grade >= 70) {
                counts[2]++; // C
            } else if (grade >= 60) {
                counts[3]++; // D
            } else {
                counts[4]++; // F
            }
        }

        return counts;
    }

    public static int countAboveAverage(int[] grades, double average) {
        int count = 0;
        for (int grade : grades) {
            if (grade > average) {
                count++;
            }
        }
        return count;
    }

    public static void printReport(int[] grades, double average, int max, int min, int[] gradeCounts, int aboveAverageCount) {
        System.out.println("=== 成績報表 ===");
        System.out.print("成績列表: ");
        for (int grade : grades) {
            System.out.print(grade + " ");
        }
        System.out.println();

        System.out.printf("平均成績: %.2f%n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);

        System.out.println("等第統計:");
        System.out.println("A (90~100): " + gradeCounts[0] + " 人");
        System.out.println("B (80~89): " + gradeCounts[1] + " 人");
        System.out.println("C (70~79): " + gradeCounts[2] + " 人");
        System.out.println("D (60~69): " + gradeCounts[3] + " 人");
        System.out.println("F (<60): " + gradeCounts[4] + " 人");

        System.out.println("高於平均分的學生人數: " + aboveAverageCount + " 人");
    }
}
