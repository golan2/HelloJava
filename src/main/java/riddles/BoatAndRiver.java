package riddles;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class BoatAndRiver {
	static final int MAX_PATH = 17;
	static final int[] values = new int[] {1,2,5,10};
	static final Set<Integer> a = new HashSet<>(4);
	static final Set<Integer> b = new HashSet<>(4);


	public BoatAndRiver() {
		for (int v : values) {
			a.add(v);
		}
	}


	public static void main(String[] args) {

		throw new RuntimeException("NOT WORKING...  The work with single Result probably not good since we need to remove all wrong path and not only the last step.?");

	}

	//return total minutes
	private static void cross(Result result, Direction d) {



		while (true) {

			if (d==Direction.GO) {
				//choose 2 ; remove from [a] add to [b] and update result
				for (int i=0 ; i<4 ; i++) {
					int first = values[i];
					if (a.contains(first)) {
						a.remove(first); b.add(first);
						for (int j=0 ; j<4 ; j++) {
							int second = values[j];
							if (a.contains(first)) {
								a.remove(second); b.add(second);
								result.push(first, second);

								cross(result, flip(d));

								if (a.isEmpty() && result.total<=MAX_PATH) {
									//Yes!!!
									return;
								}
								else {
									result.pop();	//this is not the way to go
								}

								//Rollback "second"								
								b.remove(second); a.add(second);

							}
						}

						//Rollback "first"
						b.remove(first); a.add(first);

					}

				}
			}
			else {
				throw new RuntimeException("UNIMPLEMENTED");
			}

		}





	}

	private static class Result {
		int total;
		Stack<Tuple> stack = new Stack<>();

		void push(Integer a, Integer b) {
			stack.push(new Tuple(a, b));
			total += (a>b ? a : b);
		}

		void pop() {
			Tuple tuple = stack.pop();
			total -= (tuple.a+tuple.b);
		}

		public String toString() {
			return "Total=["+total+"] Path=" + stack.stream().map(Object::toString).collect(Collectors.joining(","));
		}


	}

	private static Direction flip(Direction d) {
		if (d==Direction.GO)
			return Direction.BACK;
		else
			return Direction.GO;
	}

	private static class Tuple {
		final int a;
		final int b;
		Tuple(int a, int b) { this.a=a; this.b=b; }
		public String toString() {
			return "("+a+","+b+")";
		}
	}

	enum Direction { GO, BACK}





}
