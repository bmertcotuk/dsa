package com.bmcotuk.dsaa.datastructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WeightedGraph {

    private Set<WeightedGraphNode> nodes = new HashSet<>();

    public void addNode(WeightedGraphNode node) {
        nodes.add(node);
    }

    static class WeightedGraphNode {
        private String name;
        private int distance = Integer.MAX_VALUE;
        private Map<WeightedGraphNode, Integer> neighborNodes = new HashMap<>();
        private List<WeightedGraphNode> shortestPath = new LinkedList<>();

        public WeightedGraphNode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Map<WeightedGraphNode, Integer> getNeighborNodes() {
            return neighborNodes;
        }

        public void setNeighborNodes(Map<WeightedGraphNode, Integer> neighborNodes) {
            this.neighborNodes = neighborNodes;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<WeightedGraphNode> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<WeightedGraphNode> shortestPath) {
            this.shortestPath = shortestPath;
        }
    }


    /**
     * Calculates the shortest paths from a source node using Dijkstra's algorithm.
     * <p>
     * time: O(v^2 + e)
     * space: O(v^2 + e)
     */
    public void calculateShortestPathsWithDijkstra(WeightedGraphNode source) {

        Set<WeightedGraphNode> visited = new HashSet<>();
        Set<WeightedGraphNode> unvisited = new HashSet<>();
        source.setDistance(0);
        unvisited.add(source);

        while (!unvisited.isEmpty()) {
            WeightedGraphNode currentNode = getClosestUnvisitedNode(unvisited);
            unvisited.remove(currentNode);
            for (Map.Entry<WeightedGraphNode, Integer> entry : currentNode.getNeighborNodes().entrySet()) {
                WeightedGraphNode neighborNode = entry.getKey();
                int distance = entry.getValue();
                if (!visited.contains(neighborNode)) {
                    calculateMinimumDistance(currentNode, distance, neighborNode);
                    unvisited.add(neighborNode);
                }
            }
            visited.add(currentNode);
        }
    }

    private WeightedGraphNode getClosestUnvisitedNode(Set<WeightedGraphNode> unvisited) {
        WeightedGraphNode closestUnvisitedNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (WeightedGraphNode node : unvisited) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                closestUnvisitedNode = node;
            }
        }
        return closestUnvisitedNode;
    }

    private void calculateMinimumDistance(WeightedGraphNode sourceNode,
                                          int distance,
                                          WeightedGraphNode evaluationNode) {
        int sourceDistance = sourceNode.getDistance();
        if (sourceDistance + distance < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + distance);
            LinkedList<WeightedGraphNode> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
}
