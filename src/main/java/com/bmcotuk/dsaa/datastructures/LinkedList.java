package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;

import java.util.NoSuchElementException;

/**
 * @author Mert Cotuk
 */
public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    public void appendToTail(T data) {
        validateData(data);
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            // we are interested with the next node only
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    // using currentNode object with reference affects the whole list, no need to return the head
    // data is still what defines an object and is checked in `equals()`
    public void remove(T data) {
        validateData(data);
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // head is a special case so deserves to be outside the loop
        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return;
        }
        Node<T> currentNode = head;
        while (currentNode.getNext() != null) {
            if (currentNode.getNext().getData().equals(data)) {
                // no problem even if it is the tail
                currentNode.setNext(currentNode.getNext().getNext());
                size--;
                return;
            }
            currentNode = currentNode.getNext();
        }
        throw new NoSuchElementException("Element to be removed is not found.");
    }

    public boolean isEmpty() {
        // head == null, size == 0
        return head == null;
    }

    public int size() {
        return size;
    }

    private void validateData(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }
}
