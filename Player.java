package Juego;

public class Player extends Cell{
	String direction;
	int arrows;
	int[] pos;
	public Player(int numArrows,int[] pos){
		direction="v";
		arrows=numArrows;
		this.pos=pos;
	}
	public int[] getPos(){
		return pos.clone();
	}
	public String toString(){
		return direction;
	}
	public void rotateCW(){
		switch(direction){
		case "^":direction=">";break;
		case "<":direction="^";break;
		case ">":direction="v";break;
		default:direction="<";
		}
	}
	public void rotateCCW(){
		switch(direction){
		case "^":direction="<";break;
		case "<":direction="v";break;
		case ">":direction="^";break;
		default:direction=">";
		}
	}
	public int[] step(int limit){

		switch(direction){
		case "^":if(pos[0]>0){pos[0]=(pos[0]-1);};break;
		case "<":if(pos[1]>0){pos[1]=(pos[1]-1);};break;
		case ">":if(pos[1]<limit-1){pos[1]=(pos[1]+1);}break;
		default:if(pos[0]<limit-1){pos[0]=(pos[0]+1);};
		}
		return pos;
	}
	public boolean shot(){
		if(arrows > 0){
			arrows=arrows-1;
			return true;
		}else{
			return false;
		}
		
	}
}
