package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class JavaQueue {
public static void main(String[] args) {
        // 建立一個 Queue，使用 LinkedList 實作
        Queue<String> queue = new LinkedList<>();

        // 新增元素到 Queue
        System.out.println("==== 新增元素到 Queue ====");
        queue.offer("第一個");
        queue.offer("第二個");
        queue.offer("第三個");
        System.out.println("目前 Queue 內容：" + queue);

        // 查看但不移除 Queue 的第一個元素
        System.out.println("\n==== 查看 Queue 第一個元素 ====");
        System.out.println("第一個元素是：" + queue.peek());
        System.out.println("查看後 Queue 內容：" + queue);

        // 移除並回傳 Queue 的第一個元素
        System.out.println("\n==== 移除 Queue 元素 ====");
        String removedElement = queue.poll();
        System.out.println("移除的元素是：" + removedElement);
        System.out.println("移除後 Queue 內容：" + queue);

        // 檢查 Queue 是否為空
        System.out.println("\n==== 檢查 Queue 狀態 ====");
        System.out.println("Queue 是否為空：" + queue.isEmpty());
        System.out.println("Queue 的大小：" + queue.size());

        // 清空 Queue
        System.out.println("\n==== 清空 Queue ====");
        queue.clear();
        System.out.println("清空後 Queue 是否為空：" + queue.isEmpty());
    }
}
