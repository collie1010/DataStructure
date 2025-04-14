package GraphAPI;

public class GraphTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 創建一個最大容量為 5 的圖
		AdjListGraph graph = new AdjListGraph(5);

		try {
			// 添加頂點
			for (int i = 0; i < 5; i++) {
				graph.addVertex(i);
			}

			// 添加邊
			graph.addEdge(0, 1);
			graph.addEdge(0, 2);
			graph.addEdge(1, 2);
			graph.addEdge(2, 3);
			graph.addEdge(3, 4);

			// 列印基本資訊
			System.out.println("圖的基本資訊：");
			System.out.println("頂點數: " + graph.V());
			System.out.println("邊數: " + graph.E());

			// 列印圖結構
			graph.printGraph();

			// 測試深度優先搜索
			System.out.println("\nDFS遍歷（從頂點0開始）：");
			System.out.println(graph.dfs(0));

			// 測試廣度優先搜索
			System.out.println("\nBFS遍歷（從頂點0開始）：");
			System.out.println(graph.bfs(0));

			// 測試移除邊和頂點
			System.out.println("\n移除邊 (1-2) 後：");
			graph.removeEdge(1, 2);
			graph.printGraph();

			System.out.println("\n移除頂點 3 後：");
			graph.removeVertex(3);
			graph.printGraph();

		} catch (IllegalArgumentException e) {
			System.out.println("錯誤：" + e.getMessage());
		}
	}

}
