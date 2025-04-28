package Graph;

class GraphMatrix {
	private int V; // 頂點數量
	private int[][] adjMatrix; // 鄰接矩陣

	// 建構函數
	public GraphMatrix(int v) {
		V = v;
		adjMatrix = new int[v][v];
		// 初始化矩陣
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	}

	// 新增無權重邊
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adjMatrix[v][w] = 1;
	}

	// 新增有權重邊
	public void addEdge(int v, int w, int weight) {
		validateVertex(v);
		validateVertex(w);
		adjMatrix[v][w] = weight;
	}

	// 移除邊
	public void removeEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adjMatrix[v][w] = 0;
	}

	// 驗證頂點是否有效
	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("頂點 " + v + " 超出範圍");
		}
	}

	// 取得頂點數量
	public int getV() {
		return V;
	}

	// 取得鄰接矩陣
	public int[][] getAdjMatrix() {
		return adjMatrix;
	}

	// 印出鄰接矩陣
	public void printGraph() {
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}