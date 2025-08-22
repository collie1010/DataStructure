package Topology;

import java.util.List;

public class TopologicalSortTest {
 public static void main(String[] args) {
        // 範例1: 無環圖
        int numNodes1 = 6;
        int[][] edges1 = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        
        System.out.println("範例1 - Kahn算法結果: " + 
                          TopologicalSortKahn.topologicalSortKahn(numNodes1, edges1));
        System.out.println("範例1 - DFS算法結果: " + 
                          TopologicalSortDFS.topologicalSortDFS(numNodes1, edges1));
        
        // 範例2: 有環圖
        int numNodes2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}};
        
        System.out.println("範例2 - Kahn算法結果: " + 
                          TopologicalSortKahn.topologicalSortKahn(numNodes2, edges2));
        System.out.println("範例2 - DFS算法結果: " + 
                          TopologicalSortDFS.topologicalSortDFS(numNodes2, edges2));
        
        // 範例3: 課程依賴關係
        testCourseSchedule();
    }
    
    public static void testCourseSchedule() {
        System.out.println("\n課程安排範例:");
        // 課程0需要課程1, 課程1需要課程2
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        
        List<Integer> order = TopologicalSortKahn.topologicalSortKahn(numCourses, prerequisites);
        if (order.isEmpty()) {
            System.out.println("無法安排課程 - 存在循環依賴");
        } else {
            System.out.println("建議的課程順序: " + order);
        }
    }
}
