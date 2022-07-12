package s0578292;

import java.awt.*;
import java.util.List;
import java.awt.geom.Path2D;
import lenz.htw.ai4g.ai.AI;
import lenz.htw.ai4g.ai.DivingAction;
import lenz.htw.ai4g.ai.Info;
import lenz.htw.ai4g.ai.PlayerAction;
import lenz.htw.ai4g.ai.ShoppingAction;
import lenz.htw.ai4g.ai.ShoppingItem;
import s0578292.Helper.*;
import s0578292.Mode.Modes;
import s0578292.Pathfinding.Graph;
import s0578292.Pathfinding.Node;
import s0578292.Pathfinding.Pathfinder;

public class SecondAI1 extends AI{

	private final Point[] unreachablePearls = new Point[10];

	//TODO Auslagern in AI Data
	private List<Node> shortestPath;

	//TODO Auslagern in AI Data
	public Graph graph;

	private Modes mode = Modes.GET_SOME_UPGRADES_MODE;
	private boolean knowPath = false;
	private boolean swimOverPearl = true;
	private Point goalOnPath; 
	
	private int deltaTime;
	private Point nextPoint;
	private Point nextGoal;
	private int lastScore=0;
	private int lastMoney = 0;
	public Path2D superObstacle;
	private Rectangle[] streams;
	private Rectangle[] streamsToTheLeft;
	private Rectangle[] streamsToTheRight;

	private final NodeHelper  nodeHelper   =   new NodeHelper();
	private final PearlHelper pearlHelper  =   new PearlHelper();
	private final Pathfinder  pathfinder   =   new Pathfinder();
	private final MoveHelper  moveHelper   =   new MoveHelper();

	private final ShoppingItem[] itemsToGet = {ShoppingItem.BALLOON_SET,ShoppingItem.STREAMLINED_WIG, ShoppingItem.MOTORIZED_FLIPPERS, ShoppingItem.CORNER_CUTTER};

	public SecondAI1(Info info) {

		super(info);
		this.enlistForTournament(578292, 577812);

		Point shopLocation = new Point(info.getScene().getShopPosition(),5);
		
		Point playerLoaction = new Point((int)info.getX(), (int)info.getY());
		
		// Prepare the pearls
		AiData.pearls = info.getScene().getPearl();
		
		AiData.pearls = pearlHelper.sortPearls(AiData.pearls);
		if(shopLocation.distance(AiData.pearls[AiData.pearls.length -1]) <shopLocation.distance(AiData.pearls[0]))
			AiData.pearls = pearlHelper.sortPearlsRightToLeft(AiData.pearls);
		

		// Prepare the obstacles
		superObstacle = new Path2D.Double();
		Path2D[] obstacles = info.getScene().getObstacles();

		// Combine all obstacles to a super obstacle
		// false = we don't want a line between them
		for (Path2D obstacle : obstacles) {
			superObstacle.append(obstacle, false);
		}
		
		
		AiData.tilesGraph = nodeHelper.generateTileGraph(superObstacle, info.getScene().getWidth(), info.getScene().getHeight());
	    graph = new Graph();
		graph = nodeHelper.addNodesToGraph(graph, AiData.tilesGraph, info.getScene().getStreamsToTheLeft(), info.getScene().getStreamsToTheRight());
		
		AiData.recyclingProducts = pearlHelper.removeUnreachablePoints(info.getScene().getRecyclingProducts(), info.getMaxVelocity(),info.getAir(),graph);
		//AiData.recyclingProducts =pearlHelper.getNClosestPointsToPoint(AiData.recyclingProducts, shopLocation, 4);
		AiData.recyclingProducts= pearlHelper.calulateTheBestRecyclingProducts(AiData.recyclingProducts,shopLocation,playerLoaction, info.getMaxVelocity(), info.getMaxAir(),graph);
		
		
		//write the streams in one array
		int lengthOfLeftStreams = info.getScene().getStreamsToTheLeft().length;
		int lengthOfRightStreams = info.getScene().getStreamsToTheRight().length;
		
		streamsToTheLeft = info.getScene().getStreamsToTheLeft();
		streamsToTheRight = info.getScene().getStreamsToTheRight();
		
		
		streams = new Rectangle[lengthOfLeftStreams+lengthOfRightStreams];
		int index=0;
		while(index<lengthOfLeftStreams) {
			streams[index] = info.getScene().getStreamsToTheLeft()[index];
			index++;
		}
		while(index<lengthOfLeftStreams+ lengthOfRightStreams) {
			streams[index] = info.getScene().getStreamsToTheRight()[index-lengthOfLeftStreams];
			index++;
		}
	}

