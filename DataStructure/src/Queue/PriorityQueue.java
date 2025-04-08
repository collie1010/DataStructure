package Queue;

public class PriorityQueue {
	private int[] heap;
    private int size;
    private int capacity;
    
    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }
    
    // 插入元素
    public void enqueue(int element) {
        if (isFull()) {
            throw new IllegalStateException("優先佇列已滿");
        }
        
        // 將新元素加到堆的最後
        heap[size] = element;
        
        // 向上調整堆的性質
        heapifyUp(size);
        size++;
    }
    
    // 取出最高優先級的元素
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("優先佇列為空");
        }
        
        int max = heap[0];
        
        // 將最後一個元素移到根節點
        heap[0] = heap[size - 1];
        size--;
        
        // 向下調整堆的性質
        heapifyDown(0);
        
        return max;
    }
    
    // 查看最高優先級的元素
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("優先佇列為空");
        }
        return heap[0];
    }
    
    // 向上調整
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        
        while (index > 0 && heap[index] > heap[parent]) {
            // 交換元素
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }
    
    // 向下調整
    private void heapifyDown(int index) {
        int maxIndex = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        
        // 比較左子節點
        if (leftChild < size && heap[leftChild] > heap[maxIndex]) {
            maxIndex = leftChild;
        }
        
        // 比較右子節點
        if (rightChild < size && heap[rightChild] > heap[maxIndex]) {
            maxIndex = rightChild;
        }
        
        // 如果最大值不是當前節點，則交換並繼續向下調整
        if (maxIndex != index) {
            swap(index, maxIndex);
            heapifyDown(maxIndex);
        }
    }
    
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public int size() {
        return size;
    }
}
