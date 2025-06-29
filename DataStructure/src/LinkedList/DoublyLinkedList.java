package LinkedList;

// 節點類別
class DoublyNode {
    int data; // 儲存節點資料
    DoublyNode prev; // 指向前一個節點
    DoublyNode next; // 指向下一個節點

    // 建構子
    public DoublyNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// 雙向鏈結串列類別
public class DoublyLinkedList {
    private DoublyNode head; // 雙向鏈結串列的頭節點

    // 新增節點到鏈結串列尾端
    public void add(int data) {
        DoublyNode newNode = new DoublyNode(data);
        if (head == null) { // 如果鏈結串列為空，將新節點設為頭節點
            head = newNode;
        } else {
            DoublyNode current = head;
            while (current.next != null) { // 找到最後一個節點
                current = current.next;
            }
            current.next = newNode; // 將新節點加入到最後
            newNode.prev = current; // 設定新節點的前驅指標
        }
    }

    // 刪除指定值的節點
    public void delete(int data) {
        if (head == null) { // 如果鏈結串列為空，直接返回
            System.out.println("鏈結串列為空，無法刪除");
            return;
        }

        if (head.data == data) { // 如果要刪除的是頭節點
            head = head.next;
            if (head != null) {
                head.prev = null; // 更新新的頭節點的前驅指標
            }
            return;
        }

        DoublyNode current = head;
        while (current != null && current.data != data) { // 找到目標值所在的節點
            current = current.next;
        }

        if (current == null) { // 如果未找到目標值
            System.out.println("未找到值 " + data + " 的節點");
        } else {
            if (current.next != null) { // 如果目標節點不是最後一個節點
                current.next.prev = current.prev;
            }
            if (current.prev != null) { // 如果目標節點不是第一個節點
                current.prev.next = current.next;
            }
        }
    }

    // 從頭到尾顯示鏈結串列中的所有元素
    public void displayForward() {
        if (head == null) {
            System.out.println("鏈結串列為空");
            return;
        }

        System.out.print("正向: ");
        DoublyNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 從尾到頭顯示鏈結串列中的所有元素
    public void displayBackward() {
        if (head == null) {
            System.out.println("鏈結串列為空");
            return;
        }

        System.out.print("反向: ");
        DoublyNode current = head;

        // 找到最後一個節點
        while (current.next != null) {
            current = current.next;
        }

        // 從尾到頭遍歷鏈結串列
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.prev;
        }
        System.out.println("null");
    }
}
