package Stack;

public class ArrayStackTest {
	public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        // 推入元素
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Stack size: " + stack.size());    // 輸出：3
        System.out.println("Top element: " + stack.peek());   // 輸出：3

        // 彈出元素
        System.out.println("Popped: " + stack.pop());        // 輸出：3
        System.out.println("Popped: " + stack.pop());        // 輸出：2

        System.out.println("Stack size: " + stack.size());    // 輸出：1
        System.out.println("Is empty: " + stack.isEmpty());   // 輸出：false

        stack.pop();  // 移除最後一個元素
        System.out.println("Is empty: " + stack.isEmpty());   // 輸出：true
    }
}
