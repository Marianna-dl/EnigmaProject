import java.awt.Dimension;
import java.awt.Toolkit;

import vue.*;
import model.*;
import controllers.*;


public class Main {
	public static void main(String[] args) {
		Toolkit aTK= Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int large=dim.width;
		int haut=dim.height;
		Rotor r;
		Rotor[] tab=new Rotor[3];
		int[] entier;

		for(int i=0;i<3;i++){
			entier=new int[46];
			for(int j=0; j<entier.length;j++){
				entier[j]=j;
			}
			r=new Rotor(entier, 0);
			tab[i]=r;
			
		}
		
		
		
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		Machine m=new Machine(tab,ref,p);
		

		
		Vue v=new Vue(large, haut,m);

		Controleur controleReglages=new ReglagesControleur(v,m);
		Controleur controleCryptage=new CryptageControleur(v,m);
	}
}
