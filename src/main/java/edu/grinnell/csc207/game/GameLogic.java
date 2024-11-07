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
  // field that is the board
  // check if game is over
  // check if move is valid
  // gravity??
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  // * A board that represents the game status. */
  GameBoard board;

  // * The possible directions. */
  final char UP = 'u';
  final char DOWN = 'd';
  final char LEFT = 'l';
  final char RIGHT = 'r';

  // +--------------+-----------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Builds a new game logic.
   * 
   * @param board a GameBoard object to represent the game
   */
  public GameLogic(GameBoard board) {
    this.board = board;
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
    char[] directions = {UP, DOWN, LEFT, RIGHT};
    for (int i = 0; i < board.height(); i++) {
      for (int j = 0; j < board.width(); j++) {
        if (this.board.get(i, j) != ' ') {
          for (int k = 0; k < directions.length; k++) {
            if (this.isValidMove(i, j, directions[k])) {
              return false;
            }
          }
        }
      }
    }
    return true;
  } // checkOver()

  /**
   * Checks if a proposed move is legal.
   * 
   * @param row
   * @param col
   * @param direction
   * 
   * @return whether the proposed move is legal
   */
  public boolean isValidMove(int row, int col, char direction) {
    if (((row == 0) && (direction == UP)) || ((row == board.height() - 1) && (direction == DOWN))
        || ((col == 0) && (direction == LEFT))
        || ((col == board.width() - 1) && (direction == RIGHT)) || ((row < 0))
        || ((row >= board.height())) || ((col < 0)) || ((col >= board.width()))) {
      return false;
    } else {
      // make helper method
      char first = board.get(row, col);
      int newRow = 0;
      int newCol = 0;
      switch (direction) {
        case UP:
          newCol = col;
          newRow = row - 1;
          break;
        case DOWN:
          newCol = col;
          newRow = row + 1;
          break;
        case LEFT:
          newRow = row;
          newCol = col - 1;
          break;
        case RIGHT:
          newRow = row;
          newCol = col + 1;
          break;
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

      // if failed, swap back
      this.swapPieces(row, col, direction);
      return false;

      /*
       * // if 1 and 2 up if (((board.get(newRow + 1, newCol) == first) && (board.get(newRow + 2,
       * newCol) == first)) || ((board.get(newRow - 1, newCol) == first) && (board.get(newRow - 2,
       * newCol) == first)) || ((board.get(newRow, newCol + 1) == first) && (board.get(newRow,
       * newCol + 2) == first)) || ((board.get(newRow, newCol - 1) == first) && (board.get(newRow,
       * newCol - 2) == first)) || ((board.get(newRow + 1, newCol) == first) && (board.get(newRow -
       * 1, newCol) == first)) || ((board.get(newRow, newCol + 1) == first) && (board.get(newRow,
       * newCol - 1) == first))) { return true; } else { return false; }
       */
    }
  } // checkMove(int, int, char)

  /**
   * Swaps a given piece with the piece in the given direction.
   * 
   * @param row
   * @param col
   * @param direction
   * 
   * @pre isValidMove(row, col, direction) returns true
   */
  public void swapPieces(int row, int col, char direction) {
    char first = board.get(row, col);
    int newRow = 0;
    int newCol = 0;
    switch (direction) {
      case UP:
        newCol = col;
        newRow = row - 1;
        break;
      case DOWN:
        newCol = col;
        newRow = row + 1;
        break;
      case LEFT:
        newRow = row;
        newCol = col - 1;
        break;
      case RIGHT:
        newRow = row;
        newCol = col + 1;
        break;
    }
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
            }
            if ((board.get(i, j + 1) == cur) && (board.get(i, j + 2) == cur)) {
              board.set(i, j, ' ');
              board.set(i, j + 1, ' ');
              board.set(i, j + 2, ' ');
              board.remainingPieces -= 3;
              return 1;
            }
          } else if ((i < midRow) && (j >= midCol)) {
            if ((board.get(i + 1, j) == cur) && (board.get(i + 2, j) == cur)) {
              board.set(i, j, ' ');
              board.set(i + 1, j, ' ');
              board.set(i + 2, j, ' ');
              board.remainingPieces -= 3;
              return 1;
            }
          } else if ((i >= midRow) && (j < midCol)) {
            if ((board.get(i, j + 1) == cur) && (board.get(i, j + 2) == cur)) {
              board.set(i, j, ' ');
              board.set(i, j + 1, ' ');
              board.set(i, j + 2, ' ');
              board.remainingPieces -= 3;
              return 1;
            }
          } else {
            continue;
          }
        }
      }
    }
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
    }
  } // checkRemoveGravity()

} // GameLogic
