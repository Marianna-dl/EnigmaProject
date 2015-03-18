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
		dico = new String[600];
		int i=0;
		while((temp=b.readLine())!= null){
			dico[i]=temp.trim();
			i++;
		}
	}
	public String decrypter(String chaine){
		ArrayList<String> possible = new ArrayList<String>();
		//Comparaison de chaque mot du dicto pour voir si on peut le placer dans la chaine 
		for(int i=0;i<600;i++){
			int debut=0,fin=dico[i].length();
			boolean added=false;
			while(fin < chaine.length() && !added){
				if(this.compareLettre(chaine.substring(debut, fin),dico[i])){
					//peut-etre rajouter les positions o% le mot peut-être placer dans la chaine
					possible.add(dico[i]);
					added=true;
				}
				debut++;
				fin++;
			}
		}
		//cryptage des mots possibles 
		for(int i=0;i<possible.size();i++){
			boolean correspond =false;
			int debut=0,fin=possible.get(i).length();
			while(m.getRotor(2).getPosition()<46 && !correspond){
				String temp = m.crypter(possible.get(i));
				
				//A faire (changer chaque rotor + changer plugboard + comparer un mot a plusieurs endroits dans la chaine)
			}
		}

		return chaine;
	}
	public boolean compareLettre(String ch1, String ch2){
		for(int i=0;i<ch1.length();i++){
			if(ch1.charAt(i)==ch2.charAt(i)){
				return false;
			}
		}
		return true;
	}
}
