package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayListTest {

    @Test
    void test_addElementsLessThanInitialCapacity() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(9);
        assertEquals(5, list.get(0));
        assertEquals(7, list.get(1));
        assertEquals(9, list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    void test_addElementsMoreThanInitialCapacity() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(10);
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        assertEquals(11, list.get(0));
        assertEquals(1, list.get(10));
        assertEquals(11, list.size());
    }

    @Test
    void test_throwIndexOutOfBoundsExceptionOnInvalidIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(9);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    }

    @Test
    void test_removeElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(11);
        list.remove(1);
        list.remove(1);
        assertEquals(5, list.get(0));
        assertEquals(11, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void test_throwIndexOutOfBoundsExceptionOnDeletionFromEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void test_returnTrueFromIsEmptyForEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        assertTrue(list.isEmpty());
        list.add(5);
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void test_returnFalseFromIsEmptyFromNonEmptyList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        assertFalse(list.isEmpty());
    }

    @Test
    void test_haveSizeZeroWhenJustCreated() {
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals(0, list.size());
    }

    @Test
    void test_throwExceptionOnNullArgumentForAddOperation() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> list.add(null));
    }

    @Test
    void test_notThrowExceptionOnNullArgumentForAddOperation() {
        ArrayList<Integer> list = new ArrayList<>();
        assertDoesNotThrow(() -> list.addWithoutNullCheck(null));
    }
}
