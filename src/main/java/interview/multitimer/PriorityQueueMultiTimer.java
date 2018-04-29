package interview.multitimer;

import java.util.Collection;
import java.util.PriorityQueue;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    20/05/2015 21:45
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class PriorityQueueMultiTimer extends AbsMultiTimer {
  PriorityQueue<MultiTask> queue = new PriorityQueue<>(new MultiTaskComparator());

  @Override
  protected boolean innerAddTask(MultiTask multiTask) {
    return queue.add(multiTask);
  }

  @Override
  protected MultiTask findNextTask() {
    return queue.peek();
  }

  @Override
  protected boolean innerRemoveTask(MultiTask task) {
    return queue.remove(task);
  }

  @Override
  protected boolean emptyTasksList() {
    return false;
  }

  private static class MultiTaskComparator implements java.util.Comparator<MultiTask> {
    @Override
    public int compare(MultiTask lhs, MultiTask rhs) {
      if (lhs.when <  rhs.when) return -1;
      if (lhs.when == rhs.when) return 0;
      return 1;
    }
  }
}
