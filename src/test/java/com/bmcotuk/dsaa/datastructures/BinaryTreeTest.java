package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryTreeTest {

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
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(5, 3, 7, 8, 2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(8, tree.getRoot().getLeft().getLeft().getData());
        assertEquals(2, tree.getRoot().getLeft().getRight().getData());
        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getLeft().getRight().getLeft());
        assertNull(tree.getRoot().getLeft().getRight().getRight());
    }

    @Test
    void test_add() {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(8);
        tree.add(2);

        assertEquals(5, tree.getRoot().getData());

        assertEquals(3, tree.getRoot().getLeft().getData());
        assertEquals(7, tree.getRoot().getRight().getData());

        assertEquals(8, tree.getRoot().getLeft().getLeft().getData());
        assertEquals(2, tree.getRoot().getLeft().getRight().getData());
        assertNull(tree.getRoot().getRight().getLeft());
        assertNull(tree.getRoot().getRight().getRight());

        assertNull(tree.getRoot().getLeft().getLeft().getLeft());
        assertNull(tree.getRoot().getLeft().getLeft().getRight());
        assertNull(tree.getRoot().getLeft().getRight().getLeft());
        assertNull(tree.getRoot().getLeft().getRight().getRight());
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
    void test_size() {
        BinaryTree tree = buildTree();
        assertEquals(8, tree.size());
    }

    @Test
    void test_getRoot() {
        int expected = 5;
        BinaryTree tree = new BinaryTree();
        tree.add(expected);

        assertEquals(expected, tree.getRoot().getData());
    }

    @Test
    void test_isFull() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isFull());

        tree.add(8);
        assertTrue(tree.isFull());

        tree.add(5);
        assertFalse(tree.isFull());

        tree.add(13);
        assertTrue(tree.isFull());

        tree.add(26);
        assertFalse(tree.isFull());

        tree.add(3);
        assertTrue(tree.isFull());

        tree.add(1);
        assertFalse(tree.isFull());

        tree.add(14);
        assertTrue(tree.isFull());

        tree.add(9);
        assertFalse(tree.isFull());

        tree.remove(13);
        assertTrue(tree.isFull());
    }

    @Test
    void test_isComplete() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isComplete());

        tree.add(8);
        assertTrue(tree.isComplete());

        tree.getRoot()
                .setLeft(new BinaryTreeNode(5));
        assertTrue(tree.isComplete());

        tree.getRoot()
                .setRight(new BinaryTreeNode(13));
        assertTrue(tree.isComplete());

        tree.getRoot()
                .getLeft()
                .setRight(new BinaryTreeNode(26));
        assertFalse(tree.isComplete());

        tree.getRoot()
                .getLeft()
                .setLeft(new BinaryTreeNode(3));
        assertTrue(tree.isComplete());

        tree.getRoot()
                .getRight()
                .setRight(new BinaryTreeNode(1));
        assertFalse(tree.isComplete());

        tree.getRoot()
                .getRight()
                .setLeft(new BinaryTreeNode(14));
        assertTrue(tree.isComplete());

        tree.getRoot()
                .getRight()
                .getRight()
                .setLeft(new BinaryTreeNode(9));
        assertFalse(tree.isComplete());

        tree.getRoot()
                .getRight()
                .getRight()
                .setRight(new BinaryTreeNode(13));
        assertFalse(tree.isComplete());
    }

    @Test
    void test_isPerfect() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isPerfect());

        tree.add(8);
        assertTrue(tree.isPerfect());

        tree.getRoot()
                .setLeft(new BinaryTreeNode(5));
        assertFalse(tree.isPerfect());

        tree.getRoot()
                .setRight(new BinaryTreeNode(13));
        assertTrue(tree.isPerfect());

        tree.getRoot()
                .getLeft()
                .setRight(new BinaryTreeNode(26));
        assertFalse(tree.isPerfect());

        tree.getRoot()
                .getLeft()
                .setLeft(new BinaryTreeNode(3));
        assertFalse(tree.isPerfect());

        tree.getRoot()
                .getRight()
                .setRight(new BinaryTreeNode(1));
        assertFalse(tree.isPerfect());

        tree.getRoot()
                .getRight()
                .setLeft(new BinaryTreeNode(14));
        assertTrue(tree.isPerfect());

        tree.getRoot()
                .getRight()
                .getRight()
                .setLeft(new BinaryTreeNode(9));
        assertFalse(tree.isPerfect());

        tree.getRoot()
                .getRight()
                .getRight()
                .setRight(new BinaryTreeNode(13));
        assertFalse(tree.isPerfect());
    }

    @Test
    void test_isBinarySearchTree() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isBinarySearchTree());

        tree.add(8);
        assertTrue(tree.isBinarySearchTree());

        tree.getRoot()
                .setLeft(new BinaryTreeNode(5));
        assertTrue(tree.isBinarySearchTree());

        tree.getRoot()
                .setRight(new BinaryTreeNode(13));
        assertTrue(tree.isBinarySearchTree());

        tree.getRoot()
                .getLeft()
                .setRight(new BinaryTreeNode(26));
        assertFalse(tree.isBinarySearchTree());

        tree.getRoot()
                .getLeft()
                .setLeft(new BinaryTreeNode(3));
        assertFalse(tree.isBinarySearchTree());

        tree.getRoot()
                .getRight()
                .setRight(new BinaryTreeNode(1));
        assertFalse(tree.isBinarySearchTree());

        tree.getRoot()
                .getRight()
                .setLeft(new BinaryTreeNode(14));
        assertFalse(tree.isBinarySearchTree());

        tree.getRoot()
                .getRight()
                .getRight()
                .setLeft(new BinaryTreeNode(9));
        assertFalse(tree.isBinarySearchTree());

        tree.getRoot()
                .getRight()
                .getRight()
                .setRight(new BinaryTreeNode(13));
        assertFalse(tree.isBinarySearchTree());
    }

    @Test
    void test_isEmpty() {
        BinaryTree tree = new BinaryTree();
        assertTrue(tree.isEmpty());
        tree.add(2);
        assertFalse(tree.isEmpty());
    }

    @Test
    void test_level() {
        BinaryTree tree = new BinaryTree();
        assertEquals(-1, tree.getLevel());
        tree.add(8);
        assertEquals(0, tree.getLevel());
        tree.add(11);
        assertEquals(1, tree.getLevel());
        tree.add(7);
        assertEquals(1, tree.getLevel());
        tree.add(14);
        assertEquals(2, tree.getLevel());
        tree.add(22);
        assertEquals(2, tree.getLevel());
        tree.add(13);
        assertEquals(2, tree.getLevel());
        tree.add(3);
        assertEquals(2, tree.getLevel());
        tree.add(5);
        assertEquals(3, tree.getLevel());
    }

    @Test
    void test_contains() {
        BinaryTree tree = buildTree();
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
        String expected = "8 11 14 5 22 7 13 3 ";
        BinaryTree tree = buildTree();
        tree.traversePreOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_traverseInOrderDFS() {
        String expected = "5 14 11 22 8 13 7 3 ";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_traversePostOrderDFS() {
        String expected = "5 14 22 11 13 3 7 8 ";
        BinaryTree tree = buildTree();
        tree.traversePostOrderDFS(tree.getRoot());
        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_toString() {
        String expected = "" +
                "               8               \n" +
                "       11              7       \n" +
                "   14      22      13      3   \n" +
                " 5                      \n" +
                "                \n";
        BinaryTree tree = buildTree();
        assertEquals(expected, tree.toString());
    }

    @Test
    void test_remove_notFound() {
        String expected = "5 14 11 22 8 13 7 3 \n" +
                "5 14 11 22 8 13 7 3 \n";
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
        String expected = "5 14 11 22 8 13 7 3 \n" +
                "14 11 22 5 13 7 3 \n" +
                "14 11 22 3 13 7 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(8);
        assertFalse(tree.contains(8));
        assertEquals(7, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(5);
        assertFalse(tree.contains(5));
        assertEquals(6, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_nonLeaves() {
        String expected = "5 14 11 22 8 13 7 3 \n" +
                "14 11 22 8 13 5 3 \n" +
                "14 11 3 8 13 5 \n" +
                "13 11 3 8 5 \n" +
                "13 3 8 5 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(7);
        assertFalse(tree.contains(7));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(22);
        assertFalse(tree.contains(22));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(14);
        assertFalse(tree.contains(14));
        assertEquals(5, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(11);
        assertFalse(tree.contains(11));
        assertEquals(4, tree.size());
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    @Test
    void test_remove_leaves() {
        String expected = "5 14 11 22 8 13 7 3 \n" +
                "14 11 22 8 13 7 3 \n" +
                "14 11 3 8 13 7 \n" +
                "14 11 3 8 7 \n";
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
        String expected = "5 14 11 22 8 13 7 3 \n" +
                "14 11 22 8 13 7 3 \n" +
                "14 11 22 8 13 7 \n";
        BinaryTree tree = buildTree();
        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(5);
        assertFalse(tree.contains(5));
        assertEquals(7, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        tree.remove(3);
        assertFalse(tree.contains(3));
        assertEquals(6, tree.size());

        tree.traverseInOrderDFS(tree.getRoot());
        System.out.println();

        assertEquals(expected, outContent.toString());
    }

    /*
                   8
           11              7
       14      22      13      3
     5
     */
    private BinaryTree buildTree() {
        BinaryTree tree = new BinaryTree();
        tree.addMultiple(8, 11, 7, 14, 22, 13, 3, 5);
        return tree;
    }
}
