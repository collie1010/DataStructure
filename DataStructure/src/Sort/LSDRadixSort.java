package Sort;

/**
 * 時間複雜度: O(d*n) (d: 最大數字的位數 / n: 陣列長度) 
 * 					 (對於固定位數的整數，可以視為O(n))
 *  
 * 空間複雜度: O(n + k) (k是基數)
 * 穩定性: True
 * 
 **/

public class LSDRadixSort {
    public static void lsdRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 找出最大值來決定位數
        int max = getMax(arr);
        
        // 從最低位（個位）開始，逐位進行計數排序
        // 每次除以exp就能獲得對應位置的數字
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    // 找出陣列中的最大值
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 對特定位數進行計數排序
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        
        // 建立輸出陣列
        int[] output = new int[n];
        
        // 計數陣列，範圍是0-9
        int[] count = new int[10];
        
        // 初始化計數陣列
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // 統計每個數字出現的次數
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // 修改count[i]，使其包含實際位置信息
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 建立輸出陣列
        // 從後向前遍歷確保穩定性
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // 將排序結果複製回原陣列
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 擴展版本：處理包含負數的LSD基數排序
    public static void lsdRadixSortWithNegatives(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 找出最大絕對值
        int maxAbs = Math.abs(arr[0]);
        for (int num : arr) {
            maxAbs = Math.max(maxAbs, Math.abs(num));
        }

        // 將所有數字轉換為非負數（通過加上最大絕對值）
        for (int i = 0; i < arr.length; i++) {
            arr[i] += maxAbs;
        }

        // 執行基數排序
        lsdRadixSort(arr);

        // 還原原始值（減去最大絕對值）
        for (int i = 0; i < arr.length; i++) {
            arr[i] -= maxAbs;
        }
    }

    // 測試程式
    public static void main(String[] args) {
        // 測試案例1：一般正整數
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("測試案例1 - 排序前：");
        printArray(arr1);
        lsdRadixSort(arr1);
        System.out.println("測試案例1 - 排序後：");
        printArray(arr1);

        // 測試案例2：包含負數
        int[] arr2 = {170, -45, 75, -90, 802, 24, -2, 66};
        System.out.println("\n測試案例2 - 排序前：");
        printArray(arr2);
        lsdRadixSortWithNegatives(arr2);
        System.out.println("測試案例2 - 排序後：");
        printArray(arr2);

        // 測試案例3：包含重複數字
        int[] arr3 = {123, 123, 345, 12, 12, 543, 345};
        System.out.println("\n測試案例3 - 排序前：");
        printArray(arr3);
        lsdRadixSort(arr3);
        System.out.println("測試案例3 - 排序後：");
        printArray(arr3);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
