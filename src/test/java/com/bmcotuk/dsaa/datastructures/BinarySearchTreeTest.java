package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

    // to test console output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
    void test_addMultiple() {
        BinaryTree tree = new BinarySearchTree();
        tree.addMultiple(5, 3, 7, 8, 2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(2, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getLeft());
        assertEquals(8, tree.getRoot().getRight().getRight().getData());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight().getRight());
    }

    @Test
    void test_add() {
        BinaryTree tree = new BinarySearchTree();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(8);
        tree.add(2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(2, tree.getRoot().getLeft().getLeft().getData());
        assertNull(tree.getRoot().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getLeft());
        assertEquals(8, tree.getRoot().getRight().getRight().getData());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getRight().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight().getRight());
    }

    @Test
    void test_contains() {
        BinarySearchTree tree = buildTree();
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(7));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(11));
        assertTrue(tree.contains(13));
        assertTrue(tree.contains(14));
        assertTrue(tree.contains(22));
        assertFalse(tree.contains(0));
        assertFalse(tree.contains(12));
        assertFalse(tree.contains(-1));
    }

    @Test
    void test_traversePreOrderDFS() {
        String expected = "8 7 3 5 11 14 13 22 ";
        BinaryTree tree = buildTree();
        tree.traversePreOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_traverseInOrderDFS() {
        String expected = "3 5 7 8 11 13 14 22 ";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_traversePostOrderDFS() {
        String expected = "5 3 7 13 22 14 11 8 ";
        BinaryTree tree = buildTree();
        tree.traversePostOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_emptyTree() {
        BinaryTree tree = new BinaryTree();
        assertNull(tree.getRoot());
        assertTrue(tree.isEmpty());
    }

    @Test
    void test_oneNodeTree() {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        assertEquals(5, tree.getRoot().getData());
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
    }

    @Test
    void test_toString() {
        String expected = "" +
                "               8               \n" +
                "       7              11       \n" +
                "   3                    14   \n" +
                "    5              13  22 \n" +
                "                \n";
        BinaryTree tree = buildTree();
        assertEquals(expected, tree.toString());
    }

    @Test
    void test_remove_notFound() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 8 11 13 14 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertFalse(tree.contains(99));
        tree.remove(99);
        assertEquals(8, tree.size());
        assertFalse(tree.contains(99));
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_root() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 11 13 14 22 \n" +
                "3 5 7 13 14 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(8);
        assertFalse(tree.contains(8));
        assertEquals(7, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(11);
        assertFalse(tree.contains(11));
        assertEquals(6, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_nonLeaves() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 8 11 13 14 22 \n" +
                "3 5 8 11 13 22 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(7);
        assertFalse(tree.contains(7));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(14);
        assertFalse(tree.contains(14));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_leaves() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 7 8 11 13 14 22 \n" +
                "3 7 8 11 13 14 \n" +
                "3 7 8 11 14 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(5);
        assertFalse(tree.contains(5));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(22);
        assertFalse(tree.contains(22));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(13);
        assertFalse(tree.contains(13));
        assertEquals(5, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_deepestRightmost() {
        String expected = "3 5 7 8 11 13 14 22 \n" +
                "3 5 7 8 11 13 14 \n" +
                "3 5 7 8 11 14 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(22);
        assertFalse(tree.contains(22));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(13);
        assertFalse(tree.contains(13));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }


    /*
                   8
           7              11
       3                    14
        5              13  22
     */
    private BinarySearchTree buildTree() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.addMultiple(8, 11, 7, 14, 22, 13, 3, 5);
        return tree;
    }
}
