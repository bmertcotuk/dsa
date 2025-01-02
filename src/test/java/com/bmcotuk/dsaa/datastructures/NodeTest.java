package com.bmcotuk.dsaa.datastructures;

import com.bmcotuk.dsaa.common.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    void test_modifyTheMainNodeThroughReferenceNode() {
        Node<Integer> mainNode = new Node<>(3);
        mainNode.setNext(new Node<>(5));

        Node<Integer> referenceNode = mainNode;
        referenceNode.setData(33);
        referenceNode = referenceNode.getNext();
        referenceNode.setData(55);

        assertEquals(new Node<>(33), mainNode);
        assertEquals(new Node<>(55), mainNode.getNext());
        assertEquals(mainNode.getNext(), referenceNode);
    }
}
