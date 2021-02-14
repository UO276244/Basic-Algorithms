package algstudent.s1;

public class Vector4 {
	
	static double nTimes = 1000000;

	public static void main(String args[]) {
		long startingN = 5;
		
		
		while(startingN <= 1000000000) {
			
			long avgTime = 0;
			
			for(int i = 1; i< nTimes; i*=10) {
				avgTime += calculate(""+startingN);
			}
			
			
			
			System.out.println("N: " + startingN + " Avg Time: " + avgTime);
			startingN *= 5;
			
		}
	}
	
	
	private static long calculate(String str) {
		int n = Integer.parseInt(str);
		int[] v = new int[n];
		Vector1.fillIn(v);
		long startingTime = System.currentTimeMillis();
		Vector1.sum(v);
		long elapsedTime = System.currentTimeMillis() - startingTime;
		
		return elapsedTime;
	}

}
