package edu.grinnell.csc207.game;

import java.io.PrintWriter;
import java.util.Random;

import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

/**
 * This class houses the game board matrix and the functions that interact with the matrix directly.
 *
 * @author Lily Blanchard
 * @author Natalie Nardone
 * @author Tiffany Yan
 */
public class GameBoard {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** A matrix to represent the board state. */
  MatrixV0<Character> board;

  /** Game piece X. */
  static Character pieceX = 'X';
  /** Game piece O. */
  static Character pieceO = 'O';
  /** Game piece T. */
  static Character pieceT = 'T';
  /** Empty space piece. */
  static Character space = ' ';

  /** The height of the board. */
  int height;

  /** The width of the board. */
  int width;

  /** Default dimensions for board. */
  static int defaultHeight = 6;
  /** Default dimensions for board. */
  static int defaultWidth = 6;

  /** How many game pieces are left on the board. */
  int remainingPieces = 0;

  // +--------------+-----------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new game board with default dimensions.
   */
  public GameBoard() {
    new GameBoard(defaultWidth, defaultHeight);
  } // GameBoard()

  /**
   * Constructs a prefab 4x4 board.
   * Created for debugging purposes, not used in real gameplay.
   * @param x any character
   */
  public GameBoard(char x) {
    this.width = 4;
    this.height = 4;
    this.board = new MatrixV0(width, height, ' ');
    board.set(0, 0, 'X');
    board.set(0, 1, 'X');
    board.set(0, 2, 'O');
    board.set(0, 3, 'O');
    board.set(1, 0, 'T');
    board.set(1, 1, 'O');
    board.set(1, 2, 'X');
    board.set(1, 3, 'T');
    board.set(2, 0, 'T');
    board.set(2, 1, 'X');
    board.set(2, 2, 'O');
    board.set(2, 3, 'X');
    board.set(3, 0, 'T');
    board.set(3, 1, 'T');
    board.set(3, 2, 'T');
    board.set(3, 3, 'X');
    this.remainingPieces = this.height * this.width;
  } // GameBoard(char)

  /**
   * Constructs a new game board with user provided dimensions.
   *
   * @param h the provided height of the game board
   * @param w the provided width of the game board
   */
  public GameBoard(int h, int w) {
    if (w < 6 || h < 6) {
      new GameBoard();
    } else {
      this.board = new MatrixV0(w, h, ' ');
      this.height = h;
      this.width = w;
      this.remainingPieces = this.height * this.width;
      // fill out the board with the three elements randomly.
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          board.set(i, j, generateRandomPiece());
        } // for
      } // for
    } // if
  } // GameBoard(int, int)

  // +---------+----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Generates a random game piece.
   *
   * @return a random game piece
   */
  public static char generateRandomPiece() {
    // generates a random number 1, 2, 3
    // returns the corresponding game piece char
    Random rand = new Random();

    int random = rand.nextInt(3);
    if (random == 0) {
      return pieceX;
    } else if (random == 1) {
      return pieceT;
    } // if
    return pieceO;
  } // generateRandomPiece()

  /**
   * The width.
   *
   * @return the width
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * The height.
   *
   * @return the height
   */
  public int height() {
    return this.height;
  } // height()


  /**
   * Sets the specified value from the board.
   *
   * @param row
   *   the row of the set piece
   * @param col
   *   the column of the set piece
   * @param val
   *   the value to be set
   */
  public void set(int row, int col, char val) {
    this.board.set(row, col, val);
  } // set

  /**
   * Gets the value corresponding to the specified row and col.
   *
   * @param row
   *   the row of the piece to be looked up
   * @param col
   *   the column of the piece to be looked up
   * @return the value at the index
   */
  public char get(int row, int col) {
    return (char) this.board.get(row, col);
  } // get

  /**
   * Prints the current game board status.
   */
  public void printBoard() {
    Matrix.print(new PrintWriter(System.out, true), this.board, true);
  } // printBoard()
} // class GameBoard
