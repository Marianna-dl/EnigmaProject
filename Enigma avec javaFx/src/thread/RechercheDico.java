
public class RechercheDico extends Thread {
	//variable qui va pemettre de savoir à quel mot va commencer le thread
	private int nb;
	
	//constructeur du thread avec comme paramétres le nom du thread et la position du mot dans le dico
	public RechercheDico(String nom, int posMot){
		super(nom);
		this.nb=posMot;
	}
	
	//on doit mettre ici l'algo de décryptage
	public void run(){
		while(nb!=150){
			nb+=1;
			System.out.println("         "+this.getName()+" "+this.nb);
		}
		System.out.println("Le thread "+this.getName()+" a trouvé le mot");
		
		this.interrupt();
	}
}
