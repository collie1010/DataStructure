package BinarySearch;

/***
 * 
 * InterpolationSearch 是 BinarySearch 的改良版本
 * 假設資料是均勻分布且已排序過，以線性內插估算目標值的可能位置
 * 
 * 時間複雜度: 
 *  最佳: O(1)
 *  平均情況: O(log(log(n)))
 *  最差: O(n)
 * 
 * */

public class InterpolationSearch {

	public static int interpolationSearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right && target >= arr[left] && target <= arr[right]) {
			// 確保不會發生除以零的情況
			if (left == right) {
				if (arr[left] == target) {
					return left;
				}
				return -1;
			}

			// 使用內插公式計算預測位置
			int pos = left + (((right - left) * (target - arr[left])) / (arr[right] - arr[left]));

			// 如果找到目標值
			if (arr[pos] == target) {
				return pos;
			}

			// 如果目標值小於當前位置的值，搜尋左半部
			if (arr[pos] > target) {
				right = pos - 1;
			}
			// 如果目標值大於當前位置的值，搜尋右半部
			else {
				left = pos + 1;
			}
		}
		return -1; // 找不到目標值
	}

	// 主程式測試
	public static void main(String[] args) {
		
		int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
		int target = 7;

		int result = interpolationSearch(arr, target);
		if (result != -1) {
			System.out.println("目標值 " + target + " 在索引 " + result + " 的位置");
		} else {
			System.out.println("目標值 " + target + " 不存在於陣列中");
		}
	}
}
