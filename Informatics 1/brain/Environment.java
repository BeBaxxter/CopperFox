import java.security.SecureRandom;
import java.util.*;

/**
 * Maintain the environment for a 2D cellular automaton.
 * 
 * @author David J. Barnes
 * @version  2016.02.29
 */
public class Environment
{
    // Default size for the environment.
    private static final int DEFAULT_ROWS = 50;
    private static final int DEFAULT_COLS = 50;
    
    // The grid of cells.
    private Cell[][] cells;
    // Visualization of the environment.
    private final EnvironmentView view;

    /**
     * Create an environment with the default size.
     */
    public Environment()
    {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    /**
     * Create an environment with the given size.
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    public Environment(int numRows, int numCols)
    {
        setup(numRows, numCols);    
      
        // randomize();

        //call pattern
        initiateNewToad();
        initiateNewGlider();
        initiateNewHeavyShip();
        
        view = new EnvironmentView(this, numRows, numCols);
        view.showCells();
    }
    
    /**
     * Run the automaton for one step.
     */
    public void step()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        // Build a record of the next state of each cell.
        int[][] nextStates = new int[numRows][numCols];
        // Ask each cell to determine its next state.
        for(int row = 0; row < numRows; row++) {
            int[] rowOfStates = nextStates[row];
            for(int col = 0; col < numCols; col++) {
                rowOfStates[col] = cells[row][col].getNextState();
            }
        }
        // Update the cells' states.
        for(int row = 0; row < numRows; row++) {
            int[] rowOfStates = nextStates[row];
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, rowOfStates[col]);
            }
        }
    }
    
    /**
     * Reset the state of the automaton to all DEAD.
     */
    public void reset()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, Cell.DEAD);
            }
        }
    }
    
    /**
     * Generate a random setup.
     */
    public void randomize()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        SecureRandom rand = new SecureRandom();
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(row, col, rand.nextInt(Cell.NUM_STATES));
            }
        }
    }
    
    /**
     * Set the state of one cell.
     * @param row The cell's row.
     * @param col The cell's col.
     * @param state The cell's state.
     */
    public void setCellState(int row, int col, int state)
    {
        cells[row][col].setState(state);
    }
    
    /**
     * Return the grid of cells.
     * @return The grid of cells.
     */
    public Cell[][] getCells()
    {
        return cells;
    }
    
    /**
     * Setup a new environment of the given size.
     * @param numRows The number of rows.
     * @param numCols The number of cols;
     */
    private void setup(int numRows, int numCols)
    {
        cells = new Cell[numRows][numCols];
        for(int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell();
            }
        }
        setupNeighbors();
    }
    
    /**
     * Give to a cell a list of its neighbors.
     */
    private void setupNeighbors()
    {
        int numRows = cells.length;
        int numCols = cells[0].length;
        
        // Allow for 8 neighbors plus the cell.
        ArrayList<Cell> neighbors = new ArrayList<>(5);
        
        //Calculate the absolute coordinates of the field
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
               
                Cell cell = cells[row][col];
                
                //Calculate the relative coordinates of the neighbors depends on the cell
                for(int dr = -1; dr <= 1; dr++) {
                    for(int dc = -1; dc <= 1; dc++) {
                        
                        //Calculate the absolute coordinates of the neighbors
                        int nr = row + dr;
                        int nc = col + dc;
                        
                        //If Col or Row is outside, create the neighbors as a dead cell
                        if(nr <= -1 || nr >= numRows || nc <= -1 || nc >= numCols) {
                            neighbors.add(new Cell(Cell.DEAD));
                        } else {
                            neighbors.add(cells[nr][nc]);
                        }
                    }
                }
                // The neighbours should not include the cell at
                // (row,col) so remove it.
                neighbors.remove(cell);
                cell.setNeighbors(neighbors);
                neighbors.clear();
            }
        }
    }
    
    private void initiateNewToad(){
        int numRows = cells.length;
        int numCols = cells[0].length;

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(10, 11, Cell.ALIVE);
                setCellState(10, 12, Cell.ALIVE);
                setCellState(10, 13, Cell.ALIVE);
                setCellState(11, 12, Cell.ALIVE);
                setCellState(11, 13, Cell.ALIVE);
                setCellState(11, 14, Cell.ALIVE);
            }
        }
        
    }   
    
    private void initiateNewGlider(){
        int numRows = cells.length;
        int numCols = cells[0].length;

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(20, 31, Cell.ALIVE);
                setCellState(20, 33, Cell.ALIVE);
                setCellState(21, 32, Cell.ALIVE);
                setCellState(21, 33, Cell.ALIVE);
                setCellState(22, 32, Cell.ALIVE);
            }
        }
        
    }    
    
    private void initiateNewHeavyShip(){
        int numRows = cells.length;
        int numCols = cells[0].length;

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                setCellState(40, 14, Cell.ALIVE);
                setCellState(40, 15, Cell.ALIVE);
                setCellState(41, 12, Cell.ALIVE);
                setCellState(41, 17, Cell.ALIVE);
                setCellState(42, 11, Cell.ALIVE);
                setCellState(43, 11, Cell.ALIVE);
                setCellState(43, 17, Cell.ALIVE);
                setCellState(44, 11, Cell.ALIVE);
                setCellState(44, 12, Cell.ALIVE);
                setCellState(44, 13, Cell.ALIVE);
                setCellState(44, 14, Cell.ALIVE);
                setCellState(44, 15, Cell.ALIVE);
                setCellState(44, 16, Cell.ALIVE);
                
            }
        }
        
    }      
}
