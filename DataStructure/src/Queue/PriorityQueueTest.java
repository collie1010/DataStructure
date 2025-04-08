package Queue;

public class PriorityQueueTest {
	 public static void main(String[] args) {
	        // 創建一個容量為 5 的優先佇列
	        GenericPriorityQueue<String> queue = new GenericPriorityQueue<>(5);

	        // 插入元素 (元素, 優先級)
	        queue.enqueue("A", 3);
	        queue.enqueue("B", 5);
	        queue.enqueue("C", 1);
	        queue.enqueue("D", 4);
	        queue.enqueue("E", 2);

	        System.out.println("從優先佇列中取出元素 (按照優先級順序):");
	        while (!queue.isEmpty()) {
	            System.out.println(queue.dequeue());
	        }
	    }
}
