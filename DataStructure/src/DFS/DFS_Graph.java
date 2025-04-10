package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS_Graph {

	public static class GraphDFS {
		private List<List<Integer>> adj; // 鄰接表表示的圖

		public GraphDFS(int V) {
			adj = new ArrayList<>(V);
			for (int i = 0; i < V; i++) {
				adj.add(new ArrayList<>());
			}
		}

		public void addEdge(int v, int w) {
			adj.get(v).add(w);
		}

		public List<Integer> dfs(int start) {
			List<Integer> result = new ArrayList<>();
			boolean[] visited = new boolean[adj.size()];
			Stack<Integer> stack = new Stack<>();

			stack.push(start);

			while (!stack.isEmpty()) {
				int v = stack.pop();

				if (!visited[v]) {
					visited[v] = true;
					result.add(v);

					// 將相鄰節點按相反順序入棧
					for (int i = adj.get(v).size() - 1; i >= 0; i--) {
						int neighbor = adj.get(v).get(i);
						if (!visited[neighbor]) {
							stack.push(neighbor);
						}
					}
				}
			}
			return result;
		}
	}

	// 測試主程式
	public static void main(String[] args) {

		// 2. 測試圖 DFS
		GraphDFS graphDFS = new GraphDFS(4);
		graphDFS.addEdge(0, 1);
		graphDFS.addEdge(0, 2);
		graphDFS.addEdge(1, 2);
		graphDFS.addEdge(2, 0);
		graphDFS.addEdge(2, 3);
		graphDFS.addEdge(3, 3);

		System.out.println("圖 DFS 測試：");
		System.out.println("從頂點 2 開始的 DFS: " + graphDFS.dfs(2));
	}
}