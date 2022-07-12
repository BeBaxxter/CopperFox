package s0578292.Pathfinding;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import s0578292.Helper.AiData;

public class Pathfinder {

    /**
     * Calculates the shortest path from a source to a goal
     * @param graph as Graph
     * @param source as Node
     * @param goal as Node
     * @return the shortest path as Graph
     */
    public Graph calculateShortestPathFromSource(Graph graph, Node source,Node goal, Graphics2D gfx) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes, goal);
            unsettledNodes.remove(currentNode);
            if(currentNode.getDistance()>1000000) 

            // Only for debug stuff
            if(gfx != null) {
                gfx.drawOval(currentNode.getLocation().x, currentNode.getLocation().y, 1, 1);
            }

            if(currentNode==goal) 
            	return graph;

            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();
                	
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
     * Calculates the shortest path from source to surface
     * @param source as Node
     * @param gfx as Graphics2D
     * @return shortest path as Node
     */
    public Node calculateShortestPathFromSourceToSurface(Node source, Graphics2D gfx) {
    	int count=0;
        source.setDistance(0);
        
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
        	count++;
            Node currentNode = getLowestDistanceNodeToSurface(unsettledNodes);
            unsettledNodes.remove(currentNode);

            // Only for debug stuff
            if(gfx != null) {
                gfx.drawOval(currentNode.getLocation().x, currentNode.getLocation().y, 1, 1);
            }
            if(currentNode.getLocation().y == AiData.tileSize/2) return currentNode;

            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        
        return null;
    }

    /**
     * Calculates the minimum distance from evaluation to source node
     * @param evaluationNode as Node
     * @param edgeWeigh as Integer
     * @param sourceNode as Node
     */
    public void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }

    }

    /**
     * Returns the node with the lowest distance
     * @param unsettledNodes as Set<Node>
     * @param goal as Node
     * @return the node with the lowest distance
     */
    public Node getLowestDistanceNode(Set<Node> unsettledNodes,Node goal) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance() + (int) node.getLocation().distance(goal.getLocation());

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }

        }

        return lowestDistanceNode;
    }

    /**
     * Get the lowest distance from a node to surface
     * @param unsettledNodes as set of nodes
     * @return lowest distance as Node
     */
    public Node getLowestDistanceNodeToSurface(Set<Node> unsettledNodes) {

        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance() + node.getLocation().y;

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }

        }

        return lowestDistanceNode;
    }

}