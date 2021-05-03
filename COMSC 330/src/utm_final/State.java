package utm_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class State {

	int nombre;
	ArrayList<char[]> transitions;
	//-----------------------
	// transition format:
	// 0: input char
	// 1: destination state
	// 2: output character
	// 3: read head direction
	//-----------------------

	public State() {

	}

	public State(int nombre, ArrayList<char[]> transitions) {
		this.nombre = nombre;
		this.transitions = transitions;
	}

	// public ArrayList<Character> Transitions() {
	// return null;
	// }

	public ArrayList<char[]> getTransitions() {
		return transitions;
	}

	public int getNombre() {
		return nombre;
	}
}
