import java.util.Observable;


public class Machine extends Observable {

	private Plugboard plugboard;
	private Rotor[] tabRotor;
	private Reflecteur reflecteur;
	static final char[] CONVERT = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ','.','!','?',',',':','(',')','\'','"'};
	
	public Machine (Rotor[] tabR, Reflecteur ref, Plugboard plug){
		this.plugboard=plug;
		this.tabRotor=tabR;
		this.reflecteur=ref;
	}
	public char crypter (char c){
		int i= 0,nbLetter = 0;
		while(i<CONVERT.length && c!=CONVERT[i]){
			i++;
		}
		if(i<CONVERT.length){
			nbLetter=i;
		}
		else{
			return c;
		}
		int nbInter=plugboard.getLetter(nbLetter);
		for (Rotor r : tabRotor){
			nbInter=r.getCorrespondance(nbInter, true);
		}
		nbInter=reflecteur.getCorrespondance(nbInter);
		for (Rotor r : tabRotor){
			nbInter=r.getCorrespondance(nbInter, false);
		}
		tabRotor[0].avancer(1);
		if(tabRotor[0].getPosition()==0){
			tabRotor[1].avancer(1);
			if(tabRotor[1].getPosition()==0){
				tabRotor[2].avancer(1);
			}
		}
		return CONVERT[plugboard.getLetter(nbInter)];
	}
	public String crypter (String s){
		String retour="";
		for (int i=0 ; i<s.length() ; i++){
			retour+=crypter(s.charAt(i));
		}
		return retour;
	}
	public Plugboard getPlugboard(){
		return this.plugboard;
	}
	public Rotor getRotor(int i){
		return tabRotor[i];
	}
	
	public char[] getConvert(){
		return CONVERT;
	}
	
}