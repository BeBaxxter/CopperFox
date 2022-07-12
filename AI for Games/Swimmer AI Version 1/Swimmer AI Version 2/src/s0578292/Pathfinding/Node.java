package s0578292.Pathfinding;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private final Point location;
    private final Map<Node, Integer> adjacentNodes = new HashMap<>();

    private LinkedList<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    public Node(Point location) {
        this.location = location;
    }

    /**
     * Adds a new destination to the adjacentNodes Map
     * @param destination as Node
     * @param distance as Integer
     */
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    /**
     * Returns the current location
     * @return location as Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Gets the adjacentNodes Map
     * @return adjacentNodes as Map
     */
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    /**
     * Returns the current Distance
     * @return distance as integer
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * Sets the current distance to the new value
     * @param distance as Integer
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Get the shortest path
     * @return path as List<Node>
     */
    public List<Node> getShortestPath() {
        return shortestPath;
    }

    /**
     * Sets the shortest path
     * @param shortestPath as LinkedList<Node>
     */
    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

}
