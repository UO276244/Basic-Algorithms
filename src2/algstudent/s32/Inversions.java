package algstudent.s32;

import java.util.ArrayList;
import java.util.List;

public class Inversions {

	private List<Integer> ranking;

	public Inversions(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public long start() {
		return mergeSort(0, ranking.size() - 1, ranking);
	}

	private long mergeSort(int left, int right, List<Integer> elements) {
		long inversionsCount = 0;
		if (right > left) {
			
			int middle = (right + left) / 2;
			
			inversionsCount += mergeSort(left, middle, elements);
			inversionsCount += mergeSort(middle + 1, right, elements);
			inversionsCount += merge(left, middle, middle + 1, right, elements);
		}
		return inversionsCount;
	}

	public long merge(int leftStart, int leftEnd, int rightStart, int rightEnd, List<Integer> elements) {
		
		int sizeRight = rightEnd - rightStart + 1;
		int sizeLeft = leftEnd - leftStart + 1;
		List<Integer> leftList = new ArrayList<Integer>();
		List<Integer> rightList = new ArrayList<Integer>();
		
		// Copy the elements from left sublist into new list
		for (int i = 0; i < sizeLeft; i++) {
			leftList.add(elements.get(leftStart + i));
		}
		// Copy the elements from right sublist into new list
		for (int i = 0; i < sizeRight; i++) {
			rightList.add(elements.get(rightStart + i));
		}
		
		int auxPointer = 0; //Pointer in original subPart of elements list
		int elemsMovedFromAuxLists = 0; //
		long inversionsCounter = 0;
		
		//Check lowest value between leftList and rightList, locate that value in correspondent part
		//of original element list.
		while ( !leftList.isEmpty() && !rightList.isEmpty()){
		    if (leftList.get(0) > rightList.get(0)) {
		    	
			    elements.set(leftStart + auxPointer, rightList.remove(0));
			    
			    inversionsCounter += rightStart - leftStart - auxPointer + elemsMovedFromAuxLists ; 
			    
			    // Counter increased as many elements moved away from aux lists when inserting a value
			    //It is increased when a value in the right is lower than that on the left, so at least
			    //one inversion takes place.
			    elemsMovedFromAuxLists ++;
			   
		    } else {
		    	elements.set(leftStart + auxPointer, leftList.remove(0));
		    	
		    }
		    
		    auxPointer++;
		}
		
		
		//As one of the lists is not empty yet, add all itts elements at the end
		
		while (!rightList.isEmpty()) {
			elements.set(leftStart + auxPointer, rightList.remove(0));
			auxPointer++;
		}
		
		while (!leftList.isEmpty()) {
				elements.set(leftStart + auxPointer, leftList.remove(0));
				auxPointer++;
		}
		
		return inversionsCounter;

	}
	
	
}




