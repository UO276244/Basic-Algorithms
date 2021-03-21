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


public class SegmentsPlacement {

	private int numOfLines;
	private  Map<String, Integer> listOfSegments;
	private List<String> sortedKeys;
	
	public  void main(String filename) {
		numOfLines = 0;
		listOfSegments = new HashMap<String,Integer>();
		importSegmentsFromFile(filename);//"files/game1.txt"
		sortedKeys= new ArrayList<String>(listOfSegments.keySet());
		Collections.sort(sortedKeys);
		
		Greedy1(true);
		Greedy2(true);
		Greedy3(true);
	}
	
	public SegmentsPlacement(Map<String,Integer> listOfElems, int numOfSegments) {
		this.listOfSegments = listOfElems;
		this.numOfLines = numOfSegments;
	}
	
	
	/**
	 * GREEDY 1: It consists in the ostrich algorithm, that is, 
	 * "go placing the segments in the same order in which they are in the file, 
	 * because in the end every solution gives the same cost, 
	 * then why are we going to complicate our lives?".
	 */
	public void Greedy1(boolean toPrint) {
		
		
		//I copy the keys to a list and i order them from lower (S0) to gratest (S5)
		
		if(toPrint) {System.out.println("GREEDY 1:");}
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(String key : sortedKeys){
			movement = listOfSegments.get(key)+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			if(toPrint) {
				System.out.println(""+key+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
				
			}
			currentMaxPos = movement;
		}
		
		if(toPrint) {System.out.println("Cost of greedy 1 = " + finalCost+"\n");}
	}
	
	
	/**
	 * GREEDY 2: If consists in placing them from longest to shortest length.
	 */
	public void Greedy2(boolean toPrint) {
		
		if(toPrint) {System.out.println("GREEDY 2:");}
		List<Integer> values = new ArrayList<Integer>(listOfSegments.values());
		
		Collections.sort(values);
		
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(int i = numOfLines - 1; i>= 0; i--) {
			
			movement = values.get(i)+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			if(toPrint) {
				System.out.println(""+values.get(i)+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
				
			}
			currentMaxPos = movement;
			
		}
		
		if(toPrint) {System.out.println("Cost of greedy 2 = " + finalCost + "\n");}
	}
	
	/**
	 * GREEDY 3: If consists in placing them from shortest to longest length.
	 */
	public void Greedy3(boolean toPrint) {
		
		if(toPrint) {System.out.println("GREEDY 3:");}
		List<Integer> values = new ArrayList<Integer>(listOfSegments.values());
		Collections.sort(values);
		
		double finalCost = 0.0;
		double currentMaxPos = 0;
		double movement = 0;
		double currentMidPoint;
		for(int val : values) {
			
			movement = val+currentMaxPos;
			currentMidPoint = movement - (movement - currentMaxPos)/2;
			finalCost += currentMidPoint;
			if(toPrint) {
			System.out.println(""+val+": (" + (int)currentMaxPos + " to " + (int)movement + "), midpoint = " + currentMidPoint);
			}
			currentMaxPos = movement;
			
		}
		
		if(toPrint) {System.out.println("Cost of greedy 3 = " + finalCost);}
		
	}
	
	
	
	
	
	/**
	 * Private method to read a file and import the number of segments and its corresponding length
	 * @param fileName
	 */
	private void importSegmentsFromFile(String fileName) {
		
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
