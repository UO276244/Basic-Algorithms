package algstudent.s7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Main {

	public static final String songs1 = "files/List01.txt";
	
	public static void main(String[] args) {
		String filename = args[0];
		List<Song> allSongs = loadSongs(filename);
		
		Node root = new MyNode(allSongs, Integer.parseInt(args[1])*60);
		//Node root = new MyNode2(allSongs, 20);
		MyBandB branchAndBoundProblem = new MyBandB(root);
		branchAndBoundProblem.branchAndBound(root);
		branchAndBoundProblem.printSolutionTrace();
	}
	

	
	
	private static List<Song>loadSongs(String fileName) {
		String line;
		List<Song> songs = null;
		String[] data;
		
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			songs = new ArrayList<Song>();
			file.readLine(); //Read first line containing total number of songs
			
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
