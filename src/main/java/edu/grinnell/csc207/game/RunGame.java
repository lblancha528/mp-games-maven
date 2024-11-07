package edu.grinnell.csc207.game;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class handles the user interface and interactions.
 * 
 * @author Lily Blanchard
 * @author Natalie Nardone
 * @author Tiffany Yan
 */
public class RunGame {

  // * The possible directions. */
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
   * while checkOver = false { get user input do move for { do (remove) while (remove = 1 ie did
   * remove something) do gravity } }
   */
  public static void main(String args[]) {
    int height = 0;
    int width = 0;

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    pen.print("""
        Welcome to Letter Drop, single player

        To start, provide a width and height for the game board, with a minimum of 6.

        Each turn, select a piece by entering its column and row, and a direction to move it.
        The goal is to make sets of 3 in a row of matching pieces. These will be removed
        and all pieces will fall to the bottom of the board.

        Continue making sets until there are no more possible moves that would make a set.

        Your score is the amount of pieces you removed from the board.

          """);

    pen.println("Please enter number of rows you want for your board. Minimum height is 6.");
    height = Integer.parseInt(eyes.nextLine());
    pen.println("Please enter number of columns you want for your board. Minimum width is 6.");
    width = Integer.parseInt(eyes.nextLine());

    GameBoard board = new GameBoard(height, width);
    // GameBoard board = new GameBoard('a');
    GameLogic logic = new GameLogic(board);

    logic.checkRemoveGravity();
    logic.board.printBoard();

    while (!logic.checkOver()) {
      char direction = '\0';
      int col = 0;
      int row = 0;

      pen.println("Please enter col of your desired piece.");
      col = Integer.parseInt(eyes.nextLine());

      pen.println("Please enter row of your desired piece.");
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
    } // while
    int score = (logic.board.height() * logic.board.width()) - logic.board.remainingPieces;
    pen.println("GAME OVER \n Your Score: " + score);
    pen.close();
    eyes.close();
  } // main(String[])


} // RunGame()
