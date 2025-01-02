package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;

import java.util.NoSuchElementException;

/**
 * @author Mert Cotuk
 */
public class Queue<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public Queue() {
        size = 0;
    }

    public void enqueue(T data) {
        validateData(data);
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = last.getNext();
        }
        size++;
    }

    public T dequeue() {
        T data = peek();
        first = first.getNext();
        // do not forget to update last
        if (first == null) {
            last = null;
        }
        size--;
        return data;
    }

    public T peek() {
        validateNotEmpty();
        return first.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void validateData(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotEmpty() {
        if (first == null) {
            throw new NoSuchElementException();
        }
    }
}
