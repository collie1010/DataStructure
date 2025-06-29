package LinkedList;

public class DoublyLinkedListTest {
// 主程式測試範例
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // 新增資料到雙向鏈結串列
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("新增後的雙向鏈結串列:");
        list.displayForward();
        
        list.displayBackward();

        // 刪除資料 20
        list.delete(20);

        System.out.println("\n刪除 20 後的雙向鏈結串列:");
        list.displayForward();
        
        list.displayBackward();

        // 嘗試刪除不存在的資料 40
        list.delete(40);

        System.out.println("\n最終的雙向鏈結串列:");
        list.displayForward();
        
        list.displayBackward();
    }
}
