package Heap;

public class MaxHeapTest {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(5);
        
        // 測試插入
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        
        System.out.println("初始堆積：");
        maxHeap.printHeap();

        // 測試更新值
        System.out.println("\n將索引 2 的值更新為 90：");
        maxHeap.updateValue(2, 90);
        maxHeap.printHeap();

        // 測試刪除指定元素
        System.out.println("\n刪除索引 1 的元素：");
        maxHeap.delete(1);
        maxHeap.printHeap();

        // 測試調整大小
        System.out.println("\n調整堆積大小為 10：");
        maxHeap.resize(10);
        System.out.println("新的最大容量：" + maxHeap.getMaxSize());

        // 測試查找元素
        int searchValue = 5;
        int position = maxHeap.findElement(searchValue);
        System.out.println("\n元素 " + searchValue + " 的位置：" + position);
    }
}
