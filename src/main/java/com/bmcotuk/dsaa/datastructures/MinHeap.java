package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.BinaryTreeNode;

public class MinHeap extends AbstractHeap {

    void bubbleUp(BinaryTreeNode node) {
        while (true) {
            BinaryTreeNode parent = getParent(node);
            // reached root
            if (parent == null) {
                return;
            }
            if (parent.getData() > node.getData()) {
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
            // find the smallestNode
            BinaryTreeNode left = node.getLeft();
            BinaryTreeNode right = node.getRight();

            BinaryTreeNode smallestNode = node;
            if (left != null && left.getData() < smallestNode.getData()) {
                smallestNode = left;
            }
            if (right != null && right.getData() < smallestNode.getData()) {
                smallestNode = right;
            }

            if (smallestNode == node) {
                break;
            } else {
                int temp = node.getData();
                node.setData(smallestNode.getData());
                smallestNode.setData(temp);
                node = smallestNode;
            }
        }
    }
}
