package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringBuilderTest {

    @Test
    void test_returnEmptyString() {
        StringBuilder builder = new StringBuilder();
        assertEquals("", builder.toString());
    }

    @Test
    void test_appendRepeated() {
        StringBuilder builder = new StringBuilder();
        builder.appendRepeated("-", 3);
        builder.appendRepeated("a", 4);
        builder.appendRepeated(" ", 2);
        assertEquals("---aaaa  ", builder.toString());
    }

    @Test
    void test_returnBuiltString() {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        builder.append("def");
        builder.append("ghi");
        assertEquals("abcdefghi", builder.toString());
    }

    @Test
    void test_returnBuiltStringLargerThanInitialCapacity() {
        StringBuilder builder = new StringBuilder();
        builder.append("abc");
        builder.append("def");
        builder.append("ghi");
        builder.append("jkl");
        assertEquals("abcdefghijkl", builder.toString());
    }

    @Test
    void test_throwExceptionOnNullArgumentForAppendOperation() {
        StringBuilder builder = new StringBuilder();
        assertThrows(IllegalArgumentException.class, () -> builder.append(null));
    }
}
