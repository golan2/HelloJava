package interview.chess;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    05/06/2015 15:19
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public interface Piece {

  public static final Piece EMPTY = new EmptySquare();

  Color getColor();

  boolean isLegalMove(Point to);

  static class EmptySquare implements Piece {
    @Override
    public Color getColor() {
      return null;
    }

    @Override
    public boolean isLegalMove(Point to) {
      return false;
    }
  }
}
