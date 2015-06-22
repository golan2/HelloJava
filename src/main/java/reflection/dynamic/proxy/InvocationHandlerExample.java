package reflection.dynamic.proxy;

import golan.utils.MyLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    20/05/2015 22:38
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class InvocationHandlerExample {

  private enum Operation { Plus, Minus, Div, Multiply }

  public static void main(String[] args) {
    //arithmetic();

    List<String> list = new ArrayList<>();
    list.add("A");
    List proxy = (List) Proxy.newProxyInstance(
      NoOpAddInvocationHandler.class.getClassLoader(),
      new Class[] { List.class },
      new NoOpAddInvocationHandler(list));

    System.out.println("list=["+Arrays.toString(list.toArray())+"]");
    System.out.println("proxy=["+Arrays.toString(proxy.toArray())+"]");

    proxy.add("B");
    System.out.println("list=["+Arrays.toString(list.toArray())+"]");
    System.out.println("proxy=["+Arrays.toString(proxy.toArray())+"]");

    list.add("C");
    System.out.println("list=["+Arrays.toString(list.toArray())+"]");
    System.out.println("proxy=["+Arrays.toString(proxy.toArray())+"]");


  }

  private static void arithmetic() {InvocationHandler adder = new ArithmeticOpCalc(Operation.Plus);
    MyInterface proxy = (MyInterface) Proxy.newProxyInstance(getClassLoader(), ArithmeticOpCalc.getInterfaces(), adder);
    final long calculate = proxy.calculate(4, 5);
    System.out.println(calculate);
  }

  private static ClassLoader getClassLoader() {return InvocationHandlerExample.class.getClassLoader();}

  private static class ArithmeticOpCalc implements InvocationHandler {
    private static final Class<MyInterface> INTERFACE_CLASS = MyInterface.class;
    private final Operation operation;

    private ArithmeticOpCalc(Operation operation) {this.operation = operation;}

    private static Class[] getInterfaces() {return new Class[] { INTERFACE_CLASS };}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

      String proxy2string = (proxy == null) ? "null" : getClass().getName() + "@" + Integer.toHexString(hashCode());
      MyLog.log(MyLog.LogLevel.DEBUG, "invoke - proxy=["+proxy2string+"] operation=["+operation+"] methodName=["+method.getName()+"] args=["+ Arrays.toString(args) +"]");

      if (method.getName().equals("toString")) {
        return proxy2string;
      }
      if (method.getName().equals("calculate")) {
        int a = (int)args[0];
        int b = (int)args[1];

        if (Operation.Plus.equals(operation)) {
          return (long) a+b;
        }
      }

      return Long.MIN_VALUE;
    }
  }



  public static class NoOpAddInvocationHandler implements InvocationHandler {

    private final List proxied;

    public NoOpAddInvocationHandler(List proxied) {

      this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

      if (method.getName().startsWith("add")) {

        return false;
      }

      return method.invoke(proxied, args);
    }
  }

}
