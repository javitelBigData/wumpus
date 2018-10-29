package Juego;

import java.util.Scanner;

public class Juego {
	static Scanner keyboard =new Scanner(System.in);
	static int size;
	static int numPits;
	static int numArrows;
	String aux="";
	static String instruction;	
	static Board board;
	
	public static void main(String[] Args) {
		System.out.println("Welcome to Hunt the Wumpus\nPress enter key to Start");
		keyboard.nextLine();
		
		//set number of cells, defines one of sides of an n*n square
		System.out.println("Define size of the field");
		size = insertNumber();
		while(numPits>size){
			System.out.println("field to small, it shoulb be bigger than 3");
			size = insertNumber();
		}

		//set number of arrows the player will have
		System.out.println("Define number of arrows");
		numArrows = insertNumber();
		
		//set number of of pits the board will have
		System.out.println("Define number of pits");
		numPits = insertNumber();
		while(numPits>size){
			System.out.println("Too many pits, insert up to size of the board");
			numPits = insertNumber();
		}

		board = new Board(size, numArrows, numPits);
		
		while(board.endGame()==false){
			System.out.println(mask(board.toString()));
			instruction=keyboard.nextLine();
			switch(instruction){
			case("w"):board.step();break;//steps forward
			case("e"):board.rotateCW();break;//rotates right(clockwise)
			case("q"):board.rotateCCW();break;//rotates left(counterclockwise)
			case("r"):board.shot();break;//shots
			case("?"):System.out.println(board.toString());break;//shows all cells
			default:
			}
		}
	}
	public static String mask(String board){
		return board.replace("W"," ").replace("P", " ").replace("G"," ");
	}
	
	public static int insertNumber(){
		String aux = "";
		//set number of pits in the game can not be more than size (f.e. 4 for 4x4 board) in order to avoid too much pits in one game
		while(!aux.matches("[0-9]+")){
			aux=keyboard.nextLine();
		}
		return Integer.parseInt(aux);
	}
}
