package Topology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TarjanAlgorithm {
    private int V;               // 圖中的節點數
    private List<Integer>[] adj; // 鄰接表
    private int time;            // 用於追蹤發現時間
    
    private int[] disc;          // 節點的發現時間
    private int[] low;           // 能到達的最早發現節點的時間
    private boolean[] inStack;   // 節點是否在堆疊中
    private Stack<Integer> stack; // 用於追蹤當前路徑的堆疊
    private List<List<Integer>> result; // 儲存所有強連通分量
    
    /**
     * 建構函數
     * @param vertices 圖中的節點數
     */
    @SuppressWarnings("unchecked")
    public TarjanAlgorithm(int vertices) {
        this.V = vertices;
        this.adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 初始化其他資料結構
        disc = new int[V];
        low = new int[V];
        inStack = new boolean[V];
        stack = new Stack<>();
        result = new ArrayList<>();
    }
    
    /**
     * 添加一條邊到圖中
     * @param u 起始節點
     * @param v 目標節點
     */
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }
    
    /**
     * 運行 Tarjan 算法找出所有強連通分量
     * @return 強連通分量的列表
     */
    public List<List<Integer>> findSCCs() {
        // 初始化
        time = 0;
        result.clear();
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(inStack, false);
        stack.clear();
        
        // 對每個未訪問的節點進行 DFS
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }
        
        return result;
    }
    
    /**
     * Tarjan 算法的 DFS 部分
     * @param u 當前節點
     */
    private void dfs(int u) {
        // 設置發現時間和 low 值
        disc[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;
        
        // 訪問所有相鄰節點
        for (int v : adj[u]) {
            // 如果 v 未被訪問，則遞迴訪問它
            if (disc[v] == -1) {
                dfs(v);
                // 更新 u 的 low 值
                low[u] = Math.min(low[u], low[v]);
            } 
            // 如果 v 已訪問且在堆疊中，更新 u 的 low 值
            else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        
        // 如果 u 是強連通分量的根節點，則提取該強連通分量
        if (low[u] == disc[u]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                inStack[w] = false;
                scc.add(w);
            } while (w != u);
            
            result.add(scc);
        }
    }
    
    /**
     * 打印所有找到的強連通分量
     */
    public void printSCCs() {
        List<List<Integer>> sccs = findSCCs();
        System.out.println("強連通分量：");
        for (int i = 0; i < sccs.size(); i++) {
            System.out.print("SCC " + (i + 1) + ": ");
            for (int node : sccs.get(i)) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * 測試程式
     */
    public static void main(String[] args) {
        // 創建一個有 8 個節點的圖
        TarjanAlgorithm graph = new TarjanAlgorithm(8);
        
        // 添加邊
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);  // 0-1-2 形成一個環
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);  // 3-4-5 形成一個環
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        
        // 找出並打印所有強連通分量
        graph.printSCCs();
    }
}

