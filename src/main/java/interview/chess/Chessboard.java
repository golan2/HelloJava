package interview.chess;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    05/06/2015 15:16
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class Chessboard {
  private static final int ROWS = 8;
  private static final int COLS = 8;
  private final Piece[][]  matrix = initChessBoard();

  private Piece[][] initChessBoard() {
    Piece[][] pieces = new Piece[ROWS][COLS];
    for (int i = 0; i < pieces.length; i++) {
      Piece[] row = pieces[i];
      for (int j = 0; j < row.length; j++) {
        row[j] = Piece.EMPTY;
      }
    }
    return pieces;
  }

  /**
   * Check if given square is in the boundaries of the board.
   *
   * @param square the location to check
   * @return true/false if on-board/illegal
   */
  public boolean checkBoundaries(Point square) {
    return
          square.getCol()>=0
          &&
          square.getCol()<COLS
          &&
          square.getRow()>=0
          &&
          square.getRow()<ROWS ;
  }

  public Piece square(Point destination) {
    return matrix[destination.getRow()][destination.getCol()];
  }
}
