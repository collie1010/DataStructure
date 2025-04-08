package Tries;

import java.util.ArrayList;
import java.util.List;

/**
 * 時間複雜度 (m:單字長度 / n:前綴長度)
 *  插入 : O(m)
 *  搜尋 : O(m)
 *  刪除 : O(m)
 *  前綴搜尋 : O(p)
 *  
 *  
 * 優點:
 *  支援快速的前綴搜尋
 *  節省空間(共享前綴)
 *  支援字典序遍歷
 * 
 * 缺點:
 * 	需要較多記憶體
 *  不適合精確字串匹配
 *  建構成本較高
 * 
 * */


public class Trie {
private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    // 插入單字
    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        TrieNode current = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (current.getChildren()[index] == null) {
                current.getChildren()[index] = new TrieNode();
            }
            current = current.getChildren()[index];
        }
        current.setEndOfWord(true);
    }
    
    // 搜尋單字
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        
        TrieNode node = searchNode(word.toLowerCase());
        return node != null && node.isEndOfWord();
    }
    
    // 檢查是否有指定前綴的單字
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return false;
        }
        return searchNode(prefix.toLowerCase()) != null;
    }
    
    // 輔助方法：搜尋節點
    private TrieNode searchNode(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            int index = ch - 'a';
            if (current.getChildren()[index] == null) {
                return null;
            }
            current = current.getChildren()[index];
        }
        return current;
    }
    
    // 刪除單字
    public void delete(String word) {
        delete(root, word.toLowerCase(), 0);
    }
    
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return isEmpty(current);
        }
        
        int charIndex = word.charAt(index) - 'a';
        TrieNode node = current.getChildren()[charIndex];
        if (node == null) {
            return false;
        }
        
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);
        
        if (shouldDeleteCurrentNode) {
            current.getChildren()[charIndex] = null;
            return isEmpty(current);
        }
        return false;
    }
    
    // 檢查節點是否為空
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.getChildren()) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
    
    // 取得所有儲存的單字
    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        getAllWordsHelper(root, current, words);
        return words;
    }
    
    private void getAllWordsHelper(TrieNode node, StringBuilder current, List<String> words) {
        if (node.isEndOfWord()) {
            words.add(current.toString());
        }
        
        for (int i = 0; i < node.getChildren().length; i++) {
            if (node.getChildren()[i] != null) {
                current.append((char)('a' + i));
                getAllWordsHelper(node.getChildren()[i], current, words);
                current.setLength(current.length() - 1);
            }
        }
    }
    
    // 計算總單字數
    public int countWords() {
        return countWordsHelper(root);
    }
    
    private int countWordsHelper(TrieNode node) {
        int count = 0;
        if (node.isEndOfWord()) {
            count++;
        }
        
        for (TrieNode child : node.getChildren()) {
            if (child != null) {
                count += countWordsHelper(child);
            }
        }
        return count;
    }
    
 // 尋找具有特定前綴的所有鍵值
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        
        // 找到前綴對應的節點
        TrieNode prefixNode = findNode(prefix);
        if (prefixNode == null) {
            return result;
        }
        
        // 如果前綴本身就是一個完整的單字，加入結果
        if (prefixNode.isEndOfWord()) {
            result.add(prefix);
        }
        
        // 收集所有以該前綴開始的單字
        collectWords(prefixNode, prefix, result);
        
        return result;
    }
    
    // 尋找前綴對應的節點
    private TrieNode findNode(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (current.getChildren()[index] == null) {
                return null;
            }
            current = current.getChildren()[index];
        }
        return current;
    }
    
    // 遞迴收集所有單字
    private void collectWords(TrieNode node, String prefix, List<String> result) {
        TrieNode[] children = node.getChildren();
        
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                char c = (char) ('a' + i);
                String newPrefix = prefix + c;
                
                if (children[i].isEndOfWord()) {
                    result.add(newPrefix);
                }
                
                collectWords(children[i], newPrefix, result);
            }
        }
    }
}
