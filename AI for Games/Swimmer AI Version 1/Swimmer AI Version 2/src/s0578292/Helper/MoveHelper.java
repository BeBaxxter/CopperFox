package s0578292.Helper;

import s0578292.Pathfinding.Graph;
import s0578292.Pathfinding.Node;
import s0578292.Pathfinding.Pathfinder;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.List;

public class MoveHelper {

    private final NodeHelper nodeHelper = new NodeHelper();
    private final Pathfinder pathfinder = new Pathfinder();

    public List<Node> getReversedPathForPoints(Point start, Point goal, Graph graph) {
        
        graph.resetGraph();
        //get Node the player is in

        Node startNode = nodeHelper.getClosestNodeToLocation(start, graph);
        Node goalNode = nodeHelper.getClosestNodeToLocation(goal, graph);

        //calculate the shortest graph
        graph = pathfinder.calculateShortestPathFromSource(graph, startNode, goalNode, null);
        List<Node> path = goalNode.getShortestPath();
        path.add(goalNode);
        return nodeHelper.reverseList(goalNode.getShortestPath());
    }

    /**
     * Calculates a distance for given path
     * @param path as list of nodes
     * @return path as int
     */
    public int calculateDistanceForPath(List<Node> path) {
        return path.size() * AiData.tileSize;
    }

    /**
     * Calculates the distance for a given path
     * @param pathToPoint as list of nodes
     * @param pathFromPointToSurface as list of nodes
     * @return distance as int
     */
    public int calculateDistanceToPointToSurface(List<Node> pathToPoint, List<Node> pathFromPointToSurface) {
        int distanceToPoint = calculateDistanceForPath(pathToPoint);
        int distanceFromPointToSurface = calculateDistanceForPath(pathFromPointToSurface);
        return distanceToPoint + distanceFromPointToSurface;
    }
    
    
    
    public List<Node> calulatePathToPointWithResurfacing(Point start, Point goal,Graph graph, int air, float maxVelocity){
		//get the shortest path to the pearls from the graph, the goal Node is the first Node, the current point is the last
		List<Node> pathToNode = getReversedPathForPoints(start, goal, graph);
		
		//calcutale the distances(from player to next pearl to the surface)
		List<Node> pathFromPearlToSurface = getPathFromPointToSurface(graph,goal);
		int distanceToPointToSurface  = calculateDistanceToPointToSurface(pathToNode, pathFromPearlToSurface);
		int updatesToPointToSurface =  calculateUpdatesForDistance(distanceToPointToSurface, maxVelocity);
		
		//calulate the updates to the node over the next point
		
		
		Point pointOverGoal = pathFromPearlToSurface.get(0).getLocation();
		List<Node> pathToPointOverGoalAtSurface = getReversedPathForPoints(start, pointOverGoal, graph);
		int distanceToPointOverGoalAtSurface  = calculateDistanceForPath(pathToPointOverGoalAtSurface);
		int updatesToPointOverGoalAtSurface =  calculateUpdatesForDistance(distanceToPointOverGoalAtSurface, maxVelocity);
		
		List<Node> shortestPath;
		//when you can reach next eparl with air or you are in suizidMode: swim to next pearl
		if(canSurviveToPointToSurface(updatesToPointToSurface, air)) {
			shortestPath = pathToNode;
		}
		//if you can go to point over point at surface without dying, do it
		else if(canSurviveToPointToSurface(updatesToPointOverGoalAtSurface, air)){
			shortestPath = pathToPointOverGoalAtSurface;
			AiData.resurface = true;
		}
		
		
		else {
			//go to the surface
			shortestPath = getPathFromPointToSurface(graph, start);
			AiData.resurface = true;
		} 
		return shortestPath;
    }
    
    /**
     * Calculate a update for a given distance
     * @param distance as int
     * @param maxVelocity as float
     * @return the distance as int
     */
    public int calculateUpdatesForDistance(int distance, float maxVelocity) {
        int neededUpdates = (int) Math.floor(distance / maxVelocity);
        return (int)(neededUpdates*1.05)+100;
    }

