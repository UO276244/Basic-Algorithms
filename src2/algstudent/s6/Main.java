package algstudent.s6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algstudent.s7.Song;

public class Main {

	public static void main(String[] args) {
		
		String filename = args[0];
		int duration = Integer.parseInt(args[1]);
		List<Song> s = getSongsRandomly(7);
		BestList best = new BestList(s,getFixedT(getTotalSec(s)));
		best.compute();
		
		best.showResults();
		

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
