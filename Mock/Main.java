import java.awt.Dimension;
import java.awt.Toolkit;


public class Main {
	public static void main(String[] args) {
		Toolkit aTK= Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int large=dim.width;
		int haut=dim.height;
		Rotor r;
		Rotor[] tab=new Rotor[3];
		int[] entier=new int[46];
		for(int j=0; j<entier.length;j++){
			entier[j]=j;
		}
		for(int i=0;i<3;i++){
			r=new Rotor(entier, 1);
			tab[i]=r;
			
		}
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		Machine m=new Machine(tab,ref,p);

		Vue v=new Vue(large, haut,m);

		Controleur controleur=new Controleur(v,m);
	}
}
