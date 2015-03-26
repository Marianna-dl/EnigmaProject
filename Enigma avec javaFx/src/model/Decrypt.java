package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Decrypt {
	private Machine m;
	private String[] dico;
	
	public Decrypt(Machine mach,String file) throws IOException{
		BufferedReader b=null;
		m=mach;
		try {
			 b = new BufferedReader(new FileReader(new File(file)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp;
		dico = new String[604];
		int i=0;
		while((temp=b.readLine())!= null){
			dico[i]=temp.trim();
			i++;
		}
	}
	/*public void decrypter(String chaine){
		ArrayList<String> possible = new ArrayList<String>();
		String ch=chaine;
		//Comparaison de chaque mot du dicto pour voir si on peut le placer dans la chaine 
		for(int i=0;i<600;i++){
			int debut=0,fin=dico[i].length();
			boolean added=false;
			while(fin < chaine.length() && !added){
				if(this.compareLettre(chaine.substring(debut, fin),dico[i])){
					System.out.println(dico[i]);
					System.out.println(chaine.substring(debut, fin));
					//peut-etre rajouter les positions o% le mot peut-Ãªtre placer dans la chaine
					possible.add(dico[i]);
					added=true;
				}
				debut++;
				fin++;
			}
		}
		/*for(int i=0;i<possible.size();i++){
			System.out.println(possible.get(i));
		}*/
		/*//cryptage des mots possibles 
		for(int i=0;i<possible.size();i++){
			boolean correspond =false;
			int debut=0,fin=possible.get(i).length();
			while(m.getRotor(2).getPosition()<46 && !correspond){
				String temp = m.crypter(possible.get(i));
				
				//A faire (changer chaque rotor + changer plugboard + comparer un mot a plusieurs endroits dans la chaine)
			}
		}

		return chaine;
	}*/
	public boolean compareLettre(String ch1, String ch2){
		for(int i=0;i<ch1.length();i++){
			if(ch1.charAt(i)==ch2.charAt(i)){
				return false;
			}
		}
		return true;
	}

	public String decrypter(String s){
		String decryptee="impossible de decrypter la phrase";
		int trouve=-1;
		String mot="";
		 afficherPos();
		 // a tester avec une phrase contenant le mot message pour le moment sinon, ça prend trop de temps :/
		int i=0;
		while(i<this.dico.length && trouve==-1){
			System.out.println(dico[i]);
			rotorInitial();// A chaque mot, on remet les rotors a 0 0 0 
			System.out.print("numero de mot : "+i+" /610");
			trouve=cryptagePossibleMot(dico[i],s);//on crypte toutes les possibilites pour le mot en cours
			i++;
		}
		if(trouve!=-1){
			mot=dico[i-1];
			System.out.println(mot);
			System.out.println("t "+trouve);
			System.out.println("avant reglage");
			regleRotor(s,mot,trouve);
			System.out.println("avant decryptage");
			afficherPos();
			decryptee=this.m.crypter(s);//Les rotors sont regles, on peut decrypter
			System.out.println(decryptee);
		}

		System.out.println("rotor finaux :");
		afficherPos();
		return decryptee;
	}
	
	//Met les rotor en position 0 0 0
	public void rotorInitial(){
	
		this.m.getRotor(0).avancer(this.m.CONVERT.length-(this.m.getRotor(0).getPosition()));
		this.m.getRotor(1).avancer(this.m.CONVERT.length-(this.m.getRotor(1).getPosition()));
		this.m.getRotor(2).avancer(this.m.CONVERT.length-(this.m.getRotor(2).getPosition()));	
	}
	
	//test les 46^3 possibilites pour chaque mot donne et verifie si il est contenu dans la chaine cryptee
	public int cryptagePossibleMot(String mot, String s){
		String m="";
			int r;
			for(int tRotor=0; tRotor<46;tRotor++){	
				for(int sRotor=0; sRotor<46;sRotor++){
					this.m.getRotor(2).avancer(this.m.CONVERT.length-(this.m.getRotor(2).getPosition()-tRotor));
					this.m.getRotor(0).avancer(this.m.CONVERT.length-(this.m.getRotor(0).getPosition()));
					for(int pRotor=0; pRotor<46;pRotor++){					
						this.m.getRotor(1).avancer(this.m.CONVERT.length-(this.m.getRotor(1).getPosition()-sRotor));
						if(mot.equals("jeune")){
						System.out.println(this.m.getRotor(0).getPosition());
						}
						 m=this.m.crypter(mot);
						 if(this.m.getRotor(0).getPosition()==17){
							 System.out.println(m);
							 System.out.println(s);
						 }
						 r=s.indexOf(m);
						 if(r!=-1){
							 System.out.println("trouve "+"r "+r);
							 return r;
						 }
							
						 this.m.getRotor(0).avancer(this.m.CONVERT.length-(m.length()-1));
					}
					 this.m.getRotor(1).avancer(1);
				}
				 this.m.getRotor(2).avancer(1);
			}	
			 
			 return -1;
	}
	
	//Regle la position des rotors selon la longueur de la chaine pour pouvoir ensuite decrypter
	public void regleRotor(String s, String m, int pos){
		System.out.println("pos "+this.m.getRotor(0).getPosition());
		this.m.getRotor(0).avancer(this.m.CONVERT.length-(s.substring(0, pos).length()+m.length()));
		if(this.m.getRotor(0).getPosition()>=46-s.length()){
			this.m.getRotor(1).avancer(this.m.CONVERT.length-1);
			if(this.m.getRotor(1).getPosition()==45){
				this.m.getRotor(2).avancer(this.m.CONVERT.length-1);
			}
		}
	}
	
	
	public void afficherPos(){
		System.out.println(this.m.getRotor(0).getPosition());
		System.out.println(this.m.getRotor(1).getPosition());
		System.out.println(this.m.getRotor(2).getPosition());
	}
	
	
}
