package au.com.victorliafook.pokerhandsorter.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;

import au.com.victorliafook.pokerhandsorter.Hand;
import au.com.victorliafook.pokerhandsorter.Sorter;


public class SorterTest {
	
	@Test
	public void testWinner() {
		System.out.println("##### Unit test of the method winner() ######");
		Hand handOne = new Hand("9C 9D 8D 7C 3C".split(" "));
		Hand handTwo = new Hand("2S KD TH 9H 8H".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();
		
		System.out.println(handOne.toString() + " "
				+ handTwo.toString());
		assertEquals("Player 1 should have won this time. 1 pair vs high card.", 1, Sorter.winner(handOne, handTwo));
		System.out.println("Player 1 won with ONE PAIR. PASSED.\n");
		
		handOne = new Hand("TC 2C JC 7C 3C".split(" "));
		handTwo = new Hand("9H 9C 9S 7D 9D".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();
		
		System.out.println(handOne.toString() + " "
				+ handTwo.toString());
		assertEquals("Player 2 should have won this time. flush vs four of a kind.", 2, Sorter.winner(handOne, handTwo));
		System.out.println("Player 2 won with FOUR OF A KIND. PASSED.\n");
		
		handOne = new Hand("9C 9D 8D 7C TC".split(" "));
		handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();
		
		System.out.println(handOne.toString() + " "
				+ handTwo.toString());
		assertEquals("Player 1 should have won this time. 1 pair(high card tie-break) vs 1 pair.", 1, Sorter.winner(handOne, handTwo));
		System.out.println("Player 1 won with 1 PAIR - HIGH CARD. PASSED.\n");
		
		handOne = new Hand("9C 9D 8D 7C 3C".split(" "));
		handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();
		
		System.out.println(handOne.toString() + " "
				+ handTwo.toString());
		assertEquals("It should have returned a TIE. The tie-brakes described in the problem are not sufficient for this test case.", -1, Sorter.winner(handOne, handTwo));
		System.out.println("TIE. PASSED.\n");
		
		handOne = new Hand("TC 2C TS 2S TD".split(" "));
		handTwo = new Hand("AH KH JH QH TH".split(" "));
		handOne.sortCards();
		handTwo.sortCards();
		handOne.evaluate();
		handTwo.evaluate();
		
		System.out.println(handOne.toString() + " "
				+ handTwo.toString());
		assertEquals("Player 2 should have won this time. Royal flush vs Full house.", 2, Sorter.winner(handOne, handTwo));
		System.out.println("Player 2 won with ROYAL FLUSH. PASSED.\n");
		
		
		System.out.println("##### Unit test of the method winner() OK! ######\n");
	}
	
	
	@Test
	public void testMain() {
		System.out.println("##### Testing the sample file ######");
		int winsPlayer1 = 0;
		int winsPlayer2 = 0;
		
		InputStream is = Sorter.class.getResourceAsStream("resources/poker-hands.txt");
		InputStreamReader iss = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(iss);
		
		try {
			while(true){
				String input = reader.readLine();
				if (input == null) {
					break;
				}
				if(!input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")){
					System.out.println("Wrong input format.");
					break;
				}				
				String[] cards = input.split(" ");
				String[] handOneStr = Arrays.copyOfRange(cards, 0, 5);
				String[] handTwoStr = Arrays.copyOfRange(cards, 5, 10);
				
				Hand handOne = new Hand(handOneStr);
				Hand handTwo = new Hand(handTwoStr);
				handOne.sortCards();
				handTwo.sortCards();
				
				handOne.evaluate();
				handTwo.evaluate();
				int res = Sorter.winner(handOne, handTwo);
				if (res == 1) {
					winsPlayer1++;
				} else if (res == 2) {
					winsPlayer2++;
				} else {
					System.out.println(Sorter.TIE);
				}
				
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		assertEquals("Player 1 must have exactly 263 wins for this test case file.", winsPlayer1, 263);
		System.out.println("Player 1: 263 wins. PASSED.\n");
		assertEquals("Player 2 must have exactly 237 wins for this test case file.", winsPlayer2, 237);
		System.out.println("Player 2: 237 wins. PASSED.\n");
		System.out.println("##### Sample file ok! ######\n");
	}

	
	
	
}
