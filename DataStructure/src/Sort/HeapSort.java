package Sort;

/**
 * 時間複雜度: O(n*log(n))
 */

public class HeapSort {
	public static void sort(int[] arr) {
		int n = arr.length;

		// 建立最大堆積（原地建堆）
		buildMaxHeap(arr, n);

		// 依次將最大元素移到陣列尾端
		for (int i = n - 1; i > 0; i--) {
			// 將堆頂（最大值）與當前最後一個元素交換
			swap(arr, 0, i);

			// 對剩餘元素進行堆積調整
			heapify(arr, i, 0);
		}
	}

	// 建立最大堆積
	private static void buildMaxHeap(int[] arr, int n) {
		// 從最後一個非葉節點開始，依序向上建立最大堆積
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, n, i);
		}
	}

	// 調整堆積，使其滿足最大堆積性質
	private static void heapify(int[] arr, int n, int i) {
		int largest = i; // 初始化最大值為根節點
		int left = 2 * i + 1; // 左子節點
		int right = 2 * i + 2;// 右子節點

		// 如果左子節點大於根節點
		if (left < n && arr[left] > arr[largest]) {
			largest = left;
		}

		// 如果右子節點大於目前最大值
		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}

		// 如果最大值不是根節點
		if (largest != i) {
			swap(arr, i, largest);
			// 遞迴調整受影響的子樹
			heapify(arr, n, largest);
		}
	}

	// 交換陣列中兩個元素的位置
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// 印出陣列內容的輔助方法
	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// 測試資料
		int[] arr1 = { 12, 11, 13, 5, 6, 7, 3, 2, 1 };
		int[] arr2 = { 64, 34, 25, 12, 22, 11, 90 };

		System.out.println("原始陣列 1:");
		HeapSort.printArray(arr1);
		HeapSort.sort(arr1);
		System.out.println("排序後陣列 1:");
		HeapSort.printArray(arr1);

		System.out.println("\n原始陣列 2:");
		HeapSort.printArray(arr2);
		HeapSort.sort(arr2);
		System.out.println("排序後陣列 2:");
		HeapSort.printArray(arr2);
	}

}
