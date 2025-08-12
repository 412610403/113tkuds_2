
import java.util.*;

/**
 * 多層級 LRU 快取系統（L1, L2, L3）
 */
public class MultiLevelCacheSystem {

    // 快取層級
    private class CacheLevel {

        int capacity;
        int cost;
        Map<Integer, CacheEntry> map;
        PriorityQueue<CacheEntry> heap; // 依分數排序

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            this.heap = new PriorityQueue<>();
        }
    }

    // 快取項目
    private class CacheEntry implements Comparable<CacheEntry> {

        int key;
        String value;
        int freq;      // 存取次數
        long time;     // 最近存取時間（越小越舊）
        int level;     // 所在層級

        CacheEntry(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.time = System.nanoTime();
            this.level = level;
        }

        double score() {
            // 分數越高越應該往上層放
            return freq * 1.0 / cacheLevels[level].cost;
        }

        @Override
        public int compareTo(CacheEntry o) {
            // 分數低的優先移除
            int cmp = Double.compare(this.score(), o.score());
            if (cmp == 0) {
                return Long.compare(this.time, o.time); // 同分數時移除最舊
            }
            return cmp;
        }
    }

    private final CacheLevel[] cacheLevels; // L1, L2, L3
    private final int LEVELS = 3;

    public MultiLevelCacheSystem() {
        cacheLevels = new CacheLevel[LEVELS];
        cacheLevels[0] = new CacheLevel(2, 1);  // L1
        cacheLevels[1] = new CacheLevel(5, 3);  // L2
        cacheLevels[2] = new CacheLevel(10, 10); // L3
    }

    /**
     * 取得資料
     */
    public String get(int key) {
        for (int i = 0; i < LEVELS; i++) {
            CacheLevel level = cacheLevels[i];
            if (level.map.containsKey(key)) {
                CacheEntry entry = level.map.get(key);
                entry.freq++;
                entry.time = System.nanoTime();
                // 更新 heap
                level.heap.remove(entry);
                level.heap.offer(entry);
                // 嘗試往上層搬
                promote(entry);
                return entry.value;
            }
        }
        return null; // 不存在
    }

    /**
     * 新增或更新資料
     */
    public void put(int key, String value) {
        // 先檢查是否已存在 -> 更新
        for (int i = 0; i < LEVELS; i++) {
            CacheLevel level = cacheLevels[i];
            if (level.map.containsKey(key)) {
                CacheEntry entry = level.map.get(key);
                entry.value = value;
                get(key); // 直接當成一次存取
                return;
            }
        }
        // 新資料放 L1
        CacheEntry entry = new CacheEntry(key, value, 0);
        addToLevel(entry, 0);
    }

    // 放入指定層級（可能會觸發逐出）
    private void addToLevel(CacheEntry entry, int levelIndex) {
        CacheLevel level = cacheLevels[levelIndex];
        if (level.map.size() >= level.capacity) {
            evictFromLevel(levelIndex);
        }
        entry.level = levelIndex;
        level.map.put(entry.key, entry);
        level.heap.offer(entry);
    }

    // 從層級逐出一個最低分數的項目 -> 放到下一層
    private void evictFromLevel(int levelIndex) {
        CacheLevel level = cacheLevels[levelIndex];
        CacheEntry toRemove = level.heap.poll();
        if (toRemove != null) {
            level.map.remove(toRemove.key);
            if (levelIndex + 1 < LEVELS) {
                addToLevel(toRemove, levelIndex + 1);
            }
        }
    }

    // 往上層搬（如果分數足夠高）
    private void promote(CacheEntry entry) {
        int currentLevel = entry.level;
        if (currentLevel == 0) {
            return; // 已經在 L1

        }
        double score = entry.score();
        double upperScoreThreshold = 1.0 / cacheLevels[currentLevel - 1].cost;
        if (score > upperScoreThreshold) {
            // 從當前層移除
            cacheLevels[currentLevel].map.remove(entry.key);
            cacheLevels[currentLevel].heap.remove(entry);
            // 加入上一層
            addToLevel(entry, currentLevel - 1);
        }
    }

    public void printCacheState() {
        for (int i = 0; i < LEVELS; i++) {
            System.out.print("L" + (i + 1) + ": ");
            for (int key : cacheLevels[i].map.keySet()) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    // 測試案例
    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCacheState(); // L1: [2,3], L2: [1], L3: []

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printCacheState(); // 1 應該移到 L1

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCacheState();
    }
}
