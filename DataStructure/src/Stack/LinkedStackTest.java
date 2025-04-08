package Stack;

public class LinkedStackTest {
	public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();

        // 測試推入元素
        stack.push("第一個");
        stack.push("第二個");
        stack.push("第三個");

        // 顯示堆疊內容
        System.out.println(stack);  // 輸出：Stack: [第三個, 第二個, 第一個]

        // 測試peek
        System.out.println("頂端元素: " + stack.peek());  // 輸出：第三個
        System.out.println("堆疊大小: " + stack.size());  // 輸出：3

        // 測試pop
        System.out.println("移除: " + stack.pop());  // 輸出：第三個
        System.out.println("移除: " + stack.pop());  // 輸出：第二個

        // 檢查當前狀態
        System.out.println("堆疊大小: " + stack.size());  // 輸出：1
        System.out.println("是否為空: " + stack.isEmpty());  // 輸出：false

        // 清空堆疊
        stack.clear();
        System.out.println("清空後是否為空: " + stack.isEmpty());  // 輸出：true

        // 測試空堆疊的異常處理
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("異常訊息: " + e.getMessage());  // 輸出：Stack is empty
        }
    }
}
