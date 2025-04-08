package Graph;

import java.util.*;

class Graph {
    private int V;  // 頂點數量
    private LinkedList<Integer>[] adjList;  // 鄰接列表

    // 建構函數
    @SuppressWarnings("unchecked")
    public Graph(int v) {
        V = v;
        adjList = new LinkedList[v];
        // 初始化鄰接列表
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // 新增邊
    public void addEdge(int v, int w) {
        adjList[v].add(w);  // 新增w到v的鄰接列表
    }

    // 印出圖的結構
    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print("頂點 " + i + " 的鄰接列表:");
            for (Integer vertex : adjList[i]) {
                System.out.print(" -> " + vertex);
            }
            System.out.println();
        }
    }

    // BFS遍歷
    public void BFS(int s) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (Integer n : adjList[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // DFS遍歷
    public void DFS(int s) {
        boolean[] visited = new boolean[V];
        DFSUtil(s, visited);
    }

    private void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (Integer n : adjList[v]) {
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }
}

