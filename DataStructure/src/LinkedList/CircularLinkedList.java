package LinkedList;

// 節點類別
class CircularNode {
    int data; // 節點資料
    CircularNode next; // 指向下一個節點

    // 建構子
    public CircularNode(int data) {
        this.data = data;
        this.next = null;
    }
}

// 環狀鏈結串列類別
public class CircularLinkedList {
    private CircularNode tail; // 環狀鏈結串列的尾節點

    // 新增節點到環狀鏈結串列
    public void add(int data) {
        CircularNode newNode = new CircularNode(data);
        if (tail == null) { // 如果鏈結串列為空，初始化
            tail = newNode;
            tail.next = tail; // 自己指向自己形成環狀
        } else {
            newNode.next = tail.next; // 新節點指向頭節點
            tail.next = newNode; // 將新節點插入到尾節點後面
            tail = newNode; // 更新尾節點為新節點
        }
    }

    // 刪除指定值的節點
    public void delete(int data) {
        if (tail == null) { // 如果鏈結串列為空，直接返回
            System.out.println("鏈結串列為空，無法刪除");
            return;
        }

        CircularNode current = tail.next; // 從頭節點開始
        CircularNode previous = tail;

        do {
            if (current.data == data) { // 找到要刪除的節點
                if (current == tail && current.next == tail) { 
                    // 只有一個節點的情況
                    tail = null;
                } else {
                    previous.next = current.next; // 跳過目標節點
                    if (current == tail) { 
                        // 如果刪除的是尾節點，更新尾指標
                        tail = previous;
                    }
                }
                return;
            }
            previous = current;
            current = current.next;
        } while (current != tail.next); // 繞回到起始位置時停止

        System.out.println("未找到值 " + data + " 的節點");
    }

    // 顯示環狀鏈結串列中的所有元素
    public void display() {
        if (tail == null) { 
            System.out.println("鏈結串列為空");
            return;
        }

        CircularNode current = tail.next; // 從頭節點開始
        System.out.print("環狀鏈結串列: ");
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != tail.next); // 繞回到起始位置時停止

        System.out.println("(回到頭)");
    }
}
