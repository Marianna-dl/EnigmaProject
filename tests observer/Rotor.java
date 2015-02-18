import java.util.Observable;


public class Rotor  extends Observable{
	private int[] parcoursAller;
	private int[] parcoursRetour;
	private int position;
	
	public Rotor(int[] tab,int pos){
		this.parcoursAller=tab;
		this.parcoursRetour= new int[26];
		this.createMirror();
		this.position= pos;
	}
	public void createMirror(){
		for(int i=0;i<parcoursAller.length;i++){
			parcoursRetour[parcoursAller[i]]=i;
		}
	}
	public int getCorrespondance(int i, boolean b){
		if(b){
			return parcoursAller[i];
		}
		else{
			return parcoursRetour[i];
		}
	}
	public int getPosition(){
		return position;
	}
	public void setPosition(int i){
		System.out.println("set pos");
		this.position=i;
		/************ NOTIFIE SON OBSERVER (donc la classe vue)***********/
		setChanged();
		notifyObservers();
		/***************************************************************/
	}
	public void avancer(int i){
		int k=0;
		while(k<i){
			int temp; 
			for(int j=0;j<parcoursAller.length/2;j++){
				temp=parcoursAller[j];
				parcoursAller[j]=parcoursAller[parcoursAller.length-1-j];
				parcoursAller[parcoursAller.length-1-j]=temp;
				temp=parcoursRetour[j];
				parcoursRetour[j]=parcoursRetour[parcoursRetour.length-1-j];
				parcoursRetour[parcoursRetour.length-1-j]=temp;
			}
			k++;
		}
	}
}
