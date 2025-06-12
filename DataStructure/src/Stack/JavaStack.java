package Stack;

import java.util.Stack;

public class JavaStack {

    public static void main(String[] args) {
        // 創建一個新的 Stack 物件
        Stack<String> stack = new Stack<>();

        // 推入元素 (push)
        System.out.println("==== 推入元素 ====");
        stack.push("第一個元素");
        stack.push("第二個元素");
        stack.push("第三個元素");
        System.out.println("目前堆疊：" + stack);

        // 查看頂端元素但不移除 (peek)
        System.out.println("\n==== 查看頂端元素 ====");
        System.out.println("頂端元素是：" + stack.peek());
        System.out.println("執行 peek 後的堆疊：" + stack);

        // 彈出元素 (pop)
        System.out.println("\n==== 彈出元素 ====");
        String poppedElement = stack.pop();
        System.out.println("彈出的元素是：" + poppedElement);
        System.out.println("執行 pop 後的堆疊：" + stack);

        // 檢查堆疊是否為空
        System.out.println("\n==== 檢查堆疊狀態 ====");
        System.out.println("堆疊是否為空？" + stack.isEmpty());
        System.out.println("堆疊的大小：" + stack.size());

        // 搜尋元素
        System.out.println("\n==== 搜尋元素 ====");
        System.out.println("「第一個元素」的位置：" + stack.search("第一個元素"));

        // 清空堆疊
        System.out.println("\n==== 清空堆疊 ====");
        stack.clear();
        System.out.println("清空後堆疊是否為空？" + stack.isEmpty());
    }
}
