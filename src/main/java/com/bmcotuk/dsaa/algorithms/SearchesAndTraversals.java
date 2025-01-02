package com.bmcotuk.dsaa.algorithms;

import com.bmcotuk.dsaa.common.BinaryTreeNode;
import com.bmcotuk.dsaa.datastructures.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class SearchesAndTraversals {

    /**
     * time: O(logn)
     * space: O(1)
     */
    public int binarySearchIterative(int[] arr, int x) {
        if (arr.length == 0) {
            return -1;
        }

        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (x < arr[mid]) {
                high = mid - 1;
            } else if (x > arr[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * time: O(logn)
     * space: O(logn)
     */
    public int binarySearchRecursive(int[] arr, int x) {
        if (arr.length == 0) {
            return -1;
        }
        return binarySearchRecursion(arr, 0, arr.length, x);
    }

    private int binarySearchRecursion(int[] arr, int low, int high, int x) {
        if (low > high) {
            return -1;
        }
        int mid = (high + low) / 2;
        if (x < arr[mid]) {
            return binarySearchRecursion(arr, low, mid - 1, x);
        } else if (x > arr[mid]) {
            return binarySearchRecursion(arr, mid + 1, high, x);
        } else {
            return mid;
        }
    }

    public void depthFirstTraversalGraphIterative(Graph graph) {

    }

    public void depthFirstTraversalGraphRecursive(Graph graph) {

    }

    public void breadthFirstTraversalGraph(Graph graph) {

    }

    public void breadthFirstTraversalBinaryTree(BinaryTreeNode node) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode currentNode = queue.poll();
            if (currentNode != null) {
                System.out.print(currentNode.getData() + " ");
                queue.offer(currentNode.getLeft());
                queue.offer(currentNode.getRight());
            }
        }
    }
}
