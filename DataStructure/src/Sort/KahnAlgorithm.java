package Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KahnAlgorithm {

    /**
     * 使用 Kahn 演算法對有向無環圖 (DAG) 進行拓撲排序
     *
     * @param numNodes 節點總數 (通常是 0 到 numNodes-1)
     * @param edges    邊的集合，每個元素 {u, v} 代表一條從 u 到 v 的有向邊
     * @return 一個代表拓撲排序結果的列表。如果圖中存在循環，則回傳一個空列表。
     */
    public List<Integer> topologicalSort(int numNodes, int[][] edges) {
        
        // 步驟一：初始化圖的鄰接串列 (Adjacency List) 和入度陣列 (In-degree)
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        // 根據邊的資訊來建構圖和計算入度
        for (int[] edge : edges) {
            int u = edge[0]; // 起點
            int v = edge[1]; // 終點
            adjList.get(u).add(v); // 新增一條 από u 到 v 的邊
            inDegree[v]++;         // 終點 v 的入度加 1
        }

        // 步驟二：找到所有入度為 0 的節點，將它們加入佇列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 步驟三：主迴圈，進行拓撲排序
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);

            // 遍歷節點 u 的所有鄰居
            for (int v : adjList.get(u)) {
                // 將鄰居 v 的入度減 1 (因為 u 已經處理完畢)
                inDegree[v]--;

                // 如果鄰居 v 的入度變為 0，表示它的前置任務都已完成，加入佇列
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // 步驟四：檢查結果。如果排序後的節點數與總節點數相同，則成功
        if (topoOrder.size() == numNodes) {
            return topoOrder;
        } else {
            // 如果數量不符，代表圖中存在循環 (Cycle)
            System.out.println("圖中存在循環，無法進行拓撲排序！");
            return new ArrayList<>(); // 回傳空列表表示失敗
        }
    }

    // 主函式，用於測試
    public static void main(String[] args) {
        KahnAlgorithm solver = new KahnAlgorithm();
        
        System.out.println("--- 測試案例 1: 一個標準的有向無環圖 (DAG) ---");
        // 圖結構:
        // 5 -> 0
        // 5 -> 2
        // 4 -> 0
        // 4 -> 1
        // 2 -> 3
        // 3 -> 1
        int numNodes1 = 6;
        int[][] edges1 = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        List<Integer> result1 = solver.topologicalSort(numNodes1, edges1);
        System.out.println("拓撲排序結果: " + result1); // 可能的結果: [4, 5, 2, 3, 1, 0] 或 [5, 4, 2, 0, 3, 1] ...

        System.out.println("\n--- 測試案例 2: 一個有循環的圖 ---");
        // 圖結構:
        // 0 -> 1 -> 2 -> 0 (循環)
        int numNodes2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}};
        List<Integer> result2 = solver.topologicalSort(numNodes2, edges2);
        System.out.println("拓撲排序結果: " + result2); // 預期為空列表
    }
}