	@Override
	public String getName() {
		return "HelpImStuck";
	}

	@Override
	public Color getPrimaryColor() {
		return Color.BLACK;
	}

	@Override
	public Color getSecondaryColor() {
		return Color.ORANGE;
	}

	@Override
	public PlayerAction update() {
		Point playerPosition = new Point((int)info.getX(), (int)info.getY());
		deltaTime++;
		
		if(deltaTime>10 && moveHelper.inStream(streamsToTheLeft,streamsToTheRight,nextPoint, playerPosition.x, playerPosition.y)) {
			deltaTime=0;
			knowPath=false;
		}
		
		//-----------------------------------deciding on the Pathfinding-------------------------------
		Point[] allFish = info.getScene().getFish();
		for(Point fish: allFish) {
			if(fish.distance(playerPosition)<20) knowPath=false;
		}
	
		
		
		//when a pearl is collected, get the next one, reset path
		if(lastScore!=info.getScore()) {
			AiData.pearls =  pearlHelper.removeClosestPearl(playerPosition);
			knowPath=false;
		}
		
		if(lastMoney!=info.getMoney()) {
			AiData.recyclingProducts =  pearlHelper.removeClosestPointFromArray(AiData.recyclingProducts,playerPosition);
			nextGoal = pearlHelper.getNextRecyclingProduct();
			lastMoney=info.getMoney();
			knowPath=false;
		}
		
	
		
		//erstmal an oberfläche schwimmen
		if(info.getAir()==info.getMaxAir() && AiData.resurface) {
			knowPath = false;
			AiData.resurface = false;
			swimOverPearl = true;
		}

		//wenn über perle, aufhören über perle schwimmen zu wollen
		if(knowPath && shortestPath!=null && playerPosition.distance(shortestPath.get(0).getLocation())<100 &&swimOverPearl) {
			swimOverPearl=false;
			knowPath = false;
		}
		
		
		
		if(mode==Modes.GET_SOME_UPGRADES_MODE && playerPosition.distance(info.getScene().getShopPosition(),0 )<19 &&pearlHelper.isEmpty(AiData.recyclingProducts)) {
			//if enought money, and upgrades to buy, buy
			if(info.getMoney()>=2 && itemsToGet[itemsToGet.length -1]!=null) {
				for(int i = 0; i< itemsToGet.length;i++) {
					if(itemsToGet[i]!=null) {
						ShoppingItem itemToBuy = itemsToGet[i];
						itemsToGet[i]=null;
						return new ShoppingAction(itemToBuy);
					}
				}
				
			}
			//if not enought money or all upgrades are bought, continue to collect pearls
			else {mode = Modes.NORMAL_MODE;knowPath=false;}
		}
		
		
		
		//------------------------------------------Pathfinding---------------------------------------------
		
		//decide what nextGoal is going to be
		if(!knowPath) {
			if(mode == Modes.GET_SOME_UPGRADES_MODE && !pearlHelper.isEmpty(AiData.recyclingProducts)) {
				nextGoal = pearlHelper.getNextRecyclingProduct();
			}
			else if (mode == Modes.GET_SOME_UPGRADES_MODE && pearlHelper.isEmpty(AiData.recyclingProducts)){
				nextGoal = new Point(info.getScene().getShopPosition(),5);
			}
			else {
				if(lastScore!=info.getScore())
					nextGoal = pearlHelper.getNextPearl(playerPosition);
				else nextGoal = pearlHelper.getNextPearl(playerPosition);}
	
		}
		
		
		if(lastScore!=info.getScore()) {
			lastScore= info.getScore();
			if(pearlHelper.canCollectAllPearlsWithAir(info.getX(), info.getY(), info.getMaxVelocity(), info.getAir(), graph) && pearlHelper.isEmpty(unreachablePearls)) {
				mode = Modes.COLLECTION_MODE;
			}
		}
		
		
		//an oberfläche über nächste perlse schwimmen wenn gerade aufgetaucht (wenn aufgetaucht swimOverPearl==true)
		if(!knowPath && info.getAir()==info.getMaxAir() && swimOverPearl ) {
			if(mode !=Modes.GET_SOME_UPGRADES_MODE)nextGoal = pearlHelper.getNextPearl();
			double heightWithAir = info.getY(); 
			//node über perle suchen
			List<Node> pathToSurface = moveHelper.getPathFromPointToSurface(graph, nextGoal);
			Node nodeOverPearlAtSurface = pathToSurface.get(0);
			int index=0;
			//dont dive up to far, only that far that you have air
			while(nodeOverPearlAtSurface.getLocation().y<heightWithAir+8 && pathToSurface.size()>index+1) {
				index++;
				nodeOverPearlAtSurface = pathToSurface.get(index);
			}
				
				//way from AI to Node over Pearl (at surface)
				shortestPath =moveHelper.getReversedPathForPoints(playerPosition, nodeOverPearlAtSurface.getLocation(),graph);
				knowPath =true;
		}
		
		
			
		
		if(!knowPath) {
			
			
			
			if(mode == Modes.GET_SOME_UPGRADES_MODE) {
				
				shortestPath = moveHelper.calulatePathToPointWithResurfacing(playerPosition,nextGoal, graph, info.getAir(), info.getMaxVelocity());
				
				
			}
			//----------------------------------------------------------------------------------
			else if(mode == Modes.NORMAL_MODE) {
				
				shortestPath = moveHelper.calulatePathToPointWithResurfacing(playerPosition,nextGoal, graph, info.getAir(), info.getMaxVelocity());
			}
			
			//----------------------------------------------------------------------------------
			else if(mode == Modes.COLLECTION_MODE) {
				
				shortestPath = moveHelper.getReversedPathForPoints(playerPosition, nextGoal,graph);
				
			}
		
			knowPath = true;
		}
		
	
		Point playerLocation = new Point((int)info.getX(),(int)info.getY());
		
		//find the next point
		
		/*
		if(goalOnPath==null)
			goalOnPath= moveHelper.getNextPoint(playerLocation, shortestPath, superObstacle,streamsToTheLeft, streamsToTheRight);
		nextPoint = goalOnPath;
		
		if(goalOnPath.distance(playerLocation)<23)goalOnPath=null;
		
		*/

		nextPoint = moveHelper.getNextPoint(playerLocation, shortestPath, superObstacle,streamsToTheLeft, streamsToTheRight);
		//swim to pearl when near
		//if(nodeHelper.getClosestNodeToLocation(playerLocation, graph) == nodeHelper.getClosestNodeToLocation(nextPearl, graph))
		
		
		
		
		
		if(playerLocation.distance(nextGoal)<23) 
			nextPoint= nextGoal;
		double direction = moveHelper.calculateMoveDirection(nextPoint, info.getX(), info.getY());
		return new DivingAction(info.getMaxVelocity(), -(float) direction);
	}

