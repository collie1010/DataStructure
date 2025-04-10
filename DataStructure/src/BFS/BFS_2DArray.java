package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_2DArray {

	static class Solution {

		public List<int[]> bfs(int[][] grid) {
			List<int[]> result = new ArrayList<>();
			if (grid == null || grid.length == 0)
				return result;

			int rows = grid.length;
			int cols = grid[0].length;
			boolean[][] visited = new boolean[rows][cols];
			Queue<int[]> queue = new LinkedList<>();
			int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

			queue.offer(new int[] { 0, 0 });
			visited[0][0] = true;

			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				result.add(current);

				for (int[] dir : directions) {
					int newRow = current[0] + dir[0];
					int newCol = current[1] + dir[1];

					if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]
							&& grid[newRow][newCol] == 1) {

						queue.offer(new int[] { newRow, newCol });
						visited[newRow][newCol] = true;
					}
				}
			}
			return result;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();

		// 測試案例 1: 一般矩陣
		int[][] grid1 = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		List<int[]> result1 = solution.bfs(grid1);
		System.out.println("Test Case 1:");
		for (int[] pos : result1) {
			System.out.println("(" + pos[0] + "," + pos[1] + ")");
		}

		// 測試案例 2: 1x1矩陣
		int[][] grid2 = { { 1 } };
		List<int[]> result2 = solution.bfs(grid2);
		System.out.println("\nTest Case 2:");
		for (int[] pos : result2) {
			System.out.println("(" + pos[0] + "," + pos[1] + ")");
		}

		// 測試案例 3: 空矩陣
		int[][] grid3 = {};
		List<int[]> result3 = solution.bfs(grid3);
		System.out.println("\nTest Case 3:");
		System.out.println("Size: " + result3.size());
	}
}
