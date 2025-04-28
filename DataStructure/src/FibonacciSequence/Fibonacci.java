package FibonacciSequence;

/**
 * 時間複雜度:
 *  fibonacciRecursive: O(2^n)
 *  fibonacciRecursiveV2: O(n)
 *  fibonacciRecursiveImprove: O(n)
 * 
 * 空間複雜度:
 * 	fibonacciRecursive: O(n) 
 *  fibonacciRecursiveV2: O(n)
 *  fibonacciRecursiveImprove: O(1)
 * 
 * */


public class Fibonacci {

	public static int fibonacciRecursive(int n) {

		if (n < 1) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
		}

	}

	public static int fibonacciRecursiveV2(int n) {

		if (n == 0 || n == 1) {
			return n;
		}

		int fibo[] = new int[n + 1];

		fibo[0] = 0;
		fibo[1] = 1;

		for (int i = 2; i <= n; i++) {
			fibo[i] = fibo[i - 1] + fibo[i - 2];
		}

		return fibo[n];
	}

	public static int fibonacciImprove(int n) {

		if (n == 0 || n == 1) {
			return n;
		}

		int a = 0;
		int b = 1;

		for (int i = 2; i <= n; i++) {
			int c = a + b;
			a = b;
			b = c;
		}

		return b;
	}

	public static void main(String[] args) {
		// 測試各種情況
		System.out.println("測試 fibonacciRecursive 方法:");
		System.out.println("n = 0: " + fibonacciRecursive(0)); // 預期: 0
		System.out.println("n = 1: " + fibonacciRecursive(1)); // 預期: 1
		System.out.println("n = 2: " + fibonacciRecursive(2)); // 預期: 1
		System.out.println("n = 5: " + fibonacciRecursive(5)); // 預期: 5
		System.out.println("n = 8: " + fibonacciRecursive(8)); // 預期: 21
		System.out.println();

		System.out.println("測試 fibonacciRecursiveV2 方法:");
		System.out.println("n = 0: " + fibonacciRecursiveV2(0)); // 預期: 0
		System.out.println("n = 1: " + fibonacciRecursiveV2(1)); // 預期: 1
		System.out.println("n = 2: " + fibonacciRecursiveV2(2)); // 預期: 1
		System.out.println("n = 5: " + fibonacciRecursiveV2(5)); // 預期: 5
		System.out.println("n = 8: " + fibonacciRecursiveV2(8)); // 預期: 21
		System.out.println();

		System.out.println("測試 fibonacciImprove 方法:");
		System.out.println("n = 0: " + fibonacciImprove(0)); // 預期: 0
		System.out.println("n = 1: " + fibonacciImprove(1)); // 預期: 1
		System.out.println("n = 2: " + fibonacciImprove(2)); // 預期: 1
		System.out.println("n = 5: " + fibonacciImprove(5)); // 預期: 5
		System.out.println("n = 8: " + fibonacciImprove(8)); // 預期: 21
		System.out.println();

		// 效能比較測試
		int largeNumber = 45;

		long startTime = System.currentTimeMillis();
		int result1 = fibonacciRecursive(largeNumber);
		long endTime = System.currentTimeMillis();
		System.out.println("遞迴方法計算 n = " + largeNumber + " 的結果: " + result1);
		System.out.println("耗時: " + (endTime - startTime) + "ms");
		System.out.println();

		startTime = System.currentTimeMillis();
		int result2 = fibonacciRecursiveV2(largeNumber);
		endTime = System.currentTimeMillis();
		System.out.println("遞迴V2方法計算 n = " + largeNumber + " 的結果: " + result2);
		System.out.println("耗時: " + (endTime - startTime) + "ms");
		System.out.println();

		startTime = System.currentTimeMillis();
		int result3 = fibonacciImprove(largeNumber);
		endTime = System.currentTimeMillis();
		System.out.println("改進方法計算 n = " + largeNumber + " 的結果: " + result3);
		System.out.println("耗時: " + (endTime - startTime) + "ms");

		// 驗證結果是否一致
		if (result1 == result2 && result2 == result3) {
			System.out.println("\n所有方法結果一致！");
		} else {
			System.out.println("\n警告：結果不一致！");
		}
	}
}
