package DFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFS_BinaryTree {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val) {
			this.val = val;
		}
	}
	
	/**
     * 1. 二元樹的 DFS（非遞迴）
     * 包含前序、中序和後序遍歷
     */
    public static class TreeDFS {
        // 前序遍歷（根->左->右）
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;
            
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.add(node.val);
                
                // 先push右節點，再push左節點，確保左節點先被訪問
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
            return result;
        }
        
        // 中序遍歷（左->根->右）
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;
            
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            
            while (curr != null || !stack.isEmpty()) {
                // 到達最左節點
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
            return result;
        }
        
        // 後序遍歷（左->右->根）
        public List<Integer> postorderTraversal(TreeNode root) {
            LinkedList<Integer> result = new LinkedList<>();
            if (root == null) return result;
            
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.addFirst(node.val);  // 添加到列表開頭
                
                if (node.left != null) stack.push(node.left);
                if (node.right != null) stack.push(node.right);
            }
            return result;
        }
    }
    
    // 測試主程式
    public static void main(String[] args) {
        // 1. 測試二元樹 DFS
        TreeDFS treeDFS = new TreeDFS();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        System.out.println("二元樹 DFS 測試：");
        System.out.println("前序遍歷: " + treeDFS.preorderTraversal(root));
        System.out.println("中序遍歷: " + treeDFS.inorderTraversal(root));
        System.out.println("後序遍歷: " + treeDFS.postorderTraversal(root));
        
    }
}
