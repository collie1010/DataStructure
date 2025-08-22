package Topology;

import java.util.ArrayList;
import java.util.List;  
import java.util.Stack;

public class TopologicalSortDFS {
    
    /**
     * 使用DFS進行拓撲排序
     * @param numNodes 節點數量
     * @param edges 邊的列表
     * @return 拓撲排序結果，如果有環則返回空列表
     */
    public static List<Integer> topologicalSortDFS(int numNodes, int[][] edges) {
        // 建立鄰接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        // 節點狀態：0=未訪問，1=正在處理，2=已完成
        int[] state = new int[numNodes];
        Stack<Integer> stack = new Stack<>();
        
        // 對每個未訪問的節點進行DFS
        for (int i = 0; i < numNodes; i++) {
            if (state[i] == 0) {
                if (!dfs(i, graph, state, stack)) {
                    return new ArrayList<>(); // 發現環
                }
            }
        }
        
        // 將結果從棧中取出（逆序）
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    private static boolean dfs(int node, List<List<Integer>> graph, 
                              int[] state, Stack<Integer> stack) {
        state[node] = 1; // 標記為正在處理
        
        for (int neighbor : graph.get(node)) {
            if (state[neighbor] == 1) {
                return false; // 發現環
            }
            if (state[neighbor] == 0 && !dfs(neighbor, graph, state, stack)) {
                return false;
            }
        }
        
        state[node] = 2; // 標記為已完成
        stack.push(node);
        return true;
    }
}

