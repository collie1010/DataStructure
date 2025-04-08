package Heap;

/**
 * 時間複雜度 
 * 	插入 : O(log(n)) 
 * 	刪除 : O(log(n)) 
 *  搜尋 : O(n)
 * 
 */

public class MaxHeap {
	private int[] heap;
	private int size;
	private int maxSize;

	// 建構子
	public MaxHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		heap = new int[this.maxSize];
	}

	// 取得父節點位置
	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	// 取得左子節點位置
	private int leftChild(int pos) {
		return (2 * pos) + 1;
	}

	// 取得右子節點位置
	private int rightChild(int pos) {
		return (2 * pos) + 2;
	}

	// 交換兩個節點的值
	private void swap(int pos1, int pos2) {
		int temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}

	// 插入新節點
	public void insert(int element) {
		if (size >= maxSize) {
			resize(maxSize * 2); // 當堆積滿時，自動擴展大小
		}

		heap[size] = element;
		int current = size;
		size++;

		// 上浮操作
		bubbleUp(current);
	}

	// 上浮操作
	private void bubbleUp(int pos) {
		while (pos > 0 && heap[pos] > heap[parent(pos)]) {
			swap(pos, parent(pos));
			pos = parent(pos);
		}
	}

	// 從堆頂移除最大元素
	public int extractMax() {
		if (size <= 0) {
			throw new IllegalStateException("Heap is empty");
		}

		int maxElement = heap[0];
		heap[0] = heap[size - 1];
		size--;

		// 下沉操作
		maxHeapify(0);

		// 如果堆積大小小於最大容量的1/4，則縮小容量
		if (size > 0 && size < maxSize / 4) {
			resize(maxSize / 2);
		}

		return maxElement;
	}

	// 維護最大堆積性質
	private void maxHeapify(int pos) {
		int largest = pos;
		int left = leftChild(pos);
		int right = rightChild(pos);

		if (left < size && heap[left] > heap[largest]) {
			largest = left;
		}

		if (right < size && heap[right] > heap[largest]) {
			largest = right;
		}

		if (largest != pos) {
			swap(pos, largest);
			maxHeapify(largest);
		}
	}

	// 更新指定位置的值
	public void updateValue(int index, int newValue) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		int oldValue = heap[index];
		heap[index] = newValue;

		if (newValue > oldValue) {
			bubbleUp(index); // 如果新值更大，執行上浮
		} else {
			maxHeapify(index); // 如果新值更小，執行下沉
		}
	}

	// 刪除指定位置的元素
	public void delete(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		// 將要刪除的元素的值設為無限大
		heap[index] = Integer.MAX_VALUE;
		bubbleUp(index); // 上浮到堆頂
		extractMax(); // 移除堆頂元素
	}

	// 手動調整堆積大小
	public void resize(int newSize) {
		if (newSize < size) {
			throw new IllegalArgumentException("New size cannot be smaller than current heap size");
		}

		int[] newHeap = new int[newSize];
		System.arraycopy(heap, 0, newHeap, 0, size);
		heap = newHeap;
		maxSize = newSize;
	}

	// 查找元素位置
	public int findElement(int element) {
		for (int i = 0; i < size; i++) {
			if (heap[i] == element) {
				return i;
			}
		}
		return -1; // 未找到元素時返回-1
	}

	// 查看堆頂元素
	public int peek() {
		if (size <= 0) {
			throw new IllegalStateException("Heap is empty");
		}
		return heap[0];
	}

	// 取得堆積大小
	public int getSize() {
		return size;
	}

	// 取得堆積最大容量
	public int getMaxSize() {
		return maxSize;
	}

	// 檢查堆積是否為空
	public boolean isEmpty() {
		return size == 0;
	}

	// 印出堆積內容
	public void printHeap() {
		System.out.print("Heap: ");
		for (int i = 0; i < size; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}
}
