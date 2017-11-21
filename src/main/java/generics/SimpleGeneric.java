package generics;

import java.util.ArrayList;
import java.util.List;

public class SimpleGeneric {

	public static void main(String[] args) {
		ArrayList<Object> list1 = new ArrayList<>();
		list1.add(new Object());
		list1.add(new Stu());
		list1.add(new Stuff());
		
		ArrayList<Stu> list2 = new ArrayList<>();
		list2.add(new Stu());
		list2.add(new Stuff());

		
		sup(list1);
		sup(list2);
		
//		ext(list1);		<Object> doesn't extends Stu
		ext(list2);
	}
	
	static void ext (List<? extends Stu> list){
		for (Stu a : list) {
			a.call();
		}
	}
	
	static void sup (List<? super Stuff> list){
		for (Object a : list) {
			if (a instanceof  Stuff) {
				((Stuff) a).call();
			}
			else if ( a instanceof Stu) {
				((Stu)a).call();
			}
			else {
				System.out.println("Object call");
			}
		}
	}

	private static class Stu {
		void call() {
			System.out.println("Stu Call");
		}
	}
	
	private static class Stuff extends Stu {
		void call(){
			System.out.println("Stuff Call");
		}
	}

}
