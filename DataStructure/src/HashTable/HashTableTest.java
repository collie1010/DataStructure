package HashTable;

public class HashTableTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable<String, Integer> hashTable = new HashTable<>();

		// 插入元素
		hashTable.put("apple", 1);
		hashTable.put("banana", 2);
		hashTable.put("orange", 3);

		// 獲取元素
		System.out.println(hashTable.get("apple")); // 輸出: 1
		System.out.println(hashTable.get("banana")); // 輸出: 2

		// 移除元素
		hashTable.remove("apple");
		System.out.println(hashTable.get("apple")); // 輸出: null

		// 檢查大小
		System.out.println(hashTable.size()); // 輸出: 2
	}

}
