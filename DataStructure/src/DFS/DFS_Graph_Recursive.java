package DFS;

import java.util.ArrayList;
import java.util.List;

public class DFS_Graph_Recursive {
	
	public static class GraphDFS {
		
		private List<List<Integer>> adj; // 鄰接表表示的圖
	    private boolean[] visited;       // 記錄訪問狀態
	    private List<Integer> result;    // 存儲遍歷結果
	    
	    public GraphDFS(int V) {
	        adj = new ArrayList<>(V);
	        for (int i = 0; i < V; i++) {
	            adj.add(new ArrayList<>());
	        }
	        visited = new boolean[V];
	        result = new ArrayList<>();
	    }
	    
	    // 添加邊
	    public void addEdge(int v, int w) {
	        adj.get(v).add(w);
	    }
	    
	    // DFS 遍歷
	    public List<Integer> dfs(int start) {
	        dfsUtil(start);
	        return result;
	    }
	    
	    private void dfsUtil(int v) {
	        visited[v] = true;
	        result.add(v);
	        
	        for (int neighbor : adj.get(v)) {
	            if (!visited[neighbor]) {
	                dfsUtil(neighbor);
	            }
	        }
	    }
	}
	
	// 測試主程式
    public static void main(String[] args) {
    	GraphDFS graph = new GraphDFS(4);
        
        // 添加邊
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        
        System.out.println("DFS 從頂點 2 開始: " + graph.dfs(2));
    }
	
	
}
