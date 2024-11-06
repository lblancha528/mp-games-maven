package edu.grinnell.csc207.game;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class handles the user interface and interactions.
 * @author Lily Blanchard
 * @author Natalie Nardone
 * @author Tiffany Yan
 */
public class RunGame {

    //* The possible directions. */
    final char UP = 'u';
    final char DOWN = 'd';
    final char LEFT = 'l';
    final char RIGHT = 'r';

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
public static void main(String args[]) {
  GameBoard newBoard = new GameBoard(6, 8);
  newBoard.printBoard();
  int height = 0;
  int width = 0;

  PrintWriter pen = new PrintWriter(System.out, true);
  Scanner eyes = new Scanner(System.in);
  // print directions
  // prompt for height of board
  height = eyes;
  // prompt for width
  width = eyes;
  // build board and logic
  GameBoard board = new GameBoard(height, width);
  GameLogic logic = new GameLogic(board);

  logic.checkRemoveGravity();

  while (!logic.checkOver()) {
    char direction = '\0';
    int col = 0;
    int row = 0;
    //   prompt for col of desired piece
    col = eyes;
    // prompt for row
    row = eyes;
    //   prompt for direction to move
    direction = eyes;
    if (logic.isValidMove(row, col, direction)) {
      logic.swapPieces(row, col, direction);
      logic.checkRemoveGravity();
      logic.board.printBoard();
    } else {
      pen.println("Invalid move, please try again.");
    } // if
  } // while
} // main(String[])


} // RunGame()
