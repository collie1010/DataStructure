package Sort;

/**
 * 
 * 時間複雜度: O(n + k) (n: 陣列長度 / k: 值的範圍)  
 * 空間複雜度: O(k)
 * 穩定性: True
 * 
 * */

public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // 找出陣列中的最大值和最小值
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        // 計算計數陣列的大小並建立
        int range = max - min + 1;
        int[] count = new int[range];
        
        // 計算每個元素出現的次數
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        
        // 重建原始陣列
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                arr[index] = i + min;
                index++;
                count[i]--;
            }
        }
    }
    
    // 測試程式碼
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 5, 2, 3, 7, 1, 8, 4};
        
        System.out.println("排序前：");
        printArray(arr);
        
        countingSort(arr);
        
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
