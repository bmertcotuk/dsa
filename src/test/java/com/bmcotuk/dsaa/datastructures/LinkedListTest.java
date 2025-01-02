package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTest {

    @Test
    void test_appendElements() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.appendToTail("d");
        list.appendToTail("e");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertEquals(new Node<>("c"), list.getHead().getNext().getNext());
        assertEquals(new Node<>("d"), list.getHead().getNext().getNext().getNext());
        assertEquals(new Node<>("e"), list.getHead().getNext().getNext().getNext().getNext());
        assertEquals(5, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void test_removeElementFromHead() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("a");

        assertEquals(new Node<>("b"), list.getHead());
        assertEquals(new Node<>("c"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void test_removeElementBetweenHeadAndTail() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("b");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("c"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void test_removeElementFromTail() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        list.remove("c");

        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertNull(list.getHead().getNext().getNext());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void test_returnSizeForNonEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        assertEquals(3, list.size());
    }

    @Test
    void test_returnTrueForEmpty() {
        LinkedList<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    void test_returnFalseForNonEmpty() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        assertFalse(list.isEmpty());
    }

    @Test
    void test_throwExceptionOnElementNotFound() {
        LinkedList<String> list = new LinkedList<>();
        list.appendToTail("a");
        list.appendToTail("b");
        list.appendToTail("c");
        assertThrows(NoSuchElementException.class, () -> list.remove("x"));
        assertEquals(new Node<>("a"), list.getHead());
        assertEquals(new Node<>("b"), list.getHead().getNext());
        assertEquals(new Node<>("c"), list.getHead().getNext().getNext());
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void test_throwExceptionOnNullArgumentForAppendOperation() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> list.appendToTail(null));
    }

    @Test
    void test_throwExceptionOnNullArgumentForRemoveOperation() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows(IllegalArgumentException.class, () -> list.remove(null));
    }
}
