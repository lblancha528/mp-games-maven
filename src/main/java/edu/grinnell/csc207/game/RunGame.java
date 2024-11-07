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

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** Direction up. */
  final char up = 'u';
  /** Direction down. */
  final char down = 'd';
  /** Direction left. */
  final char left = 'l';
  /** Direction right. */
  final char right = 'r';

  /**
   * Prints all insructions and invalid messages, takes in user input for set-up
   * and moves, prints board state and score.
   * @param args
   *   Command-line arguments, not used
   */
  public static void main(String[] args) {
    int height = 0;
    int width = 0;

    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);

    pen.print("""
                          Welcome to Single-Player Letter Drop

        To start, provide a width and height for the game board, with a minimum of 6.
        If a number smaller than 6 is provided, that dimension will default to 6.

        Each turn, select a piece by entering its column and row, and a direction to
        move it. The rows and columns are 0 indexed from the top left corner.
        The goal is to make sets of 3 in a row of matching pieces. These
        will be removed and all pieces will fall to the bottom of the board.

        Continue making sets until there are no more possible moves that would
        make a set.

        Your score is the amount of pieces *you* removed from the board.

          """);

    pen.println("Please enter number of rows you want for your board. Minimum height is 6.");
    height = Integer.parseInt(eyes.nextLine());
    pen.println("Please enter number of columns you want for your board. Minimum width is 6.");
    width = Integer.parseInt(eyes.nextLine());

    GameBoard board = new GameBoard(height, width);
    GameLogic logic = new GameLogic(board);

    logic.checkRemoveGravity();
    logic.board.printBoard();
    int startScore = logic.board.remainingPieces;

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
    int score = startScore - logic.board.remainingPieces;
    pen.println("GAME OVER \n Your Score: " + score);
    pen.close();
    eyes.close();
  } // main(String[])
} // RunGame()
