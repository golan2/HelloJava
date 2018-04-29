package lambda;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    04/05/14 02:24
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class LambdaScopeTest {


    public static void main(String... args) {
        RunForrestRun runner = new RunForrestRun();
        runner.foo();
    }


    public static class Forrest {
        public Runnable wrooom(){
            return () -> { System.out.println("Hello, lambda!"); };
        }
    }

    public static class RunForrestRun {
        private Runnable r = new Forrest()::wrooom;

        public void foo() {
            r.run();
        }
    }

}
