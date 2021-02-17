

package algstudent.s0;

public class Main {
	
	public static void main(String[] args) {
		MatrixOperations mO = new MatrixOperations("files/matrixData.txt");
		
		mO.write();
		System.out.println("Sum diagonal1: "+mO.sumDiagonal1());
		System.out.println("Sum diagonal2: "+mO.sumDiagonal2());
		mO.travelPath(3, 0);
	}
}
