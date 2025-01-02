package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {

    @Test
    void test_createEmptyHashTable() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void test_createNonEmptyHashTable() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertFalse(hashTable.isEmpty());
    }

    @Test
    void test_returnSizeForEmpty() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertEquals(0, hashTable.size());
    }

    @Test
    void test_returnSizeForNonEmpty() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertEquals(14, hashTable.size());
    }

    @Test
    void test_getAllElements() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertEquals(1, hashTable.get("Manny"));
        assertEquals(2, hashTable.get("Joe"));
        assertEquals(3, hashTable.get("Jay"));
        assertEquals(4, hashTable.get("Gloria"));
        assertEquals(5, hashTable.get("Cam"));
        assertEquals(6, hashTable.get("Mitchell"));
        assertEquals(7, hashTable.get("Lily"));
        assertEquals(8, hashTable.get("Haley"));
        assertEquals(9, hashTable.get("Phil"));
        assertEquals(10, hashTable.get("Claire"));
        assertEquals(11, hashTable.get("Alex"));
        assertEquals(12, hashTable.get("Luke"));
        assertEquals(13, hashTable.get("DeDe"));
        assertEquals(14, hashTable.get("Dylan"));
    }

    @Test
    void test_returnTrueForAllContainedElements() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertTrue(hashTable.containsKey("Manny"));
        assertTrue(hashTable.containsKey("Joe"));
        assertTrue(hashTable.containsKey("Jay"));
        assertTrue(hashTable.containsKey("Gloria"));
        assertTrue(hashTable.containsKey("Cam"));
        assertTrue(hashTable.containsKey("Mitchell"));
        assertTrue(hashTable.containsKey("Lily"));
        assertTrue(hashTable.containsKey("Haley"));
        assertTrue(hashTable.containsKey("Phil"));
        assertTrue(hashTable.containsKey("Claire"));
        assertTrue(hashTable.containsKey("Alex"));
        assertTrue(hashTable.containsKey("Luke"));
        assertTrue(hashTable.containsKey("DeDe"));
        assertTrue(hashTable.containsKey("Dylan"));
    }

    @Test
    void test_returnFalseForNonContainedElements() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertFalse(hashTable.containsKey("Ted"));
        assertFalse(hashTable.containsKey("Barney"));
        assertFalse(hashTable.containsKey("Robin"));
        assertFalse(hashTable.containsKey("Marshall"));
    }

    @Test
    void test_removeElements() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        hashTable.remove("Luke");
        hashTable.remove("Manny");
        assertFalse(hashTable.containsKey("Luke"));
        assertFalse(hashTable.containsKey("Manny"));
        assertTrue(hashTable.containsKey("Haley"));
        assertFalse(hashTable.isEmpty());
        assertEquals(12, hashTable.size());
    }

    @Test
    void test_throwExceptionOnNullArguments() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertThrows(IllegalArgumentException.class, () -> hashTable.get(null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.put(null, 99));
        assertThrows(IllegalArgumentException.class, () -> hashTable.put("key", null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.put(null, null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.remove(null));
        assertThrows(IllegalArgumentException.class, () -> hashTable.containsKey(null));
    }

    @Test
    void test_throwExceptionOnNonContainedElements() {
        HashTable<String, Integer> hashTable = createNonEmptyHashTable();
        assertThrows(NoSuchElementException.class, () -> hashTable.remove("Ted"));
        assertThrows(NoSuchElementException.class, () -> hashTable.get("Ted"));
    }

    private HashTable<String, Integer> createNonEmptyHashTable() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("Manny", 1);
        hashTable.put("Joe", 2);
        hashTable.put("Jay", 3);
        hashTable.put("Gloria", 4);
        hashTable.put("Cam", 5);
        hashTable.put("Mitchell", 6);
        hashTable.put("Lily", 7);
        hashTable.put("Haley", 8);
        hashTable.put("Phil", 9);
        hashTable.put("Claire", 10);
        hashTable.put("Alex", 11);
        hashTable.put("Luke", 12);
        hashTable.put("DeDe", 13);
        hashTable.put("Dylan", 14);
        return hashTable;
    }
}
