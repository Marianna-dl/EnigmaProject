
public class Plugboard {

	private int[] plugTab;
	
	public Plugboard(){
		//Initialisation par défaut (les lettres correspondent à elles mêmes)
		this.plugTab = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		/*	Initialisation avec des valeurs mélangées (20 couples de lettres)
		 *	this.plugTab = new int[]{22,4,17,14,1,5,25,19,12,9,21,11,8,23,3,16,15,13,2,18,7,20,10,0,13,24,6};
		 */
		 
	}
	public int getLetter(int i){
		return this.plugTab[i];
	}
	public void modifierPlugboard(int[] tab){
		this.plugTab=tab;
	}
}
