package com.bmcotuk.dsaa.datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * Also known as "PrefixTree" and pronounced as "try".
 */
public class Trie {

    private static class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public boolean isEndOfWord() {
            return endOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * ----> k: number of words
     * ----> m: average length of each word
     * <p>
     * time: O(n)
     * space: O(k * m)
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.getChildren().putIfAbsent(c, new TrieNode());
            current = current.getChildren().get(c);
        }
        current.setEndOfWord(true);
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode child = current.getChildren().get(c);
            if (child == null) {
                return false;  // path not found
            }
            current = child;
        }
        return current.isEndOfWord();
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            TrieNode child = current.getChildren().get(c);
            if (child == null) {
                return false;  // path not found
            }
            current = child;
        }
        return true;
    }
}
