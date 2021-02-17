package algstudent.s1;

import algstudent.s0.MatrixOperations;

public class MatrixOperationsTimes {

	public static void main(String args[]) {
		
		int sizeMatrix = 10;
		int nTimes = 1000; //Each measure is performed 1000 times
		
		MatrixOperations mo = new MatrixOperations(sizeMatrix, 1, 100);
		long startTime1;
		long finishTime1;
		long totalTime1 = 0;
		long startTime2;
		long finishTime2;
		long totalTime2 = 0;
		while(sizeMatrix <= 1000000000) {
			totalTime1 = 0;
			totalTime2 = 0;
			
			for(int i = 0; i < nTimes; i++) {
				startTime1 = System.currentTimeMillis();
				mo.sumDiagonal1();
				finishTime1 = System.currentTimeMillis() - startTime1;
				
				totalTime1 += finishTime1;
				
				startTime2 = System.currentTimeMillis();
				mo.sumDiagonal2();
				finishTime2 = System.currentTimeMillis() - startTime2;
				
				totalTime2 += finishTime2;
			}
			
			
			System.out.println("N: " + sizeMatrix + " sumDiagonal1 time: " + 			totalTime1 + " sumDiagonal2: " + totalTime2);
			sizeMatrix *= 3;
			mo = new MatrixOperations(sizeMatrix,1,100);
		
		}
		
		
	}
}
