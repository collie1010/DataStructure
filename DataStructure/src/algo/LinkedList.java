package algo;


/**
 * 
 * 時間複雜度: (平均 / 最差)
 *  插入: O(n) / 插入頭節點: O(1)
 * 	搜尋: O(n)
 *  刪除: O(n) / 刪除頭節點: O(1) 
 * 
 * LinkList適用情境:
 *  無法預期資料數量、頻繁變動資料數量
 *  需要頻繁進行新增/刪除資料
 *  不需要快速查詢資料
 *  
 * 優點:
 *  新增/刪除 的操作較簡單
 *  資料數量可動態配置 (一開始不需要先定義長度) 
 *  
 *  
 * 缺點:
 *  無法直接透過索引值找到某節點
 *  若要找到某特定節點需要從頭開始找
 *  需要額外記憶體空間儲存指標
 *  
 *  
 * */

public class LinkedList {
	
	private class Node {
		int data;
		Node next;
		
		public Node (int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	// LinkedList的頭節點
	private Node head;
	// LinkedList的大小
	private int size;
	
	public LinkedList () {
		this.head = null;
		this.size = 0;
	}
	
	// 在串列尾端新增節點
	public void append (int data) {
		Node newNode = new Node(data);
		
		if (head == null) {
			head = newNode;
			size++;
			return;
		}
		
		Node current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = newNode;
		size++;
		
	}
	
	// 在指定位置插入節點
	public void insert (int position, int data) {
		if (position < 0 || position > size) {
			System.out.println("位置無效");
			return;
		}
		
		// 建立新節點
		Node newNode = new Node(data);
		
		// 如果插入的位置為開頭
		if (position == 0) {
			newNode.next = head; // 新節點指向原本的頭節點
			head = newNode;      // 新節點成為新的頭節點 
			size++;              // 更新串列大小
			return;
		}
		
		// 找到插入位置的前一個節點
		Node current = head;           // 從頭節點開始找
		for (int i = 0; i < position - 1; i++) { 
			current = current.next;    // 移動到插入位置的前一個節點
		}
		
		newNode.next = current.next;   // 新節點指向下一個節點
		current.next = newNode;        // 前一個節點指向新節點
		size++;                        // 更新串列大小
	}
	
	// 刪除指定值的節點
    public void delete(int data) {
        if (head == null) {
            return;
        }

        if (head.data == data) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
        }
    }
    
    // 搜尋節點
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 顯示串列
    public void display() {
        if (isEmpty()) {
            System.out.println("串列為空");
            return;
        }

        Node current = head;
        System.out.print("串列內容: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // 檢查串列是否為空
    public boolean isEmpty() {
        return head == null;
    }

    // 取得串列大小
    public int getSize() {
        return size;
    }
}
