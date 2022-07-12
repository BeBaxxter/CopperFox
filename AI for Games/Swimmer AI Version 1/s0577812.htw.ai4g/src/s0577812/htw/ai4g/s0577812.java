package s0577812.htw.ai4g;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;

import lenz.htw.ai4g.ai.AI;
import lenz.htw.ai4g.ai.DivingAction;
import lenz.htw.ai4g.ai.Info;
import lenz.htw.ai4g.ai.PlayerAction;

public class s0577812 extends AI {
    private final Point[] pearls;
    private Point nextPearl;
    private int pearlCounter = 0;
    int obstacleCheckpointDistance = 1;
    int amountOfRay = 10;
    int obstacleAvoidanceAngle = 90;
    int fleeThreshold = 4;
    int[] distanceToObstacle = new int[amountOfRay];
    private final float[] startCoord = new float[2];
    private final float[] pearlCoord = new float[2];
    private final float[][] rayDirection = new float[amountOfRay][2];

    public s0577812(Info info) {
        super(info);
        enlistForTournament(577812);

        /**
         * get all pearls and sort them with x and y
         */

        pearls = info.getScene().getPearl();

        for (int i = 0; i < pearls.length - 1; ++i){
            for(int j = 0; j < pearls.length - i - 1; ++j){
                if(pearls[j + 1].getX() < pearls[j].getX()){
                    Point temp = pearls[j];
                    pearls[j] = pearls[j + 1];
                    pearls[j + 1] = temp;
                }
            }
        }

        // Set the first pearl
        nextPearl = pearls[pearlCounter];
    }

    @Override
    public String getName() {
        return "HelpIAmStuck";
    }

    @Override
    public Color getPrimaryColor() {
        return Color.ORANGE;
    }

    @Override
    public Color getSecondaryColor() {
        return Color.BLACK;
    }

    @Override
    public PlayerAction update() {

        /** 1. Check for pearl **/
        if(pearlCounter != info.getScore()) {

            pearls[pearlCounter] = null;
            pearlCounter++;
            nextPearl = pearls[pearlCounter];

        }

        /** 2. Calculate the stuff **/

        pearlCoord[0] = nextPearl.x;
        pearlCoord[1] = nextPearl.y;
        startCoord[0] = info.getX();
        startCoord[1] = info.getY();

        // Calculate the direction we want to move
        float[] moveDirection = seek(startCoord, pearlCoord);


        // Calculate the angle size between the different rays
        // I.e. how big the angle between all the rays is
        float rayRotationAngle = (float) (obstacleAvoidanceAngle / amountOfRay * Math.PI / 180);

        // Create the rays
        for(int rayNumber = 0; rayNumber < amountOfRay; rayNumber++) {

            boolean obstacleFound = false;

            // Calculate the angle of the current ray depending on our moving direction
            float rayAngle = (obstacleAvoidanceAngle >> 1) - rayNumber * (rayRotationAngle);

            // Matrix calculation to rotate the ray's direction
            rayDirection[rayNumber][0] = (float) ((float) (Math.cos(rayAngle) * moveDirection[0]) - (Math.sin(rayAngle) * moveDirection[1]));
            rayDirection[rayNumber][1] = (float) ((float) (Math.sin(rayAngle) * moveDirection[0]) + (Math.cos(rayAngle) * moveDirection[1]));

            normalizeFloatArray(rayDirection[rayNumber]);

            // Amount of points in our view we want to check
            int obstacleCheckPointAmount = 40;

            // Check all points from current rays
            for(int i = 0; i < obstacleCheckPointAmount && !obstacleFound; i++) {

                // Create the i'th point in the ray's direction
                Point obstacleCheckPoint = new Point((int) (startCoord[0] + (rayDirection[rayNumber][0] * i * obstacleCheckpointDistance)), (int) (startCoord[1] + (rayDirection[rayNumber][1] * i * obstacleCheckpointDistance)));

                // Checking for obstacle in moving direction
                for(int o = 0; o < info.getScene().getObstacles().length && !obstacleFound; o++) {

                    // Get the o'th obstacle
                    Path2D obstacle = info.getScene().getObstacles()[o];

                    // Set the ray's distance to closest obstacle if there is any
                    if(obstacle.contains(obstacleCheckPoint)) {
                        distanceToObstacle[rayNumber] = i * obstacleCheckpointDistance;
                        obstacleFound = true;
                    }
                    else {
                        distanceToObstacle[rayNumber] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        // Check for all rays which one was the closest to an obstacle
        int closestDistanceToObstacle = Integer.MAX_VALUE;
        int closestRay = 0;

        for(int r = 0; r < distanceToObstacle.length; r++) {
            if(distanceToObstacle[r] < closestDistanceToObstacle) {
                closestRay = r;
                closestDistanceToObstacle = distanceToObstacle[r];
            }
        }

        /** 3. The moving stuff **/

        float[] fleeDirection;

        if(closestDistanceToObstacle < fleeThreshold) {

            fleeThreshold = 11;

            float[] closestObstacleCoordinates = new float[2];

            // Calculate nearest point in obstacle
            closestObstacleCoordinates[0] = startCoord[0] + closestDistanceToObstacle * rayDirection[closestRay][0];
            closestObstacleCoordinates[1] = startCoord[1] + closestDistanceToObstacle * rayDirection[closestRay][1];

            fleeDirection = flee(startCoord, closestObstacleCoordinates);
        }
        else {
            fleeDirection = moveDirection;

            fleeThreshold = 4;
        }

        // We combine flee and seek directions to the actual move direction we want to swim to
        moveDirection[0] = (3f * moveDirection[0]) + (20f * fleeDirection[0]);
        moveDirection[1] = (3f * moveDirection[1]) + (20f * fleeDirection[1]);

        normalizeFloatArray(moveDirection);

        /** 4. Prepare everything for run and fire **/

        // We calculate the radians from the direction because DivingAction want this...
        float movingRadians = (float) - (Math.atan2( moveDirection[1], moveDirection[0]));

        // Dive and seek for pearls
        return new DivingAction(1f, movingRadians);
    }

    /**
     * Returns a normalized vector
     * @param vector The array to normalize (Float)
     */
    private static void normalizeFloatArray(float[] vector) {

        // calculate the length of the vector with pythagoras
        float length = (float) Math.sqrt((vector[0] * vector[0]) + (vector[1] * vector[1]));

        vector[0] /= length;
        vector[1] /= length;
    }

    /**
     * Calculate the move direction for the diver
     * @param goal where you want to go
     * @param start your current position
     * @return move direction as float
     */
    private static float[] seek(float[] start, float[] goal) {

        float[] seekDirection = new float[2];

        seekDirection[0] = goal[0] - start[0];
        seekDirection[1] = goal[1] - start[1];

        normalizeFloatArray(seekDirection);

        return seekDirection;
    }

    /**
     * Calculate flee direction for the diver
     * @param goal where you want to flee from
     * @param start your current position
     * @return flee direction as float
     */
    private static float[] flee(float[] start, float[] goal) {

        float[] fleeDirection = new float[2];

        fleeDirection[0] = start[0] - goal[0];
        fleeDirection[1] = start[1] - goal[1];

        normalizeFloatArray(fleeDirection);

        return fleeDirection;
    }
}
