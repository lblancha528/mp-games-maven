package edu.grinnell.csc207.game;

/**
 * This class handles the user interface and interactions.
 * @author Lily Blanchard
 * @author Natalie Nardone
 * @author Tiffany Yan
 */
public class RunGame {
  // main
  // build board
  // logic takes a board as param, saves as field
  // build logic that builds -> with config info
  // handles user input -> uses gameLogic to handle user commands

  // space is null character for removed pieces

  /* 
  while checkOver = false {
    get user input 
    do move
    for {
      do (remove)
      while (remove = 1 ie did remove something)
      do gravity
    }
  }
    */
public void main() {
  GameBoard newBoard = new GameBoard(6, 8);
  newBoard.printBoard();
}
  
}
