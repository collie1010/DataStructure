package GraphAPI;

public interface Graph {
	// 添加頂點
    void addVertex(int v);
    
    // 添加邊
    void addEdge(int v, int w);
    
    // 獲取頂點 v 的所有相鄰頂點
    Iterable<Integer> adj(int v);
    
    // 獲取頂點數量
    int V();
    
    // 獲取邊的數量
    int E();
}
