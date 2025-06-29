package Queue;


/**
 * 時間複雜度 
 *  插入 : O(1)
 *  刪除 : O(1)
 *  搜尋 : O(n)
 *  Peek : O(1)

 * 優點:
 *  記憶體使用固定
 *  存取元素較快
 * 
 * 缺點:
 *  容量固定
 *  需要處理循環索引
 *  當容量已滿時無法再加入元素
 * 
 * */

public class ArrayQueue {
	private int[] array;
	private int front; // 佇列前端
	private int rear; // 佇列後端
	private int size; // 目前佇列大小
	private int capacity; // 佇列容量

	// 建構子
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		array = new int[capacity];
		front = 0;
		rear = -1;
		size = 0;
	}

	// 將元素加入佇列尾端
	public void enqueue(int element) {
		if (isFull()) {
			throw new IllegalStateException("佇列已滿");
		}
		rear = (rear + 1) % capacity;
		array[rear] = element;
		size++;
	}

	// 從佇列前端移除元素
	public int dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("佇列為空");
		}
		int element = array[front];
		front = (front + 1) % capacity;
		size--;
		return element;
	}

	// 查看佇列前端元素
	public int peek() {
		if (isEmpty()) {
			throw new IllegalStateException("佇列為空");
		}
		return array[front];
	}

	// 檢查佇列是否為空
	public boolean isEmpty() {
		return size == 0;
	}

	// 檢查佇列是否已滿
	public boolean isFull() {
		return size == capacity;
	}

	// 取得佇列目前大小
	public int size() {
		return size;
	}
}
