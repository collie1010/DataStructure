package Queue;

public class QueueTest {
	public static void main(String[] args) {
		// 使用陣列實現的佇列
		ArrayQueue arrayQueue = new ArrayQueue(5);
		arrayQueue.enqueue(1);
		arrayQueue.enqueue(2);
		arrayQueue.enqueue(3);
		System.out.println("取出元素：" + arrayQueue.dequeue()); // 輸出：1
		System.out.println("查看前端元素：" + arrayQueue.peek()); // 輸出：2

		// 使用鏈結串列實現的佇列
		LinkedQueue linkedQueue = new LinkedQueue();
		linkedQueue.enqueue(1);
		linkedQueue.enqueue(2);
		linkedQueue.enqueue(3);
		System.out.println("取出元素：" + linkedQueue.dequeue()); // 輸出：1
		System.out.println("查看前端元素：" + linkedQueue.peek()); // 輸出：2

	}
}
