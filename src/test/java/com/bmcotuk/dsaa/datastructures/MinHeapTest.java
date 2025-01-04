package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinHeapTest {

    @Test
    void test_add() {
        MinHeap heap = new MinHeap();

        heap.add(90);
        assertEquals(90, heap.getRoot().getData());
        heap.add(55);
        assertEquals(55, heap.getRoot().getData());
        heap.add(87);
        assertEquals(55, heap.getRoot().getData());
        heap.add(4);
        assertEquals(4, heap.getRoot().getData());
        heap.add(50);
        assertEquals(4, heap.getRoot().getData());
        heap.add(7);
        assertEquals(4, heap.getRoot().getData());
        heap.add(2);
        assertEquals(2, heap.getRoot().getData());
    }

    @Test
    void test_peek() {
        MinHeap heap = new MinHeap();
        assertThrows(
                IllegalStateException.class,
                heap::peek);
        heap.add(90);
        assertEquals(90, heap.peek());
        heap.add(55);
        assertEquals(55, heap.peek());
        heap.add(87);
        assertEquals(55, heap.peek());
        heap.add(4);
        assertEquals(4, heap.peek());
        heap.add(50);
        assertEquals(4, heap.peek());
        heap.add(7);
        assertEquals(4, heap.peek());
        heap.add(2);
        assertEquals(2, heap.peek());
    }

    @Test
    void test_extractRoot() {
        MinHeap heap = new MinHeap();
        assertThrows(
                IllegalStateException.class,
                heap::extractRoot);
        int[] values = {90, 55, 87, 4, 50, 7, 2};
        heap.addMultiple(values);
        assertEquals(2, heap.extractRoot());
        assertEquals(4, heap.extractRoot());
        assertEquals(7, heap.extractRoot());
        assertEquals(50, heap.extractRoot());
        assertEquals(55, heap.extractRoot());
        assertEquals(87, heap.extractRoot());
        assertEquals(90, heap.extractRoot());
        assertThrows(
                IllegalStateException.class,
                heap::extractRoot);
    }
}