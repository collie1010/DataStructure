package Topology;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortKahn {
    /**
     * 使用Kahn算法進行拓撲排序
     * @param numNodes 節點數量
     * @param edges 邊的列表，每個元素是[from, to]
     * @return 拓撲排序結果，如果有環則返回空列表
     */
    public static List<Integer> topologicalSortKahn(int numNodes, int[][] edges) {
        // 建立鄰接表和入度數組
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numNodes];
        
        // 初始化圖
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 建立圖並計算入度
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            inDegree[to]++;
        }
        
        // 找到所有入度為0的節點
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        // 處理節點
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            // 移除該節點的所有出邊
            for (int neighbor : graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // 檢查是否有環
        return result.size() == numNodes ? result : new ArrayList<>();
    }
}
