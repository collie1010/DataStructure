package LinkedHashMap;

import java.util.*;

/**
 * 自定義的 LinkedHashMap 實作
 * 結合 HashMap 的 O(1) 查找性能和雙向鏈表維護插入順序
 */
public class MyLinkedHashMap<K, V> implements Map<K, V> {
    
    /**
     * 節點類別 - 同時用於 HashMap 和雙向鏈表
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;     // HashMap 中的下一個節點（處理碰撞）
        Node<K, V> before;   // 雙向鏈表中的前一個節點
        Node<K, V> after;    // 雙向鏈表中的後一個節點
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    // HashMap 相關屬性
    private Node<K, V>[] table;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    // 雙向鏈表相關屬性
    private Node<K, V> head;  // 虛擬頭節點
    private Node<K, V> tail;  // 虛擬尾節點
    
    /**
     * 預設建構子
     */
    @SuppressWarnings("unchecked")
    public MyLinkedHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[capacity];
        this.size = 0;
        
        // 初始化雙向鏈表的虛擬頭尾節點
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.after = this.tail;
        this.tail.before = this.head;
    }
    
    /**
     * 指定初始容量的建構子
     */
    @SuppressWarnings("unchecked")
    public MyLinkedHashMap(int initialCapacity) {
        this.capacity = nextPowerOfTwo(initialCapacity);
        this.table = new Node[capacity];
        this.size = 0;
        
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.after = this.tail;
        this.tail.before = this.head;
    }
    
    /**
     * 計算 hash 值
     */
    private int hash(Object key) {
        if (key == null) return 0;
        int h = key.hashCode();
        return h ^ (h >>> 16);  // 高位參與運算，減少碰撞
    }
    
    /**
     * 獲取在 table 中的索引位置
     */
    private int getIndex(int hash) {
        return hash & (capacity - 1);
    }
    
    /**
     * 找到最接近的 2 的次方數
     */
    private int nextPowerOfTwo(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
    
    /**
     * 將節點加入雙向鏈表的尾部
     */
    private void addToTail(Node<K, V> node) {
        Node<K, V> prev = tail.before;
        prev.after = node;
        node.before = prev;
        node.after = tail;
        tail.before = node;
    }
    
    /**
     * 從雙向鏈表中移除節點
     */
    private void removeFromList(Node<K, V> node) {
        node.before.after = node.after;
        node.after.before = node.before;
    }
    
    /**
     * 擴容操作
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        int oldCapacity = capacity;
        
        capacity *= 2;
        table = new Node[capacity];
        
        // 重新 hash 所有元素
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> current = oldTable[i];
            while (current != null) {
                Node<K, V> next = current.next;
                
                // 重新計算位置
                int newIndex = getIndex(hash(current.key));
                current.next = table[newIndex];
                table[newIndex] = current;
                
                current = next;
            }
        }
    }
    
    @Override
    public V put(K key, V value) {
        int hashValue = hash(key);
        int index = getIndex(hashValue);
        
        // 檢查是否已存在相同的 key
        Node<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }
        
        // 創建新節點
        Node<K, V> newNode = new Node<>(key, value);
        
        // 加入 HashMap
        newNode.next = table[index];
        table[index] = newNode;
        
        // 加入雙向鏈表尾部
        addToTail(newNode);
        
        size++;
        
        // 檢查是否需要擴容
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
        
        return null;
    }
    
    @Override
    public V get(Object key) {
        int hashValue = hash(key);
        int index = getIndex(hashValue);
        
        Node<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null;
    }
    
    @Override
    public V remove(Object key) {
        int hashValue = hash(key);
        int index = getIndex(hashValue);
        
        Node<K, V> current = table[index];
        Node<K, V> prev = null;
        
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                // 從 HashMap 中移除
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                
                // 從雙向鏈表中移除
                removeFromList(current);
                
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        
        return null;
    }
    
    @Override
    public boolean containsKey(Object key) {
        return get(key) != null || (get(key) == null && containsNullKey(key));
    }
    
    @Override
    public boolean containsValue(Object value) {
        Node<K, V> current = head.after;
        while (current != tail) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
            current = current.after;
        }
        return false;
    }
    
    private boolean containsNullKey(Object key) {
        if (key != null) return false;
        
        Node<K, V> current = table[0];
        while (current != null) {
            if (current.key == null) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void clear() {
        Arrays.fill(table, null);
        head.after = tail;
        tail.before = head;
        size = 0;
    }
    
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new LinkedHashSet<>();
        Node<K, V> current = head.after;
        while (current != tail) {
            keySet.add(current.key);
            current = current.after;
        }
        return keySet;
    }
    
    @Override
    public Collection<V> values() {
        List<V> valueList = new ArrayList<>();
        Node<K, V> current = head.after;
        while (current != tail) {
            valueList.add(current.value);
            current = current.after;
        }
        return valueList;
    }
    
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new LinkedHashSet<>();
        Node<K, V> current = head.after;
        while (current != tail) {
            entrySet.add(new AbstractMap.SimpleEntry<>(current.key, current.value));
            current = current.after;
        }
        return entrySet;
    }
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 印出所有元素（按插入順序）
     */
    public void printInOrder() {
        System.out.print("LinkedHashMap: ");
        Node<K, V> current = head.after;
        while (current != tail) {
            System.out.print("[" + current.key + "=" + current.value + "] ");
            current = current.after;
        }
        System.out.println();
    }
    
}

