package algstudent.s6;

import java.time.Duration;

public class Song {
	
	private String iD;
	private int seconds;
	private int score;
	
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public Song(String iD, int seconds, int score) {
		this.iD = iD;
		this.seconds = seconds;
		this.score = score;
	}
	
	public String getTimeMin() {
		int mins = seconds / 60;
		int sec = seconds % 60;
		
		if(sec < 10) {
			
			return ""+mins+":0"+sec;
		}else {
			return ""+mins+":"+sec;
		}
		
	}

}
