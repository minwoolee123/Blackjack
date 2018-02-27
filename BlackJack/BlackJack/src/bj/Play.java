package bj;

import java.util.Scanner;

public class Play extends Deal {

	Scanner in = new Scanner(System.in);

	int betAmount = 0;
	int playerAmount = 1000;
	int win = 0;
	int loss = 0;
	int totalGames = 1;
	int numBlackjacks = 0;
	boolean gameOver = false;

	void play() {
		
		do {
			System.out.format("Please enter bet amount ( <= %d): ", playerAmount);
			betAmount = in.nextInt();
		} while (betAmount > playerAmount);

		in.nextLine();

		while (true) {
			if (!gameOver) {
				System.out.println("   Enter hit(h) / Stand(s):  ");
				String inValue = in.nextLine();

				int val = -1;
				if (inValue.trim().toLowerCase().contains("Hit") || inValue.trim().toLowerCase().contains("h"))
					val = 0;
				if (inValue.trim().toLowerCase().contains("Stand") || inValue.trim().toLowerCase().contains("s"))
					val = 1;

				switch (val) {
				case 0:
					flip('p');
					info();

					if (cal('p') == 21) {
						sleep(500);
						System.out.println("!!!!!!!!! BLACKJACK !!!!!!!!\n");
						winlose();
						break;
					}

					if (cal('p') > 21)
						winlose();
					break;

				case 1:
					winlose();
					break;

				}

				System.out.println();
			} else
				break;
		}
	}

	void winlose() {

		while (true) {
			
			// player burst
			if (cal('p') > 21) {
				sleep(500);
				loss++;
				playerAmount -= betAmount;
				System.out.format("**** COM wins ****  Total amount left = %d \n\n", playerAmount);

				sleep(500);
				startNewGame();
				break;
			}

			if (cal('c') > 21) {
				System.out.print("COM's hand ");
				comHand(h);
				System.out.println("Total of COM card = " + cal('c') + " burst!");
				sleep(500);
				playerAmount += betAmount;
				win++;
				System.out.format("  # # # #You win # # # #   Total amount left = %d\n\n", playerAmount);
				System.out.println();
				sleep(500);
				startNewGame();
				break;
			}

			if (cal('c') > cal('p') && cal('c') < 22) {
				System.out.print("COM's hand ");
				comHand(h);
				System.out.println("Total of COM card = " + cal('c'));
				sleep(500);
				loss++;
				playerAmount -= betAmount;
				System.out.println("###### COM wins #######  Total amount left = %d\n\n, playerAmount");
				System.out.println();
				sleep(500);
				startNewGame();
				break;
			}

			if (cal('p') > cal('c') && cal('c') >= 17) {
				System.out.print("COM's hand  ");
				comHand(h);
				System.out.println("COM card total= " + cal('c'));
				sleep(500);
				win++;
				playerAmount = betAmount;
				System.out.format(" **** You win ****  Total amount left = %d\n\n", playerAmount);
				System.out.println();
				sleep(500);
				startNewGame();
				break;
			}
			if (cal('c') == cal('p')) {
				System.out.print("COM's hand ");
				comHand(h);
				System.out.println("COM card total= " + cal('c'));
				System.out.format("**** It is a draw ****  Total amount left = %d\n\n", playerAmount);
				System.out.println();
				sleep(500);
				startNewGame();
				break;
			}

			System.out.print("COM's hand ");
			comHand(h);
			System.out.println("Total of COM card = " + cal('c'));
			sleep(1000);

			if (cal('c') < 17) {
				flip('c');
			}

		}
	}

	void sleep(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	void printStats() {
		double ratio = win * 1.0 / totalGames;
		double winLossRatio;

		if (loss > 0) {
			winLossRatio = (win * 1.0) / loss;
		} else {
			winLossRatio = 1;
		}

		System.out.format("Win Ratio = %f\n", ratio);
		System.out.format("Win Loss Ratio = %f\n", winLossRatio);
		System.out.format("Total Blackjack = %d\n", numBlackjacks);

	}

	void startNewGame() {
		if (playerAmount > 0) {
			System.out.format("Do you want to play again (yes/no): ");
			String next = in.nextLine();
			if (next.toLowerCase().contains("no")) {
				gameOver = true;
				System.out.println("**Game reset**");
				printStats();
				return;
			}
			do {
				System.out.format("Please enter bet amount ( <= %d, args): ", playerAmount);
				betAmount = in.nextInt();
			} while (betAmount > playerAmount);
			in.nextLine();
			totalGames++;
			System.out.println("********* New Game started **********");
			initHands();
			hands();

		} else {
			gameOver = true;
			System.out.println("Player's amount is gone");
			printStats();

		}
	}

}