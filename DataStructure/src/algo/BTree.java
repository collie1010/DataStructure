package algo;


public class BTree<K extends Comparable<K>, V> {
    private static final int M = 4; // B樹的階數
    private Node root;             // 根節點
    private int height;           // 樹的高度
    private int n;               // 鍵值對總數

    // B樹節點的內部類別
    private class Node {
        private int m;                             // 節點中鍵值對的數量
        private Object[] entries = new Object[M-1];    // 改用 Object 數組
        private Object[] children = new Object[M];     // 改用 Object 數組
        private boolean isLeaf;                    // 是否為葉節點

        @SuppressWarnings("unchecked")
        private Entry getEntry(int index) {
            return (Entry) entries[index];
        }

        @SuppressWarnings("unchecked")
        private Node getChild(int index) {
            return (Node) children[index];
        }

        private void setEntry(int index, Entry entry) {
            entries[index] = entry;
        }

        private void setChild(int index, Node child) {
            children[index] = child;
        }

        public Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            this.m = 0;
        }
    }

    // 鍵值對的內部類別
    private class Entry {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 建構函數
    public BTree() {
        root = new Node(true);
    }

    // 插入鍵值對
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        
        Node r = root;
        // 如果根節點已滿，需要分裂
        if (r.m == M - 1) {
            Node newRoot = new Node(false);
            newRoot.setChild(0, r);
            splitChild(newRoot, 0, r);
            root = newRoot;
            height++;
        }
        insertNonFull(root, key, value);
        n++;
    }

    // 分裂子節點
    private void splitChild(Node parent, int i, Node child) {
        Node newChild = new Node(child.isLeaf);
        newChild.m = M/2 - 1;

        // 複製後半部分的鍵值對到新節點
        for (int j = 0; j < M/2-1; j++) {
            newChild.setEntry(j, child.getEntry(j + M/2));
        }

        // 如果不是葉節點，也需要移動子節點
        if (!child.isLeaf) {
            for (int j = 0; j < M/2; j++) {
                newChild.setChild(j, child.getChild(j + M/2));
            }
        }

        child.m = M/2 - 1;

        // 在父節點中插入中間的鍵值對
        for (int j = parent.m; j > i; j--) {
            parent.setChild(j + 1, parent.getChild(j));
        }
        parent.setChild(i + 1, newChild);

        for (int j = parent.m - 1; j >= i; j--) {
            parent.setEntry(j + 1, parent.getEntry(j));
        }
        parent.setEntry(i, child.getEntry(M/2-1));
        parent.m++;
    }

    // 在非滿節點中插入鍵值對
    private void insertNonFull(Node node, K key, V value) {
        int i = node.m - 1;

        if (node.isLeaf) {
            // 在葉節點中插入
            while (i >= 0 && key.compareTo(node.getEntry(i).key) < 0) {
                node.setEntry(i + 1, node.getEntry(i));
                i--;
            }
            node.setEntry(i + 1, new Entry(key, value));
            node.m++;
        } else {
            // 在內部節點中尋找適當的子節點
            while (i >= 0 && key.compareTo(node.getEntry(i).key) < 0) {
                i--;
            }
            i++;
            
            if (node.getChild(i).m == M - 1) {
                splitChild(node, i, node.getChild(i));
                if (key.compareTo(node.getEntry(i).key) > 0) {
                    i++;
                }
            }
            insertNonFull(node.getChild(i), key, value);
        }
    }

    // 查詢鍵值對
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return search(root, key);
    }

    private V search(Node node, K key) {
        int i = 0;
        while (i < node.m && key.compareTo(node.getEntry(i).key) > 0) {
            i++;
        }

        if (i < node.m && key.compareTo(node.getEntry(i).key) == 0) {
            return node.getEntry(i).value;
        }

        if (node.isLeaf) {
            return null;
        }

        return search(node.getChild(i), key);
    }

    // 獲取樹的大小
    public int size() {
        return n;
    }

    // 判斷樹是否為空
    public boolean isEmpty() {
        return size() == 0;
    }

    // 中序遍歷打印樹的內容
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    private void inorderTraversal(Node node) {
        if (node == null) return;
        
        int i;
        for (i = 0; i < node.m; i++) {
            if (!node.isLeaf) {
                inorderTraversal(node.getChild(i));
            }
            Entry entry = node.getEntry(i);
            System.out.print(entry.key + ":" + entry.value + " ");
        }
        
        if (!node.isLeaf) {
            inorderTraversal(node.getChild(i));
        }
    }
}

