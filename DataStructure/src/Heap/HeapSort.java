package Heap;

public class HeapSort {
    public static void sort(int[] arr) {
        // 建立 MaxHeap
        MaxHeap heap = new MaxHeap(arr.length);
        
        // 將所有元素加入堆積
        for (int num : arr) {
            heap.insert(num);
        }
        
        // 依序取出元素，從後面開始放入原陣列
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }
    
    public static void main(String[] args) {
    	// 測試資料
    	int[] arr1 = { 12, 11, 13, 5, 6, 7, 3, 2, 1 };
    	System.out.println("原始陣列 1:");
    	HeapSort.printArray(arr1);
    	HeapSort.sort(arr1);
    	System.out.println("排序後陣列 1:");
    	HeapSort.printArray(arr1);
    	
    }
    
    
    public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}
