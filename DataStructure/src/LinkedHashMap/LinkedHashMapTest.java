package LinkedHashMap;

public class LinkedHashMapTest {
    /**
     * 測試方法
     */
    public static void main(String[] args) {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        
        // 測試基本功能
        System.out.println("=== 基本功能測試 ===");
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);
        map.put("date", 40);
        
        map.printInOrder();
        System.out.println("Size: " + map.size());
        
        // 測試查找
        System.out.println("\n=== 查找測試 ===");
        System.out.println("apple: " + map.get("apple"));
        System.out.println("banana: " + map.get("banana"));
        System.out.println("grape (不存在): " + map.get("grape"));
        
        // 測試更新
        System.out.println("\n=== 更新測試 ===");
        System.out.println("更新前 apple: " + map.get("apple"));
        map.put("apple", 15);
        System.out.println("更新後 apple: " + map.get("apple"));
        map.printInOrder();
        
        // 測試刪除
        System.out.println("\n=== 刪除測試 ===");
        System.out.println("刪除 banana: " + map.remove("banana"));
        map.printInOrder();
        System.out.println("Size: " + map.size());
        
        // 測試包含性
        System.out.println("\n=== 包含性測試 ===");
        System.out.println("Contains key 'cherry': " + map.containsKey("cherry"));
        System.out.println("Contains key 'banana': " + map.containsKey("banana"));
        System.out.println("Contains value 30: " + map.containsValue(30));
        System.out.println("Contains value 20: " + map.containsValue(20));
        
        // 測試批量操作
        System.out.println("\n=== 批量操作測試 ===");
        System.out.println("Keys: " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Entries: " + map.entrySet());
        
        // 測試擴容
        System.out.println("\n=== 擴容測試 ===");
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, i * 10);
        }
        map.printInOrder();
        System.out.println("Size after expansion: " + map.size());
        
        // 測試 null 值
        System.out.println("\n=== Null 值測試 ===");
        map.put(null, 999);
        map.put("nullValue", null);
        map.printInOrder();
        System.out.println("Get null key: " + map.get(null));
        System.out.println("Get null value: " + map.get("nullValue"));
    }
}
