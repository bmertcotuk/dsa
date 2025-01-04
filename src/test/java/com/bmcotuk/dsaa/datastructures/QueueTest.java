package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueueTest {

    @Test
    void test_returnSizeForEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertEquals(0, queue.size());
    }

    @Test
    void test_returnSizeForNonEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        assertEquals(2, queue.size());
    }

    @Test
    void test_returnTrueForEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertTrue(queue.isEmpty());
    }

    @Test
    void test_returnFalseForNonEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        assertFalse(queue.isEmpty());
    }

    @Test
    void test_enqueueAndDequeue() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(7);
        queue.enqueue(9);
        queue.enqueue(11);
        assertEquals(5, queue.size());

        assertEquals(3, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(3, queue.size());

        assertEquals(7, queue.dequeue());
        assertEquals(9, queue.dequeue());
        assertEquals(11, queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void test_peek() {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(7);
        assertEquals(3, queue.peek());
    }

    @Test
    void test_throwExceptionOnNullArgumentForEnqueueOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
    }

    @Test
    void test_throwExceptionOnEmptyQueueForDequeueOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    void test_throwExceptionOnEmptyQueueForPeekOperation() {
        Queue<Integer> queue = new Queue<>();
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }
}
