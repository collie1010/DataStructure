package BinarySearch;

/***
 * 
 * 時間複雜度: 
 *  最佳: O(1)
 *  平均情況: O(log(n))
 *  最差: O(log(n))
 * 
 * 空間複雜度:
 *  Iterative: O(1)
 *  Recursive: O(log(n)) (因為需要遞迴呼叫 Stack)
 *  
 * */

public class BinarySearch {
	// 二元搜尋方法
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;  // 避免 (left + right) / 2 可能的溢位
            
            // 找到目標值
            if (arr[mid] == target) {
                return mid;
            }
            
            // 目標值在左半邊
            if (arr[mid] > target) {
                right = mid - 1;
            }
            // 目標值在右半邊
            else {
                left = mid + 1;
            }
        }
        
        // 找不到目標值
        return -1;
    }
    
    // 遞迴版本的二元搜尋
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        }
        
        if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
        
        return binarySearchRecursive(arr, target, mid + 1, right);
    }
    
    // 主方法進行測試
    public static void main(String[] args) {
        // 測試用的已排序陣列
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        
        // 測試案例
        int[] testCases = {23, 2, 91, 50, 16};
        
        System.out.println("使用迭代方式的二元搜尋：");
        for (int target : testCases) {
            int result = binarySearch(arr, target);
            if (result != -1) {
                System.out.printf("數字 %d 在索引 %d 的位置%n", target, result);
            } else {
                System.out.printf("數字 %d 不在陣列中%n", target);
            }
        }
        
        System.out.println("\n使用遞迴方式的二元搜尋：");
        for (int target : testCases) {
            int result = binarySearchRecursive(arr, target, 0, arr.length - 1);
            if (result != -1) {
                System.out.printf("數字 %d 在索引 %d 的位置%n", target, result);
            } else {
                System.out.printf("數字 %d 不在陣列中%n", target);
            }
        }
    }
}
