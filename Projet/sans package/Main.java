import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import vue.*;
import model.*;
import controllers.*;


public class Main {
	public static void main(String[] args) throws IOException {
		Toolkit aTK= Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int large=dim.width;
		int haut=dim.height;
		Rotor r;
		int[] entier;
		Rotor[] tab=new Rotor[3];
		//int[] entier;

		/*for(int i=0;i<3;i++){
			entier=new int[46];
			for(int j=0; j<entier.length;j++){
				entier[j]=j;
			}
			r=new Rotor(entier, 0);
			tab[i]=r;
			
		}*/
		Integer[] array = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45};
		Collections.shuffle(Arrays.asList(array));
		
		for(int j=0; j<3;j++){
			entier=new int[46];
			Collections.shuffle(Arrays.asList(array));
			for(int i=0;i<array.length;i++){
				entier[i]=Integer.valueOf(array[i]);
			}
			r=new Rotor(entier, 0);
			tab[j]=r;
		}


		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		Machine m=new Machine(tab,ref,p);
		
		Decrypt dec=new Decrypt(m,"dico.txt");
		
		Vue v=new Vue(large, haut,m);

		Controleur controleReglages=new ReglagesControleur(v,m);
		Controleur controleCryptage=new CryptageControleur(v,m,dec);
	}
}
