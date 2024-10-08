package automatastarter;



/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.*;

/**
 *
 * @author Raven
 */
public class automataSim {
    //sets up initialization variables

    public int gridRows;
    public int gridColumns;


    public int preyVal;
    public int[][] simulationGrid;
    public int[][] printGrid;
    public final int[] rowMove = {0, -1, 0, 1};
    public final int[] colMove = {1, 0, -1, 0};
    public boolean[][] alreadyMoved;
    
    public int numPrey;
    public int numPred;
    
    
    //inits according to template options
    public automataSim(int rowNum, int colNum, int preyValue, int pattern) {
        gridRows = rowNum;
        gridColumns = colNum;
        preyVal = preyValue;
        simulationGrid = new int[gridRows][gridColumns];
        printGrid = new int[gridRows][gridColumns];
        alreadyMoved = new boolean[gridRows][gridColumns];
        
        //generates checkerboard pattern
        if(pattern == 0){
            boolean isPred = true;
            for(int i = 0; i < rowNum; i++){
                if(rowNum % 2 == 0){
                    isPred = !isPred;
                }
                for(int j = 0; j < colNum; j++){
                    simulationGrid[i][j] = isPred ? preyVal - 1 : preyVal;
                    isPred = !isPred;
                    numPred = isPred ? (numPred+1) : numPred;
                    numPrey = isPred ? numPrey : (numPrey +1);
                }
            }
        }
        
        //generates lines
        else if(pattern == 1){
           boolean isPred = true;
            for(int i = 0; i < rowNum; i++){
                isPred = !isPred;
               for(int j = 0; j < colNum; j++){
                   simulationGrid[i][j] = isPred ? preyVal - 1 : preyVal;
                    numPred = isPred ? (numPred+1) : numPred;
                    numPrey = isPred ? numPrey : (numPrey +1);
               }
           }
        }
        

        //generates four corners
        else if(pattern == 2){
            for(int i = 0; i < rowNum; i++){
               for(int j = 0; j < colNum; j++){
                   simulationGrid[i][j] = preyVal;
                   numPrey++;
               }
           }
            
           simulationGrid[0][0] = preyVal-1;
           simulationGrid[rowNum - 1][0] = preyVal-1;
           simulationGrid[rowNum - 1][colNum - 1] = preyVal-1;
           simulationGrid[0][colNum - 1] = preyVal-1;
           numPrey -= 4;
           numPred += 4;
        }
    }

    //inits the sim
    public automataSim(int rowNum, int colNum, int preyNum, int predNum, int preyValue) {
        // 0 - empty
        //1 to 20 - predator (start out at 20. decreases by 1 until dead. if 11-20, has chance to repro)
        //21 - prey
        //sets up other important stuff

        gridRows = rowNum;
        gridColumns = colNum;
        preyVal = preyValue;
        simulationGrid = new int[gridRows][gridColumns];
        printGrid = new int[gridRows][gridColumns];
        alreadyMoved = new boolean[gridRows][gridColumns];
        
        numPred = predNum;
        numPrey = preyNum;
        int rowCoord;
        int colCoord;

        Random r = new Random();

        //generates required num of prey
        for (int i = 0; i < preyNum; i++) {

            //generates random coord while current coord is not empty
            do {
                rowCoord = r.nextInt(gridRows);
                colCoord = r.nextInt(gridColumns);

            } while (simulationGrid[rowCoord][colCoord] != 0);

            //sets coord as prey
            simulationGrid[rowCoord][colCoord] = preyVal;
        }

        //generates number of preds
        for (int i = 0; i < predNum; i++) {
            //generates random coord hile current coord is not empty
            do {
                rowCoord = r.nextInt(gridRows);
                colCoord = r.nextInt(gridColumns);

            } while (simulationGrid[rowCoord][colCoord] != 0);

            //sets coord as pred
            simulationGrid[rowCoord][colCoord] = preyVal - 1;

        }

    }

