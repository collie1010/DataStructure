package LinkedList;

public class CircularLinkedListTest {
    // 主程式測試範例
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        // 新增資料到環狀鏈結串列
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("新增後的環狀鏈結串列:");
        list.display();

        // 刪除資料 20
        list.delete(20);

        System.out.println("\n刪除 20 後的環狀鏈結串列:");
        list.display();

        // 嘗試刪除不存在的資料 40
        list.delete(40);

        System.out.println("\n最終的環狀鏈結串列:");
        list.display();
    }
}
