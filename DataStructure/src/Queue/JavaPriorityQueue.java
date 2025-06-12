package Queue;

import java.util.PriorityQueue;


public class JavaPriorityQueue {
public static void main(String[] args) {
        // 創建一個預設的最小堆積（小的數字優先）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // 新增元素
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(8);
        minHeap.offer(1);
        minHeap.offer(3);
        
        System.out.println("原始佇列內容：" + minHeap);
        System.out.println("佇列大小：" + minHeap.size());
        System.out.println("佇列頭部元素（不移除）：" + minHeap.peek());
        
        System.out.println("\n依序取出元素：");
        while (!minHeap.isEmpty()) {
            System.out.println("取出：" + minHeap.poll());
        }
        
        // 創建最大堆積（大的數字優先）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        maxHeap.offer(5);
        maxHeap.offer(2);
        maxHeap.offer(8);
        maxHeap.offer(1);
        maxHeap.offer(3);
        
        System.out.println("\n最大堆積依序取出元素：");
        while (!maxHeap.isEmpty()) {
            System.out.println("取出：" + maxHeap.poll());
        }
    }
}
