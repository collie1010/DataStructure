package DFS;

public class DFS_2DArray_Recursive {

	public static class MatrixDFS {
		private int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上下左右
		private int rows;
		private int cols;

		public void dfs(int[][] matrix) {
			if (matrix == null || matrix.length == 0)
				return;

			rows = matrix.length;
			cols = matrix[0].length;
			boolean[][] visited = new boolean[rows][cols];

			// 從 (0,0) 開始搜索
			dfsUtil(matrix, 0, 0, visited);
		}

		private void dfsUtil(int[][] matrix, int row, int col, boolean[][] visited) {
			// 標記當前格子為已訪問
			visited[row][col] = true;
			System.out.println("訪問位置: (" + row + "," + col + ") 值: " + matrix[row][col]);

			// 遍歷四個方向
			for (int[] dir : directions) {
				int newRow = row + dir[0];
				int newCol = col + dir[1];

				// 檢查新位置是否有效且未被訪問
				if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
					dfsUtil(matrix, newRow, newCol, visited);
				}
			}
		}

		private boolean isValid(int row, int col) {
			return row >= 0 && row < rows && col >= 0 && col < cols;
		}

	}
	
	// 測試主程式
    public static void main(String[] args) {
    	MatrixDFS solution = new MatrixDFS();
        
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        solution.dfs(matrix);
    }
}
