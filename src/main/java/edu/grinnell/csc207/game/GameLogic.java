package edu.grinnell.csc207.game;

public class GameLogic {
  // field that is the board
  // check if game is over
  // check if move is valid
  // gravity??
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  //* A board that represents the game status. */
  GameBoard board;

  //* The possible directions. */
  char UP = 'u';
  char DOWN = 'd';
  char LEFT = 'l';
  char RIGHT = 'r';

  // +--------------+-----------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Builds a new game logic.
   * @param board
   *    a GameBoard object to represent the game
   */
  public GameLogic (GameBoard board) {
    this.board = board;
  } // GameLogic

  // +---------+----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Checks if there are any valid moves left.
   * @return whether there are valid moves left
   */
  public boolean checkOver () {
    return false;
  } // checkOver()

  /**
   * Checks if a proposed move is legal.
   * @param row
   * @param col
   * @param direction
   * 
   * @return whether the proposed move is legal
   */
  public boolean isValidMove (int row, int col, char direction) {
    return false;
  } // checkMove(int, int, char)

  /**
   * Swaps a given piece with the piece in the given direction.
   * @param row
   * @param col
   * @param direction
   */
  public void swapPieces (int row, int col, char direction) {

  } // swapPieces(int, int, char)

  /**
   * Finds and removes the first row or column of 3 or more matching pieces in a row. 
   * @return 0 if no set was removed, 1 if a set was removed
   */
  public int removeSet () {
    return 0;
  } // removeSet()

  /**
   * Simulates gravity on the game board so that all pieces are bottom justified.
   */
  public void runGravity() {

  } // runGravity()
} // GameLogic
