package algo;

public class LinkedListTest {
	public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // 測試新增節點
        System.out.println("===測試新增節點===");
        list.append(10);
        list.append(20);
        list.append(30);
        list.display();

        // 測試插入節點
        System.out.println("\n===測試插入節點===");
        list.insert(2, 250);
        list.insert(2, 300);
        list.display();

        // 測試搜尋節點
        System.out.println("\n===測試搜尋節點===");
        System.out.println("搜尋 15: " + list.search(15));
        System.out.println("搜尋 25: " + list.search(25));

        // 測試刪除節點
        System.out.println("\n===測試刪除節點===");
        list.delete(15);
        list.display();

        // 測試串列大小
        System.out.println("\n===測試串列大小===");
        System.out.println("串列大小: " + list.getSize());
    }
}
