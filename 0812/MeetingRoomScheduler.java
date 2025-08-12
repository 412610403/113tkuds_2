
import java.util.*;

public class MeetingRoomScheduler {

    // Part 1: 計算最少需要的會議室數量
    public int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }

        // 依據會議開始時間排序
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        // Min Heap 儲存當前會議室的結束時間
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // 如果最早結束的會議已經結束，就可以重用該會議室
            if (!heap.isEmpty() && heap.peek() <= start) {
                heap.poll();
            }

            heap.offer(end);  // 安排此會議到某會議室
        }

        return heap.size();  // heap size 就是需要的最少會議室數
    }

    // Part 2: 有 N 個會議室時，安排最大總會議時間
    public int maxMeetingTimeWithFixedRooms(int[][] meetings, int roomCount) {
        if (meetings == null || meetings.length == 0 || roomCount == 0) {
            return 0;
        }

        // 根據會議結束時間排序
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1]));

        // 儲存每間會議室目前的結束時間
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();
        int totalTime = 0;

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int duration = end - start;

            // 如果目前有 roomCount 間會議室使用中
            if (roomEndTimes.size() == roomCount) {
                // 取最早結束的會議室
                int earliestEnd = roomEndTimes.peek();

                // 如果該會議室在此會議開始前就結束，可排入
                if (earliestEnd <= start) {
                    roomEndTimes.poll();  // 移除
                    roomEndTimes.offer(end);  // 加入新的結束時間
                    totalTime += duration;
                }
                // 否則跳過這場會議（不能安排）
            } else {
                // 還有空的會議室，直接安排
                roomEndTimes.offer(end);
                totalTime += duration;
            }
        }

        return totalTime;
    }

    // 測試主程式
    public static void main(String[] args) {
        MeetingRoomScheduler scheduler = new MeetingRoomScheduler();

        System.out.println("=== 測試案例 1 ===");
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("最少會議室數: " + scheduler.minMeetingRooms(meetings1));  // ➜ 2

        System.out.println("\n=== 測試案例 2 ===");
        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println("最少會議室數: " + scheduler.minMeetingRooms(meetings2));  // ➜ 2

        System.out.println("\n=== 測試案例 3 ===");
        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println("最少會議室數: " + scheduler.minMeetingRooms(meetings3));  // ➜ 2

        System.out.println("\n=== 測試案例 4（有 1 間會議室） ===");
        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        int roomCount = 1;
        System.out.println("最大總會議時間: " + scheduler.maxMeetingTimeWithFixedRooms(meetings4, roomCount));  // ➜ 5
    }
}
