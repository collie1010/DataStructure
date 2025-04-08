package Sort;

/**
 * 
 * 時間複雜度: O(n*n) 
 * 空間複雜度: O(1)
 * 穩定性: False
 * 
 * */

public class SelectionSort {
	// 選擇排序
	public static void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			// 交換元素
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}
	}
	
	// 測試程式碼
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 5, 2, 3, 7, 1, 8, 4};
        
        System.out.println("排序前：");
        printArray(arr);
        
        selectionSort(arr);
        
        System.out.println("排序後：");
        printArray(arr);
    }
    
    // 印出陣列的輔助方法
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
