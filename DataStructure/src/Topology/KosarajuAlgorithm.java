package Topology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class KosarajuAlgorithm {
    private int V; // 頂點數量
    private List<List<Integer>> graph; // 鄰接表表示的圖
    private List<List<Integer>> transposeGraph; // 轉置圖
    
    public KosarajuAlgorithm(int vertices) {
        this.V = vertices;
        this.graph = new ArrayList<>();
        this.transposeGraph = new ArrayList<>();
        
        // 初始化鄰接表
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
            transposeGraph.add(new ArrayList<>());
        }
    }
    
    /**
     * 添加邊到原圖
     */
    public void addEdge(int from, int to) {
        graph.get(from).add(to);
        transposeGraph.get(to).add(from); // 同時添加到轉置圖
    }
    
    /**
     * 第一次DFS - 計算完成時間並存入棧中
     */
    private void firstDFS(int vertex, boolean[] visited, Stack<Integer> finishStack) {
        visited[vertex] = true;
        System.out.println("第一次DFS訪問頂點: " + vertex);
        
        // 遍歷所有相鄰頂點
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                firstDFS(neighbor, visited, finishStack);
            }
        }
        
        // 在回溯時將頂點推入棧（完成時間較晚的在棧頂）
        finishStack.push(vertex);
        System.out.println("頂點 " + vertex + " 完成，推入棧");
    }
    
    /**
     * 第二次DFS - 在轉置圖上尋找強連通分量
     */
    private void secondDFS(int vertex, boolean[] visited, List<Integer> currentSCC) {
        visited[vertex] = true;
        currentSCC.add(vertex);
        
        // 在轉置圖上遍歷相鄰頂點
        for (int neighbor : transposeGraph.get(vertex)) {
            if (!visited[neighbor]) {
                secondDFS(neighbor, visited, currentSCC);
            }
        }
    }
    
    /**
     * 主要的Kosaraju演算法實作
     */
    public List<List<Integer>> findSCCs() {
        Stack<Integer> finishStack = new Stack<>();
        boolean[] visited = new boolean[V];
        List<List<Integer>> sccs = new ArrayList<>();
        
        System.out.println("=== 第一階段：對原圖進行DFS ===");
        // 第一次DFS：對原圖進行深度優先搜尋
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                System.out.println("\n從頂點 " + i + " 開始第一次DFS");
                firstDFS(i, visited, finishStack);
            }
        }
        
        System.out.println("\n完成時間順序（棧頂到棧底）: " + finishStack);
        
        // 重置訪問標記
        Arrays.fill(visited, false);
        
        System.out.println("\n=== 第二階段：對轉置圖進行DFS ===");
        // 第二次DFS：按完成時間的逆序對轉置圖進行DFS
        int sccCount = 1;
        while (!finishStack.isEmpty()) {
            int vertex = finishStack.pop();
            if (!visited[vertex]) {
                List<Integer> currentSCC = new ArrayList<>();
                System.out.println("\n找到強連通分量 " + sccCount + "，從頂點 " + vertex + " 開始");
                secondDFS(vertex, visited, currentSCC);
                
                // 將SCC按升序排列以便輸出
                Collections.sort(currentSCC);
                sccs.add(currentSCC);
                System.out.println("強連通分量 " + sccCount + ": " + currentSCC);
                sccCount++;
            }
        }
        
        return sccs;
    }
    
    /**
     * 列印圖的結構
     */
    public void printGraph() {
        System.out.println("\n原圖結構:");
        for (int i = 0; i < V; i++) {
            System.out.println("頂點 " + i + " -> " + graph.get(i));
        }
        
        System.out.println("\n轉置圖結構:");
        for (int i = 0; i < V; i++) {
            System.out.println("頂點 " + i + " -> " + transposeGraph.get(i));
        }
    }
    
    /**
     * 主函數 - 演示範例
     */
    public static void main(String[] args) {
        // 範例1：簡單的強連通圖
        System.out.println("範例1：簡單強連通圖");
        KosarajuAlgorithm kosaraju1 = new KosarajuAlgorithm(5);
        
        // 添加邊
        kosaraju1.addEdge(0, 2);
        kosaraju1.addEdge(0, 3);
        kosaraju1.addEdge(1, 0);
        kosaraju1.addEdge(2, 1);
        kosaraju1.addEdge(3, 4);
        
        kosaraju1.printGraph();
        
        List<List<Integer>> sccs1 = kosaraju1.findSCCs();
        System.out.println("\n最終結果 - 強連通分量: " + sccs1);
        
        System.out.println("\n" + "=".repeat(60));
        
        // 範例2：複雜的強連通圖
        System.out.println("範例2：複雜強連通圖");
        KosarajuAlgorithm kosaraju2 = new KosarajuAlgorithm(8);
        
        // 構建一個包含多個SCC的圖
        kosaraju2.addEdge(0, 1);
        kosaraju2.addEdge(1, 2);
        kosaraju2.addEdge(2, 0); // SCC: {0, 1, 2}
        
        kosaraju2.addEdge(2, 3);
        kosaraju2.addEdge(3, 4);
        kosaraju2.addEdge(4, 5);
        kosaraju2.addEdge(5, 3); // SCC: {3, 4, 5}
        
        kosaraju2.addEdge(5, 6);
        kosaraju2.addEdge(6, 7); // SCC: {6}, {7}
        
        kosaraju2.printGraph();
        
        List<List<Integer>> sccs2 = kosaraju2.findSCCs();
        System.out.println("\n最終結果 - 強連通分量: " + sccs2);
        System.out.println("總共找到 " + sccs2.size() + " 個強連通分量");
    }
}
