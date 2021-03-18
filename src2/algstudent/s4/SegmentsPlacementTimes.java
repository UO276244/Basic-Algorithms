package algstudent.s4;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SegmentsPlacementTimes {
	
	private static int n;
	private static int nTimes;
	private static SegmentsPlacement segmentsAlgorithms;
	private static Map<String,Integer> segmentsMap;
	private static Random rand;
	
	
	public static void main(String args[]) {
		rand = new Random();
		n = 100;
		nTimes = 5;
		long timeGreedy1;
		long timeGreedy2;
		long timeGreedy3;
		System.out.println("N\t\t" + "Greedy1\t\t" + "Greedy2\t\t" + "Greedy3\t\t");
		while(true) {
		
		
			segmentsMap = createdMap(n);
			segmentsAlgorithms = new SegmentsPlacement(segmentsMap, n);
			
			 timeGreedy1 = System.currentTimeMillis();
			 for(int i = 0;i<nTimes;i++) {
				 segmentsAlgorithms.Greedy1(false);
			}
			timeGreedy1 = (System.currentTimeMillis() - timeGreedy1)/nTimes;
			
			 timeGreedy2 = System.currentTimeMillis();
			 for(int i = 0;i<nTimes;i++) {
				 segmentsAlgorithms.Greedy2(false);
			}
			timeGreedy2 = (System.currentTimeMillis() - timeGreedy2)/nTimes;
			
			 timeGreedy3 = System.currentTimeMillis();
			 for(int i = 0;i<nTimes;i++) {
				 segmentsAlgorithms.Greedy3(false);
			}
			timeGreedy3 = (System.currentTimeMillis() - timeGreedy3)/nTimes;
			
			
			
			
			
			System.out.println(n + "\t\t" + timeGreedy1+"\t\t"+timeGreedy2+"\t\t"+timeGreedy3);
			n*=2;
		}
		
	}
	
	
	private static HashMap<String,Integer> createdMap(int numOfElems){
		
		HashMap<String,Integer> hash = new HashMap<String,Integer>();
		for(int i = 0; i<numOfElems;i++) {
			hash.put("S"+i, rand.nextInt(100));
		
		}
		
		return hash;
	}
}
