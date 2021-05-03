package com.kuma.fsm.dfa;

import lombok.var;

import java.util.HashMap;
import java.util.Map;

/**
 * Tire Tree
 *
 * @author kuma 2021-05-03
 */
public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        // Ignore blank word fast fail

        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            var children = cur.children;
            // Get the children node or create a new one
            cur = children.computeIfAbsent(ch, k -> new TrieNode());
        }
        cur.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode next = cur.children.get(ch);

            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startWith(String prefix) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode next = cur.children.get(ch);

            if (next == null) {
                return false;
            }
            cur = next;
        }
        // all match means start with
        return true;
    }

    /**
     * Return the length of the match word at the begin of the txt.
     */
    public int search(String txt, int beginIndex) {
        TrieNode cur = root;
        int length = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            char ch = txt.charAt(i);
            TrieNode next = cur.children.get(ch);
            if (next == null) {
                return length;
            }
            length ++;
            cur = next;
        }

        if (cur.isEnd) {
            return length;
        }
        return 0;
    }

    private static class TrieNode {
        private boolean isEnd;
        private final Map<Character, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
            isEnd = false;
        }
    }
}
