package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS_BinaryTree {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static class Solution {

		public List<List<Integer>> levelOrder(TreeNode root) {
			List<List<Integer>> result = new ArrayList<>();
			if (root == null)
				return result;

			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);

			while (!queue.isEmpty()) {
				int levelSize = queue.size();
				List<Integer> currentLevel = new ArrayList<>();

				for (int i = 0; i < levelSize; i++) {
					TreeNode node = queue.poll();
					currentLevel.add(node.val);

					if (node.left != null) {
						queue.offer(node.left);
					}
					if (node.right != null) {
						queue.offer(node.right);
					}
				}
				result.add(currentLevel);
			}
			return result;
		}

	}

	public static void main(String[] args) {
		Solution solution = new Solution();

		// 測試案例 1: 正常二元樹
		TreeNode root1 = new TreeNode(3);
		root1.left = new TreeNode(9);
		root1.right = new TreeNode(20);
		root1.right.left = new TreeNode(15);
		root1.right.right = new TreeNode(7);
		System.out.println("Test Case 1: " + solution.levelOrder(root1));
		// Expected output: [[3], [9, 20], [15, 7]]

		// 測試案例 2: 只有root節點
		TreeNode root2 = new TreeNode(1);
		System.out.println("Test Case 2: " + solution.levelOrder(root2));
		// Expected output: [[1]]

		// 測試案例 3: 空樹
		System.out.println("Test Case 3: " + solution.levelOrder(null));
		// Expected output: []
	}
}
