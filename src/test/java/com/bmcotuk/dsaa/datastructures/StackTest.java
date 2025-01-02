package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StackTest {

    @Test
    void test_returnSizeForEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertEquals(0, stack.size());
    }

    @Test
    void test_returnSizeForNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        assertEquals(3, stack.size());
    }

    @Test
    void test_returnTrueForEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void test_returnFalseForNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        assertFalse(stack.isEmpty());
    }

    @Test
    void test_pushElements() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(4, stack.size());
    }

    @Test
    void test_popElements() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(8, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());

        assertEquals(2, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void test_peekElement() {
        Stack<Integer> stack = createNonEmptyStack();
        assertEquals(8, stack.peek());
    }

    @Test
    void test_throwExceptionOnEmptyStackForPopOperation() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void test_throwExceptionOnEmptyStackForPeekOperation() {
        Stack<Integer> stack = new Stack<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    private Stack<Integer> createNonEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        return stack;
    }
}
