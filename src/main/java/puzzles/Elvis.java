package puzzles;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    08/12/13 19:00
 * <B>Description:</B>
 *
 * [1] ALIVE
 * =========
 * Take a look at the 2 constants in this class ("_instance" and "ALIVE")
 * The order of lines cause that when we instantiate "_instance" the "ALIVE" is not yet initialized (i.e. it is still null)
 * This is why "alive" variable is initialized in the constructor to be null.
 * If you change "alive" to boolean (instead of Boolean) you will get ExceptionInInitializerError which is cause by NullPointerException :-)
 *
 * [2] WORKING
 * ===========
 * The WORKING constant is like ALIVE only that it is boolean (instead of Boolean) which makes it a "real" constant (i.e. compile time constant).
 * As such it is initialized immediately and not according to order of lines.
 * This is why "working" is true.
 *
 * [3] SINGING
 * ===========
 * The SINGING is a boolean but is not a "real" constant because its value is unknown at compile time.
 * So at point of creation it is false (default boolean value) and later on it is set to true.
 *
 *
 * </pre>
 */
public class Elvis {
    private static final Elvis   _instance = new Elvis();
    private static final Boolean ALIVE     = true;
    private static final boolean WORKING   = true;
    private static final boolean SINGING   = returnTrue();

    private Boolean alive   = ALIVE;
    private Boolean working = WORKING;
    private Boolean singing = SINGING;

    public static Elvis get_instance() {
        return _instance;
    }

    private static boolean returnTrue() {return true;}

    public void setAlive  (Boolean alive  ) { this.alive   = alive;  }
    public void setWorking(Boolean working) { this.working = working;  }
    public void setSinging(Boolean singing) { this.singing = singing;  }
    public Boolean isAlive  () { return alive; }
    public Boolean isSinging() { return singing; }
    public Boolean isWorking() { return working; }

    @Override
    public String toString() {
        return "Elvis{" +
          "alive=" + alive +
          ", working=" + working +
          ", singing=" + singing +
          ", ALIVE=" + ALIVE +
          ", WORKING=" + WORKING +
          ", SINGING=" + SINGING +
          '}';
    }

    public static void main(String[] args) {
        System.out.println(Elvis.get_instance());
    }
}
