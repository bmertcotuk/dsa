package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;

/**
 * @author Mert Cotuk
 */
@SuppressWarnings("java:S106")
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree() {
        super();
    }


    // pass the root as the only start
    @Override
    public void add(int data) {
        root = addRecursion(root, data);
    }

    // assigning and returning solves "pass by value" issues
    // adds by following BST
    private BinaryTreeNode addRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            node = new BinaryTreeNode(data);
            size++;
        } else {
            if (data <= node.getData()) {
                node.setLeft(addRecursion(node.getLeft(), data));
            } else {
                node.setRight(addRecursion(node.getRight(), data));
            }
        }
        return node;
    }

    @Override
    public boolean contains(int data) {
        return containsRecursion(root, data);
    }

    // always have a clear "else" block to avoid "missing return statement"
    private boolean containsRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            return false;
        } else {
            if (data < node.getData()) {
                return containsRecursion(node.getLeft(), data);
            } else if (data > node.getData()) {
                return containsRecursion(node.getRight(), data);
            } else {
                return true;
            }
        }
    }

    // we only use the deepest node in order to maintain the balance of a binary tree
    @Override
    public void remove(int data) {
        root = removeRecursion(root, data);
    }

    private BinaryTreeNode removeRecursion(BinaryTreeNode node, int data) {
        if (node == null) {
            return null;
        }
        // recursion will work like find, it will only touch the affected sub-tree and remove the value
        if (data < node.getData()) {
            node.setLeft(removeRecursion(node.getLeft(), data));
            return node;
        } else if (data > node.getData()) {
            node.setRight(removeRecursion(node.getRight(), data));
            return node;
        } else {
            // size should be decremented when clearing or setting, two children case is just a step for recursion
            // has no child
            if (node.getLeft() == null && node.getRight() == null) {
                size--;
                return null;
            }
            // has only right child
            if (node.getLeft() == null && node.getRight() != null) {
                size--;
                return node.getRight();
            }
            // has only left child
            if (node.getLeft() != null && node.getRight() == null) {
                size--;
                return node.getLeft();
            }
            // has two children
            // replacement value will be the smallest of the right sub-tree to keep the tree balanced
            int replacementValue = findSmallestValue(node.getRight());
            node.setData(replacementValue);
            // delete the replacement value
            node.setRight(removeRecursion(node.getRight(), replacementValue));
            return node;
        }
    }

    private int findSmallestValue(BinaryTreeNode node) {
        return node.getLeft() == null
                ? node.getData()
                : findSmallestValue(node.getLeft());
    }
}
