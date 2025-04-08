package algo;

public class BinarySearchTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
BinarySearchTree bst = new BinarySearchTree();
        
        // 插入節點
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        System.out.println("原始樹結構：");
        bst.printTree();
        
        System.out.println("\n中序遍歷：");
        bst.inorderTraversal();
        
        // 搜尋節點
        System.out.println("搜尋 40：" + bst.search(40));
        System.out.println("搜尋 90：" + bst.search(90));
        
        // 刪除節點
        System.out.println("\n刪除 50 後的樹結構：");
        bst.delete(50);
        bst.printTree();
        
        System.out.println("\n樹的高度：" + bst.getHeight());

	}

}
