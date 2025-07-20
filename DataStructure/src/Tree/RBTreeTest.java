package Tree;

public class RBTreeTest {
     // 測試程式
    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        
        // 插入測試
        int[] values = {10, 20, 30, 40, 50, 25};
        System.out.println("插入元素: ");
        for (int value : values) {
            System.out.print(value + " ");
            rbTree.insert(value);
        }
        System.out.println();
        
        System.out.println("中序遍歷結果: ");
        rbTree.inorderTraversal();
        
        // 搜尋測試
        System.out.println("搜尋 25: " + rbTree.contains(25));
        System.out.println("搜尋 35: " + rbTree.contains(35));
        
        // 刪除測試
        System.out.println("刪除 30 後的中序遍歷: ");
        rbTree.delete(30);
        rbTree.inorderTraversal();
    }
}
