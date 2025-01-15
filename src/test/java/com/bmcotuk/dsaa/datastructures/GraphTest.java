package com.bmcotuk.dsaa.datastructures;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphTest {

    @Test
    void test_addNode() {
        Graph graph = new Graph();
        List<Integer> values = List.of(1, 2, 3, 4, 5, 6, 7);
        for (int value : values) {
            graph.addNode(value);
        }

        assertTrue(graph.getNodes().containsAll(values));
    }

    @Test
    void test_getNodes() {
        Graph graph = createGraph();

        assertEquals(Set.of(1, 2, 3, 4, 5, 6, 7), graph.getNodes());
    }

    @Test
    void test_removeNode() {
        Graph graph = createGraph();
        graph.removeNode(2);

        assertFalse(graph.getNodes().contains(2));
        assertFalse(graph.getNeighbors(4).contains(2));
    }

    @Test
    void test_getNeighbors() {
        Graph graph = createGraph();

        assertEquals(List.of(1, 3, 6, 5), graph.getNeighbors(2));
        assertEquals(List.of(1, 2), graph.getNeighbors(4));
    }

    @Test
    void test_addEdge() {
        Graph graph = createGraph();

        graph.addEdge(7, 6);
        assertTrue(graph.getNeighbors(7).contains(6));

        graph.addEdge(8, 15);
        assertTrue(graph.getNeighbors(8).contains(15));
        assertTrue(graph.getNodes().containsAll(Set.of(8, 15)));
    }

    @Test
    void test_removeEdge() {
        Graph graph = createGraph();

        graph.removeEdge(2, 3);
        assertFalse(graph.getNeighbors(2).contains(3));
    }

    @Test
    void test_isCyclic() {
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(1, List.of(2, 4));
        adjacencyMap.put(2, List.of(3));
        adjacencyMap.put(3, List.of(4));
        adjacencyMap.put(4, new ArrayList<>());

        assertFalse(new Graph(adjacencyMap).isCyclic());

        adjacencyMap.put(4, List.of(2));
        assertTrue(new Graph(adjacencyMap).isCyclic());
    }

    @Test
    void test_isDirected() {
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(1, List.of(2, 4));
        adjacencyMap.put(2, List.of(1, 3));
        adjacencyMap.put(3, List.of(2, 4));
        adjacencyMap.put(4, new ArrayList<>());

        assertTrue(new Graph(adjacencyMap).isDirected());

        adjacencyMap.put(4, List.of(1, 3));
        assertFalse(new Graph(adjacencyMap).isDirected());
    }

    @Test
    void test_isDirectedAcyclicGraph() {
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(1, List.of(2, 4));
        adjacencyMap.put(2, List.of(3));
        adjacencyMap.put(3, List.of(4));
        adjacencyMap.put(4, new ArrayList<>());

        assertTrue(new Graph(adjacencyMap).isDirectedAcyclicGraph());
    }

    @Test
    void test_isStronglyConnected_false() {
        assertTrue(new Graph().isStronglyConnected());

        Graph graph = createGraph();
        assertFalse(graph.isStronglyConnected());
    }

    @Test
    void test_topologicalSort() {
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(1, List.of(2, 3));
        adjacencyMap.put(2, List.of(4, 5));
        adjacencyMap.put(3, List.of(4));
        adjacencyMap.put(4, List.of(5));
        adjacencyMap.put(5, List.of());

        List<Integer> expected = List.of(1, 3, 2, 4, 5);

        assertEquals(expected, new Graph(adjacencyMap).topologicalSort());
    }

    @Test
    void test_isStronglyConnected_true() {
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        adjacencyMap.put(0, List.of(1));
        adjacencyMap.put(1, List.of(2));
        adjacencyMap.put(2, List.of(3, 4));
        adjacencyMap.put(3, List.of(0));
        adjacencyMap.put(4, List.of(2));

        assertTrue(new Graph(adjacencyMap).isStronglyConnected());
    }

    @Test
    void test_dfsIterative() {
        Graph graph = createGraph();
        assertEquals(Set.of(4, 1, 3, 2, 6, 5, 7), graph.dfsIterative(4));
    }

    @Test
    void test_dfsRecursive() {
        Graph graph = createGraph();
        assertEquals(Set.of(4, 1, 3, 2, 6, 5, 7), graph.dfsRecursive(4));
    }

    @Test
    void test_bfs() {
        Graph graph = createGraph();
        assertEquals(Set.of(4, 1, 2, 3, 6, 5, 7), graph.bfs(4));
    }

    @Test
    void test_toString() {
        Graph graph = createGraph();

        String expected = "1 -> [ 3 ]\n" +
                "2 -> [ 1, 3, 6, 5 ]\n" +
                "3 -> [ 6 ]\n" +
                "4 -> [ 1, 2 ]\n" +
                "5 -> [ 7 ]\n" +
                "6 -> [ 7 ]\n" +
                "7 -> [  ]\n";

        assertEquals(expected, graph.toString());
    }

    private Graph createGraph() {
        Graph graph = new Graph();
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 6);
        graph.addEdge(2, 5);
        graph.addEdge(5, 7);
        graph.addEdge(1, 3);
        graph.addEdge(3, 6);
        graph.addEdge(6, 7);
        return graph;
    }
}