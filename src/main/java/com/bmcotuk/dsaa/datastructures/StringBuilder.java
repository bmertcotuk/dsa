package com.bmcotuk.dsaa.datastructures;

import java.util.Arrays;

/**
 * @author Mert Cotuk
 */
public class StringBuilder {

    private static final int INITIAL_CAPACITY = 10;

    private char[] array;
    private int size;

    public StringBuilder() {
        array = new char[INITIAL_CAPACITY];
        size = 0;
    }

    public void append(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        for (char c : s.toCharArray()) {
            add(c);
        }
    }

    public void appendRepeated(String s, int times) {
        for (int i = 0; i < times; i++) {
            append(s);
        }
    }

    private void add(char c) {
        ensureCapacity();
        array[size++] = c;
    }

    public String toString() {
        // no better way without copying exists, even Java does the same
        return new String(Arrays.copyOfRange(array, 0, size));
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }
}
