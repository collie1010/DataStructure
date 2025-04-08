package algo;

public class RedBlackTree<T extends Comparable<T>> {
    // 定義節點顏色
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    // 節點類別定義
    private class Node {
        T data;           // 節點數據
        Node left;        // 左子節點
        Node right;       // 右子節點
        boolean color;    // 節點顏色
        int size;        // 以該節點為根的子樹中的節點數

        Node(T data, boolean color) {
            this.data = data;
            this.color = color;
            this.size = 1;
        }
    }

    private Node root;  // 根節點

    // 判斷節點是否為紅色
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    // 獲取樹的大小
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    // 左旋轉
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 右旋轉
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 顏色翻轉
    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // 插入操作
    public void insert(T data) {
        root = insert(root, data);
        root.color = BLACK;  // 根節點永遠是黑色
    }

    private Node insert(Node h, T data) {
        if (h == null) {
            return new Node(data, RED);
        }

        int cmp = data.compareTo(h.data);
        if (cmp < 0) {
            h.left = insert(h.left, data);
        } else if (cmp > 0) {
            h.right = insert(h.right, data);
        } else {
            h.data = data;
        }

        // 修復紅黑樹性質
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    // 查詢操作
    public boolean contains(T data) {
        return contains(root, data);
    }

    private boolean contains(Node node, T data) {
        if (node == null) {
            return false;
        }
        
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, data);
        } else if (cmp > 0) {
            return contains(node.right, data);
        } else {
            return true;
        }
    }

    // 中序遍歷（用於檢視樹的內容）
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.print(node.data + " ");
        inorderTraversal(node.right);
    }

    // 最小值
    public T min() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return min(root).data;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    // 最大值
    public T max() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        return max(root).data;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }
}

