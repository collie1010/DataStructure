package Stack;

import java.util.Arrays;


/**
 * 時間複雜度 
 * 	插入 : O(1) 
 * 	刪除 : O(1) 
 *  搜尋 : O(n)
 * 
 */

public class ArrayStack<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    // 建構子
    public ArrayStack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // 將元素推入堆疊頂端
    public void push(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    // 從堆疊頂端移除並回傳元素
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = (T) elements[--size];
        elements[size] = null; // 幫助垃圾回收
        return element;
    }

    // 查看堆疊頂端元素但不移除
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) elements[size - 1];
    }

    // 檢查堆疊是否為空
    public boolean isEmpty() {
        return size == 0;
    }

    // 取得堆疊中的元素數量
    public int size() {
        return size;
    }

    // 確保陣列容量足夠
    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length * 2;
            elements = Arrays.copyOf(elements, newSize);
        }
    }

    // 清空堆疊
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
}
