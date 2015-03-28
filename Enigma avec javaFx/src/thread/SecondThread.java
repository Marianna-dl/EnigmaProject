package thread;
import java.io.IOException;

import model.Decrypt;

public class SecondThread extends Thread {
	//variable qui va pemettre de savoir ˆ quel mot va commencer le thread
	private Decrypt decryptage;
	private boolean paused=true;
	private String aDecrypter="";
	private String chDecryptee="";
	private int[] posRotors;
	private float indiceMax;

	//constructeur du thread avec comme paramŽtres le nom du thread et la position du mot dans le dico
	public SecondThread(Decrypt d) throws IOException{
		this.decryptage=new Decrypt(d);
		this.posRotors=new int[3];
	}
	
	public void pause(){
		paused=true;
	}
	public void unpause(){
		System.out.println("unpause");
		paused=false;
		synchronized(this){
			this.notify();
		}
	}
	
	public int[] getPosRotors(){
			return this.posRotors;
	}
	
	public void setString(String s){
		this.aDecrypter=s;
	}
	 public float getIndice(){
		 return indiceMax;
	 }
	//on doit mettre ici l'algo de decryptage
	public void run(){
		 if (paused) {
			 synchronized(this)
			 {
				 try {
					 this.wait();
				 } catch (InterruptedException ie) {
					 ie.printStackTrace();
				 }
			 }
		 }
			decryptage.rotorInitial();
			decryptage.afficherPos();
			System.out.println(this.decryptage.calculIndiceCo(aDecrypter));
			String chDecryptee=this.decryptage.getMachine().crypter(aDecrypter);
			chDecryptee=chDecryptee.replaceAll("[^a-zA-Z]", "");
			float indice=decryptage.calculIndiceCo(chDecryptee);
			indiceMax=indice;
			System.out.println(indiceMax);
			decryptage.rotorInitial();
			this.decryptage.getMachine().getRotor(0).avancer(this.decryptage.getMachine().CONVERT.length-1);
			this.decryptage.getMachine().getRotor(1).avancer(this.decryptage.getMachine().CONVERT.length-1);
			this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-1);
			
			decryptage.afficherPos();
			int tRotor=45;
			int pRotor=45;
			int sRotor=45;
			
			while(tRotor>=0 && !paused){
				System.out.println("boucle 1");
				sRotor=45;
				while(sRotor>=0 && !paused){
					System.out.println("boucle 2");
					this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+tRotor);
					this.decryptage.getMachine().getRotor(0).avancer(this.decryptage.getMachine().CONVERT.length-1);
					pRotor=45;
					while(pRotor>=0 && !paused){
						System.out.println("boucle 3");
						this.decryptage.getMachine().getRotor(1).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(1).getPosition())+sRotor);
						this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+tRotor);
						System.out.println("r1 "+this.decryptage.getMachine().getRotor(0).getPosition());
						System.out.println("r2 "+this.decryptage.getMachine().getRotor(1).getPosition());
						System.out.println("r3 "+this.decryptage.getMachine().getRotor(2).getPosition());
						    chDecryptee=this.decryptage.getMachine().crypter(aDecrypter);
						    chDecryptee=chDecryptee.replaceAll("[^a-z]", "");
							indice=this.decryptage.calculIndiceCo(chDecryptee);
							if(indice>indiceMax){//On garde l'indice de coincidence le plus eleve, ca veut dire que le texte est proche du francais
								indiceMax=indice;
								this.posRotors[0]=pRotor; //On sauvegarde la position des rotors pour pouvoir decrypter apres
								this.posRotors[1]=sRotor;
								this.posRotors[2]=tRotor;
								System.out.println("indiceMax "+indice);
							}
							if(indiceMax>=0.065 && chDecryptee.length()>50){//On peut essayer de finir le boucle plus vite. On peut supposer que si l'indice est superieur a 0.065 alors 
													//qu'il est tres probable que ce soit bien notre texte decrypte
	
								 pause();
								
							}
							
							this.decryptage.getMachine().getRotor(0).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(0).getPosition())+pRotor);
							pRotor--;
					}
					this.decryptage.getMachine().getRotor(1).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(1).getPosition())+1);
					sRotor--;
				}
				this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+1);
				tRotor--;
		}
	}
}
