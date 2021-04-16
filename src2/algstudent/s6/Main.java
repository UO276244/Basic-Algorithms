package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		String filename = args[0];
		int duration = Integer.parseInt(args[1]);
		BestList best = new BestList("files/"+filename,duration);
		best.compute();
		
		best.showResults();
		

	}
	
	

}
