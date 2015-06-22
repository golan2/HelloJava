package collections.hashmap.performance;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;

import java.util.HashMap;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/11/2014 10:14
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MapBenchmark extends SimpleBenchmark {
  private HashMap<Key, Integer> map;
  @Param
  private int mapSize;
  @Override
  protected void setUp() throws Exception {
    map = new HashMap<>(mapSize);
    for (int i = 0; i < mapSize; ++i) {
      map.put(Keys.of(i), i);
    }
  }
  public void timeMapGet(int reps) {
    for (int i = 0; i < reps; i++) {
      map.get(Keys.of(i % mapSize));
    }
  }
}