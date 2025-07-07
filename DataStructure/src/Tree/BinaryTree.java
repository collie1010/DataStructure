package Tree;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree {

    // 節點類別
    static class Node {
        int value; // 節點的值
        Node left; // 左子節點
        Node right; // 右子節點

        Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    Node root; // 樹的根節點

    // 插入新值到二元樹（按層次順序）
    public void insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                break;
            } else {
                queue.add(current.left);
            }

            if (current.right == null) {
                current.right = newNode;
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    // 列印樹的結構（樹形結構）
    public void printTree() {
        if (root == null) {
            System.out.println("樹是空的！");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int level = 0; // 標記當前層級
        int height = getHeight(root); // 獲取樹的高度，用於計算縮排

        System.out.println("樹的結構：");

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // 當前層級的節點數量

            // 根據層級設定適當的縮排（每層縮排減少）
            int spaces = (int) Math.pow(2, height - level) - 1;
            printSpaces(spaces);

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (current != null) {
                    System.out.print(current.value);
                    queue.add(current.left);
                    queue.add(current.right);
                } else {
                    System.out.print(" "); // 如果節點為空，用空格佔位
                    queue.add(null);
                    queue.add(null);
                }

                printSpaces(spaces * 2 + 1); // 節點之間的間距
            }

            System.out.println(); // 換行，進入下一層
            level++;

            // 如果下一層全是空節點，停止列印
            if (queue.stream().allMatch(node -> node == null)) break;
        }
    }

    // 計算樹的高度（用於縮排計算）
    private int getHeight(Node root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    // 列印指定數量的空格
    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
}
