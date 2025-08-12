
import java.util.*;

public class Task {

    String name;
    int priority;
    long timestamp; // 用於相同優先級時的排序

    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.timestamp = System.nanoTime();
    }

// 用於 changePriority 時更新優先級和時間戳記
    void updatePriority(int newPriority) {
        this.priority = newPriority;
        this.timestamp = System.nanoTime(); // 更新時間戳記
    }

    @Override
    public String toString() {
        return name + "(優先級:" + priority + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

public class PriorityQueueWithHeap {

    private final PriorityQueue<Task> taskQueue;

    public PriorityQueueWithHeap() {
        // 自定義比較器：優先級高的先執行，相同優先級則先進先出
        taskQueue = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority); // 高優先級在前
            }
            return Long.compare(a.timestamp, b.timestamp); // 時間早的在前
        });
    }

    /**
     * 添加任務
     */
    public void addTask(String name, int priority) {
        // 檢查是否已存在同名任務
        if (findTask(name) != null) {
            System.out.println("任務 '" + name + "' 已存在，無法重複添加");
            return;
        }

        Task task = new Task(name, priority);
        taskQueue.offer(task);
        System.out.println("新增任務: " + task
                + " (當前佇列大小: " + taskQueue.size() + ")");
    }

    /**
     * 執行優先級最高的任務
     */
    public Task executeNext() {
        if (taskQueue.isEmpty()) {
            System.out.println("沒有待執行的任務");
            return null;
        }
        Task task = taskQueue.poll();
        System.out.println("執行任務: " + task
                + " (剩餘任務: " + taskQueue.size() + ")");
        return task;
    }

    /**
     * 查看下一個要執行的任務（不移除）
     */
    public Task peek() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        return taskQueue.peek();
    }

    /**
     * 修改任務優先級
     */
    public boolean changePriority(String name, int newPriority) {
        Task targetTask = findTask(name);

        if (targetTask == null) {
            System.out.println("找不到任務: " + name);
            return false;
        }

        // 記錄舊的優先級
        int oldPriority = targetTask.priority;

        // 從佇列中移除任務
        taskQueue.remove(targetTask);

        // 更新優先級和時間戳記
        targetTask.updatePriority(newPriority);

        // 重新加入佇列
        taskQueue.offer(targetTask);

        System.out.println("修改任務 '" + name + "' 優先級: "
                + oldPriority + " → " + newPriority);
        return true;
    }

    /**
     * 查找指定名稱的任務
     */
    private Task findTask(String name) {
        for (Task task : taskQueue) {
            if (task.name.equals(name)) {
                return task;
            }
        }
        return null;
    }

    /**
     * 檢查佇列是否為空
     */
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    /**
     * 獲取佇列大小
     */
    public int size() {
        return taskQueue.size();
    }

    /**
     * 顯示待執行任務狀態
     */
    public void showPendingTasks() {
        if (taskQueue.isEmpty()) {
            System.out.println("沒有待執行的任務");
        } else {
            System.out.println("下一個任務: " + peek());
            System.out.println("待執行任務總數: " + taskQueue.size());

            // 顯示所有任務（按優先級排序）
            System.out.print("所有待執行任務: ");
            List<Task> sortedTasks = new ArrayList<>(taskQueue);
            sortedTasks.sort((a, b) -> {
                if (a.priority != b.priority) {
                    return Integer.compare(b.priority, a.priority);
                }
                return Long.compare(a.timestamp, b.timestamp);
            });

            for (int i = 0; i < sortedTasks.size(); i++) {
                if (i > 0) {
                    System.out.print(" → ");
                }
                System.out.print(sortedTasks.get(i).name);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap scheduler = new PriorityQueueWithHeap();

        System.out.println("=== 任務調度系統示範 ===");

        // 測試案例：添加任務
        System.out.println("\n1. 添加任務:");
        scheduler.addTask("備份", 1);
        scheduler.addTask("緊急修復", 5);
        scheduler.addTask("更新", 3);

        System.out.println("\n2. 當前狀態:");
        scheduler.showPendingTasks();

        // 測試 peek 功能
        System.out.println("\n3. 查看下一個任務:");
        Task nextTask = scheduler.peek();
        if (nextTask != null) {
            System.out.println("下一個要執行的任務: " + nextTask);
        }

        // 測試 changePriority 功能
        System.out.println("\n4. 修改任務優先級:");
        scheduler.changePriority("備份", 6);  // 將備份優先級提高到6
        scheduler.changePriority("不存在的任務", 10);  // 測試不存在的任務

        System.out.println("\n5. 修改後的狀態:");
        scheduler.showPendingTasks();

        // 執行所有任務
        System.out.println("\n6. 開始執行任務:");
        while (!scheduler.isEmpty()) {
            scheduler.executeNext();
        }

        System.out.println("\n=== 額外測試：相同優先級任務 ===");

        // 測試相同優先級的任務順序
        scheduler.addTask("任務A", 3);
        simulateDelay(); // 使用靜態方法避免在迴圈中使用 sleep
        scheduler.addTask("任務B", 3);
        simulateDelay();
        scheduler.addTask("任務C", 3);

        System.out.println("\n相同優先級任務執行順序:");
        while (!scheduler.isEmpty()) {
            scheduler.executeNext();
        }

        System.out.println("\n所有任務執行完成！");
    }

    /**
     * 模擬延遲，確保時間戳記不同（避免在迴圈中使用 Thread.sleep）
     */
    private static void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
