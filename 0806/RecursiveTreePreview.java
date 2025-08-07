
import java.util.*;

public class RecursiveTreePreview {

    static int countFiles(Map<String, Object> folder) {
        int count = 0;
        for (Object val : folder.values()) {
            if (val instanceof Map) {
                count += countFiles((Map) val); 
            }else {
                count++;
            }
        }
        return count;
    }

    static void printMenu(List<Object> menu, int level) {
        for (Object item : menu) {
            if (item instanceof List) {
                printMenu((List) item, level + 1); 
            }else {
                System.out.println("  ".repeat(level) + item);
            }
        }
    }

    static List<Integer> flatten(List<Object> list) {
        List<Integer> flat = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof List) {
                flat.addAll(flatten((List) item)); 
            }else {
                flat.add((Integer) item);
            }
        }
        return flat;
    }

    static int maxDepth(List<Object> list) {
        int max = 1;
        for (Object item : list) {
            if (item instanceof List) {
                max = Math.max(max, 1 + maxDepth((List) item));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Map<String, Object> folder = Map.of("a.txt", 1, "folder1", Map.of("b.txt", 1, "c.txt", 1));
        System.out.println("Total files: " + countFiles(folder));

        List<Object> menu = List.of("Home", List.of("Products", List.of("Phones", "Laptops")), "About");
        printMenu(menu, 0);

        List<Object> nestedList = List.of(1, List.of(2, List.of(3, 4)), 5);
        System.out.println("Flattened: " + flatten(nestedList));
        System.out.println("Max depth: " + maxDepth(nestedList));
    }
}
