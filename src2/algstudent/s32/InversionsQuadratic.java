package algstudent.s32;

import java.util.List;

public class InversionsQuadratic {
	
	List<Integer> ranking;

	public InversionsQuadratic(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public String start() {
		long count_Inversion = 0; 
		int n = ranking.size();
		
        for (int i = 0; i < n - 1; i++) 
            for (int j = i + 1; j < n; j++) 
                if (ranking.get(i) > ranking.get(j)) 
                	count_Inversion++; 
  
        return ""+  count_Inversion; 
	}

}
