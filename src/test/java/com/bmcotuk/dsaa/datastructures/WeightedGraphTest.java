package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.bmcotuk.dsaa.datastructures.WeightedGraph.WeightedGraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedGraphTest {

    @Test
    void test_calculateShortestPathsWithDijkstra() {
        WeightedGraph graph = new WeightedGraph();
        WeightedGraphNode nodeA = new WeightedGraphNode("A");
        WeightedGraphNode nodeB = new WeightedGraphNode("B");
        WeightedGraphNode nodeC = new WeightedGraphNode("C");
        WeightedGraphNode nodeD = new WeightedGraphNode("D");
        WeightedGraphNode nodeE = new WeightedGraphNode("E");
        WeightedGraphNode nodeF = new WeightedGraphNode("F");

        nodeA.setNeighborNodes(Map.of(
                nodeB, 10,
                nodeC, 15
        ));
        nodeB.setNeighborNodes(Map.of(
                nodeF, 15,
                nodeD, 12
        ));
        nodeC.setNeighborNodes(Map.of(
                nodeE, 10
        ));
        nodeD.setNeighborNodes(Map.of(
                nodeF, 1,
                nodeE, 2
        ));
        nodeF.setNeighborNodes(Map.of(
                nodeE, 5
        ));

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph.calculateShortestPathsWithDijkstra(nodeA);

        assertEquals(0, nodeA.getDistance());
        assertEquals(10, nodeB.getDistance());
        assertEquals(15, nodeC.getDistance());
        assertEquals(22, nodeD.getDistance());
        assertEquals(24, nodeE.getDistance());
        assertEquals(23, nodeF.getDistance());
    }
}