package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Normally heaps are implemented with an array which takes O(logn) time for insertion and removal.
 * <p>
 * Here, pointer-based approach is used. This approach requires BFS which causes O(n) overhead in time and space.
 */
public abstract class AbstractHeap extends BinaryTree {

    /**
     * time: O(n)
     * space: O(n)
     */
    @Override
    public void add(int data) {
        BinaryTreeNode newNode = new BinaryTreeNode(data);
        if (root == null) {
            root = newNode;
            size++;
        } else {
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                BinaryTreeNode current = queue.poll();

                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    size++;
                    bubbleUp(newNode); // only addition to regular BinaryTree insertion
                    return;
                } else {
                    queue.offer(current.getLeft());
                }

                if (current.getRight() == null) {
                    current.setRight(newNode);
                    size++;
                    bubbleUp(newNode); // only addition to regular BinaryTree insertion
                    return;
                } else {
                    queue.offer(current.getRight());
                }
            }
        }
    }

    /**
     * time: O(1)
     * space: O(1)
     */
    public int peek() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty.");
        }
        return root.getData();
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    @SuppressWarnings("java:S2259")
    public int extractRoot() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty.");
        }
        int rootValue = root.getData();

        if (size == 1) {
            root = null;
            size--;
        } else {
            Queue<BinaryTreeNode> queue = new LinkedList<>();
            queue.offer(root);

            BinaryTreeNode lastNode = null;
            while (!queue.isEmpty()) {
                lastNode = queue.poll();

                if (lastNode.getLeft() != null) {
                    queue.offer(lastNode.getLeft());
                }
                if (lastNode.getRight() != null) {
                    queue.offer(lastNode.getRight());
                }
            }

            root.setData(lastNode.getData());
            removeNode(lastNode);
            bubbleDown(root);
            size--;
        }

        return rootValue;
    }

    abstract void bubbleUp(BinaryTreeNode node);

    abstract void bubbleDown(BinaryTreeNode node);

    BinaryTreeNode getParent(BinaryTreeNode child) {
        if (child == root) {
            return null;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();

            // only addition to BFS on a regular BinaryTree
            if (current.getLeft() == child || current.getRight() == child) {
                return current;
            }

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
        // child is the root or not in the tree
        return null;
    }
}
