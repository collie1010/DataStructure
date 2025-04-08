package Queue;

public class GenericPriorityQueue<T> {

	private class Node {
		T data;
		int priority;

		Node(T data, int priority) {
			this.data = data;
			this.priority = priority;
		}
	}

	private Node[] heap;
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	public GenericPriorityQueue(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.heap = new GenericPriorityQueue.Node[capacity];
	}

	public void enqueue(T item, int priority) {
		if (isFull()) {
			throw new IllegalStateException("優先佇列已滿");
		}

		Node newNode = new Node(item, priority);
		heap[size] = newNode;
		heapifyUp(size);
		size++;
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new IllegalStateException("優先佇列為空");
		}

		T maxItem = heap[0].data;
		heap[0] = heap[size - 1];
		size--;
		heapifyDown(0);

		return maxItem;
	}

	private void heapifyUp(int index) {
		while (index > 0) {
			int parent = (index - 1) / 2;
			if (heap[index].priority > heap[parent].priority) {
				swap(index, parent);
				index = parent;
			} else {
				break;
			}
		}
	}

	private void heapifyDown(int index) {
		int maxIndex = index;
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;

		if (leftChild < size && heap[leftChild].priority > heap[maxIndex].priority) {
			maxIndex = leftChild;
		}

		if (rightChild < size && heap[rightChild].priority > heap[maxIndex].priority) {
			maxIndex = rightChild;
		}

		if (maxIndex != index) {
			swap(index, maxIndex);
			heapifyDown(maxIndex);
		}
	}

	private void swap(int i, int j) {
		Node temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == capacity;
	}
}
