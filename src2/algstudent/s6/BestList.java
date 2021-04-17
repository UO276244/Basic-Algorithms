package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BestList {

	List<Song> songs;
	int maxDurationSeconds;
	int counter = 0; //num of nodes
	int totalSongs;
	
	List<Song> bestBlockA;
	List<Song> bestBlockB;
	
	List<Song> auxBlockA;
	List<Song> auxBlockB;
	
	public BestList(String filename, int maxDurationMinutes) {
		
		songs = loadSongs(filename);
		this.maxDurationSeconds = maxDurationMinutes*60;
		bestBlockA = new ArrayList<Song>();
		bestBlockB = new ArrayList<Song>();
		auxBlockA = new ArrayList<Song>();
		auxBlockB = new ArrayList<Song>();
	}
	
	/*
	 * Method to apply backtracking
	 */
	public void compute() {
		compute(0); 
		
	}
	
	/**
	 * Check if any of the elements in list A is contained in B, if they have at least one element in common, return true
	 * @param A
	 * @param B
	 * @return
	 */
	private boolean shareElement(List<Song> A, List<Song> B) {
		
		for(Song s : A) {
			if(B.contains(s)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private void compute(int index){
		
		if(index == totalSongs) { //We have reached the last song
			
			if(calcScoreTotal(auxBlockA) >= calcScoreTotal(bestBlockA)) {
				bestBlockA = new ArrayList<Song>(auxBlockA);
			}
			
			if(calcScoreTotal(auxBlockB) >= calcScoreTotal(bestBlockB)
					&& !shareElement(auxBlockB,bestBlockA)) { //This extra condition checks that B has no repeated songs comparing to A
				bestBlockB = new ArrayList<Song>(auxBlockB);
			}
			
		}
		
		else {
			
			//Song not included
			compute(index + 1);
			counter++;
			//
			
			//Just try to add song to block A if it does not exceed the 20 mins total
			if(avalableSpaceInBlock(songs.get(index),auxBlockA)) {
				
				//Song included in blockA
				auxBlockA.add(songs.get(index));
				compute(index + 1);
				counter++;
				auxBlockA.remove(songs.get(index));
				//Delete song from blockA
			}
			//Just try to add song to block B if it does not exceed the 20 mins total
			if(avalableSpaceInBlock(songs.get(index),auxBlockB)) {
			
				//Song included in blockA
				auxBlockB.add(songs.get(index));
				compute(index + 1);
				counter++;
				auxBlockB.remove(songs.get(index));
				//Delete song from blockB
			}
			
			
				
		}
			
				
	}
		
		
	
	public void showResults() {
		
		int totalScA = calcScoreTotal(bestBlockA);
		int totalScB = calcScoreTotal(bestBlockB);
		
		

		System.out.println("Total num of songs: " + totalSongs + "\n");
		for(Song s : songs) {
			System.out.println("id: " + s.getiD() +"\t seconds: "+ s.getTimeMin() + "\t score:" + s.getScore());
		}
		
		System.out.println("\nLength of each block: " + ((int) maxDurationSeconds/60) + ":" + ((int) maxDurationSeconds%60));
		System.out.println("Total score: " + (totalScB+totalScA));
		System.out.println("Total counters: " + counter);
		
		System.out.println("\nBest Block A: ");
		
		for(Song s : bestBlockA) {
			System.out.println("id: " + s.getiD() +"\t seconds: "+ s.getTimeMin() + "\t score:" + s.getScore());
		}
		System.out.println("Total duration Block A: " + calcDurationTotal(bestBlockA));
		System.out.println("Total score Block A: " + totalScA);
		
		System.out.println("\nBest Block B: ");
		
		for(Song s : bestBlockB) {
			System.out.println("id: " + s.getiD() +"\t seconds: "+ s.getTimeMin() + "\t score:" + s.getScore());
		}
		System.out.println("Total duration Block B: " + calcDurationTotal(bestBlockB));
		System.out.println("Total score Block B: " + totalScB);
	}
	
	/**
	 * Check if the song s has space inside the block
	 * @param s
	 * @param block
	 * @return
	 */
	private boolean avalableSpaceInBlock(Song s, List<Song> block) {
		
		if(calcDurationTotal(block) + s.getSeconds() > maxDurationSeconds) {
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 
	 * @param block
	 * @return
	 */
	private int calcDurationTotal(List<Song> block) {
		
		int total = 0;
		
		for(Song s : block) {
			total += s.getSeconds();
		}
		
		return total;
		
	}
	
	
	/**
	 * Compute the total score of a block of songs
	 * @param block
	 * @return
	 */
	private int calcScoreTotal(List<Song> block) {
		
		int total = 0;
		
		for(Song s : block) {
			total += s.getScore();
		}
		
		return total;
	}
	
	/**
	 * Import the songs from a file. I suppose the format pf the file is correct,
	 * so little things are checked about the format.
	 * @param fileName
	 * @return
	 */
	private List<Song>loadSongs(String fileName) {
		String line;
		List<Song> songs = null;
		String[] data;
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			songs = new ArrayList<Song>();
			totalSongs = Integer.parseInt(file.readLine());
			
			while (file.ready()) {
				line = file.readLine();
				data = line.split("\t");
				String[] auxTime = data[1].split(":");
				songs.add(new Song(data[0],
									Integer.parseInt(auxTime[0])*60 + Integer.parseInt(auxTime[1]), 
									Integer.parseInt(data[2])));
				
			}
			
			file.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}

		return songs;
	}



}
