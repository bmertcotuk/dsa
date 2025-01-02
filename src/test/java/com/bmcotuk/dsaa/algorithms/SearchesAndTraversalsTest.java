package com.bmcotuk.dsaa.algorithms;

import com.bmcotuk.dsaa.datastructures.BinarySearchTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SearchesAndTraversalsTest {

    // to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @InjectMocks
    private SearchesAndTraversals searchesAndTraversals;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.out.println("CONSOLE OUTPUT:\n" + outContent);
    }

    @Test
    void test_binarySearchIterative_found() {
        int[] arr = new int[]{1, 3, 4, 6, 8, 12};
        assertEquals(1, searchesAndTraversals.binarySearchIterative(arr, 3));

        int[] arr2 = new int[]{5, 8, 16, 23, 52};
        assertEquals(3, searchesAndTraversals.binarySearchIterative(arr2, 23));
    }

    @Test
    void test_binarySearchIterative_notFound() {
        int[] arr = new int[]{1, 3, 4, 6, 8, 12};
        assertEquals(-1, searchesAndTraversals.binarySearchIterative(arr, 5));
    }

    @Test
    void test_binarySearchIterative_emptyArray() {
        int[] arr = new int[]{};
        assertEquals(-1, searchesAndTraversals.binarySearchIterative(arr, 1));
    }

    @Test
    void test_binarySearchRecursive_found() {
        int[] arr = new int[]{1, 3, 4, 6, 8, 12};
        assertEquals(1, searchesAndTraversals.binarySearchRecursive(arr, 3));

        int[] arr2 = new int[]{5, 8, 16, 23, 52};
        assertEquals(3, searchesAndTraversals.binarySearchRecursive(arr2, 23));
    }

    @Test
    void test_binarySearchRecursive_notFound() {
        int[] arr = new int[]{1, 3, 4, 6, 8, 12};
        assertEquals(-1, searchesAndTraversals.binarySearchRecursive(arr, 5));
    }

    @Test
    void test_binarySearchRecursive_emptyArray() {
        int[] arr = new int[]{};
        assertEquals(-1, searchesAndTraversals.binarySearchRecursive(arr, 1));
    }

    @Test
    void test_traverseLevelOrderBFS() {
        String expected = "8 7 11 3 14 5 13 22 ";
        BinarySearchTree tree = buildTree();
        searchesAndTraversals.breadthFirstTraversalBinaryTree(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    private BinarySearchTree buildTree() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.addMultiple(8, 11, 7, 14, 22, 13, 3, 5);
        return tree;
    }
}