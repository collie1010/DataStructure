package Sort;

/**
 * 
 * 時間複雜度: O(d*n) (d: 最大數字的位數 / n: 陣列長度)
 * 空間複雜度: O(n + k) (k是基數)
 * 穩定性: True
 * 
 * 適用情境:
 *  適用於固定長度的整數排序
 *  數據範圍較小且密集時的效果較佳
 *  
 */

public class RadixSort {
	public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 找出最大值
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        // 對每個位數進行計數排序
        // exp是當前處理的位數，從個位數開始
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    // 使用計數排序來排序特定位數
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];  // 暫存排序結果
        int[] count = new int[10];  // 計數陣列，對應0-9這10個數字
        
        // 初始化計數陣列
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // 統計每個數字出現的次數
        for (int value : arr) {
            count[(value / exp) % 10]++;
        }

        // 修改count陣列，使count[i]表示小於等於i的數字個數
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 建立輸出陣列
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // 複製輸出陣列到原陣列
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 處理包含負數的基數排序
    public static void radixSortWithNegative(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 將陣列分成負數和非負數兩部分
        int negCount = 0;
        for (int num : arr) {
            if (num < 0) {
                negCount++;
            }
        }

        int[] negatives = new int[negCount];
        int[] nonNegatives = new int[arr.length - negCount];
        int negIndex = 0, nonNegIndex = 0;

        // 分離負數和非負數
        for (int num : arr) {
            if (num < 0) {
                negatives[negIndex++] = -num; // 將負數轉為正數處理
            } else {
                nonNegatives[nonNegIndex++] = num;
            }
        }

        // 分別排序
        radixSort(negatives);
        radixSort(nonNegatives);

        // 合併結果
        for (int i = 0; i < negCount; i++) {
            arr[i] = -negatives[negCount - 1 - i]; // 反轉順序並轉回負數
        }
        System.arraycopy(nonNegatives, 0, arr, negCount, nonNegatives.length);
    }

    // 測試程式
    public static void main(String[] args) {
        // 測試案例1：一般正整數
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("測試案例1 - 排序前：");
        printArray(arr1);
        radixSort(arr1);
        System.out.println("測試案例1 - 排序後：");
        printArray(arr1);

        // 測試案例2：包含負數
        int[] arr2 = {170, -45, 75, -90, 802, 24, -2, 66};
        System.out.println("\n測試案例2 - 排序前：");
        printArray(arr2);
        radixSortWithNegative(arr2);
        System.out.println("測試案例2 - 排序後：");
        printArray(arr2);

        // 測試案例3：重複數字
        int[] arr3 = {53, 89, 53, 11, 89, 27, 11, 53};
        System.out.println("\n測試案例3 - 排序前：");
        printArray(arr3);
        radixSort(arr3);
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
