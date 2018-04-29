package interview.design.library.books;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    20/11/2014 23:03
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Rental {

  private long date;
  private int customerId;
  private String bookTitle;

  public Rental(int customerId, String bookTitle) {
    this.date = System.currentTimeMillis();
    this.customerId = customerId;
    this.bookTitle = bookTitle;
  }

  public long getDate() {
    return date;
  }

  public Rental() {
    super();
  }

  public void setDate(long date) {
    this.date = date;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  @Override
  public String toString() {
    return new StringBuilder().append("<Rental customerId=\"").append(customerId).append("\" bookTitle=\"").append(bookTitle).append("\" date=\"").append(date).append("\" />").toString();
  }

}
