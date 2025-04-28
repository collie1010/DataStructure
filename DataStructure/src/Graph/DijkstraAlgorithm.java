package Graph;

public class DijkstraAlgorithm {
    private final int V;  // 頂點數量
    private final int[][] graph;  // 圖的鄰接矩陣

    // 建構函數
    public DijkstraAlgorithm(GraphMatrix graphMatrix) {
        this.V = graphMatrix.getV();
        this.graph = graphMatrix.getAdjMatrix();
        
        // 將無權重邊（值為1）轉換為實際距離1
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 0 && i != j) {
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    // 找出距離最小且未訪問的頂點
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // 執行 Dijkstra 演算法找出最短路徑
    public void findShortestPaths(int source) {
        int[] dist = new int[V];     // 儲存從源點到各點的最短距離
        boolean[] visited = new boolean[V];  // 記錄頂點是否已被訪問
        int[] parent = new int[V];    // 儲存最短路徑的父節點

        // 初始化
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parent[i] = -1;
        }

        // 源點到自己的距離為0
        dist[source] = 0;

        // 尋找所有頂點的最短路徑
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            // 更新相鄰頂點的距離
            for (int v = 0; v < V; v++) {
                if (!visited[v] && 
                    graph[u][v] != Integer.MAX_VALUE && 
                    dist[u] != Integer.MAX_VALUE && 
                    dist[u] + graph[u][v] < dist[v]) {
                    
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        // 印出結果
        printSolution(dist, parent, source);
    }

    // 印出最短路徑
    private void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + j);
    }

    // 印出所有結果
    private void printSolution(int[] dist, int[] parent, int source) {
        System.out.println("從頂點 " + source + " 到其他頂點的最短路徑：");
        System.out.println("目標頂點\t距離\t路徑");
        
        for (int i = 0; i < V; i++) {
            if (i != source) {
                System.out.print(i + "\t\t");
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.print("∞\t");
                } else {
                    System.out.print(dist[i] + "\t");
                }
                printPath(parent, i);
                System.out.println();
            }
        }
    }
}

