package design.patterns.singleton;

public enum EnumSingleton {
	INSTANCE;
	public static EnumSingleton getInstance() {
		return EnumSingleton.INSTANCE;
	}

	public void op1() { System.out.println("op1"); }
	public void op2() { System.out.println("op2"); }
}
