package collections.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/01/2015 01:45
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *  http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 * </pre>
 */
public class GroceryTransactions {

  public static void main(String[] args) {
    List<Transaction> transactions = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      transactions.add(new Transaction());
    }

    Stream<Transaction.ComparableTransaction> sortable = transactions.stream().map(Transaction.ComparableTransaction::wrap);
    Stream<Transaction.ComparableTransaction> sorted = sortable.sorted(Transaction.ComparableTransaction::compareTo);
    Stream<Transaction> sortedTransactions = sorted.map(Transaction.ComparableTransaction::getTransaction);
    Iterator<Transaction> iterator = sortedTransactions.iterator();


    //
    //
    //transactions.stream().map(new Function<Transaction, Transaction.ComparableTransaction>() {
    //  @Override
    //  public Transaction.ComparableTransaction apply(Transaction transaction) {
    //    return new Transaction.ComparableTransaction(transaction);
    //  }
    //});
    //
    //for (Transaction transaction : transactions) {
    //  System.out.println(transaction);
    //}
    //
    //System.out.println("===========================================");
    //
    //Stream<Transaction> stream;
    //stream = transactions.stream();
    //stream = stream.filter(t -> t.getType() == Transaction.GROCERY);
    //stream = stream.sorted();
    //Iterator<Transaction> iterator = stream.iterator();
    //
    while (iterator.hasNext()) {
      Transaction next = iterator.next();
      System.out.println(next);
    }

    //List<Integer> transactionsIds = stream.map(Transaction::getId).collect(toList());
    //
    //for (Integer transactionsId : transactionsIds) {
    //  System.out.print(transactionsId+", ");
    //}
  }

}
