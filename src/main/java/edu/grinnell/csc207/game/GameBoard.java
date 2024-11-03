package edu.grinnell.csc207.game;
import edu.grinnell.csc207.util.MatrixV0;

public class GameBoard {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** A matrix to represent the board state. */
  MatrixV0 board;

  static char pieceX = 'X';
  static char pieceO = 'O';
  static char pieceT = 'T';

  /** The height of the board. */
  int height;

  /** The width of the board. */
  int width;

  /** Default dimensions for board. */
  static int DEFAULT_HEIGHT = 6;
  static int DEFAULT_WIDTH = 6;

  // +--------------+-----------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new game board with default dimensions.
   */
  public GameBoard () {

  } // GameBoard()

  /**
   * Constructs a new game board with user provided dimensions.
   * @pre Minimum height or width of 4.
   * 
   * @param height
   *   the provided height of the game board
   * @param width
   *   the provided width of the game board
   */
  public GameBoard (int height, int width) {

  } // GameBoard(int, int)

  // +---------+----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Generates a random game piece.
   * @return a random game piece
   */
  public static char generateRandomPiece () {
  // generates a random number 1, 2, 3
  // returns the corresponding game piece char
  return 'a';
  } // generateRandomPiece()

  /**
   * The width.
   * @return the width
   */
  public int width () {
    return this.width;
  } // width()

  /**
   * The height.
   * @return the height
   */
  public int height () {
    return this.height;
  } // height()
} // class GameBoard
