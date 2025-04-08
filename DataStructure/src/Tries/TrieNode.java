package Tries;

public class TrieNode {
	private TrieNode[] children;
    private boolean isEndOfWord;
    private static final int ALPHABET_SIZE = 26;  // 假設只處理小寫字母
    
    public TrieNode() {
        children = new TrieNode[ALPHABET_SIZE];
        isEndOfWord = false;
    }
    
    public TrieNode[] getChildren() {
        return children;
    }
    
    public boolean isEndOfWord() {
        return isEndOfWord;
    }
    
    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }
}
