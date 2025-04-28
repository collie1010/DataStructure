package Graph;

public class DijkstraAlgoTest {
	public static void main(String[] args) {
		// 測試有權重的圖
		System.out.println("有權重圖的最短路徑：");
		GraphMatrix weightedGraph = new GraphMatrix(5);
		weightedGraph.addEdge(0, 1, 4);
		weightedGraph.addEdge(0, 2, 2);
		weightedGraph.addEdge(1, 2, 1);
		weightedGraph.addEdge(1, 3, 5);
		weightedGraph.addEdge(2, 3, 8);
		weightedGraph.addEdge(2, 4, 10);
		weightedGraph.addEdge(3, 4, 2);

		System.out.println("鄰接矩陣：");
		weightedGraph.printGraph();

		DijkstraAlgorithm dijkstra1 = new DijkstraAlgorithm(weightedGraph);
		System.out.println("\n執行 Dijkstra 演算法：");
		dijkstra1.findShortestPaths(0);

		// 測試無權重的圖
		System.out.println("\n無權重圖的最短路徑：");
		GraphMatrix unweightedGraph = new GraphMatrix(5);
		unweightedGraph.addEdge(0, 1);
		unweightedGraph.addEdge(0, 2);
		unweightedGraph.addEdge(1, 2);
		unweightedGraph.addEdge(1, 3);
		unweightedGraph.addEdge(2, 4);
		unweightedGraph.addEdge(3, 4);

		System.out.println("鄰接矩陣：");
		unweightedGraph.printGraph();

		DijkstraAlgorithm dijkstra2 = new DijkstraAlgorithm(unweightedGraph);
		System.out.println("\n執行 Dijkstra 演算法：");
		dijkstra2.findShortestPaths(0);
	}
}
