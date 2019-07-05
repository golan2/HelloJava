package reflection.simple;

import java.lang.reflect.InvocationTargetException;

public class HelloReflection {


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("1");
        Reffi.class.getDeclaredMethod("foo").invoke(new Reffi());
        System.out.println("2");
        Reffi.class.getDeclaredMethod("foo").invoke(new Reffi(), null);
        System.out.println("3");
        Reffi.class.getDeclaredMethod("foo", Integer.TYPE).invoke(new Reffi(), 3);
    }


    private static class Reffi {
        public void foo() {
            System.out.println("foo()");
        }

        public void foo(int a) {
            System.out.println("foo("+a+")");
        }

        public void foo(String a, String b) {
            System.out.println("foo("+a+","+b+")");
        }
    }
}
