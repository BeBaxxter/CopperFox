package s0578292.Helper;

import s0578292.Pathfinding.Graph;
import s0578292.Pathfinding.Node;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class PearlHelper {

	
	
	private class PathInformation{

		public int neededUpdates;
		public Point[] path;
		 PathInformation(Point[]  path, int neededUpdates){
			this.path =path;
			this.neededUpdates = neededUpdates;
		}
	}
	
	private class PointInformation{
		public Point point;
		public double distanceToClosestGoal;
		public double distanceShop;
		public double distancePlayer;
		public boolean betweenShopAndPlayer;
		public double score;
		
	}
	

	public PointInformation[] sortUsingScore(PointInformation[] allPointInformation) {

        for (int i = 0; i < allPointInformation.length - 1; ++i){
            for(int j = 0; j < allPointInformation.length - i - 1; ++j){
            	
                if(allPointInformation[j + 1].score > allPointInformation[j].score){
                	PointInformation temp = allPointInformation[j];
                    allPointInformation[j] = allPointInformation[j + 1];
                    allPointInformation[j + 1] = temp;
                }
            }
        }
        return  allPointInformation;
    }
	
	
	

	private final NodeHelper nodeHelper = new NodeHelper();
    MoveHelper moveHelper = new MoveHelper();

    /**
     * sorts the array that is put inside as a parameter
     * @param pearls array that contains a couple of pearl locations as Points
     * @return the sorted pearls array
     */
    public Point[] sortPearls(Point[] pearls) {

        for (int i = 0; i < pearls.length - 1; ++i){
            for(int j = 0; j < pearls.length - i - 1; ++j){

                if(pearls[j + 1].getX() < pearls[j].getX()){
                    Point temp = pearls[j];
                    pearls[j] = pearls[j + 1];
                    pearls[j + 1] = temp;
                }
            }
        }

        return  pearls;
    }

    public Point[] sortPearlsRightToLeft(Point[] pearls) {

        for (int i = 0; i < pearls.length - 1; ++i){
            for(int j = 0; j < pearls.length - i - 1; ++j){

                if(pearls[j + 1].getX() > pearls[j].getX()){
                    Point temp = pearls[j];
                    pearls[j] = pearls[j + 1];
                    pearls[j + 1] = temp;
                }
            }
        }

        return  pearls;
    }

    private int getUpdatesForRecyclingProducts(Point[] points, Point position, float maxVelocity, int maxAir, Graph graph) {
    	//look for  nextPoint
    	
    	Point nextRecyclingProduct; 
    	boolean resurface = false;
    	int distance = 0;
    	Node pathGoal;
    	int air = maxAir;
    	
    	
    	while(!isEmpty(points)) {
    	
	    	nextRecyclingProduct = getNextPointInArray(points);
	    	
	    	//calculate path from recyclingProduct to another or from surface to recyclingProduct
	    	if(!resurface) {
		    	List<Node> pathToNode = moveHelper.getReversedPathForPoints(position, nextRecyclingProduct, graph);
				
		    	//look if we have to resurface
				List<Node> pathFromPearlToSurface = moveHelper.getPathFromPointToSurface(graph, nextRecyclingProduct);
				int distanceToPointToSurface  = moveHelper.calculateDistanceToPointToSurface(pathToNode, pathFromPearlToSurface);
				int updatesToPointToSurface =  moveHelper.calculateUpdatesForDistance(distanceToPointToSurface, maxVelocity);
				List<Node> shortestPath;
				//when you can reach next recyclingProduct with air : swim to next recyclingProduct
				if(moveHelper.canSurviveToPointToSurface(updatesToPointToSurface, air)) {
					shortestPath = pathToNode;	
					points = removeClosestPointFromArray(points, nextRecyclingProduct);
					//reduce air 
					air -= moveHelper.calculateUpdatesForDistance(moveHelper.calculateDistanceForPath(shortestPath), maxVelocity);
				}
		
				else {
					//go to the surface
					shortestPath = moveHelper.getPathFromPointToSurface(graph,position);
					resurface = true;
					//refill air because you are at surface
					air =maxAir;
				} 
				distance+= moveHelper.calculateDistanceForPath(shortestPath);
				pathGoal = shortestPath.get(0);
				position = pathGoal.getLocation();
		    	}
	    	
	    	//ai at surface: calculate path over recyclingProduct
	    	else {		
	    		List<Node> pathToSurface = moveHelper.getPathFromPointToSurface(graph, nextRecyclingProduct);
				Node nodeOverPearlAtSurface = pathToSurface.get(0);
				List<Node> shortestPath;
				shortestPath =moveHelper.getReversedPathForPoints(position, nodeOverPearlAtSurface.getLocation(),graph);
				distance+= moveHelper.calculateDistanceForPath(shortestPath);
				pathGoal = shortestPath.get(0);
				position = pathGoal.getLocation();
				resurface =false;
	    	}
    	}
		return moveHelper.calculateUpdatesForDistance(distance, maxVelocity);
		
		//
    	
    	//return the needed distance
    }
    
    public Point[] calulateTheBestRecyclingProducts(Point[] points,  Point shopLocation, Point playerLocation, float maxVelocity, int maxAir, Graph graph) {
    	//calulate path with different amount of perals
    	PathInformation path1 = calulateTheBestNRecyclingProducts( points,  shopLocation,  playerLocation,4, maxVelocity, maxAir, graph);
    	PathInformation path2 = calulateTheBestNRecyclingProducts( points,  shopLocation,  playerLocation,6, maxVelocity, maxAir, graph);
    	PathInformation path3 = calulateTheBestNRecyclingProducts( points,  shopLocation,  playerLocation,8, maxVelocity, maxAir,graph);
		Point[] bestPath;

    	
    	
    	//if the lath with more recyclingProducts is only slightly longer that the one with less, collect more recycling products
    	
    	if((path3.neededUpdates <path1.neededUpdates *1.8||path3.neededUpdates<1900)&&! (path3.neededUpdates>path1.neededUpdates *4)) {
    		bestPath = path3.path;
    	}
    	else if((path2.neededUpdates <path1.neededUpdates *1.6 || path2.neededUpdates<1700)&&! (path3.neededUpdates>path1.neededUpdates *3.5)) {
    		bestPath = path2.path;
    	}
    	else bestPath = path1.path;
    	
    	
    	return bestPath;
    }
    
    
    public Point[] calulateNOptimalPointsFromArray(Point[] points,Point shopPosition, Point playerPosition,int numberOfPoints,float maxVelocity, float maxAir) {
    	double minDistanceClosestGoal= Double.MAX_VALUE;
		double maxDistanceShop = Double.MAX_VALUE;
		double maxDistancePlayer = Double.MAX_VALUE;
		double maxDepth = (maxVelocity*maxAir) /2.5;
		PointInformation[] allPointInforamtion = new PointInformation[points.length];
		
    	for(int i=0;i< points.length;i++) {
    		
    		Point point = points[i];
    		if(point!=null) {

    		allPointInforamtion[i] = new PointInformation();
    		allPointInforamtion[i].point=point;
    		allPointInforamtion[i].distanceShop=point.distance(shopPosition);
    		allPointInforamtion[i].distancePlayer=point.distance(playerPosition);
    		if((point.x<shopPosition.x && point.x>playerPosition.x )|| (point.x>shopPosition.x && point.x<playerPosition.x)) {
    			allPointInforamtion[i].betweenShopAndPlayer =true;
    		}
    		else allPointInforamtion[i].betweenShopAndPlayer =false;
    		
    		if(allPointInforamtion[i].distanceShop>maxDistanceShop) maxDistanceShop=allPointInforamtion[i].distanceShop;
    		if(allPointInforamtion[i].distancePlayer>maxDistancePlayer) maxDistancePlayer=allPointInforamtion[i].distancePlayer;
    		}
    	}
    	for(int i=0;i<points.length;i++) {
    		double distanceToClosestGoal = Double.MAX_VALUE; 
    		Point closestGoal; 
    		for(int j=0;j<points.length;j++) {
    			if(i!=j && points[i]!=null && points[j]!=null) {
    			double distance = points[i].distance(points[j]);
	    			if(distance<distanceToClosestGoal) {
	    				distanceToClosestGoal= distance;
	    				closestGoal = points[j];
	    			}
    			}
    			
    		}
    		if(points[i]!=null) {
    			allPointInforamtion[i].distanceToClosestGoal = distanceToClosestGoal;
	    		if(distanceToClosestGoal<minDistanceClosestGoal) {
	    			minDistanceClosestGoal = distanceToClosestGoal;
	    		}
    		}
    	}
    	
    	
    	for(int i=0;i< allPointInforamtion.length ;i++) {
    		if(allPointInforamtion[i]!=null) {
    			allPointInforamtion[i].score = allPointInforamtion[i].distanceShop/maxDistanceShop + allPointInforamtion[i].distancePlayer/maxDistancePlayer + (1- allPointInforamtion[i].point.y / maxDepth)
    					+(1/allPointInforamtion[i].distanceToClosestGoal)*minDistanceClosestGoal;
    			if(allPointInforamtion[i].betweenShopAndPlayer)allPointInforamtion[i].score +=1;
    		}
    		else {
    			allPointInforamtion[i] = new PointInformation();
    			allPointInforamtion[i].score =0;
    		}
    	}
    	allPointInforamtion = sortUsingScore(allPointInforamtion);
    	
    	Point[] optimalPoints=new Point[numberOfPoints];
    	for(int i=0; i<numberOfPoints; i++) {
    		optimalPoints[i] = allPointInforamtion[i].point;
    	}
    	return optimalPoints;
    }
    
    public Point[] optimisePath(Point[] points, Point shopLocation, Point playerPositon) {
        Point[] sortedPath =new Point[points.length];
        Point lastPoint = playerPositon;
    	for (int i = 0; i < points.length; ++i){
        	int bestNextPointIndex=0;
            double bestAttractiveness = Double.MAX_VALUE;//low value is attractive
    		
            for(int j=0; j<points.length;j++) {
            	if(points[j]!=null) {
            		double attractiveness = getDifferenceDistanceToShopAndPoint(lastPoint,shopLocation, points[j] );
            		if(attractiveness <bestAttractiveness) {
            			bestAttractiveness= attractiveness;
            			bestNextPointIndex = j;
            		}
            	}
           }
    		sortedPath[i]= points [bestNextPointIndex];
    		lastPoint = points [bestNextPointIndex];
    		points[bestNextPointIndex]=null;
        }

        return  sortedPath;
    }
    
    //lower value means that is good to collect the point first
    private double getDifferenceDistanceToShopAndPoint(Point start, Point shopPosition, Point anotherPoint) {
    	//low value comes when the pearls is close and the shop is  farther
    	//first collect the points that are far form shop and then the ones closer
    	return start.distance(anotherPoint) - anotherPoint.distance(shopPosition);
    }
    
    
    
    public PathInformation calulateTheBestNRecyclingProducts(Point[] points,  Point shopLocation, Point playerLocation, int numberOfPoints, float maxVelocity, int maxAir, Graph graph) {
    	Point[] firstPath = optimisePath(	getNClosestPointsToPoint(points.clone(), shopLocation,numberOfPoints, false), shopLocation, playerLocation);
    	Point[] secondPath = optimisePath( 	getNClosestPointsToPoint(points.clone(), shopLocation,numberOfPoints, true),shopLocation, playerLocation);
    	Point[] thirdPath = optimisePath(	getNClosestPointsToPoint(points.clone(), playerLocation,numberOfPoints, false),shopLocation, playerLocation);
    	Point[] fourthPath = optimisePath(	getNClosestPointsToPoint(points.clone(), playerLocation,numberOfPoints, true),shopLocation, playerLocation);
    	Point[] fifthPath = optimisePath( 	calulateNOptimalPointsFromArray(points.clone(), shopLocation, playerLocation, numberOfPoints, maxVelocity, maxAir),shopLocation, playerLocation);

    	
    	Point[] optimalPath = firstPath;
    	int leastUpdates = Integer.MAX_VALUE;
    	Point[][] allRecyclingProductPaths = {firstPath, secondPath,thirdPath,fourthPath, fifthPath};
    	int index=0;
    	for(Point[] path:allRecyclingProductPaths) {
    		index++;
    		int updatesForThisPath =  getUpdatesForRecyclingProducts(path.clone(), playerLocation,maxVelocity, maxAir, graph);
    		if(updatesForThisPath<leastUpdates) {
    			leastUpdates = updatesForThisPath;
    			optimalPath = path;
    		}
    	}
    	
    	
		return new PathInformation(optimalPath, leastUpdates);
    }
    
    /**
     * Get the closest point in array to another point
     * @param points as Point 2D array
     * @param point as Point
     * @return the closest point as Point
     */
    private Point getClosestPointInArrayToPoint(Point[] points, Point point) {
    	double distance = Double.MAX_VALUE;
    	Point closestPoint = null;
    	for(Point pointInArray: points) {
    		if(pointInArray !=null && point.distance(pointInArray)<distance) {
    			distance = point.distance(pointInArray);
    			closestPoint = pointInArray;
    		}
    	}
    	return closestPoint;
    }

    /**
     * Get the N closest point in array to another point
     * @param points as Point 2D array
     * @param point as Point
     * @param numberOfPoints the number of points that you want to get
     * @return the closest points as 2D Point array
     */
    public Point[] getNClosestPointsToPoint(Point[] points, Point point, int numberOfPoints) {
    	return getNClosestPointsToPoint(points, point, numberOfPoints, false);
    }

    /**
     * Get the N closest point in array to another point
     * @param points as Point 2D array
     * @param point as Point
     * @param numberOfPoints the number of points that you want to get
     * @param searchPointIsPreviousPoint as boolean
     * @return the closest points as 2D Point array
     */
    public Point[] getNClosestPointsToPoint(Point[] points, Point point, int numberOfPoints, boolean searchPointIsPreviousPoint) {
    	Point[] closestPoints = new Point[numberOfPoints];
    	Point searchPoint = point;
    	for(int i =0; i<numberOfPoints;i++) {
    		closestPoints[i] = getClosestPointInArrayToPoint(points, searchPoint);
    		points = removeClosestPointFromArray(points, closestPoints[i]);
    		
    		if(searchPointIsPreviousPoint)
    			searchPoint = closestPoints[i];
    	}
    	return closestPoints;
    }

    /**
     * Removes the closest point from a given array
     * @param points as 2D Points array
     * @param point as Point
     * @return closest point as 2D array
     */
    public Point[] removeClosestPointFromArray(Point[] points, Point point) {
    	int closestPointIndex=0;
    	double closestDistance=Double.MAX_VALUE;
    	
    	for(int i=0; i<points.length;i++) {
    		if(points[i]!=null) {
    			double distance = point.distance(points[i]);
        		if(distance<closestDistance) {
        			closestPointIndex=i;
        			closestDistance = distance;
        		}
    		}
    	}
    	points[closestPointIndex] = null;
    	return points;
    }
    
    public Point getClosestPointFromArray(Point[] points,Point point) {
    	int closestPointIndex=0;
    	double closestDistance=Double.MAX_VALUE;
    	
    	for(int i=0; i<points.length;i++) {
    		if(points[i]!=null) {
    			double distance = point.distance(points[i]);
        		if(distance<closestDistance) {
        			closestPointIndex=i;
        			closestDistance = distance;
        		}
    		}
    	}
    	return points[closestPointIndex];
    }
    
    public Point getClosestPearl(Point point) {
    	return getClosestPointFromArray(AiData.pearls,point);
    }

    public Point getClosestRecyclingroduct(Point point) {
    	return getClosestPointFromArray(AiData.recyclingProducts,point);
    }

    /**
     * Get the next pearl from array
     * @return the next pearl in list
     */
    public Point getNextPearl() {
    	return getNextPointInArray(AiData.pearls);
    }
    public Point getNextPearl(Point position) {
    	if(getNextPointInArray(AiData.pearls)!=getClosestPearl(position))
    		if(Math.abs(getNextPointInArray(AiData.pearls).x - getClosestPearl(position).x)  < 100 )
    			return getClosestPearl(position);
    	return getNextPointInArray(AiData.pearls);
    }
    
    public Point getNextPearl(int money, Point position, boolean atSurface) {
    	if(money==0 && atSurface)return getNextPointInArray(AiData.pearls);
    	else return getClosestPearl(position);	
    }

	public Point getNextPearl(int money, Point position) {
    	return getNextPearl(money, position, false)	;
    }

    /**
     * Get the next recycling product
     * @return as Point
     */
    public Point getNextRecyclingProduct() {
    	return getNextPointInArray(AiData.recyclingProducts);
    }

    /**
     * Get the next point
     * @param points as 2D Point array
     * @return next point as Point
     */
    private Point getNextPointInArray(Point[] points) {
    	for (Point point : points) {
            if (point != null) {
                return point;
            }
        }
        return null;
    }

    /**
     * Remove the closest pearl from Array
     * @param playerPosition as point
     * @return  pearls array without the closest pearl
     */
    public Point[] removeClosestPearl(Point playerPosition) {
    	int closestPearlIndex=0;
    	double closestDistance=Double.MAX_VALUE;
    	
    	for(int i=0; i<AiData.pearls.length;i++) {
    		if(AiData.pearls[i]!=null) {
    			double distance = playerPosition.distance(AiData.pearls[i]);
        		if(distance<closestDistance) {
        			closestPearlIndex=i;
        			closestDistance = distance;
        		}
    		}
    	}
        AiData.pearls[closestPearlIndex] = null;
    	return AiData.pearls;
    }
    
    /**
     * Checks if a given Pearl array is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(Point[] pearls) {
    	for(Point pearl: pearls) {
    		if(pearl!= null)
    			return false;
    	}
    	return true;
    }

    /**
     * Check if all pearls can get collection with the given air
     * @return true if possible
     */
    public boolean canCollectAllPearlsWithAir(float x, float y, float maxVelocity, int air, Graph graph) {
        Point pearlBefore = null;
        int neededUpdates = 0;
        for(Point pearl : AiData.pearls) {
            if(pearl != null) {
                Point start;

                start = Objects.requireNonNullElseGet(pearlBefore, () -> new Point((int) x, (int) y));

                List<Node> shortestPath = moveHelper.getReversedPathForPoints(start,pearl, graph);
                int distance = moveHelper.calculateDistanceForPath(shortestPath);
                int updates = moveHelper.calculateUpdatesForDistance(distance, maxVelocity);
                neededUpdates+=updates;
                pearlBefore = pearl;
            }
        }

        return air > neededUpdates;
    }



    public Point[] removeUnreachablePoints(Point[] unreachablePoints, float maxVelocity, int air, Graph graph) {
        for(int i=0; i<unreachablePoints.length;i++) {
            if(!pearlReachableFromSurface(graph,unreachablePoints[i], maxVelocity, air)) {
            	unreachablePoints[i] = null;
            }
        }
        return unreachablePoints;
    }
    
    /**
     * Checks if the pearl is reachable from surface
     * @param pearl as Point
     * @return if the pearl is reachable from the surface return true
     */
    public boolean pearlReachableFromSurface( Graph graph, Point pearl, float maxVelocity, int air) {

        //calculate the updates needed for the path
        List<Node> pathFromNodeToSurface = moveHelper.getPathFromPointToSurface(graph,pearl);
        int distanceToPearlToSurface = moveHelper.calculateDistanceForPath(pathFromNodeToSurface)*2;
        int updatesToPearlToSurface =(int) (moveHelper.calculateUpdatesForDistance(distanceToPearlToSurface, maxVelocity)*1.1);

        //if it's not reachable return false
        return air >= updatesToPearlToSurface;

    }
}
