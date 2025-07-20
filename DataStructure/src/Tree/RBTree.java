package Tree;

public class RBTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private class Node {
        T data;
        Node left, right, parent;
        boolean color;
        
        Node(T data) {
            this.data = data;
            this.color = RED; // 新節點預設為紅色
        }
    }
    
    private Node root;
    private Node NIL; // 哨兵節點，代表空節點
    
    public RBTree() {
        NIL = new Node(null);
        NIL.color = BLACK;
        NIL.left = NIL.right = NIL.parent = NIL;
        root = NIL;
    }
    
    // 插入操作
    public void insert(T data) {
        Node newNode = new Node(data);
        newNode.left = newNode.right = newNode.parent = NIL;
        
        Node parent = NIL;
        Node current = root;
        
        // 找到插入位置
        while (current != NIL) {
            parent = current;
            if (data.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        newNode.parent = parent;
        
        if (parent == NIL) {
            root = newNode; // 樹為空
        } else if (data.compareTo(parent.data) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        
        // 修復紅黑樹性質
        insertFixup(newNode);
    }
    
    // 插入後修復
    private void insertFixup(Node node) {
        while (node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                
                if (uncle.color == RED) {
                    // Case 1: 叔叔節點是紅色
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        // Case 2: 當前節點是右子節點
                        node = node.parent;
                        rotateLeft(node);
                    }
                    // Case 3: 當前節點是左子節點
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else {
                // 對稱情況
                Node uncle = node.parent.parent.left;
                
                if (uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        root.color = BLACK; // 根節點永遠是黑色
    }
    
    // 刪除操作
    public void delete(T data) {
        Node nodeToDelete = search(root, data);
        if (nodeToDelete == NIL) return;
        
        Node originalNode = nodeToDelete;
        boolean originalColor = originalNode.color;
        Node replacementNode;
        
        if (nodeToDelete.left == NIL) {
            replacementNode = nodeToDelete.right;
            transplant(nodeToDelete, nodeToDelete.right);
        } else if (nodeToDelete.right == NIL) {
            replacementNode = nodeToDelete.left;
            transplant(nodeToDelete, nodeToDelete.left);
        } else {
            originalNode = minimum(nodeToDelete.right);
            originalColor = originalNode.color;
            replacementNode = originalNode.right;
            
            if (originalNode.parent == nodeToDelete) {
                replacementNode.parent = originalNode;
            } else {
                transplant(originalNode, originalNode.right);
                originalNode.right = nodeToDelete.right;
                originalNode.right.parent = originalNode;
            }
            
            transplant(nodeToDelete, originalNode);
            originalNode.left = nodeToDelete.left;
            originalNode.left.parent = originalNode;
            originalNode.color = nodeToDelete.color;
        }
        
        if (originalColor == BLACK) {
            deleteFixup(replacementNode);
        }
    }
    
    // 刪除後修復
    private void deleteFixup(Node node) {
        while (node != root && node.color == BLACK) {
            if (node == node.parent.left) {
                Node sibling = node.parent.right;
                
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                
                if (sibling.left.color == BLACK && sibling.right.color == BLACK) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.right.color == BLACK) {
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                // 對稱情況
                Node sibling = node.parent.left;
                
                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }
                
                if (sibling.right.color == BLACK && sibling.left.color == BLACK) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.left.color == BLACK) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }
    
    // 左旋轉
    private void rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        
        if (rightChild.left != NIL) {
            rightChild.left.parent = node;
        }
        
        rightChild.parent = node.parent;
        
        if (node.parent == NIL) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        
        rightChild.left = node;
        node.parent = rightChild;
    }
    
    // 右旋轉
    private void rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        
        if (leftChild.right != NIL) {
            leftChild.right.parent = node;
        }
        
        leftChild.parent = node.parent;
        
        if (node.parent == NIL) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }
        
        leftChild.right = node;
        node.parent = leftChild;
    }
    
    // 替換節點
    private void transplant(Node oldNode, Node newNode) {
        if (oldNode.parent == NIL) {
            root = newNode;
        } else if (oldNode == oldNode.parent.left) {
            oldNode.parent.left = newNode;
        } else {
            oldNode.parent.right = newNode;
        }
        newNode.parent = oldNode.parent;
    }
    
    // 搜尋節點
    private Node search(Node node, T data) {
        while (node != NIL && !data.equals(node.data)) {
            if (data.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }
    
    // 找到最小值節點
    private Node minimum(Node node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }
    
    // 公開的搜尋方法
    public boolean contains(T data) {
        return search(root, data) != NIL;
    }
    
    // 中序遍歷
    public void inorderTraversal() {
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.data + "(" + (node.color == RED ? "R" : "B") + ") ");
            inorderHelper(node.right);
        }
    }
    
}

