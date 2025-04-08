package LinkedList;

public class ListNode {
	
	int val;
	ListNode next;
	
	ListNode () {
		
	}
	
	ListNode (int val) {
		this.val = val;
	}
	
	ListNode (int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
	
	 // 輔助方法：建立鏈表
    public static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }
    
    // 輔助方法：印出鏈表
    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        System.out.print("鏈表內容: ");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
	
}