    /**
     * Get the path from a given point to the surface
     * @param graph as Graph
     * @param position as Point
     * @return a List of nodes
     */
    public List<Node> getPathFromPointToSurface(Graph graph, Point position) {
    	graph.resetGraph();
        Node positionNode = nodeHelper.getClosestNodeToLocation(position, graph);
        List<Node> path = pathfinder.calculateShortestPathFromSourceToSurface(positionNode,  null).getShortestPath();
       if(path.size()==0) path.add(positionNode);
        return nodeHelper.reverseList(path);
    }



    /**
     * calculates the move direction
     * @param nextPoint the next Point the diver should move to
     * @return the move direction in double
     */
    public double calculateMoveDirection(Point nextPoint, float x, float y) {
        double nextPointX = nextPoint.getX();
        double nextPointY = nextPoint.getY();

        double deltaX = nextPointX - x;
        double deltaY = nextPointY - y;

        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Checks if player cam survive from point to surface
     * @param neededUpdatesToPointToSurface as int
     * @param air as int
     * @return true if possible and false if not
     */
    public boolean canSurviveToPointToSurface(int neededUpdatesToPointToSurface, int air) {
        return air > neededUpdatesToPointToSurface;
    }

    /**
     * Checks if the player can see the next point
     * @return true, if player can see the point, false, if player can't see the point
     */
    public boolean canSeePointObstacles(Path2D superObstacle, Point nextPoint, float xPoint, float yPoint) {
        int x = (int) xPoint;
        int y = (int) yPoint;
        int xDelta = nextPoint.x- x ;
        int yDelta = nextPoint.y - y ;

        Point playerLocation = new Point(x,y);

        double direction = Math.atan2( yDelta, xDelta );
        double distance = playerLocation.distance(nextPoint);
        for(int i=0; i<distance;i++) {

            double sensorX = i*Math.cos(direction);
            double sensorY = i*Math.sin(direction);
            double sensorPointX = xPoint + sensorX;
            double sensorPointY = yPoint + sensorY;
            
            
           
            	//when you swim aginst the streamlineToTheRight stream is an obstacle
	            if(superObstacle.contains(sensorPointX ,sensorPointY))
	                return false;
            
            
	            if(playerLocation.distance(sensorPointX, sensorPointY)>0 && playerLocation.distance(sensorPointX, sensorPointY)<10) {
	                //look a pixel around the player to be sure that it will not intersect with an obstacle
	            	if(superObstacle.intersects(sensorPointX-1, sensorPointY-1, 2, 2))return false;
	            	
	            }
	        }
            
        return true;
    } 
    
    
    
    public boolean canSeePointStreams( Rectangle[] streamsToTheLeft, Rectangle[] streamsToTheRight,Point nextPoint, float xPoint, float yPoint) {
        int x = (int) xPoint;
        int y = (int) yPoint;
        int xDelta = nextPoint.x- x ;
        int yDelta = nextPoint.y - y ;

        Point playerLocation = new Point(x,y);

        double direction = Math.atan2( yDelta, xDelta );
        double distance = playerLocation.distance(nextPoint);
        for(int i=0; i<distance;i++) {

            double sensorX = i*Math.cos(direction);
            double sensorY = i*Math.sin(direction);
            double sensorPointX = xPoint + sensorX;
            double sensorPointY = yPoint + sensorY;
            
            
            for(Rectangle streamToTheRight:streamsToTheRight) {
            	//when you swim aginst the streamlineToTheRight stream is an obstacle
	            if((streamToTheRight.contains(sensorPointX, sensorPointY)&&xDelta<0))
	                return false;
            
	            if(playerLocation.distance(sensorPointX, sensorPointY)>0 && playerLocation.distance(sensorPointX, sensorPointY)<10) {
	                //look a pixel around the player to be sure that it will not intersect with an obstacle
	            	if(streamToTheRight.intersects(sensorPointX-1, sensorPointY-1, 2, 2)&&xDelta<0)return false;
	            	
	            }
	        }
            for(Rectangle streamToTheLeft:streamsToTheLeft) {
            	//when you swim aginst the streamlineToTheRight stream is an obstacle
	            if(streamToTheLeft.contains(sensorPointX, sensorPointY)&&xDelta>0)
	                return false;
            
	            if(playerLocation.distance(sensorPointX, sensorPointY)>0 && playerLocation.distance(sensorPointX, sensorPointY)<10) {
	                //look a pixel around the player to be sure that it will not intersect with an obstacle
	            	if(streamToTheLeft.intersects(sensorPointX-1, sensorPointY-1, 2, 2)&&xDelta>0)return false;
	            	
	            }
	        }
            
        }
        return true;
    }

    /**
     * Get the next point
     * @param playerLocation as Point
     * @param shortestPath as Node list
     * @param superObstacle as Path2D
     * @return next Point as Point
     */
    public Point getNextPoint(Point playerLocation, List<Node> shortestPath, Path2D superObstacle, Rectangle[] streamsToTheLeft,Rectangle[] streamsToTheRight) {
    	boolean useStupidPathfinding=false;
    	Point nextPoint =null;
    	Iterator<Node> pathIterator = shortestPath.iterator();
		if(pathIterator.hasNext())
		nextPoint = pathIterator.next().getLocation(); //error here can mean that shortest path is empty by some reason
		
		while(true) {
            assert nextPoint != null;
            if (!(!canSeePointObstacles(superObstacle, nextPoint, playerLocation.x, playerLocation.y) &&pathIterator.hasNext()))
                break;
            nextPoint = pathIterator.next().getLocation();
		}
		
		if(inStream(streamsToTheLeft,streamsToTheRight,nextPoint, playerLocation.x, playerLocation.y)) {
			useStupidPathfinding=true;
		}
		else {
			//look for farthest point outside the streams
			while(!canSeePointStreams(streamsToTheLeft,streamsToTheRight,nextPoint, playerLocation.x, playerLocation.y)&&pathIterator.hasNext()) {
				 nextPoint = pathIterator.next().getLocation();
			}
		}
		
		
		
		//to not get stuck all the time, when you are stuck use the "zick zack" path following
		//-> the ai gets away form the obstacle where it is stuck
		//goes so fast that you can't even see the AI being stuck
		//AI is stuck when it when it nextNode is the node at the beginning of the path and that node cannot be seen ->that because of the way the canSeeSensorPoint method works
		if((!canSeePointObstacles(superObstacle , nextPoint, playerLocation.x, playerLocation.y) &&!pathIterator.hasNext())|| useStupidPathfinding ) {
			pathIterator = shortestPath.iterator();
			while(playerLocation.distance(nextPoint)>30&&pathIterator.hasNext()) {
				nextPoint = pathIterator.next().getLocation();
			}
		}
		return nextPoint;
    }

	private boolean inStreamAgainstDirection( Rectangle[] streamsToTheLeft,Rectangle[] streamsToTheRight, Point nextPoint, float xPoint, float yPoint) {

        double xDelta = nextPoint.x- xPoint ;
        
		for(Rectangle streamToTheLeft: streamsToTheLeft) {
			//if you are in  streamline agaist the swim direction  return true
			if(streamToTheLeft.intersects(xPoint-1, yPoint-1, 2, 2)&&xDelta>0)return true;
		}
		for(Rectangle streamToTheRight: streamsToTheRight) {
			//if you are in  streamline agaist the swim direction  return true
			if(streamToTheRight.intersects(xPoint-1, yPoint-1, 2, 2)&&xDelta<0)return true;
		}
		
		
		return false;
	}
	
	public boolean inStream( Rectangle[] streamsToTheLeft,Rectangle[] streamsToTheRight, Point nextPoint, float xPoint, float yPoint) {
  
		for(Rectangle streamToTheLeft: streamsToTheLeft) {
			//if you are in  streamline agaist the swim direction  return true
			if(streamToTheLeft.intersects(xPoint-1, yPoint-1, 2, 2))return true;
		}
		for(Rectangle streamToTheRight: streamsToTheRight) {
			//if you are in  streamline agaist the swim direction  return true
			if(streamToTheRight.intersects(xPoint-1, yPoint-1, 2, 2))return true;
		}
		
		
		return false;
	}
    
}
