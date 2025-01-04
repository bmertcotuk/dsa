package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@SuppressWarnings("java:S106")
public class BinaryTree {

    BinaryTreeNode root;
    int size;

    public BinaryTree() {
        size = 0;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addMultiple(int... multiple) {
        for (int data : multiple) {
            add(data);
        }
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public void add(int data) {
        BinaryTreeNode newNode = new BinaryTreeNode(data);
        if (root == null) {
            root = newNode;
            size++;
        } else {
            // use BFS (leve-order traversal)
            Queue<BinaryTreeNode> queue = new LinkedList<>(); // best implementation of queue interface for this
            queue.offer(root); // better than .add() in capacity restricted queue

            while (!queue.isEmpty()) {
                BinaryTreeNode currentNode = queue.poll();

                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;
                    return;
                } else { // no return here to check its children
                    queue.offer(currentNode.getLeft());
                }

                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    size++;
                    return;
                } else {  // no return here to check its children
                    queue.offer(currentNode.getRight());
                }
            }
        }
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public boolean contains(int data) {

        if (root == null) { // no need to create Queue
            return false;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.poll();

            if (currentNode.getData() == data) {
                return true;
            }

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
        return false;
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public void remove(int data) {

        if (root == null) {
            return;
        }

        BinaryTreeNode targetNode = null;
        BinaryTreeNode lastNode = null;

        // targetNode: assign node to be deleted
        // lastNode: assign the last node in iteration
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            lastNode = queue.poll();

            if (lastNode.getData() == data)
                targetNode = lastNode;

            if (lastNode.getLeft() != null)
                queue.offer(lastNode.getLeft());

            if (lastNode.getRight() != null)
                queue.offer(lastNode.getRight());
        }

        // not found
        if (targetNode == null) {
            return;
        }
        // last node of the tree as a replacement value
        targetNode.setData(lastNode.getData());
        // remove last node - replacement node
        removeLastNode(lastNode);
        size--;
    }

    // we check by object reference, therefore, we know that we are deleting the correct node
    private void removeLastNode(BinaryTreeNode lastNode) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.poll();

            if (currentNode.getLeft() != null) {
                if (currentNode.getLeft() == lastNode) {
                    currentNode.setLeft(null);
                    return;
                } else {
                    queue.offer(currentNode.getLeft());
                }
            }

            if (currentNode.getRight() != null) {
                if (currentNode.getRight() == lastNode) {
                    currentNode.setRight(null);
                    return;
                } else {
                    queue.offer(currentNode.getRight());
                }
            }
        }
    }

    /**
     * Each node should have either 0 or 2 children.
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public boolean isFull() {
        if (root == null) {
            return true;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            boolean hasLeft = current.getLeft() != null;
            boolean hasRight = current.getRight() != null;

            if (hasLeft ^ hasRight) { // XOR
                return false;
            }

            if (hasLeft) queue.offer(current.getLeft());
            if (hasRight) queue.offer(current.getRight());
        }

        return true;
    }

    /**
     * Each level except the leaves should be full. Leaves should be filled from left to right.
     * <p>
     * BFS traverses level-based. A leaf simply has no children. Order in while loop ensures the condition of "filling from left to right" in the same level, i.e. leaf level.
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public boolean isComplete() {
        if (root == null) {
            return true;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean lacksOneChild = false;
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();

            if (current.getLeft() != null) {
                if (lacksOneChild) {
                    return false; // non-full node and it was not the leaf
                }
                queue.offer(current.getLeft());
            } else {
                lacksOneChild = true; // found a non-full node
            }

            if (current.getRight() != null) {
                if (lacksOneChild) {
                    return false; // non-full node and it was not the leaf
                }
                queue.offer(current.getRight());
            } else {
                lacksOneChild = true; // found a non-full node
            }
        }
        return true;
    }

    /**
     * Full, complete, and has exactly 2^level - 1 nodes
     * <p>
     * time: O(n)
     * space: O(n)
     */
    public boolean isPerfect() {
        if (root == null) {
            return true;
        }

        int maxLevel = getLevel();
        return isPerfectRecursion(root, 0, maxLevel);
    }

    private boolean isPerfectRecursion(BinaryTreeNode node, int currentLevel, int maxLevel) {
        if (node == null) {
            return true;
        }

        // check if leaf node is at the correct depth
        if (node.getLeft() == null && node.getRight() == null) {
            return maxLevel == currentLevel;
        }

        // a node can only have 0 or 2 children
        if (node.getLeft() == null || node.getRight() == null) {
            return false;
        }

        // recursively check left and right subtrees.
        return isPerfectRecursion(node.getLeft(), currentLevel + 1, maxLevel)
                && isPerfectRecursion(node.getRight(), currentLevel + 1, maxLevel);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTreeRecursion(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTreeRecursion(BinaryTreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.getData() <= min || node.getData() >= max) {
            return false;
        }

        return isBinarySearchTreeRecursion(node.getLeft(), min, node.getData())
                && isBinarySearchTreeRecursion(node.getRight(), node.getData(), max);
    }

    public void traversePreOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            traversePreOrderDFS(node.getLeft());
            traversePreOrderDFS(node.getRight());
        }
    }

    public void traverseInOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            traverseInOrderDFS(node.getLeft());
            System.out.print(node.getData() + " ");
            traverseInOrderDFS(node.getRight());
        }
    }

    public void traversePostOrderDFS(BinaryTreeNode node) {
        if (node != null) {
            traversePostOrderDFS(node.getLeft());
            traversePostOrderDFS(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    // root is assumed to have level 0, therefore "level" and "depth" are interchangeable
    // empty tree is assumed to have level/depth -1
    public int getLevel() {
        return maxLevelFromNode(root);
    }

    int maxLevelFromNode(BinaryTreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(maxLevelFromNode(node.getLeft()), maxLevelFromNode(node.getRight()));
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        int maxLevel = getLevel() + 1;
        int maxWidth = (int) Math.pow(2, maxLevel) - 1;
        StringBuilder sb = new StringBuilder();

        List<BinaryTreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        for (int level = 0; level <= maxLevel; level++) {

            List<BinaryTreeNode> nextLevel = new ArrayList<>();
            sb.append(getLevelString(currentLevel, maxWidth));
            sb.append("\n");

            for (BinaryTreeNode node : currentLevel) {
                if (node != null) {
                    nextLevel.add(node.getLeft());
                    nextLevel.add(node.getRight());
                } else {
                    nextLevel.add(null); // Placeholder for alignment
                    nextLevel.add(null);
                }
            }
            currentLevel = nextLevel;
            maxWidth /= 2; // Reduce spacing for the next level
        }

        return sb.toString();
    }

    private String getLevelString(List<BinaryTreeNode> level, int width) {
        int spacesBetween = width * 2 + 1;
        StringBuilder line = new StringBuilder();

        for (BinaryTreeNode node : level) {
            line.append(" ".repeat(Math.max(0, spacesBetween / 2)));
            if (node == null) {
                line.append(" ");
            } else {
                line.append(String.valueOf(node.getData()));
            }
            line.append(" ".repeat(Math.max(0, spacesBetween / 2)));
        }

        return line.toString();
    }
}
