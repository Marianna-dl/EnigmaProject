public class Reflecteur {
	private int[] tabCouple;
	
	public Reflecteur(){
		this.tabCouple = new int[]{39,23,27,12,7,30,5,35,13,38,21,4,9,25,44,32,20,29,42,17,11,46,2,33,14,45,3,18,34,6,36,16,24,29,8,31,43,10,41,1,39,19,37,15,26,22};
	}
	public int getCorrespondance(int i){
		return tabCouple[i];
	}
}
