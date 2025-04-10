package DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS_2DArray {

	public static class MatrixDFS {
		private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 上下左右

		public List<int[]> dfs(int[][] matrix) {
			List<int[]> result = new ArrayList<>();
			if (matrix == null || matrix.length == 0)
				return result;

			int rows = matrix.length;
			int cols = matrix[0].length;
			boolean[][] visited = new boolean[rows][cols];
			Stack<int[]> stack = new Stack<>();

			// 從 (0,0) 開始
			stack.push(new int[] { 0, 0 });

			while (!stack.isEmpty()) {
				int[] current = stack.pop();
				int row = current[0];
				int col = current[1];

				if (!visited[row][col]) {
					visited[row][col] = true;
					result.add(new int[] { row, col });

					// 檢查四個方向
					for (int[] dir : DIRECTIONS) {
						int newRow = row + dir[0];
						int newCol = col + dir[1];

						if (isValid(newRow, newCol, rows, cols) && !visited[newRow][newCol]) {
							stack.push(new int[] { newRow, newCol });
						}
					}
				}
			}
			return result;
		}

		private boolean isValid(int row, int col, int rows, int cols) {
			return row >= 0 && row < rows && col >= 0 && col < cols;
		}
	}

	// 測試主程式
	public static void main(String[] args) {

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		MatrixDFS matrixDFS = new MatrixDFS();
		List<int[]> matrixResult = matrixDFS.dfs(matrix);

		System.out.println("矩陣 DFS 測試：");
		System.out.println("訪問順序：");
		for (int[] pos : matrixResult) {
			System.out.printf("(%d,%d) -> %d%n", pos[0], pos[1], matrix[pos[0]][pos[1]]);
		}
	}
}
