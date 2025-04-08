package Graph;

public class GraphTest {
    public static void main(String[] args) {
        // 使用鄰接列表的圖
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("鄰接列表表示的圖：");
        g.printGraph();

        System.out.println("\nBFS遍歷（從頂點0開始）：");
        g.BFS(0);

        System.out.println("\nDFS遍歷（從頂點0開始）：");
        g.DFS(0);

        // 使用鄰接矩陣的圖
        GraphMatrix gMatrix = new GraphMatrix(4);
        gMatrix.addEdge(0, 1);
        gMatrix.addEdge(0, 2);
        gMatrix.addEdge(1, 2);
        gMatrix.addEdge(2, 0);
        gMatrix.addEdge(2, 3);
        gMatrix.addEdge(3, 3);

        System.out.println("\n\n鄰接矩陣表示的圖：");
        gMatrix.printGraph();
    }
}

