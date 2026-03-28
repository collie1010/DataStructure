package UnionFind;

public class UnionFindTest {
    public static void main(String[] args) {
        // 建立 10 個元素的並查集
        UnionFind uf = new UnionFind(10);
            
        // 合併一些元素
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(5, 6);
        uf.union(6, 7);    
            
        // 檢查連通性
        System.out.println(uf.connected(0, 2));  // true (0-1-2 相連)
        System.out.println(uf.connected(0, 3));  // false (0 和 3 不相連)
            
        // 合併後再檢查
        uf.union(2, 4);
        System.out.println(uf.connected(0, 3));  // true (現在相連了)
            
        // 獲取集合數量
        System.out.println("集合數量: " + uf.getCount());    
    }
    

}
