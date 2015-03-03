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
		int[] entier;

		for(int i=0;i<3;i++){
			entier=new int[46];
			for(int j=0; j<entier.length;j++){
				entier[j]=j;
			}
			r=new Rotor(entier, 0);
			tab[i]=r;
			
		}
		
	/*	Rotor[] tab=new Rotor[3];
		int[] entier1=new int[46],entier2=new int[46],entier3=new int[46];
		for(int j=0; j<entier1.length;j++){
		entier1[j]=j;
		entier2[j]=j;
		entier3[j]=j;
		}
		tab[0]=new Rotor(entier1, 0);
		tab[1]=new Rotor(entier2, 0);
		tab[2]=new Rotor(entier3, 0);*/
		
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		Machine m=new Machine(tab,ref,p);
		
		/*m.getRotor(1).avancer(2);
		String crypt = m.crypter("une phrase.");
		System.out.println(crypt);
		
		m.getRotor(0).avancer(46-crypt.length());
		m.getRotor(1).avancer(46);
		m.getRotor(2).avancer(46);
		System.out.println("Position rotor 1 : "+m.getRotor(0).getPosition());
		System.out.println("Position rotor 2 : "+m.getRotor(1).getPosition());
		System.out.println("Position rotor 3 : "+m.getRotor(2).getPosition());*/

		//Normalement, on obtient à nouveau "une phrase." (ou ce qu'on a mis à la place dans crypt) 
		//System.out.println(m.crypter(crypt));
		
		Vue v=new Vue(large, haut,m);

		Controleur controleur=new Controleur(v,m);
	}
}
