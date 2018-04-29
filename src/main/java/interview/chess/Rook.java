package interview.chess;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    05/06/2015 15:35
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class Rook extends AbsPiece {
  protected Rook(Color color, Point location, Chessboard chessboard) {
    super(color, location, chessboard);
  }

  @Override
  protected boolean isLeapEnabled() {
    return false;
  }

  @Override
  protected boolean isValidMove(Point to) {
    return (location.getRow()==to.getRow() || location.getCol()==to.getCol());
  }

  @Override
  public String toString() {
    return "Rook{}";
  }
}
