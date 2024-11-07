package edu.grinnell.csc207.game;

/**
 * This class handles the game board status effects including checking when the game is over,
 * checking if a move is valid, and executing that move.
 *
 * @author Lily Blanchard
 * @author Natalie Nardone
 * @author Tiffany Yan
 */
public class GameLogic {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** A board that represents the game status. */
  GameBoard board;

  /** Direction up. */
  final char up = 'u';
  /** Direction down. */
  final char down = 'd';
  /** Direction left. */
  final char left = 'l';
  /** Direction right. */
  final char right = 'r';

  // +--------------+-----------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Builds a new game logic.
   *
   * @param boardInit
   *   a GameBoard object to represent the game
   */
  public GameLogic(GameBoard boardInit) {
    this.board = boardInit;
  } // GameLogic

  // +---------+----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Checks if there are any valid moves left.
   *
   * @return whether there are valid moves left
   */
  public boolean checkOver() {
    char[] directions = {up, down, left, right};
    for (int i = 0; i < board.height(); i++) {
      for (int j = 0; j < board.width(); j++) {
        if (this.board.get(i, j) != ' ') {
          for (int k = 0; k < directions.length; k++) {
            if (this.isValidMove(i, j, directions[k])) {
              return false;
            } // if
          } // for
        } // if
      } // for
    } // for
    return true;
  } // checkOver()

  /**
   * Checks if a proposed move is legal.
   *
   * @param row
   *   the row of the piece to be moved
   * @param col
   *   the column of the piece to be moved
   * @param direction
   *   the direction said piece will move
   *
   * @return whether the proposed move is legal
   */
  public boolean isValidMove(int row, int col, char direction) {
    if (((row == 0) && (direction == up)) || ((row == board.height() - 1) && (direction == down))
        || ((col == 0) && (direction == left))
        || ((col == board.width() - 1) && (direction == right)) || ((row < 0))
        || ((row >= board.height())) || ((col < 0)) || ((col >= board.width()))) {
      return false;
    } else {
      // make helper method
      char first = board.get(row, col);
      int newRow = 0;
      int newCol = 0;
      switch (direction) {
        case up: newCol = col; newRow = row - 1; break;
        case down: newCol = col; newRow = row + 1; break;
        case left: newRow = row; newCol = col - 1; break;
        case right: newRow = row; newCol = col + 1; break;
        default: break;
      } // switch
      // temporarily swap pieces.
      this.swapPieces(row, col, direction);

      // if 1+ from top and bottom, do check single up and down
      if ((newRow >= 1) && (newRow < this.board.height() - 1)) {
        if ((board.get(newRow + 1, newCol) == first) && (board.get(newRow - 1, newCol) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if
      // if at least 2 from top, check double up
      if (newRow >= 2) {
        if ((board.get(newRow - 1, newCol) == first) && (board.get(newRow - 2, newCol) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if
      // if 2+ from bottom, check double down
      if (newRow < this.board.height() - 2) {
        if ((board.get(newRow + 1, newCol) == first) && (board.get(newRow + 2, newCol) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if
      // if 1+ from left and right, check single left and right
      if ((newCol >= 1) && (newCol < this.board.width() - 1)) {
        if ((board.get(newRow, newCol + 1) == first) && (board.get(newRow, newCol - 1) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if
      // if 2+ from left, check double left
      if (newCol >= 2) {
        if ((board.get(newRow, newCol - 1) == first) && (board.get(newRow, newCol - 2) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if
      // if 2+ from right, check double right
      if (newCol < this.board.width() - 2) {
        if ((board.get(newRow, newCol + 1) == first) && (board.get(newRow, newCol + 2) == first)) {
          this.swapPieces(row, col, direction);
          return true;
        } // if
      } // if

      // swap back
      this.swapPieces(row, col, direction);
      return false;
    } // if
  } // checkMove(int, int, char)

  /**
   * Swaps a given piece with the piece in the given direction.
   *
   * @param row
   *   the row of the piece to be moved
   * @param col
   *   the column of the piece to be moved
   * @param direction
   *   the direction said piece will move
   *
   * @pre isValidMove(row, col, direction) returns true
   */
  public void swapPieces(int row, int col, char direction) {
    char first = board.get(row, col);
    int newRow = 0;
    int newCol = 0;
    switch (direction) {
      case up: newCol = col; newRow = row - 1; break;
      case down: newCol = col; newRow = row + 1; break;
      case left: newRow = row; newCol = col - 1; break;
      case right: newRow = row; newCol = col + 1; break;
      default: break;
    } // switch
    char second = board.get(newRow, newCol);
    board.set(row, col, second);
    board.set(newRow, newCol, first);
  } // swapPieces(int, int, char)

  /**
   * Finds and removes the first row or column of 3 or more matching pieces in a row.
   *
   * @return 0 if no set was removed, 1 if a set was removed
   */
  public int removeSet() {
    int midCol = board.width() - 2;
    int midRow = board.height() - 2;

    for (int i = 0; i < board.height(); i++) {
      for (int j = 0; j < board.width(); j++) {
        char cur = board.get(i, j);
        if (cur != ' ') {
          if ((i < midRow) && (j < midCol)) {
            if ((board.get(i + 1, j) == cur) && (board.get(i + 2, j) == cur)) {
              board.set(i, j, ' ');
              board.set(i + 1, j, ' ');
              board.set(i + 2, j, ' ');
              board.remainingPieces -= 3;
              return 1;
            } // if
            if ((board.get(i, j + 1) == cur) && (board.get(i, j + 2) == cur)) {
              board.set(i, j, ' ');
              board.set(i, j + 1, ' ');
              board.set(i, j + 2, ' ');
              board.remainingPieces -= 3;
              return 1;
            } // if
          } else if ((i < midRow) && (j >= midCol)) {
            if ((board.get(i + 1, j) == cur) && (board.get(i + 2, j) == cur)) {
              board.set(i, j, ' ');
              board.set(i + 1, j, ' ');
              board.set(i + 2, j, ' ');
              board.remainingPieces -= 3;
              return 1;
            } // if
          } else if ((i >= midRow) && (j < midCol)) {
            if ((board.get(i, j + 1) == cur) && (board.get(i, j + 2) == cur)) {
              board.set(i, j, ' ');
              board.set(i, j + 1, ' ');
              board.set(i, j + 2, ' ');
              board.remainingPieces -= 3;
              return 1;
            } // if
          } else {
            continue;
          } // if
        } // if
      } // for
    } // for
    return 0;
  } // removeSet()

  /**
   * Simulates gravity on the game board so that all pieces are bottom justified.
   */
  public void runGravity() {
    char current;
    for (int col = 0; col < board.width(); col++) {
      for (int row = board.height() - 1; row >= 0; row--) {
        current = board.get(row, col);
        if (current == ' ') {
          for (int x = row; x >= 0; x--) {
            char newChar = board.get(x, col);
            if (newChar != ' ') {
              board.set(row, col, newChar);
              board.set(x, col, current);
              break;
            } // if
          } // for
        } // if
      } // for
    } // for
  } // runGravity()

  /**
   * Combined steps of check for 3 in a row, delete all 3 in a rows, and apply gravity. Loops until
   * no 3 in a rows exist.
   */
  public void checkRemoveGravity() {
    int status = 1;
    while (status == 1) {
      status = this.removeSet();
      this.runGravity();
    } // while
  } // checkRemoveGravity()
} // GameLogic
