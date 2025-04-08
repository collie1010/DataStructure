package Stack;

public class LinkedStack<T>  {
	private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top;    // 堆疊頂端
    private int size;    // 堆疊大小

    // 建構子
    public LinkedStack() {
        top = null;
        size = 0;
    }

    // 將元素推入堆疊頂端
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // 從堆疊頂端移除並回傳元素
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // 查看堆疊頂端元素但不移除
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    // 檢查堆疊是否為空
    public boolean isEmpty() {
        return top == null;
    }

    // 取得堆疊中的元素數量
    public int size() {
        return size;
    }

    // 清空堆疊
    public void clear() {
        top = null;
        size = 0;
    }

    // 將堆疊內容轉換為字串
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: [");
        Node current = top;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
