package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_Graph {
	static class Solution {
		public List<Integer> bfs(int[][] graph, int start) {
			List<Integer> result = new ArrayList<>();
			if (graph == null || graph.length == 0)
				return result;

			int n = graph.length;
			boolean[] visited = new boolean[n];
			Queue<Integer> queue = new LinkedList<>();

			queue.offer(start);
			visited[start] = true;

			while (!queue.isEmpty()) {
				int node = queue.poll();
				result.add(node);

				for (int i = 0; i < n; i++) {
					if (graph[node][i] == 1 && !visited[i]) {
						queue.offer(i);
						visited[i] = true;
					}
				}
			}
			return result;
		}
	}
	
	public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 測試案例 1: 一般圖
        int[][] graph1 = {
            {0, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {0, 0, 1, 0}
        };
        System.out.println("Test Case 1: " + solution.bfs(graph1, 0));
        // Expected output: [0, 1, 2, 3]
        
        // 測試案例 2: 單節點圖
        int[][] graph2 = {{0}};
        System.out.println("Test Case 2: " + solution.bfs(graph2, 0));
        // Expected output: [0]
        
        // 測試案例 3: 斷開的圖
        int[][] graph3 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        System.out.println("Test Case 3: " + solution.bfs(graph3, 0));
        // Expected output: [0]
    }
}
