package Juego;

public class Cell {
	String name;
	
	public Cell(){
		this.name = " ";
	}
	public Cell(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
}
