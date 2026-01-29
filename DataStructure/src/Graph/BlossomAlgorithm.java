package  Graph;
import java.util.*;

/**
 * 帶花樹演算法 (Edmonds' Blossom Algorithm) Java 實作
 * 
 * 核心目標：在一般圖中尋找最大匹配 (Maximum Matching)
 * 時間複雜度：O(E * V^2) 或優化後可達 O(E * V)
 */
public class BlossomAlgorithm {

    private int n;                  // 節點數量
    private List<Integer>[] adj;    // 鄰接表 (Adjacency List)
    private int[] match;            // match[i] = j 表示節點 i 與 j 匹配
    private int[] p;                // parent 陣列，用於記錄增廣路徑
    private int[] base;             // base[i] 表示節點 i 所屬的「花」的根 (用於縮點)
    private int[] vis;              // 訪問標記：0=未訪問, 1=奇點, 2=偶點
    private Queue<Integer> queue;   // BFS 用的佇列

    // 建構子
    public BlossomAlgorithm(int n) {
        this.n = n;
        this.adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        this.match = new int[n + 1];
        this.p = new int[n + 1];
        this.base = new int[n + 1];
        this.vis = new int[n + 1];
        this.queue = new LinkedList<>();
    }

    // 添加邊 (無向圖)
    public void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    // 尋找最近公共祖先 (Lowest Common Ancestor)
    // 用於在發現奇環時，找到該環與當前路徑的交匯點
    private int lca(int u, int v) {
        boolean[] seen = new boolean[n + 1];
        // 從 u 往上走，標記路徑
        while (true) {
            u = base[u];
            seen[u] = true;
            if (match[u] == 0) break; // 到達未匹配點 (樹根)
            u = p[match[u]];          // 往上跳兩步 (匹配點 -> 父節點)
        }
        // 從 v 往上走，遇到的第一個已標記點即為 LCA
        while (true) {
            v = base[v];
            if (seen[v]) return v;
            v = p[match[v]];
        }
    }

    // 標記「花」並處理縮點
    // u, v 是形成奇環的邊的兩個端點，l 是它們的 LCA
    private void markBlossom(int u, int v, int l) {
        while (base[u] != l) {
            int parentOfMatch = match[u];
            
            // 將奇環上的點標記為偶點 (加入佇列以便繼續擴展)
            vis[base[u]] = 1;      
            vis[base[parentOfMatch]] = 0; 
            
            if (vis[parentOfMatch] != 0) { // 如果原本是奇點，現在變成偶點加入佇列
                vis[parentOfMatch] = 0;
                queue.add(parentOfMatch);
            }
            
            // 更新並查集 base，將環上所有點歸類為 l
            base[u] = l;
            base[parentOfMatch] = l;
            
            // 記錄路徑方向
            p[u] = v;
            
            // 繼續處理環上的下一個點
            v = parentOfMatch;
            u = p[parentOfMatch];
        }
    }

    // 尋找增廣路徑 (BFS)
    private boolean bfs(int s) {
        Arrays.fill(vis, -1);
        Arrays.fill(p, 0);
        // 初始化 base，每個點自己是自己的 base
        for (int i = 1; i <= n; i++) base[i] = i;

        queue.clear();
        queue.add(s);
        vis[s] = 0; // 0 代表偶點 (Type A), 1 代表奇點 (Type B)

        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            for (int v : adj[u]) {
                // 情況 1: 遇到同一個花中的點，或是已經處理過的奇點，忽略
                if (base[u] == base[v] || match[u] == v) continue;

                // 情況 2: 找到一個奇環 (Blossom)
                // v 已經被訪問過且是偶點 (0)，代表我們繞了一圈回來了
                if (v == s || (match[v] != 0 && p[match[v]] != 0)) {
                    int curbase = lca(u, v);
                    markBlossom(u, v, curbase);
                    markBlossom(v, u, curbase);
                } 
                // 情況 3: 擴展路徑
                else if (p[v] == 0) {
                    p[v] = u; // 記錄父節點
                    
                    // 如果 v 未匹配，說明找到增廣路徑了！
                    if (match[v] == 0) {
                        augment(v);
                        return true;
                    } 
                    // 如果 v 已匹配，繼續延伸
                    else {
                        vis[v] = 1; // v 是奇點
                        vis[match[v]] = 0; // v 的配對點是偶點
                        queue.add(match[v]); // 將配對點加入佇列
                    }
                }
            }
        }
        return false;
    }

    // 根據 p 陣列回溯並翻轉匹配狀態
    private void augment(int u) {
        while (u != 0) {
            int v = p[u];
            int next = match[v];
            match[v] = u;
            match[u] = v;
            u = next;
        }
    }

    // 主計算方法
    public int solve() {
        // 初始化匹配
        Arrays.fill(match, 0);
        int matchingSize = 0;
        
        // 對每個未匹配的點嘗試尋找增廣路徑
        for (int i = 1; i <= n; i++) {
            if (match[i] == 0) {
                if (bfs(i)) {
                    matchingSize++;
                }
            }
        }
        return matchingSize;
    }

    // 獲取最終匹配結果
    public int[] getMatch() {
        return match;
    }

    // ==============================
    // 測試 Main 方法
    // ==============================
    public static void main(String[] args) {
        System.out.println("--- Blossom Algorithm Demo ---");
        
        // 範例圖：5 個節點
        // 1-2, 2-3, 3-1 (這是一個奇環/三角形), 3-4, 4-5
        // 預期結果：最大匹配數為 2 (例如: 1-2, 3-4, 5 落單; 或 1-2, 4-5, 3 落單)
        
        int n = 5;
        BlossomAlgorithm solver = new BlossomAlgorithm(n);
        
        solver.addEdge(1, 2);
        solver.addEdge(2, 3);
        solver.addEdge(3, 1); // 形成 Blossom
        solver.addEdge(3, 4);
        solver.addEdge(4, 5);

        int maxMatching = solver.solve();
        int[] result = solver.getMatch();

        System.out.println("最大匹配對數: " + maxMatching);
        System.out.println("匹配詳情 (索引 1~" + n + "):");
        for (int i = 1; i <= n; i++) {
            if (result[i] != 0 && i < result[i]) { // 只印出 i < j 的情況避免重複
                System.out.println("節點 " + i + " <--> 節點 " + result[i]);
            }
        }
    }
}

