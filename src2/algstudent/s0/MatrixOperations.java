package algstudent.s0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class MatrixOperations {

	private int[][] matrix;
	private Random rand = new Random();
	private int n;
	private final int MOVE_UP = 1;
	private final int MOVE_RIGHT = 2;
	private final int MOVE_DOWN =3;
	private final int MOVE_LEFT = 4;
	

	/**
	 * Creates a new matrix of size n x n and fills it with random values. These
	 * random values must be parameterizable between a maximum (max) and a minimum
	 * (min) value.
	 * 
	 * @param n
	 * @param min
	 * @param max
	 */
	public MatrixOperations(int n, int min, int max) {
		this.n = n;

		matrix = new int[n][n];

		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[i].length; i++) {

				matrix[i][j] = rand.nextInt((max - min) + 1) + min;

			}

		}
	}

	/**
	 * Creates a matrix using data of the file provided as parameter. This file must
	 * have 1 integer number as the first line. Following lines contain n values to
	 * represent every element of the matrix row. Each of the values will be
	 * separated by a tabulator.
	 * 
	 * @param filename
	 */
	public MatrixOperations(String filename) {
		matrix = loadMatrix(filename);
	}

	/**
	 * Returns the matrix size (n).
	 * 
	 * @return
	 */
	public int getSize() {
		return n;
	}

	/**
	 * Prints in the console all the matrix elements.
	 */
	public void write() {
		String aux = "";
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<n;j++) {
				aux += matrix[i][j] + "	";
			}
			System.out.println(aux);
			aux = "";
		}
	}

	/**
	 * Computes the summation of all the elements of the matrix diagonal. This
	 * implementation must iterate over all the matrix elements, but only sums
	 * appropriate elements. So, the complexity is quadratic.
	 * 
	 * @return
	 */
	public int sumDiagonal1() {
		int sum = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j<matrix[i].length;j++) {
				
				if(i==j || i+j==n-1) {
					sum += matrix[i][j];
				}
				
			}
		}
		
		return sum;

	}

	/**
	 * Computes the summation of all the elements of the matrix diagonal. This
	 * second version should only consider the elements of the main diagonal. So,
	 * the complexity is linear.
	 * 
	 * @return
	 */
	public int sumDiagonal2() {
		int sum = 0;
		for(int i = 0; i < getSize(); i++) {
			sum += matrix[i][i];
		}
		
		return sum;
	}
	
	/**
	 * Given a matrix with integer numbers between 1 and 4, 
	 * this method iterates through the matrix starting at position (i, j) 
	 * according to the following number meanings: 1 – move up; 2 – move right; 3 – move down; 4 – move left. 
	 * Traversed elements would be set to -1 value. 
	 * The process will finish if it goes beyond the limits of the matrix or an already traversed position is reached.
	 * @param i
	 * @param j
	 */
	public void travelPath(int i, int j) {
		if(i>=n || j>= n) {
			throw new IllegalArgumentException("Illegal starting point.");
		}

		int currentLocation = matrix[i][j];
		int iAux = i;
		int jAux = j;
		boolean endedTravel = false;
		int numOfMov = 0;
		
		while(!endedTravel) {
			
			matrix[iAux][jAux] = -1;
			
			if(currentLocation == MOVE_UP) {
				
				iAux--;  //GO UP 1 ROW
				
			}else if(currentLocation == MOVE_RIGHT) {
				
				jAux++; //GO RIGHT ONE COLUMN
				
			}else if(currentLocation == MOVE_DOWN) {
				
				iAux++; //GO DOWN ONE ROW
				
			}else if(currentLocation == MOVE_LEFT) {
				
				jAux--; //GO LEFT ONE COLUMN
				
			}
			else if(currentLocation == -1) {
				endedTravel = true;
				break; //REACHED AN ALREADY VISITED POS
			}
			else {
			
				throw new IllegalArgumentException("In order to travel, no values greater than 4 or lower than -1 are allowed.");
			}
			
			numOfMov++;
			
			if((iAux>=0 && iAux <=getSize()-1 && jAux>=0 && jAux <=getSize()-1) ) {
				
				currentLocation = matrix[iAux][jAux];
				
			}else {
				endedTravel = true;
				break; //REACHED AN ALREADY VISITED POS
			}
			
		}
		
		System.out.println("RESULT OF TRAVEL:\n");
		write();
		System.out.println("Number of movements: " + numOfMov);

	}

	
	
	/**
	 * Import the matrix from a file. I suppose the format pf the file is correct,
	 * so little things are checked about the format.
	 * @param fileName
	 * @return
	 */
	private int[][] loadMatrix(String fileName) {
		String line;
		int lineCount = 0;
		int matSize = 0; // Size of the auxiliar matrix
		int[][] auxMatrix = null;
		String[] numsAsString = null;
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();

				if (lineCount == 0) {

					matSize = Integer.parseInt(line);
					auxMatrix = new int[matSize][matSize];

				} else {
					
					numsAsString = line.split("	"); // Split tabulation

					if (numsAsString.length != matSize) {
						
						throw new IllegalArgumentException("<Error in size of the matrix or in row >" + (lineCount -1));
						
					} else {

						for (int i = 0; i < matSize; i++) {
							
							auxMatrix[lineCount - 1][i] = Integer.parseInt(numsAsString[i]);
							
						}
						
					}

				}
				
				lineCount++;

			}
			if(lineCount -1 == matSize) {
				n = matSize;
			}
			else {
				throw new IllegalArgumentException("Invalid matrix size, there are " + (matSize-lineCount) +" rows left.");
			}
			
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}

		return auxMatrix;
	}

}
