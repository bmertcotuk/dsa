package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;


public class MaxHeap extends AbstractHeap {

    void bubbleUp(BinaryTreeNode node) {
        while (true) {
            BinaryTreeNode parent = getParent(node);
            // reached root
            if (parent == null) {
                return;
            }
            if (parent.getData() < node.getData()) {
                int temp = parent.getData();
                parent.setData(node.getData());
                node.setData(temp);
                node = parent;
            } else {
                // heap property is already maintained
                return;
            }
        }
    }

    void bubbleDown(BinaryTreeNode node) {
        while (true) {
            // find the largestNode
            BinaryTreeNode left = node.getLeft();
            BinaryTreeNode right = node.getRight();

            BinaryTreeNode largestNode = node;
            if (left != null && left.getData() > largestNode.getData()) {
                largestNode = left;
            }
            if (right != null && right.getData() > largestNode.getData()) {
                largestNode = right;
            }

            if (largestNode == node) {
                break;
            } else {
                int temp = node.getData();
                node.setData(largestNode.getData());
                largestNode.setData(temp);
                node = largestNode;
            }
        }
    }
}
