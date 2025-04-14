package GraphAPI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjListGraph implements Graph {
	private final int MAX_VERTICES; // 最大頂點數
	private List<Integer>[] adj; // 鄰接表
	private int V; // 當前頂點數
	private int E; // 當前邊數
	private boolean[] vertexExists; // 追踪頂點是否存在

	/**
	 * 建構子
	 * 
	 * @param maxV 圖的最大頂點數
	 */
	@SuppressWarnings("unchecked")
	public AdjListGraph(int maxV) {
		this.MAX_VERTICES = maxV;
		this.V = 0;
		this.E = 0;
		// 初始化鄰接表
		this.adj = (List<Integer>[]) new List[maxV];
		for (int i = 0; i < maxV; i++) {
			adj[i] = new ArrayList<>();
		}
		this.vertexExists = new boolean[maxV];
	}

	/**
	 * 添加頂點
	 * 
	 * @param v 要添加的頂點
	 */
	@Override
	public void addVertex(int v) {
		validateVertexRange(v);
		if (!vertexExists[v]) {
			vertexExists[v] = true;
			V++;
		}
	}

	/**
	 * 添加邊
	 * 
	 * @param v 起始頂點
	 * @param w 終止頂點
	 */
	@Override
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);

		// 檢查邊是否已存在
		if (!adj[v].contains(w)) {
			adj[v].add(w);
			adj[w].add(v); // 因為是無向圖，所以兩個方向都要加
			E++;
		}
	}

	/**
	 * 獲取與頂點 v 相鄰的所有頂點
	 * 
	 * @param v 目標頂點
	 * @return 相鄰頂點的迭代器
	 */
	@Override
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return new ArrayList<>(adj[v]); // 返回一個複本以防止外部修改
	}

	/**
	 * 獲取圖中的頂點數
	 * 
	 * @return 頂點數
	 */
	@Override
	public int V() {
		return V;
	}

	/**
	 * 獲取圖中的邊數
	 * 
	 * @return 邊數
	 */
	@Override
	public int E() {
		return E;
	}

	/**
	 * 獲取頂點的度數（與其相連的邊的數量）
	 * 
	 * @param v 目標頂點
	 * @return 度數
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * 移除邊
	 * 
	 * @param v 起始頂點
	 * @param w 終止頂點
	 */
	public void removeEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);

		if (adj[v].remove(Integer.valueOf(w))) {
			adj[w].remove(Integer.valueOf(v));
			E--;
		}
	}

	/**
	 * 移除頂點
	 * 
	 * @param v 要移除的頂點
	 */
	public void removeVertex(int v) {
		validateVertex(v);

		// 移除所有與此頂點相關的邊
		int edgesToRemove = adj[v].size();
		for (int w : new ArrayList<>(adj[v])) {
			adj[w].remove(Integer.valueOf(v));
		}
		adj[v].clear();

		vertexExists[v] = false;
		V--;
		E -= edgesToRemove;
	}

	/**
	 * 檢查兩個頂點之間是否有邊
	 * 
	 * @param v 起始頂點
	 * @param w 終止頂點
	 * @return 如果有邊則返回true
	 */
	public boolean hasEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return adj[v].contains(w);
	}

	/**
	 * 驗證頂點是否在有效範圍內
	 * 
	 * @param v 要驗證的頂點
	 */
	private void validateVertexRange(int v) {
		if (v < 0 || v >= MAX_VERTICES) {
			throw new IllegalArgumentException("頂點 " + v + " 超出有效範圍");
		}
	}

	/**
	 * 驗證頂點是否存在
	 * 
	 * @param v 要驗證的頂點
	 */
	private void validateVertex(int v) {
		validateVertexRange(v);
		if (!vertexExists[v]) {
			throw new IllegalArgumentException("頂點 " + v + " 不存在");
		}
	}

	/**
	 * 列印圖的結構
	 */
	public void printGraph() {
		System.out.println("圖的鄰接表表示：");
		for (int v = 0; v < MAX_VERTICES; v++) {
			if (vertexExists[v]) {
				System.out.print(v + " -> ");
				System.out.println(adj[v]);
			}
		}
	}

	/**
	 * 執行深度優先搜索
	 * 
	 * @param start 起始頂點
	 * @return 訪問順序
	 */
	public List<Integer> dfs(int start) {
		validateVertex(start);
		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[MAX_VERTICES];
		dfsHelper(start, visited, result);
		return result;
	}

	private void dfsHelper(int v, boolean[] visited, List<Integer> result) {
		visited[v] = true;
		result.add(v);
		for (int w : adj[v]) {
			if (!visited[w]) {
				dfsHelper(w, visited, result);
			}
		}
	}

	/**
	 * 執行廣度優先搜索
	 * 
	 * @param start 起始頂點
	 * @return 訪問順序
	 */
	public List<Integer> bfs(int start) {
		validateVertex(start);
		List<Integer> result = new ArrayList<>();
		boolean[] visited = new boolean[MAX_VERTICES];
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int v = queue.poll();
			result.add(v);

			for (int w : adj[v]) {
				if (!visited[w]) {
					visited[w] = true;
					queue.offer(w);
				}
			}
		}
		return result;
	}
}
