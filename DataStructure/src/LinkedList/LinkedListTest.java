package LinkedList;

public class LinkedListTest {
    // 主程式測試範例
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // 新增資料到鏈結串列
        list.add(10);
        list.add(20);
        list.add(30);
        
        System.out.println("新增後的鏈結串列:");
        list.display();

        // 刪除資料 20
        list.delete(20);
        
        System.out.println("刪除 20 後的鏈結串列:");
        list.display();

        // 嘗試刪除不存在的資料 40
        list.delete(40);

        // 再次顯示鏈結串列內容
        System.out.println("最終的鏈結串列:");
        list.display();
    }
}

