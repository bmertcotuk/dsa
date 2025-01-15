package com.bmcotuk.dsaa.datastructures;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph {

    private final Map<Integer, List<Integer>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public Graph(Map<Integer, List<Integer>> adjacencyMap) {
        this.adjacencyMap = adjacencyMap;
    }

    /**
     * time: O(1)
     * space: O(1)
     */
    public void addNode(int data) {
        adjacencyMap.putIfAbsent(data, new ArrayList<>());
    }

    /**
     * time: O(v+e)
     * space: O(1)
     */
    public void removeNode(int data) {
        adjacencyMap.remove(data);

        for (Map.Entry<Integer, List<Integer>> entry : adjacencyMap.entrySet()) {
            int fromNode = entry.getKey();
            List<Integer> toNodes = entry.getValue();
            if (toNodes.contains(data)) {
                adjacencyMap.get(fromNode)
                        .remove(Integer.valueOf(data));
            }
        }
    }

    /**
     * time: O(1)
     * space: O(1)
     */
    public Set<Integer> getNodes() {
        return adjacencyMap.keySet();
    }

    /**
     * time: O(1)
     * space: O(1)
     */
    public List<Integer> getNeighbors(int data) {
        return adjacencyMap.get(data);
    }

    /**
     * time: O(deg(fromNode))
     * space: O(1)
     */
    public void addEdge(int fromNode, int toNode) {
        addNode(fromNode);
        addNode(toNode);

        if (!adjacencyMap.get(fromNode).contains(toNode)) {
            adjacencyMap.get(fromNode)
                    .add(toNode);
        }
    }

    /**
     * time: O(deg(fromNode))
     * space: O(1)
     */
    public void removeEdge(int fromNode, int toNode) {
        if (adjacencyMap.containsKey(fromNode)) {
            adjacencyMap.get(fromNode)
                    .remove(Integer.valueOf(toNode));
        }
    }

    /**
     * DFS from each node and try to detect a cycle using recursion stack for unvisited nodes.
     * <p>
     * time: O(v+e)
     * space: O(v)
     */
    public boolean isCyclic() {
        Set<Integer> visited = new HashSet<>();         // visited
        Set<Integer> recursionStack = new HashSet<>();  // being visited

        for (int fromNode : adjacencyMap.keySet()) {
            if (!visited.contains(fromNode)
                    && isCyclicDFSRecursion(fromNode, visited, recursionStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicDFSRecursion(int current,
                                         Set<Integer> visited,
                                         Set<Integer> recursionStack) {
        visited.add(current);
        recursionStack.add(current);
        for (int neighbor : adjacencyMap.get(current)) {
            if (!visited.contains(neighbor)) { // recursion case
                if (isCyclicDFSRecursion(neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) { // base case
                // found a back edge to a node in current recursion stack, we have a cycle
                return true;
            }
        }

        // remove from recursionStack since current node is explored
        recursionStack.remove(current);
        return false;
    }

    /**
     * Check if each edge (fromNode, toNode) has a corresponding edge (toNode, fromNode).
     * <p>
     * time: O(v + e^2)
     * space: O(1)
     */
    public boolean isDirected() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyMap.entrySet()) {
            int fromNode = entry.getKey();
            for (int toNode : entry.getValue()) {
                List<Integer> neighboursOfToNode = adjacencyMap.get(toNode);
                if (!neighboursOfToNode.contains(fromNode)) {
                    return true;
                }
            }
        }
        // each tuple (toNode, fromNode) has a tuple (fromNode, toNode), undirected
        return false;
    }

    /**
     * time: O(v+e)
     * space: O(v)
     */
    public boolean isDirectedAcyclicGraph() {
        return isDirected() && !isCyclic();
    }

    /**
     * Connectedness generally concerns undirected graphs. For directed graphs, we can speak of 2 options:
     * <ul>
     * <li> Strongly Connected: From every node, you can reach every other node. </li>
     * <li> Weakly Connected: If you replace all directed edges with undirected edges, then the graph is connected in the undirected sense. </li>
     * <p>
     * This method simply checks if each node is reachable from a start node both in the graph and its transpose.
     * <p>
     * DFS version of this algorithm is called "Strongly Connected Components (SCC)" and this version with BFS is called "Kosaraju's Algorithm".
     * <p>
     * time: O(v+e)
     * space: O(v+e)
     */
    public boolean isStronglyConnected() {
        if (adjacencyMap.isEmpty()) {
            return true;
        }

        int startNode = adjacencyMap.keySet()
                .iterator()
                .next();

        // check connected from startNode to all other nodes
        Set<Integer> visited = bfs(startNode);
        if (visited.size() < adjacencyMap.size()) {
            return false;
        }

        // check the same for the transpose
        Graph transposedGraph = new Graph(buildTransposedAdjacencyMap());
        visited = transposedGraph.bfs(startNode);
        return visited.size() >= adjacencyMap.size();
    }

    private Map<Integer, List<Integer>> buildTransposedAdjacencyMap() {
        Map<Integer, List<Integer>> transposedAdjacencyMap = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyMap.entrySet()) {
            int fromNode = entry.getKey();
            for (int toNode : entry.getValue()) {
                transposedAdjacencyMap.computeIfAbsent(toNode, k -> new ArrayList<>())
                        .add(fromNode);
            }
        }
        return transposedAdjacencyMap;
    }

    /**
     * time: O(v+e)
     * space: O(v)
     */
    public Set<Integer> dfsIterative(int rootNode) {
        Set<Integer> visited = new LinkedHashSet<>();
        Stack<Integer> stack = new Stack<>();
        visited.add(rootNode);
        stack.push(rootNode);

        while (!stack.isEmpty()) {
            int currentNode = stack.pop();
            for (int neighborNode : getNeighbors(currentNode)) {
                if (!visited.contains(neighborNode)) {
                    visited.add(neighborNode);
                    stack.push(neighborNode);
                }
            }
        }
        return visited;
    }

    /**
     * time: O(v+e)
     * space: O(v)
     */
    public Set<Integer> dfsRecursive(int rootNode) {
        Set<Integer> visited = new LinkedHashSet<>();
        dfsRecursion(rootNode, visited);
        return visited;
    }

    private void dfsRecursion(int currentNode, Set<Integer> visited) {
        if (!visited.contains(currentNode)) {
            visited.add(currentNode);
            for (int neighborNode : getNeighbors(currentNode)) {
                dfsRecursion(neighborNode, visited);
            }
        }
    }

    /**
     * time: O(v+e)
     * space: O(v)
     */
    public Set<Integer> bfs(int rootNode) {
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(rootNode);
        queue.offer(rootNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighborNode : getNeighbors(currentNode)) {
                if (!visited.contains(neighborNode)) {
                    visited.add(neighborNode);
                    queue.offer(neighborNode);
                }
            }
        }
        return visited;
    }

    /**
     * Assumes the graph is DAG, makes no cycle check.
     * <p>
     * time: O(v+e)
     * space: O(v)
     */
    public List<Integer> topologicalSort() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> recursionStack = new Stack<>();

        for (int node : adjacencyMap.keySet()) {
            if (!visited.contains(node)) {
                dfsTopological(node, visited, recursionStack);
            }
        }

        // just reverse of the recursionStack
        List<Integer> topologicalOrder = new ArrayList<>();
        while (!recursionStack.isEmpty()) {
            topologicalOrder.add(recursionStack.pop());
        }
        return topologicalOrder;
    }

    private void dfsTopological(int currentNode,
                                Set<Integer> visited,
                                Stack<Integer> recursionStack) {
        visited.add(currentNode);
        for (int neighbor : adjacencyMap.getOrDefault(currentNode, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsTopological(neighbor, visited, recursionStack);
            }
        }
        // essence of topological ordering since, it's pushed after all descendants
        recursionStack.push(currentNode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyMap.entrySet()) {
            int fromNode = entry.getKey();
            List<Integer> toNodes = entry.getValue();
            sb.append(fromNode).append(" -> ");
            sb.append("[ ");
            for (int i = 0; i < toNodes.size(); i++) {
                sb.append(toNodes.get(i));
                if (i != toNodes.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");
            sb.append("\n");
        }
        return sb.toString();
    }
}
