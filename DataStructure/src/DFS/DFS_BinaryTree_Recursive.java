package DFS;

import java.util.ArrayList;
import java.util.List;

public class DFS_BinaryTree_Recursive {
	
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int val) {
			this.val = val;
		}
	}
	
	public static class TreeDFS {
		 // 前序遍歷 (Pre-order: 根->左->右)
	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> result = new ArrayList<>();
	        preorderDFS(root, result);
	        return result;
	    }
	    
	    private void preorderDFS(TreeNode node, List<Integer> result) {
	        if (node == null) return;
	        
	        result.add(node.val);           // 訪問根節點
	        preorderDFS(node.left, result); // 遍歷左子樹
	        preorderDFS(node.right, result);// 遍歷右子樹
	    }
	    
	    // 中序遍歷 (In-order: 左->根->右)
	    public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> result = new ArrayList<>();
	        inorderDFS(root, result);
	        return result;
	    }
	    
	    private void inorderDFS(TreeNode node, List<Integer> result) {
	        if (node == null) return;
	        
	        inorderDFS(node.left, result);  // 遍歷左子樹
	        result.add(node.val);           // 訪問根節點
	        inorderDFS(node.right, result); // 遍歷右子樹
	    }
	}
	
	// 測試主程式
    public static void main(String[] args) {
        TreeDFS solution = new TreeDFS();
        
        // 建立測試樹
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        System.out.println("前序遍歷: " + solution.preorderTraversal(root));
        System.out.println("中序遍歷: " + solution.inorderTraversal(root));
    }
	
}
