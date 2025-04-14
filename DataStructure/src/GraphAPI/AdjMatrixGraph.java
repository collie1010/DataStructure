package GraphAPI;

import java.util.ArrayList;
import java.util.List;

public class AdjMatrixGraph implements Graph {
	private boolean[][] adj; // 鄰接矩陣
	private int V; // 頂點數
	private int E; // 邊數
	private final int MAX_VERTICES; // 最大頂點數

	// 建構子
	public AdjMatrixGraph(int maxV) {
		this.MAX_VERTICES = maxV;
		this.V = 0;
		this.E = 0;
		this.adj = new boolean[maxV][maxV];
	}

	// 實現 addVertex 方法
	@Override
	public void addVertex(int v) {
		if (v >= 0 && v < MAX_VERTICES && !isValidVertex(v)) {
			V++;
		}
	}

	// 實現 addEdge 方法
	@Override
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);

		if (!adj[v][w]) {
			E++;
			adj[v][w] = true;
			adj[w][v] = true; // 因為是無向圖
		}
	}

	// 實現 adj 方法
	@Override
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		List<Integer> neighbors = new ArrayList<>();

		for (int w = 0; w < MAX_VERTICES; w++) {
			if (adj[v][w]) {
				neighbors.add(w);
			}
		}
		return neighbors;
	}

	// 實現 V 方法
	@Override
	public int V() {
		return V;
	}

	// 實現 E 方法
	@Override
	public int E() {
		return E;
	}

	// 輔助方法：驗證頂點是否有效
	private void validateVertex(int v) {
		if (v < 0 || v >= MAX_VERTICES || !isValidVertex(v)) {
			throw new IllegalArgumentException("頂點 " + v + " 不存在");
		}
	}

	// 輔助方法：檢查頂點是否已存在
	private boolean isValidVertex(int v) {
		// 檢查是否有任何與此頂點相連的邊
		for (int i = 0; i < MAX_VERTICES; i++) {
			if (adj[v][i] || adj[i][v]) {
				return true;
			}
		}
		return false;
	}

	// 新增一個方法來列印圖的結構（用於測試）
	public void printGraph() {
		System.out.println("圖的鄰接矩陣表示：");
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.print(adj[i][j] ? "1 " : "0 ");
			}
			System.out.println();
		}
	}
}
