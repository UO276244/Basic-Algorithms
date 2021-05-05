package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MyNode extends Node{
	
	private List<Song> A;
	private List<Song> B;

	private int timeLimit;
	private ArrayList<Node> children;
	
	List<Song> allSongs;
	
	//Constructor for children
	public MyNode(List<Song> allSongs, List<Song> A, List<Song> B, int timeLimit, int depth, UUID parent) {
		this.allSongs = allSongs;
		this.A = A;
		this.B = B;
		this.timeLimit = timeLimit;
		this.depth = depth;
		this.parentID = parent;
		
	}
	//Constructor only for root
	public MyNode(List<Song> allSongs,int timeLimit) {
		this.A = new ArrayList<Song>();
		this.B = new ArrayList<Song>();
		this.allSongs = allSongs;
		this.timeLimit = timeLimit;
		
	}
//	
	
	@Override
	public void calculateHeuristicValue() {
		
		if( getTotalScore(A) > timeLimit || getTotalScore(B) > timeLimit ) {
			heuristicValue = Integer.MAX_VALUE;
		} else {
			
			heuristicValue = (getTotalScore(B) + getTotalScore(A)) * (-1);
		}
	}
//	
//	@Override
//	public void calculateHeuristicValue() {
//		
//		if(getTotalScore(A) > timeLimit || getTotalScore(B) > timeLimit) {
//			this.heuristicValue = Integer.MAX_VALUE; //tHIS WILL BE LOWEST PRIORITY IN HEURISTIC (WORST CASE)
//		}else {
//		
//			//double currentRatioA = A.getRatio();
//			//double currentRatioB = B.getRatio();
//			
//			int toalScore = getTotalScore(A) + getTotalScore(B);
//			int currentTotalTimeA = getTotalSec(A);
//			int currentTotalTimeB = getTotalSec(A);
//			
//			for(int i = this.depth + 1; i < allSongs.size(); i++) {
//				
//				Song s = allSongs.get(i);
//				
//				if(currentTotalTimeA + s.getSeconds() <= timeLimit) {
//					//currentRatioA = (A.getTotalScore() + s.getScore())/(A.getTotalSec() + s.getSeconds());
//					toalScore += s.getScore();
//					currentTotalTimeA += s.getSeconds();
//					
//				}
//				else if(currentTotalTimeB + s.getSeconds() <= timeLimit) {
//					//currentRatioB = (B.getTotalScore() + s.getScore())/(B.getTotalSec() + s.getSeconds());
//					toalScore += s.getScore();
//					currentTotalTimeB += s.getSeconds();
//				}
//				
//				//The heuristic value will be the biggest ratio
//			
//				
//			}
//			this.heuristicValue = ((-1)*toalScore);
//			//this.heuristicValue = (int) ((currentRatioA >= currentRatioB)? currentRatioA : currentRatioB);
//		}
//		
//	}
//	
//	

	@Override
	public ArrayList<Node> expand() {
		
		children = new ArrayList<Node>();
		
		if(depth < allSongs.size()) {
			
		
		//Adding song to A
		A.add(allSongs.get(this.depth));
		children.add(new MyNode(new ArrayList<Song>(allSongs),new ArrayList<Song>(A) 
				,new ArrayList<Song>(B), 
				this.timeLimit, this.depth+1,this.ID));
		A.remove(allSongs.get(this.depth));
		
		//Adding song to B
		B.add(allSongs.get(this.depth));
		children.add(new MyNode(new ArrayList<Song>(allSongs),new ArrayList<Song>(A) 
				,new ArrayList<Song>(B), 
				this.timeLimit, this.depth+1,this.ID));
		B.remove(allSongs.get(this.depth));
		
		//Adding song to none list
		children.add(new MyNode(new ArrayList<Song>(allSongs),new ArrayList<Song>(A) 
				,new ArrayList<Song>(B), 
				this.timeLimit,this.depth+1,this.ID));
		
		}
		return children;
	}

	@Override
	public boolean isSolution() {
		return this.depth == allSongs.size() - 1 &&
				getTotalSec(A) <= timeLimit &&
						getTotalSec(B) <= timeLimit;
	}
	
	
	public String toString() {
		String auxA = "A: ";
		for(Song s : A) {
			auxA += ""+s.getiD() + " ";
		}
		auxA += "\n Total Score: " + getTotalScore(A);
		
		String auxB = "B: ";
		for(Song s : B) {
			auxB += ""+s.getiD() + " ";
		}
		auxB += "\n Total Score: " + getTotalScore(B);
		return auxA + "\n" + auxB + "\n";
		
	}
	
	private int getTotalSec(List<Song> block) {
		
		int aux = 0;
		for(Song s : block) {
			aux += s.getSeconds();
		}
		
		return aux;
		
	}
	
	
	private int getTotalScore(List<Song> block) {
		int aux = 0;
		for(Song s : block) {
			aux += s.getScore();
		}
		
		return aux;
		
	}

}
