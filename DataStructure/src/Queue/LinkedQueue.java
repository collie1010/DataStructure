package Queue;

/**
 * 時間複雜度 
 *  插入 : O(1)
 *  刪除 : O(1)
 *  
 * 優點:
 *  容量大小可動態調整
 *  不需要預先定義容量
 *  記憶體使用更有效率
 * 
 * 缺點:
 *  需要額外的記憶體儲存節點的指標
 *  存取元素較慢
 * 
 * */

public class LinkedQueue {
	// 節點類別
	private class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node front; // 佇列前端
	private Node rear;  // 佇列後端
	private int size;   // 佇列大小

	// 建構子
	public LinkedQueue() {
		front = null;
		rear = null;
		size = 0;
	}

	// 將元素加入佇列尾端
	public void enqueue(int element) {
		Node newNode = new Node(element);

		if (isEmpty()) {
			front = newNode;
		} else {
			rear.next = newNode;
		}
		rear = newNode;
		size++;
	}

	// 從佇列前端移除元素
	public int dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("佇列為空");
		}

		int element = front.data;
		front = front.next;
		size--;

		if (isEmpty()) {
			rear = null;
		}
		return element;
	}

	// 查看佇列前端元素
	public int peek() {
		if (isEmpty()) {
			throw new IllegalStateException("佇列為空");
		}
		return front.data;
	}

	// 檢查佇列是否為空
	public boolean isEmpty() {
		return size == 0;
	}

	// 取得佇列目前大小
	public int size() {
		return size;
	}
}
