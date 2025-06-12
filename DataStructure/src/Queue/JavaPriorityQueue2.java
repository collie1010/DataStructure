package Queue;

import java.util.PriorityQueue;

public class JavaPriorityQueue2 {
// 自定義任務類別
    static class Task implements Comparable<Task> {
        private String name;
        private int priority;
        
        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Task other) {
            // 數字越小優先級越高
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return String.format("Task{name='%s', priority=%d}", name, priority);
        }
    }
    
    public static void main(String[] args) {
        // 創建任務優先佇列
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        
        // 新增任務
        taskQueue.offer(new Task("寫報告", 2));
        taskQueue.offer(new Task("開會", 1));
        taskQueue.offer(new Task("回信", 3));
        taskQueue.offer(new Task("緊急bug修復", 1));
        
        System.out.println("所有任務：" + taskQueue);
        System.out.println("最優先的任務：" + taskQueue.peek());
        
        System.out.println("\n依優先順序執行任務：");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("執行任務：" + task);
        }
    }
}
