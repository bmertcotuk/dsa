package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieTest {

    @Test
    void test_insert() {
        Trie trie = createTrie();

        assertTrue(trie.search("specific"));
        assertTrue(trie.search("space"));
    }

    @Test
    void test_search() {
        Trie trie = createTrie();

        assertFalse(trie.search(""));
        assertTrue(trie.search("specific"));
        assertTrue(trie.search("specifically"));
        assertTrue(trie.search("spectacular"));
        assertTrue(trie.search("space"));
        assertTrue(trie.search("spider"));
        assertTrue(trie.search("wonder"));
        assertTrue(trie.search("wander"));
        assertTrue(trie.search("space"));
        assertFalse(trie.search("especially"));
        assertFalse(trie.search("football"));
    }

    @Test
    void test_startsWith() {
        Trie trie = createTrie();

        assertTrue(trie.startsWith(""));
        assertTrue(trie.startsWith("s"));
        assertTrue(trie.startsWith("sp"));
        assertTrue(trie.startsWith("spe"));
        assertTrue(trie.startsWith("spa"));
        assertTrue(trie.startsWith("spectacular"));
        assertTrue(trie.startsWith("w"));
        assertTrue(trie.startsWith("won"));
        assertFalse(trie.startsWith("e"));
        assertFalse(trie.startsWith("e"));
    }

    private Trie createTrie() {
        Trie trie = new Trie();
        trie.insert("specific");
        trie.insert("specifically");
        trie.insert("spectacular");
        trie.insert("space");
        trie.insert("spider");
        trie.insert("wonder");
        trie.insert("wander");
        return trie;
    }
}