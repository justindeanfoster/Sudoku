import java.util.*;

public class Board{
    int[][][] grid;
    final int SET_NUM_INDEX = 1;    // this is to determine whether or not the number is preset or not
    final int NUM_INDEX = 0;        // this is where the actual numbers will go
    public Board(){
        grid = new int[9][9][2];
    }
    public Board(int[][] board){
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
                if(!horizontalSet.add(grid[x][y][NUM_INDEX]))
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
                if(!horizontalSet.add(grid[y][x][NUM_INDEX]))
                    return false;
            }
            horizontalSet.clear();
        }
        return true;
    }


    public boolean checkThreeByThree(){
        Set<Integer> verticalSet = new HashSet<Integer>();
        for(int x = 0; x < grid.length; x++ ){
            for( int y = 0; y < grid[0].length; y++){
                if(!verticalSet.add(grid[y][x][NUM_INDEX]))
                    return false;
            }
            verticalSet.clear();
        }
        return true;
    }

    public void printBoard(){
        for(int x = 0; x < grid.length; x++ ){
            for( int y = 0; y < grid[0].length; y++){
                System.out.print(grid[x][y][NUM_INDEX] + " ");
            }
            System.out.println();
        }
    }      
}