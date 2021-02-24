package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}
	
	@Override
	public void sort() {
		int posMin;
		
		for(int i = 0; i < super.elements.length-1; i++) {
			posMin = super.findPosMin(i);
			super.interchange(i, posMin);
			
		}
	}  
	
	@Override
	public String getName() {
		return "Selection";
	} 
} 
