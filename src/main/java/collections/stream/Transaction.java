package collections.stream;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    20/01/2015 01:38
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Transaction  {
  private static final AtomicInteger idGenerator     = new AtomicInteger();
  private static final AtomicInteger timeGenerator   = new AtomicInteger();
  private static final Random        randomGenerator = new Random();
  public  static final int           GROCERY         = 1;
  public  static final int           GENERAL         = 0;

  ////

  private final Integer id;
  private final Integer timestamp;
  private final Integer type;
  private final Double  value;

  public Transaction() {

    id = idGenerator.incrementAndGet();


    if (randomGenerator.nextBoolean()) {
      type = GROCERY;
    }
    else {
      type = GENERAL;
    }


    timestamp = timeGenerator.get();
    if (randomGenerator.nextInt()%3==0) {
      timeGenerator.incrementAndGet();
    }


    value = Math.round(randomGenerator.nextFloat()*10000.0)/100.0;

  }

  public Integer getId() {
    return id;
  }

  public Integer getTimestamp() {
    return timestamp;
  }

  public Integer getType() {
    return type;
  }

  public Double getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Transaction)) {
      return false;
    }

    Transaction that = (Transaction) o;

    if (!timestamp.equals(that.timestamp)) {
      return false;
    }
    if (!type.equals(that.type)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = timestamp.hashCode();
    result = 31 * result + type.hashCode();
    return result;
  }


  @Override
  public String toString() {
    return "Transaction{" +
        "id=" + id +
        ", timestamp=" + timestamp +
        ", type=" + type +
        ", value=" + value +
        '}';
  }

  public static class ComparableTransaction implements Comparable<ComparableTransaction>{
    private final Transaction transaction;

    public ComparableTransaction(Transaction transaction) {this.transaction = transaction;}

    public Transaction getTransaction() {
      return transaction;
    }

    @Override
    public int compareTo(ComparableTransaction o) {
      return this.transaction.value.compareTo(o.transaction.value);
    }

    public static ComparableTransaction wrap(Transaction transaction) {
      return new ComparableTransaction(transaction);
    }
  }
}
