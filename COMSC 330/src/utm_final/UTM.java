package utm_final;

import java.util.ArrayList;
import java.util.Scanner;

public class UTM {

	static ArrayList<State> statemaster = new ArrayList<State>();
	static State current;
	static char[] Tape;
	static int readHead;
	static Scanner Tanner = new Scanner(System.in);
	static String TuringNumber = "";
	static String[] TN = new String[] { "10", "110", "1110", "11110", "111110", "1111110", "11111110", "111111110",
			"111111111", "11111111110" };

	public static void main(String[] args) {
		
		System.out.print("This program simulates a Universal Turing Machine (UTM). You, as the user, must enter a Turing Machine "
				+ "\nand a data stream, up to 75 characters long, for this machine. The input alphabet which can be read by this "
				+ "\nUTM consists of {a, b, c} and the tape alphabet consists of {^, [, ], a, b, c} where Δ represents a blank "
				+ "\nspace, the [ and ] represent left and right bounds of a LBA, respectively. The machine will handle no more "
				+ "\nthan 10 and no less than 2 states. State 1 will correspond to the start state and state 2 will correspond to "
				+ "\nthe halting state. Please identify the following information regarding your Turing Machine:" + "\n" +"\n");
		
		System.out.println("How many states are in your turing machine?");
		int states = Tanner.nextInt();
		State newstate = new State();

		for (int i = 1; i <= states; i++) {

			System.out.println("How many transitions would you like in state " + i);
			int trans = Tanner.nextInt();
			// create x number of transition objects for the

			System.out.print("Enter transitions: \n");
			int count = 0;
			// adding the transition objects to the transitions ArrayList
			// then add those transitions to the specfic state object
			ArrayList<char[]> transitions = new ArrayList<char[]>();
			while (count != trans) {
				String input1 = Tanner.next();
				char[] nextTransition = new char[4];
				for (int a = 0; a < 4; a++) {
					nextTransition[a] = input1.charAt(a);
				}
				transitions.add(nextTransition);
				count++;
			}
			newstate = new State(i, transitions);
			statemaster.add(newstate);
		}
		runTM();
	}

	public static void runTM() {

		System.out.println("Input the data stream for your machine. This should be a string with no spaces or "
				+ "\ncommas, only containing characters from the tape alphabet (i.e. [abbc^b^aacb^c]):");
		String input = Tanner.next();

		Tape = new char[3 * input.length() + 2];

		// Fill array with blank characters
		for (int j = 0; j < Tape.length; j++) {
			Tape[j] = '^';
		}

		for (int a = 1; a <= input.length(); a++) {
			Tape[input.length() + a] = input.charAt(a - 1);
		}
		// Tape[0] = '[';
		// Tape[input.length() + 1] = ']';
		current = statemaster.get(0);
		readHead = input.length() + 1;

		while (current.getNombre() != 2) {

			if (readHead == 'a') {
				TuringNumber += TN[0];
			} else if (readHead == 'b') {
				TuringNumber += TN[1];
			} else if (readHead == 'x') {
				TuringNumber += TN[2];
			} else
				TuringNumber += TN[3];

			char[] trans = new char[0];
			for (char[] t : current.getTransitions()) {
				if (t[0] == Tape[readHead]) {
					trans = t;
					break;
				}
			}
			if (trans.length == 0) {
				System.out.println("No transition found for '" + Tape[readHead] + "'");
				break;

			} else {
				for (State s : statemaster) {
					if (s.getNombre() == Integer.parseInt("" + trans[1])) {
						current = s;
					}
				}
				TuringNumber += TN[current.getNombre()];
				System.out.print("State: " + current.getNombre() + "\n");

				if (current.getNombre() != Integer.parseInt("" + trans[1])) {
					
					System.out.println("No state found with number '" + trans[1] + "'");
					for (int z = 0; z < trans.length; z++) {
						System.out.print(trans[z]);
					}
					break;
				}
				Tape[readHead] = trans[2];

				if (trans[2] == 'a') {
					TuringNumber += TN[0];
				} else if (trans[2] == 'b') {
					TuringNumber += TN[1];
				} else if (trans[2] == 'x') {
					TuringNumber += TN[2];
				} else
					TuringNumber += TN[3];

				
				if (trans[3] == 'L') {
					readHead--;
					TuringNumber += TN[1];
				} else if (trans[3] == 'R') {
					readHead++;
					TuringNumber += TN[0];
				} else
					System.out.println("Unknown direction: '" + trans[3] + "'");

				if (readHead < 0 || readHead > Tape.length) {
					System.out.println("ERROR: read head out of bounds: " + readHead);
				}
				
			}
			System.out.print("Encoded Transistion: "+ TuringNumber + "\n");
			TuringNumber = "";
			
			for (int c = 0; c < Tape.length; c++) {
				System.out.print(Tape[c]);
			}
			System.out.print("\n" + "\n");
		}
		//System.out.print(TuringNumber);
	}
}
