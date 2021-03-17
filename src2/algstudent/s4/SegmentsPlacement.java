package algstudent.s4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algstudent.s32.Inversions;

public class SegmentsPlacement {

	private static int numOfLines;
	private static Map<String, Integer> listOfSegments;
	
	
	public static void main(String args[]) {
		numOfLines = 0;
		listOfSegments = new HashMap<String,Integer>();
		importSegmentsFromFile(args[0]);
		
		
		Greedy1();
		Greedy2();
		Greedy3();
	}
	
	
	/**
	 * GREEDY 1: It consists in the ostrich algorithm, that is, 
	 * "go placing the segments in the same order in which they are in the file, 
	 * because in the end every solution gives the same cost, 
	 * then why are we going to complicate our lives?".
	 */
	private static void Greedy1() {
		
		List<String> sortedKeys= new ArrayList<String>(listOfSegments.keySet());
		Collections.sort(sortedKeys);
		//I copy the keys to a list and i order them from lower (S0) to gratest (S5)
		
		System.out.println("GREEDY 1:");
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(String key : sortedKeys){
			movement = listOfSegments.get(key)+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			System.out.println(""+key+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
			currentMaxPos = movement;
		}
		
		System.out.println("Cost of greedy 1 = " + finalCost+"\n");
	}
	
	
	/**
	 * GREEDY 2: If consists in placing them from longest to shortest length.
	 */
	private static void Greedy2() {
		
		System.out.println("GREEDY 2:");
		List<Integer> values = new ArrayList<Integer>(listOfSegments.values());
		Inversions sorter = new Inversions(values);
		sorter.mergeSort(0, values.size() - 1);
		values = sorter.getRanking();
		
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(int i = values.size() - 1; i>= 0; i--) {
			
			movement = values.get(i)+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			System.out.println(""+values.get(i)+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
			currentMaxPos = movement;
			
		}
		
		System.out.println("Cost of greedy 2 = " + finalCost + "\n");
	}
	
	/**
	 * GREEDY 3: If consists in placing them from shortest to longest length.
	 */
	private static void Greedy3() {
		
		System.out.println("GREEDY 3:");
		List<Integer> values = new ArrayList<Integer>(listOfSegments.values());
		Inversions sorter = new Inversions(values);
		sorter.mergeSort(0, values.size() - 1);
		
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(int val : sorter.getRanking()) {
			
			movement = val+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			System.out.println(""+val+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
			currentMaxPos = movement;
			
		}
		
		System.out.println("Cost of greedy 3 = " + finalCost);
		
	}
	
	
	
	
	
	/**
	 * Private method to read a file and import the number of segments and its corresponding length
	 * @param fileName
	 */
	private static void importSegmentsFromFile(String fileName) {
		
		String line;
		int lineCount = 0;
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();

				if (lineCount == 0) {
					
					numOfLines = Integer.parseInt(line);

				} else {
					
					listOfSegments.put("S"+(lineCount-1),Integer.parseInt(line));
				}

			
				lineCount++;
			}
				
				

				file.close();
			
			
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}

		
	}
}
