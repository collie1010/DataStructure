package Tree;

public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // 插入一些節點（按層次順序）
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);

        System.out.println("\n列印樹的結構：");
        tree.printTree(); // 列印樹的結構
    }
}
