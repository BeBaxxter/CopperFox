package s0578292.Helper;

import s0578292.Pathfinding.Graph;
import s0578292.Pathfinding.Node;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodeHelper {

    /**
     * Adds nodes with their neighbors to the graph
     * @param graph as path
     * @param tilesGraph as boolean 2d array
     * @return graph
     */
    public Graph addNodesToGraph(Graph graph, Boolean[][] tilesGraph, Rectangle[] streamsToTheLeft, Rectangle[] streamsToTheRight) {
        Node[][] nodes = createNodeArray(tilesGraph);

        for(int x = 0; x < tilesGraph.length; x++) {

            for(int y = 0; y < tilesGraph[x].length; y++) {

                //if not out of bounds add adjacent Nodes
                if(nodes[x][y]!=null) {
                	
                	int distanceWhenAgainstStreamLine = AiData.tileSize * 100;

                	int distanceWhenWithStreamLine = (int) (AiData.tileSize*0.5);
                	//diatance when dive through form above, below
                	int distanceWhenThroughStreamLine = AiData.tileSize*2;
                	
                	int distanceToLeftNode;
                	int distanceToRightNode;
                	int distanceToAboveNode;
                	int distanceToBelowNode;
                	
                	//left node
                	if(pointInStreamLine(streamsToTheLeft,x-1,y)) 
                		distanceToLeftNode=distanceWhenWithStreamLine;
                	
                	else if(pointInStreamLine(streamsToTheRight,x-1,y)) 
                		distanceToLeftNode=distanceWhenAgainstStreamLine;
                	
                	else 
                		distanceToLeftNode = AiData.tileSize;
                	
                	
                	//right node
                	if(pointInStreamLine(streamsToTheLeft,x+1,y)) 
                		distanceToRightNode=distanceWhenAgainstStreamLine;
                	
                	else if(pointInStreamLine(streamsToTheRight,x+1,y)) 
                		distanceToRightNode=distanceWhenWithStreamLine;
                	
                	else 
                		distanceToRightNode = AiData.tileSize;
                	
                	
                	//above node
                	if(pointInStreamLine(streamsToTheLeft,x,y-1)) 
                		distanceToAboveNode=distanceWhenThroughStreamLine;
                	
                	else if(pointInStreamLine(streamsToTheRight,x,y-1)) 
                		distanceToAboveNode=distanceWhenThroughStreamLine;
                
                	else 
                		distanceToAboveNode = AiData.tileSize;
                	
                	
                	//below node
                	if(pointInStreamLine(streamsToTheLeft,x,y+1)) 
                		distanceToBelowNode=distanceWhenThroughStreamLine;
                	
                	
                	else if(pointInStreamLine(streamsToTheRight,x,y+1)) 
                		distanceToBelowNode=distanceWhenThroughStreamLine;
                	
                	else 
                		distanceToBelowNode = AiData.tileSize;
                	
                	
                	
                	
                	
                	
                	
                    if(x>0 )
                        if(nodes[x-1][y]!=null)
                            nodes[x][y].addDestination(nodes[x-1][y],distanceToLeftNode);
                    if(y>0)
                        if(nodes[x][y-1]!=null)
                            nodes[x][y].addDestination(nodes[x][y-1], distanceToAboveNode);
                    if(x+1<nodes.length)
                        if(nodes[x+1][y]!=null)
                            nodes[x][y].addDestination(nodes[x+1][y], distanceToRightNode);
                    if(y+1<nodes[x].length)
                        if(nodes[x][y+1]!=null)
                            nodes[x][y].addDestination(nodes[x][y+1], distanceToBelowNode);

                    //add Node with all Destinations to graph
                    graph.addNode(nodes[x][y]);
                }
            }
        }
        return graph;
    }
    
    private boolean pointInStreamLine(Rectangle[] streams, int x, int y) {
    	for(Rectangle rectancle : streams) {
    		//point is  in a streamline to this side -> distance is longer or shorter
    		if(rectancle.intersects(x*AiData.tileSize, y*AiData.tileSize, AiData.tileSize, AiData.tileSize) )
    			return true;
    	}
    	
    	//point is not in a streamline to this side -> distance is normal
    	return false;
    }

    /**
     * Creates a node array with given tilesGraph
     * @param tilesGraph as boolean 2d array
     * @return a 2D array filled with Nodes, Nodes are created with the location
     */
    public Node[][] createNodeArray(Boolean[][] tilesGraph) {

        Node[][] nodes = new Node[tilesGraph.length][tilesGraph[0].length];

        for(int x = 0; x < tilesGraph.length; x++) {

            for(int y = 0; y < tilesGraph[x].length; y++) {

                //only add node when it is not in an obstacle
                if(tilesGraph[x][y])nodes[x][y]=new Node(new Point(x * AiData.tileSize + AiData.tileSize / 2,y * AiData.tileSize + AiData.tileSize/2));

            }

        }
        return nodes;
    }

    /**
     * Calculates the closest node to the location
     * @param point as position
     * @return the Node closes to the Point
     */
    public Node getClosestNodeToLocation(Point point, Graph graph) {

        double minDistance= Double.MAX_VALUE;
        Node minDistanceNode = new Node(null);

        for(Node node:graph.getNodes()) {
            double distance =getNodeCenterCoordinates(node).distance(point);

            if(distance<minDistance) {
                minDistance = distance;
                minDistanceNode = node;
            }

        }
        return minDistanceNode;
    }

    /**
     * Calculates a node x and y into a Point
     * @param node as node
     * @return the center coordinate as point
     */
    public Point getNodeCenterCoordinates(Node node) {
        int x = node.getLocation().x  + AiData.tileSize / 2;
        int y = node.getLocation().y  + AiData.tileSize / 2;
        return new Point(x,y);

    }


    /**
     * Reverses a node list
     * @param nodeList as list
     * @return List of nodes
     */
    public List<Node> reverseList(List<Node> nodeList){
        Collections.reverse(nodeList);
        return nodeList;
    }

    /**
     * Generates a boolean tile graph
     * @param superObstacle the obstacles as a 2D path
     * @return boolean array graph
     */
    public Boolean[][] generateTileGraph(Path2D superObstacle, int sceneWidth, int sceneHeight) {

        //create an empty tileGraph with a certain size
        int tileGraphWidth =  sceneWidth  / AiData.tileSize +1;
        int tileGraphHeight = sceneHeight / AiData.tileSize +1;
        Boolean[][] tileGraph = new Boolean[tileGraphWidth][tileGraphHeight];

        //set the initial value of the tiles to have no obstacles in it(no obstacle found until now)
        for (Boolean[] booleans : tileGraph) {
            Arrays.fill(booleans, true);
        }

        //find obstacles in the graph
        //go into every cell
        for(int x = 0; x < sceneWidth; x += AiData.tileSize) {

            for(int y = 0; y < sceneHeight; y += AiData.tileSize) {

                //if one of the pixels in the cell is in an obstacle, mark the cell to be in an obstacle
                if(superObstacle.intersects(x, y, AiData.tileSize, AiData.tileSize))
                    tileGraph[x / AiData.tileSize][y / AiData.tileSize] = false;
            }

        }
        return tileGraph;
    }
}
