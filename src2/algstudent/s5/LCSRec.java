package algstudent.s5;

import java.util.Random;

public class LCSRec {
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	public String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Find a MSC directly by a recursive approach 
	 */
	public String findLongestSubseq(String s1, String s2){
		
		//Base case
		if(s1.length() == 0 || s2.length() == 0) {
			return "";
		}
		else {
			
			char c1 = s1.charAt(s1.length()-1);
			char c2 = s2.charAt(s2.length()-1);
			
			String s1prime = s1.substring(0, s1.length()-1); //S1 without rightmost element
			String s2prime = s2.substring(0, s2.length()-1); //S2 without rightmost element
			
			String l1 = findLongestSubseq(s1prime, s2);
			String l2 = findLongestSubseq(s1, s2prime);
			String l3 = findLongestSubseq(s1prime, s2prime);
			
			int longest = -1;
			if(c1 == c2) {
				l3 = l3 + c2;
				longest = longest(l1,l2,l3);
			}
			else {
				longest = longest(l1,l2,l3);
			}
			
			if(longest == 1) return l1;
			else if(longest == 2) return l2;
			else if(longest == 3) return l3;
			else throw new RuntimeException();
			 
		}
		
		
		 
		
	}
	
	/**
	 * Get the index for the longest of three strings
	 * @param l1 e.g. input L1=MSC(S1’, S2). S1’ S1 without its last char
	 * @param l2 e.g. input L1=MSC(S1, S2'). S2' S2 without its last char
	 * @param l3 e.g. input L3=MSC(S1’, S2’) or L3+1 when both current chars are equal
	 * @return index of the longest string
	 */
	@SuppressWarnings("unused")
	private int longest(String l1, String l2, String l3) {
		
		if(l1.length() >= l2.length() && l1.length() >= l3.length()) {return 1;}
		else if(l2.length() >= l1.length() && l2.length() >= l3.length()) {return 2;}
		else if(l3.length() >= l1.length() && l3.length() >= l2.length()) {return 3;}
		else {return -1;}
		}
	}