    /**
     * turns simulationGrid into a neat one for printing
     *
     */
    public void gridTransform() {

        //iterates through grid
        for (int rowCoord = 0; rowCoord < gridRows; rowCoord++) {
            for (int columnCoord = 0; columnCoord < gridColumns; columnCoord++) {

                //if simulation grid has prey value, maps it to 2 on the print array (1 for pred, 0 for empty)
                if (simulationGrid[rowCoord][columnCoord] == preyVal) {
                    printGrid[rowCoord][columnCoord] = 2;
                } else if (simulationGrid[rowCoord][columnCoord] > 0) {
                    printGrid[rowCoord][columnCoord] = 1;
                }
            }
        }

    }

    /**
     * prints out all items in 2d array simulationGrid
     *
     * @param grid array to print
     */
    public void printArray(int[][] grid) {

        //interates through and prints elements of grid
        for (int[] row : grid) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            //adds a new line after each row
            System.out.println();
        }
    }

    /**
     * generates list of a coordinate's adjacent neighbours
     *
     * @param rowCoord row of coordinate to be checked
     * @param columnCoord col of coordinate to be checked
     * @return array of coordinate's neighbours
     */
    public int[] neighbourCheck(int rowCoord, int columnCoord) {

        //sets up main variables
        int[] neighbours = new int[4];

        for (int x = 0; x < 4; x++) {
            //checks if it's pred or prey and adds to neighbours array
            neighbours[x] = statusCheck(reValueRow(rowCoord + rowMove[x]), reValueCol(columnCoord + colMove[x]));

        }

        return neighbours;

    }

    /**
     * returns whether coordinate is pred/prey/empty
     *
     * @param row row coord to be checked
     * @param col col coord to be checked
     * @return 0 if empty, 1 if pred, and 2 if prey
     */
    public int statusCheck(int row, int col) {

        //while coords are out of bounds, changes it to be within bounds
        row = reValueRow(row);
        col = reValueCol(col);

        //grabs value from coords
        int stat = simulationGrid[row][col];

        //checks value and returns matching int
        if (stat == preyVal) {
            return 2;
        } else if (stat == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    /**
     * wraps around col coord
     *
     * @param col
     * @return
     */
    public int reValueCol(int col) {
        int colLength = simulationGrid[0].length;
        while (!(col >= 0 && col < colLength)) {
            if (col < 0) {
                col += colLength;
            } else if (col >= colLength) {
                col = col - colLength - 1;

            }
        }
        return col;
    }

    /**
     * wraps around row coord
     *
     * @param row
     * @return
     */
    public int reValueRow(int row) {
        int rowLength = simulationGrid.length;
        while (!(row >= 0 && row < rowLength)) {
            if (row < 0) {
                row += rowLength;
            } else if (row >= rowLength) {
                row = row - rowLength - 1;
            }

        }
        return row;

    }

    /**
     * checks and rolls for a pred reproduction
     *
     * @param currentVal current pred health
     * @param predNeighbours number of pred neighbours
     * @param emptyNeighbours number of empty neighbours
     * @return true if reproduction succeeded
     */
    public boolean predRepro(int currentVal, int predNeighbours, int emptyNeighbours) {
        Random r = new Random();
        return currentVal > 10 && predNeighbours >= 1 && emptyNeighbours >= 1 && r.nextInt(20) == 0;
    }

    /**
     * checks and rolls for a prey reproduction
     *
     * @param preyNeighbours number of prey neighbours
     * @param emptyNeighbours number of empty neighbours
     * @return true if reproduction succeeded
     */
    public boolean preyRepro(int preyNeighbours, int emptyNeighbours) {
        Random r = new Random();
        return preyNeighbours >= 1 && emptyNeighbours >= 1 && r.nextInt(10) == 0;
    }

    /**
     * chooses random number. returns 0 if limit is 0
     *
     * @param randLimit upper bound (excl)
     * @return random index
     */
    public int randIndexer(int randLimit) {
        Random r = new Random();
        int randIndex;
        try {
            randIndex = r.nextInt(randLimit);
        } catch (IllegalArgumentException e) {
            randIndex = 0;
        }
        return randIndex;
    }

    
    public void step(){
        //resets vars
        numPred = 0;
        numPrey = 0;
            for (boolean[] row :alreadyMoved) {
                Arrays.fill(row, false);
            }

            //iterates through every possible item 
            for (int row = 0; row < gridRows; row++) {
                for (int col = 0; col < gridColumns; col++) {
                    move(row, col);

                }
            }
    }
    public void move(int row, int col) {
        
        if (alreadyMoved[row][col] == false) {

            //checks status and skips if coord is empty
            int status = statusCheck(row, col);
            if (status != 0) {

                //grabs neighbours and stores in nice variables
                int[] neighbours = neighbourCheck(row, col);
                //iterate through. 2 arrays one with jsut the values one with indexes
                ArrayList<Integer> emptyIndexes = new ArrayList<>();
                ArrayList<Integer> predIndexes = new ArrayList<>();
                ArrayList<Integer> preyIndexes = new ArrayList<>();
                int emptyCount = 0;
                int preyCount = 0;
                int predCount = 0;

                for (int z = 0; z < neighbours.length; z++) {
                    //grabs current neighbour
                    int currentNeighbour = neighbours[z];

                    //based on neighbour's value
                    //adds to count of empty/prey/preds
                    //saves the index in the neighbours array to another array 
                    switch (currentNeighbour) {
                        case 0 -> {
                            emptyCount++;
                            emptyIndexes.add(z);
                        }
                        case 1 -> {
                            predCount++;
                            predIndexes.add(z);
                        }
                        default -> {
                            preyCount++;
                            preyIndexes.add(z);
                        }

                    }
                }

                int randIndex;

                //if prey
                if (status == 2) {
                    
                    //checks if prey starves
                    if (emptyCount == 0) {
                        simulationGrid[row][col] = 0;

                    } else {
                        numPrey++;

                        //grabs rando index from emptyindexes and moves to random empty spot
                        randIndex = randIndexer(emptyCount);
                        int newRowCoord = reValueRow(row + rowMove[emptyIndexes.get(randIndex)]);
                        int newColCoord = reValueCol(col + colMove[emptyIndexes.get(randIndex)]);
                        simulationGrid[newRowCoord][newColCoord] = preyVal;

                        //rolls for reproduction
                        if (preyRepro(preyCount, emptyCount)) {
                            numPrey++;
                        } else {
                            //overwrites current index if no child
                            simulationGrid[row][col] = 0;

                        }

                        alreadyMoved[newRowCoord][newColCoord] = true;

                    }
                    alreadyMoved[row][col] = true;

                } //pred scenario
                else {
                    numPred++;
                    int currentVal = simulationGrid[row][col];
                    System.out.println("Predator at (" + row + ", " + col + ") has health: " + currentVal);


                    //checks if prey is around to eat
                    if (preyCount > 0) {

                        randIndex = randIndexer(preyCount);

                        //moves to prey's spot and deletes old coord value
                        int newRowCoord = reValueRow(row + rowMove[preyIndexes.get(randIndex)]);
                        int newColCoord = reValueCol(col + colMove[preyIndexes.get(randIndex)]);
                        simulationGrid[newRowCoord][newColCoord] = preyVal - 1;
                        simulationGrid[row][col] = 0;
                        alreadyMoved[newRowCoord][newColCoord] = true;
                        System.out.println("ate a prey");
                    } 
                    //removes an hp from predll
                    else if (emptyCount == 0) {
                        if(currentVal == 1){
                            numPred -=1;
                        }
                        simulationGrid[row][col] = currentVal - 1;
                    } else {

                        //moves to random empty spot
                        randIndex = randIndexer(emptyCount);
                        int newRowCoord = reValueRow(row + rowMove[emptyIndexes.get(randIndex)]);
                        int newColCoord = reValueCol(col + colMove[emptyIndexes.get(randIndex)]);
                        //takes a food away from current value
                        simulationGrid[newRowCoord][newColCoord] = currentVal - 1;

                        //checks and rolls for reproduction
                        if (predRepro(currentVal, predCount, emptyCount)) {
                            simulationGrid[newRowCoord][newColCoord] = preyVal - 1;
                            numPred++;

                        } else {
                            simulationGrid[row][col] = 0;
                        }

                        alreadyMoved[newRowCoord][newColCoord] = true;
                    }

                    alreadyMoved[row][col] = true;

                }

            }
        }

    }

}
