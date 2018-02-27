package bj;

public class Deal extends Cards {
static 	 int h=7;				
static 	 int[] player = new int[h];		//player
static 	 int[] com = new int[h];		//Computer
static 	 int P = 0;				// player's array number
static 	 int C = 0;
 	 char x;				//player vs computer

	void flip(char x) {	
		//Flip through the card one by one
		if (MAX < h*2)
			shaffle();
		for (int i = 0; i < 1; i++) {
			int n = (int) (Math.random() * 52) + 1;
			if (cards[n] != 99) {		
				//99 has been substituted as a sign
				if (x == 'p') {
					player[P] = n;
					P++;
				}
				if (x == 'c') {
					com[C] = n;
					C++;
				}
				cards[n] = 99;	//Assign 99 as a sign
				MAX--;
			} else
				i--;
		}
	}

	void hands() {				// First deal cards
		flip('c');
		flip('p');
		flip('c');
		info();
	}

	void shaffle() {			// Card set shuffle
		System.out.println("Shuffle cards");
		MAX = 52;
		cards_m();
	}

	void yourHand() {
		for (int b = 0; b < player.length; b++) {
			if (player[b] != 0)
				System.out.print(cards_m[player[b]] + " ");
		}
		System.out.println();
	}
	void comHand() {
		for (int b = 0; b < com.length; b++) {
			if (com[b] != 0)
				System.out.print(cards_m[com[b]] + " ");
		}
		System.out.println();
	}

	void comHand(int i) {
		for (int b = 0; b < i; b++) {
			if (com[b] != 0)
				System.out.print(cards_m[com[b]] + " ");
		}
		System.out.println();
	}

	void initHands() {			// Initializing your hand
		for (int i = 0; i < player.length; i++) {
			player[i] = 0;
			com[i] = 0;
			P = 0;
			C = 0;
		}
	}

	int cal(char i) {			//Hand calculation
		int[] n = player;
		if (i == 'c')			//'c' for computer
			n = com;
		if (i == 'p')			//'p' for player
			n = player;

		int p = 0;
		for (int d : n) {
			if (d == 0)
				p += 0;
			if (d >= 1 && d <= 9)
				p += d;
			if (d >= 10 && d <= 13)
				p += 10;
			if (d >= 14 && d <= 22)
				p += d - RANK;
			if (d >= 23 && d <= 26)
				p += 10;
			if (d >= 27 && d <= 35)
				p += d - RANK * 2;
			if (d >= 36 && d <= 39)
				p += 10;
			if (d >= 40 && d <= 48)
				p += d - RANK * 3;
			if (d >= 49 && d <= 52)
				p += 10;
		}
		return p;
	}

	void info() { // Display of various information
	// comment the lines out which you like to know about.

		System.out.print("Hand of PC ");
		comHand(1); // show first card
		System.out.print("Your hand ");
		yourHand(); //

		System.out.println("Total of your card= " + cal('p')); // you
	}
}