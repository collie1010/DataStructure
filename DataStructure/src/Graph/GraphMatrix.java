package Graph;

class GraphMatrix {
    private int V;  // 頂點數量
    private int[][] adjMatrix;  // 鄰接矩陣

    // 建構函數
    public GraphMatrix(int v) {
        V = v;
        adjMatrix = new int[v][v];
    }

    // 新增邊
    public void addEdge(int v, int w) {
        adjMatrix[v][w] = 1;
    }

    // 移除邊
    public void removeEdge(int v, int w) {
        adjMatrix[v][w] = 0;
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
