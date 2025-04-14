package HashTable;

public class HashTable<K, V> {
	
	// HashTable 的預設初始容量（數組大小）
    private static final int DEFAULT_CAPACITY = 16;
    
    // 負載因子（Load Factor），用於決定何時擴容
    // 0.75 表示當 HashTable 已使用的空間達到總容量的 75% 時會進行擴容
    private static final float LOAD_FACTOR = 0.75f;
    
    private Entry<K, V>[] table;
    private int size;
    
    // Entry 內部類別，用於存儲鍵值對
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }
    
    // 計算 hash 值
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }
    
    // 插入鍵值對
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        // 檢查是否需要擴容
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            Entry<K, V> prev = null;
            
            // 檢查是否已存在相同的 key
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;  // 更新值
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newEntry;
        }
        size++;
    }
    
    // 獲取值
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        int index = hash(key);
        Entry<K, V> current = table[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    
    // 移除鍵值對
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;
        
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    
    // 擴容方法
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        
        // 重新放置所有元素
        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
    
    // 獲取大小
    public int size() {
        return size;
    }
    
    // 檢查是否為空
    public boolean isEmpty() {
        return size == 0;
    }
}
