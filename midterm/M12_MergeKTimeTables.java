
import java.util.*;

public class M12_MergeKTimeTables {

    static class Node {

        int time;
        int listIndex;
        int elementIndex;

        Node(int time, int listIndex, int elementIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                temp.add(sc.nextInt());
            }
            lists.add(temp);
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.time - b.time);

        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(new Node(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            merged.add(curr.time);

            int nextIndex = curr.elementIndex + 1;
            if (nextIndex < lists.get(curr.listIndex).size()) {
                minHeap.offer(new Node(lists.get(curr.listIndex).get(nextIndex), curr.listIndex, nextIndex));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            System.out.print(merged.get(i));
            if (i < merged.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        sc.close();
    }
}

/*
 Time Complexity：O(N log K)
 說明：假設總元素數為N，K條已排序列表
 每個元素最多進出 heap 一次 → O(N log K)
 */
