package thread;
import java.io.IOException;

import model.Decrypt;

public class FirstThread extends Thread {
	//variable qui va pemettre de savoir à quel mot va commencer le thread
	private Decrypt decryptage;
	private boolean paused=true;
	private String aDecrypter="";
	private String chDecryptee="";
	private int[] posRotors;
	private float indiceMax;
	//constructeur du thread avec comme paramétres le nom du thread et la position du mot dans le dico
	public FirstThread(Decrypt d) throws IOException{
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
	
	 public String getChaine(){
		 return chDecryptee;
	 }
	 
	 public float getIndice(){
		 return indiceMax;
	 }
	 
	/**
	 * On decrypte la chaine dans le run en calculant l'indice de coincidence et en gardant l'indice le plus eleve 
	 * On stock la position des rotors selon l'indiceMax de coincidence 
	*/	
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
			String chDecryptee=this.decryptage.getMachine().crypter(aDecrypter);
			chDecryptee=chDecryptee.replaceAll("[^a-z]", ""); //On enleve tout ce qui n'est pas une lettre car l'indice de coincidence est base sur l'alphabet de 26 lettres
			float indice=decryptage.calculIndiceCo(chDecryptee);
			indiceMax=indice;
			System.out.println("i "+indiceMax);
			
			decryptage.rotorInitial();
			decryptage.afficherPos();
			int tRotor=0;
			int pRotor=0;
			int sRotor=0;
			//On parcourt 46^3 au plus
			while(tRotor<46 && !paused){
				sRotor=0;
				while(sRotor<46 && !paused){
					this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+tRotor);
					this.decryptage.getMachine().getRotor(0).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(0).getPosition()));
					pRotor=0;
					while(pRotor<46 && !paused){
						this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+tRotor);
						this.decryptage.getMachine().getRotor(1).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(1).getPosition())+sRotor);
						//System.out.println("r1 "+this.decryptage.getMachine().getRotor(0).getPosition());
						//System.out.println("r2 "+this.decryptage.getMachine().getRotor(1).getPosition());
						//System.out.println("r3 "+this.decryptage.getMachine().getRotor(2).getPosition());
						    chDecryptee=this.decryptage.getMachine().crypter(aDecrypter);
							chDecryptee=chDecryptee.replaceAll("[^a-z]", "");
							indice=this.decryptage.calculIndiceCo(chDecryptee);
							if(indice<0.073 && indice>=indiceMax){//On garde l'indice de coincidence le plus eleve, ca veut dire que le texte est proche du francais
								indiceMax=indice;
								this.posRotors[0]=pRotor; //On sauvegarde la position des rotors pour pouvoir decrypter apres
								this.posRotors[1]=sRotor;
								this.posRotors[2]=tRotor;
								System.out.println("indiceMax "+indice);
								
							}
							if(indiceMax>=0.072 && chDecryptee.length()>=100){//On peut essayer de finir la boucle plus vite. On peut supposer que si l'indice est superieur a 0.065 alors 
													//lorsque la longueur du texte est grande, qu'il est tres probable que ce soit bien notre texte decrypte
								 pause(); //si c'est bon, on stop le thread
								
							}
							
							pRotor++;
							this.decryptage.getMachine().getRotor(0).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(0).getPosition())+pRotor);

					}
					this.decryptage.getMachine().getRotor(1).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(1).getPosition())+1);
					sRotor++;
				}
				this.decryptage.getMachine().getRotor(2).avancer(this.decryptage.getMachine().CONVERT.length-(this.decryptage.getMachine().getRotor(2).getPosition())+1);
				tRotor++;
		}
	}
}
