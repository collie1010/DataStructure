package Tree;

public class AVLTree<T extends Comparable<T>> {
    
    private class Node {
        T data;
        Node left, right;
        int height;
        
        Node(T data) {
            this.data = data;
            this.height = 1; // 新節點的高度為1
        }
    }
    
    private Node root;
    
    // 取得節點高度
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }
    
    // 計算平衡因子 (左子樹高度 - 右子樹高度)
    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    
    // 更新節點高度
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }
    
    // 右旋轉 (LL 情況)
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        
        // 執行旋轉
        x.right = y;
        y.left = T2;
        
        // 更新高度
        updateHeight(y);
        updateHeight(x);
        
        // 回傳新的根節點
        return x;
    }
    
    // 左旋轉 (RR 情況)
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        // 執行旋轉
        y.left = x;
        x.right = T2;
        
        // 更新高度
        updateHeight(x);
        updateHeight(y);
        
        // 回傳新的根節點
        return y;
    }
    
    // 插入操作
    public void insert(T data) {
        root = insertHelper(root, data);
    }
    
    private Node insertHelper(Node node, T data) {
        // 1. 執行一般的 BST 插入
        if (node == null) {
            return new Node(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insertHelper(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertHelper(node.right, data);
        } else {
            // 相等的值不允許重複插入
            return node;
        }
        
        // 2. 更新當前節點的高度
        updateHeight(node);
        
        // 3. 取得平衡因子
        int balance = getBalance(node);
        
        // 4. 如果節點不平衡，有四種情況需要處理
        
        // LL 情況
        if (balance > 1 && data.compareTo(node.left.data) < 0) {
            return rotateRight(node);
        }
        
        // RR 情況
        if (balance < -1 && data.compareTo(node.right.data) > 0) {
            return rotateLeft(node);
        }
        
        // LR 情況
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        
        // RL 情況
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        // 回傳未改變的節點指標
        return node;
    }
    
    // 刪除操作
    public void delete(T data) {
        root = deleteHelper(root, data);
    }
    
    private Node deleteHelper(Node node, T data) {
        // 1. 執行一般的 BST 刪除
        if (node == null) {
            return node;
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = deleteHelper(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteHelper(node.right, data);
        } else {
            // 找到要刪除的節點
            if (node.left == null || node.right == null) {
                Node temp = node.left != null ? node.left : node.right;
                
                if (temp == null) {
                    // 沒有子節點的情況
                    temp = node;
                    node = null;
                } else {
                    // 有一個子節點的情況
                    node = temp;
                }
            } else {
                // 有兩個子節點的情況，找到中序後繼者
                Node temp = findMin(node.right);
                
                // 複製中序後繼者的資料到此節點
                node.data = temp.data;
                
                // 刪除中序後繼者
                node.right = deleteHelper(node.right, temp.data);
            }
        }
        
        // 如果樹只有一個節點，直接回傳
        if (node == null) {
            return node;
        }
        
        // 2. 更新當前節點的高度
        updateHeight(node);
        
        // 3. 取得平衡因子
        int balance = getBalance(node);
        
        // 4. 如果節點不平衡，有四種情況需要處理
        
        // LL 情況
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        
        // LR 情況
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        
        // RR 情況
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        
        // RL 情況
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        return node;
    }
    
    // 找到最小值節點
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    // 搜尋操作
    public boolean search(T data) {
        return searchHelper(root, data);
    }
    
    private boolean searchHelper(Node node, T data) {
        if (node == null) {
            return false;
        }
        
        if (data.equals(node.data)) {
            return true;
        }
        
        if (data.compareTo(node.data) < 0) {
            return searchHelper(node.left, data);
        } else {
            return searchHelper(node.right, data);
        }
    }
    
    // 前序遍歷
    public void preOrderTraversal() {
        System.out.print("前序遍歷: ");
        preOrderHelper(root);
        System.out.println();
    }
    
    private void preOrderHelper(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }
    
    // 中序遍歷
    public void inOrderTraversal() {
        System.out.print("中序遍歷: ");
        inOrderHelper(root);
        System.out.println();
    }
    
    private void inOrderHelper(Node node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }
    
    // 後序遍歷
    public void postOrderTraversal() {
        System.out.print("後序遍歷: ");
        postOrderHelper(root);
        System.out.println();
    }
    
    private void postOrderHelper(Node node) {
        if (node != null) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }
    
    // 顯示樹的結構（帶高度和平衡因子）
    public void displayTree() {
        System.out.println("樹結構（格式：資料(高度,平衡因子)）:");
        displayHelper(root, "", true);
    }
    
    private void displayHelper(Node node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + 
                             node.data + "(" + node.height + "," + getBalance(node) + ")");
            
            if (node.left != null || node.right != null) {
                displayHelper(node.right, prefix + (isLast ? "    " : "│   "), false);
                displayHelper(node.left, prefix + (isLast ? "    " : "│   "), true);
            }
        }
    }
    
    // 取得樹的高度
    public int getTreeHeight() {
        return getHeight(root);
    }
    
    // 檢查是否為平衡樹
    public boolean isBalanced() {
        return isBalancedHelper(root);
    }
    
    private boolean isBalancedHelper(Node node) {
        if (node == null) {
            return true;
        }
        
        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && 
               isBalancedHelper(node.left) && 
               isBalancedHelper(node.right);
    }
    
    // 計算節點數量
    public int size() {
        return sizeHelper(root);
    }
    
    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }
    
}
