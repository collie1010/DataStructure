package Sort;

/**
 * 
 * 時間複雜度: O(n*n) 
 * 空間複雜度: O(1)
 * 穩定性: True
 * 
 * */

public class InsertionSort {
    
    // 插入排序方法
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // 將大於 key 的元素往後移
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            
            // 顯示每次迭代後的陣列狀態（用於觀察排序過程）
            //printArray("迭代 " + i + ": ", arr);
        }
    }
    
    // 用於顯示陣列內容的輔助方法
    private static void printArray(String message, int[] arr) {
        System.out.print(message + "[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    // 檢查陣列是否已排序的方法
    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    // 主方法進行測試
    public static void main(String[] args) {
        // 測試案例
        int[][] testCases = {
            {64, 34, 25, 12, 22, 11, 90},         // 一般情況
            {1, 2, 3, 4, 5},                      // 已排序
            {5, 4, 3, 2, 1},                      // 反序
            {1},                                  // 單一元素
            {},                                   // 空陣列
            {1, 1, 1, 1, 1},                     // 相同元素
            {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5},  // 重複元素
            {-5, 0, -3, 8, -1, 2, -4}           // 負數
        };
        
        // 對每個測試案例執行排序
        for (int i = 0; i < testCases.length; i++) {
            System.out.printf("\n測試案例 %d:\n", i + 1);
            
            int[] arr = testCases[i].clone();  // 複製陣列以保留原始資料
            
            System.out.print("排序前: ");
            printArray("", arr);
            
            // 記錄開始時間
            long startTime = System.nanoTime();
            
            // 執行排序
            insertionSort(arr);
            
            // 記錄結束時間
            long endTime = System.nanoTime();
            
            System.out.print("排序後: ");
            printArray("", arr);
            
            // 驗證排序結果
            boolean sorted = isSorted(arr);
            System.out.println("是否正確排序: " + sorted);
            
            // 顯示執行時間
            System.out.printf("執行時間: %.3f 微秒\n", (endTime - startTime) / 1000.0);
            System.out.println("--------------------");
        }
        
        // 效能測試 - 大型陣列
        System.out.println("\n效能測試 - 大型陣列:");
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int)(Math.random() * 10000);  // 隨機數值
        }
        
        // 複製陣列用於不同的測試情況
        int[] sortedLargeArray = largeArray.clone();
        int[] reverseLargeArray = largeArray.clone();
        
        // 預先排序
        java.util.Arrays.sort(sortedLargeArray);
        // 建立反序陣列
        for (int i = 0; i < reverseLargeArray.length / 2; i++) {
            int temp = reverseLargeArray[i];
            reverseLargeArray[i] = reverseLargeArray[reverseLargeArray.length - 1 - i];
            reverseLargeArray[reverseLargeArray.length - 1 - i] = temp;
        }
        
        // 測試不同情況
        System.out.println("\n1. 隨機陣列:");
        testLargeArray(largeArray.clone());
        
        System.out.println("\n2. 已排序陣列:");
        testLargeArray(sortedLargeArray.clone());
        
        System.out.println("\n3. 反序陣列:");
        testLargeArray(reverseLargeArray.clone());
    }
    
    // 用於測試大型陣列的輔助方法
    private static void testLargeArray(int[] arr) {
        System.out.printf("陣列大小: %d\n", arr.length);
        
        long startTime = System.nanoTime();
        insertionSort(arr);
        long endTime = System.nanoTime();
        
        boolean sorted = isSorted(arr);
        System.out.println("是否正確排序: " + sorted);
        System.out.printf("執行時間: %.3f 毫秒\n", (endTime - startTime) / 1000000.0);
    }
}

