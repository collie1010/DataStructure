package LinkedList;

// 範例 Linked List 的實作
class Node {
    int data; // 儲存節點資料
    Node next; // 指向下一個節點

    // 建構子
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    private Node head; // 鏈結串列的起始節點（頭節點）

    // 新增節點到鏈結串列尾端
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) { // 如果鏈結串列為空，將新節點設為頭節點
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) { // 找到最後一個節點
                current = current.next;
            }
            current.next = newNode; // 將新節點加入到最後
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
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) { // 找到目標節點的前一個節點
            current = current.next;
        }

        if (current.next == null) { // 如果未找到目標值
            System.out.println("未找到值 " + data + " 的節點");
        } else {
            current.next = current.next.next; // 跳過目標節點，完成刪除
        }
    }

    // 顯示鏈結串列中的所有元素
    public void display() {
        if (head == null) {
            System.out.println("鏈結串列為空");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
