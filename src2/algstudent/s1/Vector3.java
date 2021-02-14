package algstudent.s1;

public class Vector3 {
	
	public static void main(String args[]) {
		long startingN = 5;
		String[] arguments = new String[1];
		arguments[0] = ""+startingN;
		
		while(startingN <= 1000000000) {
		
			Vector2.main(arguments);
			startingN *= 5;
			arguments[0] = ""+startingN;
		}
	}

}
