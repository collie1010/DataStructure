package Tree;

public class AVLTreeTest {
     // 測試用主程式
    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        
        System.out.println("=== AVL 樹測試 ===");
        
        // 插入測試
        int[] values = {10, 20, 30, 40, 50, 25, 5, 15, 35};
        System.out.println("插入元素: ");
        for (int value : values) {
            System.out.print(value + " ");
            avlTree.insert(value);
        }
        System.out.println("\n");
        
        // 顯示樹結構
        avlTree.displayTree();
        System.out.println();
        
        // 遍歷測試
        avlTree.inOrderTraversal();
        avlTree.preOrderTraversal();
        avlTree.postOrderTraversal();
        System.out.println();
        
        // 樹資訊
        System.out.println("樹高度: " + avlTree.getTreeHeight());
        System.out.println("節點數量: " + avlTree.size());
        System.out.println("是否平衡: " + avlTree.isBalanced());
        System.out.println();
        
        // 搜尋測試
        System.out.println("搜尋 25: " + avlTree.search(25));
        System.out.println("搜尋 45: " + avlTree.search(45));
        System.out.println();
        
        // 刪除測試
        System.out.println("刪除 30 後:");
        avlTree.delete(30);
        avlTree.displayTree();
        avlTree.inOrderTraversal();
        System.out.println("是否平衡: " + avlTree.isBalanced());
        System.out.println();
        
        System.out.println("刪除 40 後:");
        avlTree.delete(40);
        avlTree.displayTree();
        avlTree.inOrderTraversal();
        System.out.println("是否平衡: " + avlTree.isBalanced());
    }
}
