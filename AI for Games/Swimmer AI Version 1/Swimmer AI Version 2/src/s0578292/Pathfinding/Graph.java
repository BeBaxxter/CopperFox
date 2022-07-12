package s0578292.Pathfinding;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {

    private final Set<Node> nodes = new HashSet<>();

    /**
     * Adds a new node to the current Set of nodes
     * @param newNode the new node that should be added
     */
    public void addNode(Node newNode) {
        nodes.add(newNode);
    }

    /**
     * Returns the current node from a set of nodes
     * @return the current node
     */
    public Set<Node> getNodes() {
        return nodes;
    }

    /**
     * Resets the graph completely
     */
    public void resetGraph() {
    	for(Node node: this.getNodes()) {
			node.setShortestPath(new LinkedList<>());
			node.setDistance(Integer.MAX_VALUE);
		}
    }
}
