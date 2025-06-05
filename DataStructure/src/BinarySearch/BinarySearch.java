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
    
    
}
