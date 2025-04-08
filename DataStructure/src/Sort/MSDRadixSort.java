package Sort;

/**
 * 時間複雜度: O(d*n) (d: 最大數字的位數 / n: 陣列長度) 
 * 空間複雜度: O(n + k) (k是基數)
 * 穩定性: True
 * 
 **/

public class MSDRadixSort {
    // 為了處理不同位數，我們需要一個特殊的值來標記數字的結束
    private static final int RADIX = 10;
    
    public static void msdRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 找出最大值以確定最大位數
        int max = getMax(arr);
        
        // 計算最大位數
        int maxDigits = getDigitCount(max);
        
        // 建立輔助陣列
        int[] aux = new int[arr.length];
        
        // 開始排序
        msdRadixSort(arr, aux, 0, arr.length - 1, maxDigits);
    }

    private static void msdRadixSort(int[] arr, int[] aux, int low, int high, int d) {
        // 如果區間長度小於等於1或已經處理完所有位數，則返回
        if (high <= low || d == 0) {
            return;
        }

        // 計算當前位的除數
        int exp = (int) Math.pow(10, d - 1);

        // 計數陣列
        int[] count = new int[RADIX + 1];

        // 計算當前位的數字分布
        for (int i = low; i <= high; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit + 1]++;
        }

        // 計算累積和
        for (int r = 0; r < RADIX; r++) {
            count[r + 1] += count[r];
        }

        // 複製到輔助陣列
        for (int i = low; i <= high; i++) {
            int digit = (arr[i] / exp) % 10;
            aux[count[digit]++] = arr[i];
        }

        // 複製回原陣列
        for (int i = low; i <= high; i++) {
            arr[i] = aux[i - low];
        }

        // 遞迴處理每個子區間
        for (int r = 0; r < RADIX; r++) {
            int newLow = low + count[r];
            int newHigh = low + count[r + 1] - 1;
            if (newHigh > newLow) {
                msdRadixSort(arr, aux, newLow, newHigh, d - 1);
            }
        }
    }

    // 處理負數的MSD基數排序
    public static void msdRadixSortWithNegatives(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 找出最大絕對值
        int maxAbs = Math.abs(arr[0]);
        for (int num : arr) {
            maxAbs = Math.max(maxAbs, Math.abs(num));
        }

        // 分離正負數
        int negCount = 0;
        for (int num : arr) {
            if (num < 0) negCount++;
        }

        int[] negatives = new int[negCount];
        int[] positives = new int[arr.length - negCount];
        int negIndex = 0, posIndex = 0;

        for (int num : arr) {
            if (num < 0) {
                negatives[negIndex++] = -num;
            } else {
                positives[posIndex++] = num;
            }
        }

        // 分別排序正負數
        msdRadixSort(negatives);
        msdRadixSort(positives);

        // 合併結果
        for (int i = 0; i < negCount; i++) {
            arr[i] = -negatives[negCount - 1 - i];
        }
        System.arraycopy(positives, 0, arr, negCount, positives.length);
    }

    // 獲取數字的位數
    private static int getDigitCount(int num) {
        if (num == 0) return 1;
        return (int) Math.floor(Math.log10(num)) + 1;
    }

    // 獲取最大值
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // 測試主程式
    public static void main(String[] args) {
        // 測試案例1：普通正整數
        int[] arr1 = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("測試案例1 - 排序前：");
        printArray(arr1);
        msdRadixSort(arr1);
        System.out.println("測試案例1 - 排序後：");
        printArray(arr1);

        // 測試案例2：包含負數
        int[] arr2 = {170, -45, 75, -90, 802, 24, -2, 66};
        System.out.println("\n測試案例2 - 排序前：");
        printArray(arr2);
        msdRadixSortWithNegatives(arr2);
        System.out.println("測試案例2 - 排序後：");
        printArray(arr2);

        // 測試案例3：重複數字
        int[] arr3 = {1234, 1234, 3456, 123, 123, 5432, 3456};
        System.out.println("\n測試案例3 - 排序前：");
        printArray(arr3);
        msdRadixSort(arr3);
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
