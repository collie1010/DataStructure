package UnionFind;

public class UnionFind {
    private int[] parent;  // 記錄每個節點的父節點
    private int count;     // 記錄集合數量
    
    // 建構子：初始化 n 個獨立的集合
    public UnionFind(int n) {
        parent = new int[n];
        count = n;
        // 每個元素的父節點初始為自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    // Find：查找元素 x 的根節點（所屬集合的代表）
    public int find(int x) {
        // 如果 x 不是根節點，繼續往上找
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
    
    // Union：合併兩個元素所在的集合
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        // 如果已經在同一個集合，不需要合併
        if (rootX == rootY) {
            return;
        }
        
        // 將一個集合的根節點指向另一個
        parent[rootX] = rootY;
        count--;  // 集合數量減 1
    }
    
    // 判斷兩個元素是否在同一個集合
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    // 獲取集合數量
    public int getCount() {
        return count;
    }
}

