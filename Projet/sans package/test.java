
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Création des rotors avec 3 tableaux d'entiers diffèrents mais avec le même contenu
		Rotor[] tab=new Rotor[3];
		int[] entier1=new int[46],entier2=new int[46],entier3=new int[46];
		for(int j=0; j<entier1.length;j++){
		entier1[j]=j;
		entier2[j]=j;
		entier3[j]=j;
		}
		tab[0]=new Rotor(entier1, 0);
		tab[1]=new Rotor(entier2, 0);
		tab[2]=new Rotor(entier3, 0);
		
		//Création de la machine avec les valeurs par défaut
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		Machine m=new Machine(tab,ref,p);
		
		//On place les rotors à 0 2 0
		m.getRotor(1).avancer(2);
		
		//On crypte et affiche la chaine cryptée (normalement zdokmrov5ma avec ces paramètres par "une phrase.")
		String crypt = m.crypter("une phrase.");
		System.out.println(crypt);
		
		//On re-règle les rotors sur 0 2 0
		m.getRotor(0).avancer(46-crypt.length());
		m.getRotor(1).avancer(46);
		m.getRotor(2).avancer(46);
		System.out.println("Position rotor 1 : "+m.getRotor(0).getPosition());
		System.out.println("Position rotor 2 : "+m.getRotor(1).getPosition());
		System.out.println("Position rotor 3 : "+m.getRotor(2).getPosition());

		//Normalement, on obtient à nouveau "une phrase." (ou ce qu'on a mis à la place dans crypt) 
		System.out.println(m.crypter(crypt));
		
		/*Un essai avec une nouvelle machine
		Rotor[] tabBis=new Rotor[3];
		int[] entierb1=new int[46],entierb2=new int[46],entierb3=new int[46];
		for(int j=0; j<entierb1.length;j++){
		entierb1[j]=j;
		entierb2[j]=j;
		entierb3[j]=j;
		}
		tabBis[0]=new Rotor(entierb1, 0);
		tabBis[1]=new Rotor(entierb2, 0);
		tabBis[2]=new Rotor(entierb3, 0);
		Reflecteur refBis = new Reflecteur();
		Plugboard pBis=new Plugboard();
		Machine mBis=new Machine(tabBis,refBis,pBis);
		
		mBis.getRotor(1).avancer(2);
		System.out.println("pos : "+mBis.getRotor(0).getPosition());
		System.out.println(mBis.crypter(crypt));
		*/
	}

}
