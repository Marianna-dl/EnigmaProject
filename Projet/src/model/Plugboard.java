package model;
import java.util.Observable;


public class Plugboard extends Observable {

	private int[] plugTab;
	
	public Plugboard(){
		//Initialisation par defaut (les lettres correspondent a  elles memes)
		//this.plugTab = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45};
		//	Initialisation avec des valeurs melangees (20 couples de lettres)
		 	this.plugTab = new int[]{0,1,2,3,4,5,6,7,8,9,10,33,15,28,25,12,16,35,30,23,20,32,22,19,34,14,27,26,13,29,18,31,21,11,24,17,36,37,38,39,40,41,42,43,44,45};
		 
		 
	}
	public int getLetter(int i){
		return this.plugTab[i];
	}
	public void setLetter(int i,int j){
		 this.plugTab[i]=j;
		 this.plugTab[j]=i;
	}
	public void defaireCouple(int i){
		 int tmp=this.plugTab[i];
		 this.plugTab[i]=i;
		 this.plugTab[tmp]=tmp;
		 setChanged();

		 notifyObservers();
	}
	public void modifierPlugboard(int[] tab){
		this.plugTab=tab;
	}
}
