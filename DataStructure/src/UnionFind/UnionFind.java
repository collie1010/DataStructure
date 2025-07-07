package UnionFind;

public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count; // 連通分量的數量
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        
        // 初始化，每個元素的父節點是自己
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    // 查找操作（帶路徑壓縮）
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 路徑壓縮
        }
        return parent[x];
    }
    
    // 合併操作（按秩合併）
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            // 按秩合併
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            count--; // 合併後連通分量減少
        }
    }
    
    // 判斷兩個元素是否連通
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    // 獲取連通分量的數量
    public int getCount() {
        return count;
    }
}

