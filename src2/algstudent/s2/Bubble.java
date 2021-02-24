package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the BUBBLE or DIRECT EXCHANGE */
public class Bubble extends Vector {
	public Bubble(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		for (int i = 0; i < super.elements.length-1; i++) 
            for (int j = 0; j < super.elements.length-i-1; j++) 
                if (super.elements[j] > super.elements[j+1]) 
                { 
                    super.interchange(j+1, j);
                } 
	}  
	
	@Override
	public String getName() {
		return "Bubble";
	} 
} 

