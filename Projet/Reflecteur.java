public class Reflecteur {
	private int[] tabCouple;
	
	public Reflecteur(){
		this.tabCouple = new int[]{38,22,26,11,6,29,4,34,12,37,20,3,8,24,43,31,19,28,41,16,10,45,1,32,13,44,2,33,17,5,35,15,23,27,7,30,42,9,0,40,39,18,36,14,25,21};
	}
	public int getCorrespondance(int i){
		return tabCouple[i];
	}
}
