package algstudent.s7;

import java.util.ArrayList;
import java.util.List;

public class BlockOfSongs {
	
	private List<Song> songs;
	private int totalSec = 0;
	private int totalScore = 0;
	
	public BlockOfSongs() {
		songs = new ArrayList<Song>();
	}
	
	public BlockOfSongs(List<Song> songs) {
		this.songs = songs;
		this.totalSec = calculateDuration();
		this.totalScore = calculateScore();
	}
	
	
	private int calculateScore() {
		
		int aux=0;
		for(Song s : songs) {
			aux += s.getScore();
		}
		
		return aux;
		
	}
	
	private int calculateDuration() {

		int aux=0;
		for(Song s : songs) {
			aux += s.getSeconds();
		}
		
		return aux;
	}
	
	public double getRatio() {
		
		if(totalSec == 0) {
			return 0.0;
		}
		
		return (double)totalScore/totalSec;
	}
	
	public List<Song> getSongs(){
		
		return songs;
	}
	
	public void Add(Song s) {
		songs.add(s);
		this.totalSec += s.getSeconds();
		this.totalScore += s.getScore();
	}
	
	public void Remove(Song s) {
		songs.remove(songs.indexOf(s));
		this.totalSec -= s.getSeconds();
		this.totalScore -= s.getScore();
	}
	
	
	public int getTotalSec() {
		return totalSec;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public Song getWorstSong() {
		
		Song aux = null;
		int ratioScore_Sec = Integer.MAX_VALUE;
		for(Song s : songs){
			if(s.getScore_SecRatio() < ratioScore_Sec) {
				aux = s;
				ratioScore_Sec = s.getScore_SecRatio();
			}
		}
		
		return aux;
		
	}
	

}
