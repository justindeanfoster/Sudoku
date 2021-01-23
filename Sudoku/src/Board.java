import java.util.*;

public class Board{
    int[][][] grid;
    final int SET_NUM_INDEX = 1;    // this is to determine whether or not the number is preset or not
    final int NUM_INDEX = 0;        // this is where the actual numbers will go
    public Board(){
        grid = new int[9][9][2];
    }
    public Board(int[][] board){
        grid = new int[9][9][2];
        for( int x = 0; x < board.length; x++){
            for(int y = 0; y < board[0].length; y++){
                if(board[x][y] != 0){
                    grid[x][y][SET_NUM_INDEX] = 1;
                    grid[x][y][NUM_INDEX] = board[x][y];

                }
            }
        }
    }

    /* This method takes a coordinate point and makes 
    the guess. Returns true or false whether its a valid move*/
    public boolean makeGuess(int x, int y, int guess){
        if(grid[x][y][SET_NUM_INDEX] == 1 || guess < 1 || guess > 9){
            return false;
        }
        grid[x][y][NUM_INDEX]  = guess;
        return true;
    }

    public boolean winScenario(){
        
        return checkHorizontal() && checkVertical() && checkThreeByThree();
    }

    /* because in make guess, its limited to 1-9, 
    if all numbers are unique, then it should be
    in the range 1-9*/
    public boolean checkHorizontal(){
        Set<Integer> horizontalSet = new HashSet<Integer>();
        for(int x = 0; x < grid.length; x++ ){
            for( int y = 0; y < grid[0].length; y++){
                if(!horizontalSet.add(grid[x][y][NUM_INDEX]) || grid[x][y][NUM_INDEX] == 0)
                    return false;
            }
            horizontalSet.clear();
        }
        return true;
    }

    /*This is the same function as checkHorizontal but
    the coordinates are flipped allowing for tracing of the 
    vertical rather than horizontal */
    public boolean checkVertical(){
        Set<Integer> horizontalSet = new HashSet<Integer>();
        for(int x = 0; x < grid.length; x++ ){
            for( int y = 0; y < grid[0].length; y++){
                if(!horizontalSet.add(grid[y][x][NUM_INDEX]) 
                                || grid[x][y][NUM_INDEX] == 0)
                    return false;
            }
            horizontalSet.clear();
        }
        return true;
    }


    public boolean checkThreeByThree(){
        Set<Integer> threeByThreeSet = new HashSet<Integer>();
        int[][] coords = {{0,0},{0,3},{0,6},
                          {3,0},{3,3},{3,6},
                          {6,0},{6,3},{6,6}};
        for(int i = 0; i < coords.length; i++){
            for(int x = 0; x < 3; x++ ){
                for( int y = 0; y < 3; y++){
                if(!threeByThreeSet.add(grid[coords[i][0] + x][coords[i][1] + y][NUM_INDEX])
                                        || grid[coords[i][0] + x][coords[i][1] + y][NUM_INDEX] == 0)
                    return false;
                }
                threeByThreeSet.clear();
            }
        }
        return true;
    }

    public boolean solveBoard(int r, int c){
        if( c == 0 && r == 9){
            //success case
            return true;
        }else if( c == 9){
            //end column case
            return solveBoard(r + 1, 0);
        }else if(grid[r][c][SET_NUM_INDEX] == 1){
            //preset number case
            return solveBoard(r, c + 1);
        }else{
            int[] poss = {1,2,3,4,5,6,7,8,9};
            boolean foundSol = false;
            for(int i = 0; i < poss.length; i++){
                if(checkPossibleGuess(r,c,poss[i])){
                    grid[r][c][NUM_INDEX] = poss[i];
                    
                    if(foundSol = solveBoard(r, c + 1)){
                        break;
                    }else{
                        grid[r][c][NUM_INDEX] = 0;
                    }
                }
            }
            return foundSol;
        }
        
    }

    private boolean checkPossibleGuess(int r, int c, int guess){
        //row
        for(int c_i = 0; c_i < grid.length; c_i++){
            if(grid[r][c_i][NUM_INDEX] == guess){
                return false;
            }
        }
        //column
        for(int r_i = 0; r_i < grid.length; r_i++){
            if(grid[r_i][c][NUM_INDEX] == guess){
                return false;
            }
        }
        //3x3
        for(int r_i = 0; r_i < 3; r_i++){
            for(int c_i = 0; c_i < 3; c_i++){
                if(grid[r_i + 3*(r/3)][c_i + 3*(c/3)][NUM_INDEX] == guess)
                    return false;
            }
        }
        return true;
    }

    public void printBoard(){
        System.out.println("\n=================================================");
        for(int x = 0; x < grid.length; x++ ){
            for( int y = 0; y < grid[0].length; y++){
                if(y == 0)
                    System.out.print("|   ");
                System.out.print(grid[x][y][NUM_INDEX] + "   ");
                if(((y + 1) % 3) == 0 ){
                    System.out.print("|   ");
                }
                
            }
            if(((x + 1) % 3) == 0){
                System.out.println("\n=================================================");
            }else
                System.out.println();
        }
    }      
}