	@Override
	public void drawDebugStuff(Graphics2D gfx) {



		if(nextPoint!=null && AiSettings.drawSensor) {
			drawSensor(gfx);
		}

		//test if nodes are as intended by drawing them
		if(nextPoint!=null && AiSettings.drawNode) {
			gfx.drawOval(nextPoint.x, nextPoint.y , 10, 10);
		}

		// Draw anchor Points
		if(AiSettings.drawAnchorPoints) {
			for(Node node:graph.getNodes())
				gfx.drawOval(node.getLocation().x * 10, node.getLocation().y * 10, 1, 1);
		}

		// Draw the shortest path
		if(shortestPath!=null && AiSettings.drawShortestPath) {

			for(Node node:shortestPath) {
				gfx.setColor(Color.yellow);
				gfx.drawOval(node.getLocation().x , node.getLocation().y, 1, 1);
			}
			gfx.setColor(Color.green);
		}

		if(AiSettings.drawCalculatedGraph) {
			Point playerPosition = new Point((int)info.getX(), (int)info.getY());
			Node playerNode = nodeHelper.getClosestNodeToLocation(playerPosition, graph);
			Node perlsNode = nodeHelper.getClosestNodeToLocation(nextGoal, graph);
			pathfinder.calculateShortestPathFromSource(graph, playerNode, perlsNode, gfx);
		}
	}

	/**
	 * Draws a sensor in front of the player
	 * @param gfx as graphics 2D object
	 */
	private void drawSensor(Graphics2D gfx) {
		int x = (int) info.getX();
		int y = (int) info.getY();
		int xDelta = x - nextPoint.x ;
		int yDelta = y - nextPoint.y ;
		Point playerLocation = new Point(x,y);

		double direction = Math.atan2(- yDelta,- xDelta);
		double distance = playerLocation.distance(nextPoint);

		for(int i=0; i<distance;i++) {

			double sensorX = i*Math.cos(direction);
			double sensorY = i*Math.sin(direction);
			double sensorPointX = info.getX()+ sensorX;
			double sensorPointY=info.getY()+ sensorY;

			if(i%5==0)
				gfx.drawRect((int)sensorPointX, (int)sensorPointY, 2, 2);

		}
		

	}
}