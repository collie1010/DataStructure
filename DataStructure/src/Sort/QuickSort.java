package Sort;

/**
 * 
 * 時間複雜度: 平均: O(n*log(n)) / 最差: O(n*n)  
 * 空間複雜度: O(log(n))
 * 穩定性: False
 * 
 * 最差的情境包含:
 *  - 幾乎已經排序完畢
 *  - 所有元素皆為相同
 * 
 * 避免出現最差的情境:
 *  - 隨機選取pivot
 *  - 在排序前先進行shuffle  
 *  - 選取陣列中的中位數作為pivot
 *  
 * */

public class QuickSort {
	
	 // 快速排序的主要方法
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    
    // 遞迴實作的快速排序
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 取得分割點
            int pivotIndex = partition(arr, low, high);
            
            // 遞迴排序分割點左邊的子陣列
            quickSort(arr, low, pivotIndex - 1);
            // 遞迴排序分割點右邊的子陣列
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    // 分割方法
    private static int partition(int[] arr, int low, int high) {
        // 選擇最右邊的元素作為基準值
        int pivot = arr[high];
        
        // i 是小於基準值區域的邊界
        int i = low - 1;
        
        // 由左至右遍歷陣列
        for (int j = low; j < high; j++) {
            // 如果目前元素小於基準值，擴展小於區域並交換元素
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        // 將基準值放到正確的位置
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    // 交換陣列中兩個元素的位置
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // 印出陣列內容的輔助方法
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    // 主方法進行測試
    public static void main(String[] args) {
        // 測試案例 1：隨機順序的陣列
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("測試案例 1 - 隨機順序的陣列:");
        System.out.print("排序前: ");
        printArray(arr1);
        quickSort(arr1);
        System.out.print("排序後: ");
        printArray(arr1);
        
        // 測試案例 2：已經排序的陣列
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("\n測試案例 2 - 已經排序的陣列:");
        System.out.print("排序前: ");
        printArray(arr2);
        quickSort(arr2);
        System.out.print("排序後: ");
        printArray(arr2);
        
        // 測試案例 3：反序的陣列
        int[] arr3 = {7, 6, 5, 4, 3, 2, 1};
        System.out.println("\n測試案例 3 - 反序的陣列:");
        System.out.print("排序前: ");
        printArray(arr3);
        quickSort(arr3);
        System.out.print("排序後: ");
        printArray(arr3);
        
        // 測試案例 4：包含重複元素的陣列
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("\n測試案例 4 - 包含重複元素的陣列:");
        System.out.print("排序前: ");
        printArray(arr4);
        quickSort(arr4);
        System.out.print("排序後: ");
        printArray(arr4);
    }
}
