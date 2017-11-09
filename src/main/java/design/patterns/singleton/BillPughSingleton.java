package design.patterns.singleton;

public class BillPughSingleton extends SingletonParent {

	private BillPughSingleton() {		
		super();	
	}

	private static class LazyHolder {
		private static final BillPughSingleton _instance = new BillPughSingleton();
	}
	
	public static BillPughSingleton getInstance() {
		return LazyHolder._instance;
	}
	
	public void op1() { System.out.println("op1"); }
	public void op2() { System.out.println("op2"); }


}
