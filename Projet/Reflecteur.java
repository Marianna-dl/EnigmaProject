public class Reflecteur {
	private int[] tabCouple;
	
	public Reflecteur(){
		this.tabCouple = new int[]{13,7,21,16,17,12,15,1,18,10,9,24,5,0,25,6,3,4,8,22,23,2,19,20,11,14};
	}
	public int getCorrespondance(int i){
		return tabCouple[i];
	}
}
