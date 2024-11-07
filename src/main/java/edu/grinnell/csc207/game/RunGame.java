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
  int height = 0;
  int width = 0;

  PrintWriter pen = new PrintWriter(System.out, true);
  Scanner eyes = new Scanner(System.in);

  pen.print("Intro and Instruction here. /n");

  pen.println("Please enter number of rows you want for your board. Minimum height is 6.");
  height = Integer.parseInt(eyes.nextLine());
  pen.println("Please enter number of columns you want for your board. Minimum width is 6.");
  width = Integer.parseInt(eyes.nextLine());

  GameBoard board = new GameBoard(height, width);
  GameLogic logic = new GameLogic(board);

  //logic.checkRemoveGravity();
  board.printBoard();

  //while (!logic.checkOver()) {
    char direction = '\0';
    int col = 0;
    int row = 0;

    pen.println("Please enter col of you desired change.");
    col = Integer.parseInt(eyes.nextLine());

    pen.println("Please enter row of you desired change.");
    row = Integer.parseInt(eyes.nextLine());

    pen.println("Please enter direction you want to move to. Valid directions are u, d, l, r.");
    direction = eyes.nextLine().charAt(0);

    if (logic.isValidMove(row, col, direction)) {
      logic.swapPieces(row, col, direction);
      logic.checkRemoveGravity();
      logic.board.printBoard();
    } else {
      pen.println("Invalid move, please try again.");
    } // if
  //} // while
  pen.close();
  eyes.close();
} // main(String[])


} // RunGame()
