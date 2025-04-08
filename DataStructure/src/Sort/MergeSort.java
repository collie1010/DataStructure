package Sort;

/**
 * 
 * 時間複雜度: O(n*log(n)) 
 * 空間複雜度: O(n)
 * 穩定性: True
 * 
 * */

public class MergeSort {
	 // 合併排序的主要方法
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        int[] temp = new int[arr.length];  // 建立暫存陣列
        mergeSort(arr, temp, 0, arr.length - 1);
    }
    
    // 遞迴實作的合併排序
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;  // 避免溢位
            
            // 遞迴排序左半部
            mergeSort(arr, temp, left, mid);
            // 遞迴排序右半部
            mergeSort(arr, temp, mid + 1, right);
            
            // 合併排序後的兩半部
            merge(arr, temp, left, mid, right);
        }
    }
    
    // 合併兩個已排序的子陣列
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 複製要合併的元素到暫存陣列
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        
        int i = left;      // 左半部起始索引
        int j = mid + 1;   // 右半部起始索引
        int k = left;      // 合併後的陣列索引
        
        // 比較並合併兩個子陣列
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        
        // 複製剩餘的左半部元素
        while (i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
        // 注意：右半部的剩餘元素已經在正確的位置上
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
        mergeSort(arr1);
        System.out.print("排序後: ");
        printArray(arr1);
        
        // 測試案例 2：已經排序的陣列
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("\n測試案例 2 - 已經排序的陣列:");
        System.out.print("排序前: ");
        printArray(arr2);
        mergeSort(arr2);
        System.out.print("排序後: ");
        printArray(arr2);
        
        // 測試案例 3：反序的陣列
        int[] arr3 = {7, 6, 5, 4, 3, 2, 1};
        System.out.println("\n測試案例 3 - 反序的陣列:");
        System.out.print("排序前: ");
        printArray(arr3);
        mergeSort(arr3);
        System.out.print("排序後: ");
        printArray(arr3);
        
        // 測試案例 4：包含重複元素的陣列
        int[] arr4 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("\n測試案例 4 - 包含重複元素的陣列:");
        System.out.print("排序前: ");
        printArray(arr4);
        mergeSort(arr4);
        System.out.print("排序後: ");
        printArray(arr4);
        
        // 測試案例 5：較大的陣列
        int[] arr5 = new int[20];
        for (int i = 0; i < arr5.length; i++) {
            arr5[i] = (int)(Math.random() * 100);  // 產生0-99的隨機數
        }
        System.out.println("\n測試案例 5 - 較大的隨機陣列:");
        System.out.print("排序前: ");
        printArray(arr5);
        mergeSort(arr5);
        System.out.print("排序後: ");
        printArray(arr5);
    }
}
