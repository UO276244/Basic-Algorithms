package algstudent.s1;

public class Vector2 {
	

	
	public static void main(String args[]) {
		int n = Integer.parseInt(args[0]);
		int[] v = new int[n];
		Vector1.fillIn(v);
		//TODO: measure the time to sum all elements in v
		long startingTime = System.currentTimeMillis();
		Vector1.sum(v);
		long elapsedTime = System.currentTimeMillis() - startingTime;
		
		System.out.println("N: "+ n +" Time: " + elapsedTime);
		//​​
	}

}
