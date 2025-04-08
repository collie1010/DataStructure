package Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 時間複雜度: O(n + k) (k為桶子數量)
 * 空間複雜度: O(n + k) 
 * 穩定性: True
 *  
 */

public class BucketSort {
	public static void bucketSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 1. 找出最大值和最小值
        int maxVal = arr[0], minVal = arr[0];
        for (int num : arr) {
            if (num > maxVal) maxVal = num;
            if (num < minVal) minVal = num;
        }

        // 2. 創建桶子
        // 根據數據範圍決定桶子數量，這裡使用數組長度作為桶子數量
        int bucketCount = arr.length;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        
        // 初始化桶子
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 3. 將元素分配到桶子中
        int range = maxVal - minVal + 1;
        for (int num : arr) {
            if (range == 1) { // 處理所有元素相同的情況
                buckets.get(0).add(num);
                continue;
            }
            // 計算桶子索引
            int bucketIndex = (int) ((num - minVal) * (bucketCount - 1L) / (range - 1));
            buckets.get(bucketIndex).add(num);
        }

        // 4. 對每個桶子進行排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 5. 將排序後的元素放回原陣列
        int currentIndex = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[currentIndex++] = num;
            }
        }
    }

    // 提供另一個重載方法，可以指定桶子數量
    public static void bucketSort(int[] arr, int bucketCount) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 1. 找出最大值和最小值
        int maxVal = arr[0], minVal = arr[0];
        for (int num : arr) {
            if (num > maxVal) maxVal = num;
            if (num < minVal) minVal = num;
        }

        // 2. 創建桶子
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 3. 將元素分配到桶子中
        int range = maxVal - minVal + 1;
        for (int num : arr) {
            if (range == 1) {
                buckets.get(0).add(num);
                continue;
            }
            int bucketIndex = Math.min((int) ((num - minVal) * (bucketCount - 1L) / (range - 1)), 
                                     bucketCount - 1);
            buckets.get(bucketIndex).add(num);
        }

        // 4. 對每個桶子進行排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 5. 將排序後的元素放回原陣列
        int currentIndex = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[currentIndex++] = num;
            }
        }
    }
    
 // 測試程式
    public static void main(String[] args) {
        // 測試案例1：一般情況
        int[] arr1 = {42, 32, 73, 91, 27, 55, 36, 69};
        System.out.println("測試案例1 - 排序前：");
        printArray(arr1);
        bucketSort(arr1);
        System.out.println("測試案例1 - 排序後：");
        printArray(arr1);

        // 測試案例2：包含重複元素
        int[] arr2 = {5, 5, 3, 2, 8, 3, 5, 2};
        System.out.println("\n測試案例2 - 排序前：");
        printArray(arr2);
        bucketSort(arr2, 5); // 使用5個桶子
        System.out.println("測試案例2 - 排序後：");
        printArray(arr2);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
