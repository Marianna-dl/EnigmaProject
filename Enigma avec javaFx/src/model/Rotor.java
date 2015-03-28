package model;


import java.util.Observable;

public class Rotor extends Observable  {
	private int[] parcoursAller;
	private int[] parcoursRetour;
	private int position;
	
	public Rotor(int[] tab,int pos){
		this.parcoursAller=tab;
		this.parcoursRetour= new int[46];
		this.createMirror();
		this.avancer(pos);
		this.position=pos;
	}
	/**
	 * Constructeur par copie, sert notamment pour que les threads n'accedent pas aux memes ressources
	 */	
	public Rotor(Rotor r){
		this.parcoursAller=new int[46];
		for(int i=0;i<46;i++){
			this.parcoursAller[i]=r.getParcoursAller()[i];
		}
		this.parcoursRetour= new int[46];
		this.createMirror();
		this.avancer(r.getPosition());
		this.position=r.getPosition();
	}
	

	public int[] getParcoursAller(){
		return this.parcoursAller;
	}
	
	/**
	 * Permet de faire le parcours retour en fonction du parcours alle
	 */	
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
		this.position=i%46;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Avance les rotors et modifie les entrees/sorties des rotors
	 */	
	public void avancer(int i){
		int k=0;
		while(k<i){
			int temp1,temp2;
			temp1=parcoursAller[1];
			parcoursAller[1]=parcoursAller[0];
			for(int j=1;j<parcoursAller.length-1;j++){
				temp2=parcoursAller[j+1];
				parcoursAller[j+1]=temp1;
				temp1=temp2;
			}
			parcoursAller[0]=temp1;
			k++;
			this.setPosition(this.getPosition()+1);
		}
		this.createMirror();
	}
}
