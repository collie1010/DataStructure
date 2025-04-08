package algo;

public class RedBlackTreeTest {
	public static void main(String[] args) {
	    RedBlackTree<Integer> tree = new RedBlackTree<>();
	    
	    // 插入一些數值
	    tree.insert(10);
	    tree.insert(5);
	    tree.insert(15);
	    tree.insert(3);
	    tree.insert(7);
	    
	    // 印出中序遍歷結果
	    System.out.println("中序遍歷：");
	    tree.inorderTraversal();
	    
	    // 測試查詢
	    System.out.println("Contains 7: " + tree.contains(7));
	    System.out.println("Contains 8: " + tree.contains(8));
	    
	    // 印出最大值和最小值
	    System.out.println("最小值：" + tree.min());
	    System.out.println("最大值：" + tree.max());
	    
	    // 印出樹的大小
	    System.out.println("樹的大小：" + tree.size());
	}

}
