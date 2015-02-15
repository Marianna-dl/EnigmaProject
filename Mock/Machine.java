import java.util.Observer;
import java.util.Observable;

public class Machine extends Observable {
	private String[] tabRotor;
	private String reflecteur;
	private String plug;
	public Machine(){
		this.tabRotor=new String[3];
		this.tabRotor[0]="Rotor 1";
		this.tabRotor[1]="Rotor 2";
		this.tabRotor[2]="Rotor 3";
		this.plug="plugboard";
		this.reflecteur="reflecteur";
	}
	public char crypter(char c){
		return 'z';
	}
	public String decrypter(String c){
		return "chaine decryptee";
	}
	public String getPlugboard(){
		return "get: Plugboard";
	}
	public String getRotor(int i){
		return "get: Rotor "+i;
	}

	public String getPosRotor(int i){
		return ""+(i+1)+"";
	}

	public void up(){
		setChanged();
		notifyObservers();
	
	}

}