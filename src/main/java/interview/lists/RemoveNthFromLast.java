package interview.lists;

import interview.lists.Node;
import interview.lists.SinglyLinkedList;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    11/06/2015 07:58
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class RemoveNthFromLast<T> extends SinglyLinkedList<T> {

  /**
   * finds the nth to last element of a linked list, where 0 is the last element (tail) of the list
   * @param n the index from last
   * @return the nth to last node; or null if the list contains less than N elements
   */
  public T findNthFromLast(int n) {
    Node<T> lead = head;
    Node<T> current = head;

    if (n<0) {   //n==0 is "findTheLast"
      return null;
    }

    //promote lead "N" steps ahead
    for (int i = 0; i < n; i++) {
      if (lead == null)
        return null;
      else
        lead = lead.getNext();
    }

    if (lead==null) {
      return null;   //N is greater or equal to the size of the list
    }

    //iterate until lead is in the end (so current is on the NthToLast)
    while (lead.hasNext()) {
      lead = lead.getNext();
      current = current.getNext();
    }

    return current.getValue();
  }

  public T removeNthFromLast(int n) {
    Node<T> lead = head;
    Node<T> current = head;
    Node<T> prev = null;

    if (n<0) {         //n==0 is "removeLast"
      return null;
    }

    if (head==null) {
      return null;
    }

    //promote lead "N" steps ahead
    for (int i = 0; i < n; i++) {
      if (lead == null)
        return null;
      else {
        lead = lead.getNext();
      }
    }

    if (lead==null) {
      return null;   //N is greater or equal to the size of the list
    }

    //iterate until lead is in the end (so current is on the NthToLast)
    while (lead.hasNext()) {
      lead = lead.getNext();
      prev = current;
      current = current.getNext();
    }

    if (prev==null) {
      //remove the first
      head = head.getNext();
    }
    else {
      //remove "current"
      prev.setNext(current.getNext());
    }

    return current.getValue();
  }

}
