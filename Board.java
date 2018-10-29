package Juego;

import java.util.Random;

public class Board {
	Cell[][] board;
	boolean gold;
	int dimension;
	Player player;
	boolean endGame;
	
	public Board(int size, int arrows, int numPits){
		board = new Cell[size][size];
		dimension=size;
		initializeBoard(numPits, arrows);
	}
	public void initializeBoard(int numPits, int arrows){
		endGame=false;
		gold=false;
		int initPos[]={0,0};
		player = new Player(arrows,initPos);
		//Initialize Cells
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				board[i][j]=new Cell();
			}
		}
		//places player at the first position
		board[0][0]=player;
		//random cells positioning
		//placing Pits
		placeCells(numPits,"P");
		//Placing Wumpus
		placeCells(1,"W");
		//Placing GOLD
		placeCells(1,"G");	
		getAdjacents(player.getPos());
	}
	
	public void step(){
		int[] prevPos = player.getPos();
		int[] newPosition = player.step(dimension);
		//check if you die
		if(board[newPosition[0]][newPosition[1]].toString().equals("W") || board[newPosition[0]][newPosition[1]].toString().equals("P") ){
			System.out.println("YOU DIED");
			endGame=true;
		}
		else{
			//clear previous cell
			board[prevPos[0]][prevPos[1]]=new Cell(" ");
			//gold?
			if(board[newPosition[0]][newPosition[1]].toString().equals("G")){
				System.out.println("I found a gold nugget! Now I have to get out of here");
				gold=true;
			}
			//new Position
			board[newPosition[0]][newPosition[1]]=player;
			getAdjacents(newPosition);
		}
	}
	
	public void getAdjacents(int[] pos){
		String messages="";
		//check all cells if exist
		if(pos[0]>0){
			messages=messages+board[pos[0]-1][pos[1]].toString();
		}
		if(pos[0]<dimension-1){
			messages=messages+board[pos[0]+1][pos[1]].toString();
		}
		if(pos[1]>0){
			messages=messages+board[pos[0]][pos[1]-1].toString();
		}
		if(pos[1]<dimension-1){
			messages=messages+board[pos[0]][pos[1]+1].toString();
		}
		//if one of them contains pit or wumpus you have an special alert
		if(messages.contains("P")){
			System.out.println("I can feel a breeze close here, I have to be careful");
		}
		if(messages.contains("W")){
			System.out.println("That stinky noise... The Wumpus should be around");
		}
	}
	
	public void rotateCW(){
		player.rotateCW();
	}
	
	public void rotateCCW(){
		player.rotateCCW();
	}
	
	public void shot(){
		int[] pos = player.getPos();
		//check if arrows available
		if(player.shot()){
			//get direction of the player
			System.out.println(player.toString());
			switch(player.toString()){
			case "^":checkWumpus(pos[1],pos[1]+1,0,pos[0]-1);break;
			case "<":checkWumpus(0,pos[1]-1,pos[0],pos[0]+1);break;
			case ">":checkWumpus(pos[1]+1,dimension,pos[0],pos[0]+1);break;
			default:checkWumpus(pos[1],pos[1]+1,pos[0]+1,dimension);break;
			}
		}
	}
	
	public void checkWumpus(int minX, int maxX, int minY, int maxY){
		boolean wumpus=false;
		for(int i=minY;i<maxY && !wumpus;i++){
			for(int j=minX;j<maxX && !wumpus;j++){
				if(board[i][j].toString().equals("W")){
					System.out.println("WAAAARGH! I think I killed the Wumpus!");
					wumpus=true;
					board[i][j]=new Cell();
				}
			}
		}
	}
	
	public boolean endGame(){
		//if you died or you have gold and you are at the first cell
		if(endGame == true || (gold==true && player.getPos()[0]==0 && player.getPos()[1]==0)){
			return true;
		}
		else{return false;}
	}
	
	public void placeCells(int num, String name){
		Random rgen = new Random();
		int[] auxPos;
		int randomPosition;
		for(int i=0;i<num;i++){
			randomPosition = rgen.nextInt((dimension*dimension)-1)+1;
			auxPos=convertToCoords(randomPosition);
			//avoid random collision
			if(board[auxPos[0]][auxPos[1]].toString().equals(" ")){
				board[auxPos[0]][auxPos[1]]=new Cell(name);
			} else
			{
				i=i-1;
			}
		}
	}
	//convert unidimentional array to a matrix n*n coords
	public int[] convertToCoords(int pos){
		int[] aux = new int[2];
		aux[0] = pos/dimension;
		aux[1] = pos%dimension;
		return aux;
	}
	
	//shows board
	public String toString(){
		String aux="";
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				aux=aux+"["+board[i][j].toString()+"]";
			}
			aux=aux+"\n";
		}
		return aux;
	}
}
