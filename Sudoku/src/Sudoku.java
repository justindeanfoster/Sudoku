import java.util.Scanner;

public class Sudoku{
    public static void main(String [ ] args)
    {
      int[][] grid_array ={ {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
      {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
      {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
      {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
      {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
      {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
      {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
      {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
      {0, 0, 5, 2, 0, 6, 3, 0, 0} };
      int[][] grid_sol ={ {3, 1, 6, 5, 7, 8, 4, 9, 2},
        {5, 2, 9, 1, 3, 4, 7, 6, 8},
        {4, 8, 7, 6, 2, 9, 5, 3, 1},
        {2, 6, 3, 4, 1, 5, 9, 8, 7},
        {9, 7, 4, 8, 6, 3, 1, 2, 5},
        {8, 5, 1, 7, 9, 2, 6, 4, 3},
        {1, 3, 8, 9, 4, 7, 2, 5, 6},
        {6, 9, 2, 3, 5, 1, 8, 7, 4},
        {7, 4, 5, 2, 8, 6, 3, 1, 9} };
      Board grid = new Board(grid_array);
      Scanner in = new Scanner(System.in);
      int selection;
      do{
        System.out.println("Choose: \n1. Play a Game\n2.Sudoku Solver\n3. Quit");
        selection = in.nextInt();
        switch(selection){
          case 1: 
            do{
              System.out.println("Make a guess, enter coordinates X then Y and then your number");
              grid.makeGuess(in.nextInt(), in.nextInt(), in.nextInt());
              grid.printBoard();
            }while(!grid.winScenario());
            break;
          case 2: 
                grid.printBoard();
                grid.solveBoard(0, 0);
                grid.printBoard();
              break;
          case 3: break;
        }
        
      }while(selection != 3);
      grid.printBoard();
      in.close();
      System.out.println("WUS GOOD MF");
    }

    
}
