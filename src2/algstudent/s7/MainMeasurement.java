package algstudent.s7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algstudent.s6.BestList;

public class MainMeasurement {

	public static void main(String[] args) {
		
		MyBandB bnb;
		BestList back;
		Node root; 
		List<Song> songs;
		int n = 25;
		int limit;
		long timeStart;
		long timeEnd ;
		
		for(int i=n; i<=40000000; i*=2) {
			
			songs = getSongsRandomly(i);
			limit = (int)((getTotalSec(songs)/10)*4);
			
			back = new BestList(songs, limit);
			
			
			 timeStart = System.currentTimeMillis();
			back.compute();
			 timeEnd = System.currentTimeMillis();
			System.out.println("Backtracking for n: " + i + " --- time: " + (timeEnd - timeStart));
			
		}
		
		
		for(int i=n; i<800; i*=2) {
			
			songs = getSongsRandomly(i);
			limit = getFixedT(getTotalSec(songs));
			

			root = new MyNode(songs, limit);
			bnb = new MyBandB(root);
			
			 timeStart = System.currentTimeMillis();
			bnb.branchAndBound(root);
			 timeEnd = System.currentTimeMillis();
			System.out.println("BnB for n: " + i + " --- time: " + (timeEnd - timeStart));
			
			
		}
		
		
		
	}
	

	private static int getTotalSec(List<Song> block) {
		
		int aux = 0;
		for(Song s : block) {
			aux += s.getSeconds();
		}
		
		return aux;
		
	}
	
	
	private static int getFixedT(int time) {
		return (int)((time/10)*4);
	}
	
	
	private static List<Song> getSongsRandomly(int n) {
		/*
		* Generates n random songs
		* Song time generated according a normal distribution mean 2 mins and standard distribution 1 min (> 30 secs)
		* Scores are generated according a normal distribution mean 2000 and standard distribution 1000 (> 300)
		*/
		
		List<Song> songs = new ArrayList<Song>();
		int t_secs, score;
		Random rand = new Random();
		
		for (int i=0; i<n; i++) {
			do {
				t_secs = (int) (rand.nextGaussian() * 120 + 60);
			}while (t_secs < 30);
			
			do {
				score = (int) (rand.nextGaussian() * 2000 + 1000);
			}while (score < 300);
			songs.add(new Song(String.valueOf(i), t_secs, score));
		}
		
		return songs;
	}
	

}
