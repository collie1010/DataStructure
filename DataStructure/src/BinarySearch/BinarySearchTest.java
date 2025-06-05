package BinarySearch;

public class BinarySearchTest {
    // 主方法進行測試
    public static void main(String[] args) {
        // 測試用的已排序陣列
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        
        // 測試案例
        int[] testCases = {23, 2, 91, 50, 16};
        
        System.out.println("使用迭代方式的二元搜尋：");
        for (int target : testCases) {
            int result = BinarySearch.binarySearch(arr, target);
            if (result != -1) {
                System.out.printf("數字 %d 在索引 %d 的位置%n", target, result);
            } else {
                System.out.printf("數字 %d 不在陣列中%n", target);
            }
        }
        
        System.out.println("\n使用遞迴方式的二元搜尋：");
        for (int target : testCases) {
            int result = BinarySearch.binarySearchRecursive(arr, target, 0, arr.length - 1);
            if (result != -1) {
                System.out.printf("數字 %d 在索引 %d 的位置%n", target, result);
            } else {
                System.out.printf("數字 %d 不在陣列中%n", target);
            }
        }
    }
}
