import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

/**
 * 
 * Classe Controleur
 * @author Marianna
 * 
 *
 */
public class Controleur implements ActionListener, KeyListener{
	private Machine modele;
	private Vue vue;
	
	public Controleur(Vue v, Machine m){
		this.modele=m;
		this.vue=v;
		initListener();
	}
	
	/**
	 * Ajoute les listeners pour certains composants de la vue qui doivent declencher un event
	 */
	public void initListener(){
		this.vue.getTextClear().addKeyListener(this);
		this.vue.getTextCrypt().addKeyListener(this);
		this.vue.getBoutonDecrypte().addActionListener(this);
		this.vue.getBoutonCrypte().addActionListener(this);
		this.vue.getBoutonAppliquer().addActionListener(this);
		
	}
	/**
	 * Event declenche apres un clique sur un bouton
	 */
	public void actionPerformed(ActionEvent e){
		//Si on clique sur le bouton decrypte
		if(e.getSource()==this.vue.getBoutonDecrypte()){
			if(this.vue.getStateCheckbox()){ // On verifie si les parametres des rotors sont connus
				/*String ch=this.vue.getTextCrypt().getText();
				String decryptee=this.modele.decrypter(ch);
				this.vue.getTextCrypt().setText("");
				this.vue.getTextClear().setText(decryptee);*/
				this.vue.getTextClear().setText("decrypte sans parametres");
			}
			else{ // Si ils sont connus, on remet les rotors dans leur position initiale et on decrypte
				rotorInitial();
				String chDecryptee=this.modele.crypter(traiteChaine(this.vue.getTextCrypt().getText()));
				this.vue.getTextClear().setText(chDecryptee);
			}
		}
		else if(e.getSource()==this.vue.getBoutonCrypte()){
			String chaine=traiteChaine(this.vue.getTextClear().getText());
			
			if(lettreExiste(chaine)){
				String cryptee=this.modele.crypter(chaine);
				this.vue.getTextCrypt().setText(cryptee);
			}
		}
		//Si c'est le bouton appliquer qui est clique, on change les parametres d'Enigma (positions rotors)
		else if(e.getSource()==this.vue.getBoutonAppliquer()){
			int r1=-1;
			int r2=-1;
			int r3=-1;
			try {
			    r1=Integer.parseInt(this.vue.getPosRotor1().getText());
			    r2=Integer.parseInt(this.vue.getPosRotor2().getText());
			    r3=Integer.parseInt(this.vue.getPosRotor3().getText());
			} catch(NumberFormatException nfe) {
			     System.out.println("Les positions doivent etre des entiers");
			}
			if(r1>=0 && r1<this.modele.CONVERT.length){
				this.modele.getRotor(0).avancer(46-(this.modele.getRotor(0).getPosition()-r1));
				this.vue.getRotorInitial1().setText(""+r1);
			}
			if(r2>=0 && r2<this.modele.CONVERT.length){
				this.modele.getRotor(1).avancer(46-(this.modele.getRotor(1).getPosition()-r2));
				this.vue.getRotorInitial2().setText(""+r2);
			}
			if(r3>=0 && r3<this.modele.CONVERT.length){
				this.modele.getRotor(2).avancer(46-(this.modele.getRotor(2).getPosition()-r3));
				this.vue.getRotorInitial3().setText(""+r3);
			}
			/*verifie si defaire n'est pas vide alors on défait si on peut*/
			/*verifie si faire n'est pas vide alors on fait si on peut */
			/**/
			JLabel[] couples=this.vue.getCouples();
			String defaireCouple=this.vue.getDestroyCouple().getText().toUpperCase();
			if(defaireCouple.length()==2 && (int)defaireCouple.charAt(0)>=65 && (int)defaireCouple.charAt(0)<=90 
					&& (int)defaireCouple.charAt(1)>=65 && (int)defaireCouple.charAt(1)<=90){
				
				for(int i=0;i<couples.length;i++){
					if(couples[i].getText().equals(defaireCouple)){
						this.vue.getMessageErreur().setText("Couple Defait");
						this.vue.getMessageErreur().setVisible(true);
						break;
					}
				}
			}
			
			
			
			
			
		}
		
	}

	@Override
	/**
	 * Event declenche apres un avoir relache une touche du clavier
	 */
	public void keyReleased(KeyEvent e) {
		resetColor();
		if(e.getSource()==this.vue.getTextClear()){// Si le texte tape est un texte en clair, on le crypte
			char chaine=(traiteChaine(""+e.getKeyChar())).charAt(0);
			if(!(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) && lettreExiste(chaine)){
				char lettreCryptee=this.modele.crypter(chaine);
				this.vue.getTextCrypt().setText(this.vue.getTextCrypt().getText()+lettreCryptee);
				allumeLettre(lettreCryptee);
			}
			else if(!this.vue.getTextClear().getText().equals("") && e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				String ch;
				if(this.vue.getTextClear().getText().length()!=0){
					ch=this.vue.getTextCrypt().getText().substring(0, this.vue.getTextCrypt().getText().length()-1);
					this.vue.getTextCrypt().setText(ch);
				}
				else{
					 ch=this.vue.getTextCrypt().getText().substring(0, 0);	
					 this.vue.getTextCrypt().setText(ch);
				}
				reculerRotor();	
			}
			
			if(this.vue.getTextClear().getText().equals("")){
				rotorInitial();
				this.vue.getTextCrypt().setText("");
			}
		}
		if(e.getSource()==this.vue.getTextCrypt()){ 
			char chaine=(traiteChaine(""+e.getKeyChar())).charAt(0);
			if(!(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) && lettreExiste(chaine)){
				char lettreCryptee=this.modele.crypter(chaine);
				this.vue.getTextClear().setText(this.vue.getTextClear().getText()+lettreCryptee);
				allumeLettre(lettreCryptee); // On colorie en jaune la lettre sortante sur le clavier revelateur
			}
			else if(!this.vue.getTextCrypt().getText().equals("") && e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				System.out.println(this.vue.getTextCrypt().getText().length());
				String ch;
				if(this.vue.getTextCrypt().getText().length()!=0){
					ch=this.vue.getTextClear().getText().substring(0, this.vue.getTextClear().getText().length()-1);
					this.vue.getTextClear().setText(ch);
				}
				else{
					 ch=this.vue.getTextClear().getText().substring(0, 0);	
					 this.vue.getTextClear().setText(ch);
				}
				reculerRotor();
			}
			if(this.vue.getTextCrypt().getText().equals("")){
				rotorInitial();
				this.vue.getTextClear().setText("");
			}
		}
		
	}
	/**
	 * Traite une chaine pour pouvoir la cryptee (supprime les accents et retour chariot)
	 *  @param ch
	 * 			La chaine ou le char qu'on veut traiter 
	 * @return La chaine sans accents, en minuscule et sans retour chariot
	 */		
	public String traiteChaine(String ch){
		ch=ch.toLowerCase();
		ch=ch.replaceAll("[\r\n]+", "");
		ch=ch.replaceAll("è|é|ê|ë", "e");
		ch=ch.replaceAll("à|á|â|ã|ä|å", "a");
		ch=ch.replaceAll("ì|í|î|ï", "a");
		ch=ch.replaceAll("ù|ú|û|ü", "u");
		return ch;
	}
	
	/**
	 * Enleve les couleurs du clavier revelateur
	 */	
	public void resetColor(){
		for(int i=0;i<this.vue.getCases().length;i++){
			this.vue.getCases()[i].setOpaque(false);
			this.vue.getCases()[i].repaint();
		}
	}
	
	/**
	 * Verifie qu'une lettre est cryptable
	 * @param c
	 * 			Le char qu'on veut verifier
	 * @return true si la lettre est bien dans le tableau des lettres cryptables, false sinon
	 */	
	public boolean lettreExiste(char c){
		for(int i=0;i<this.modele.CONVERT.length;i++){ //on Regarde si la lettre est bien une lettre qu'on peut crypter
			if(this.modele.CONVERT[i]==c){	
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifie que toutes les lettres de la chaine sont cryptables
	 * @param s
	 * 			La chaine qu'on veut verifier
	 * @return true si toutes les lettres de la chaine sont bien dans le tableau des lettres cryptables, false sinon
	 */	
	public boolean lettreExiste(String s){
		int occurences=0;
		for(int j=0; j<s.length();j++){
			for(int i=0;i<this.modele.CONVERT.length;i++){ 
				if(this.modele.CONVERT[i]==s.charAt(j)){	
					occurences++;
				}
			}
			if(occurences==s.length()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remet les rotors dans leur position initiale
	 */	
	public void rotorInitial(){
		int posrotor1=Integer.parseInt(this.vue.getRotorInitial1().getText());
		int posrotor2=Integer.parseInt(this.vue.getRotorInitial2().getText());
		int posrotor3=Integer.parseInt(this.vue.getRotorInitial3().getText());
		this.modele.getRotor(0).avancer(this.modele.CONVERT.length-(this.modele.getRotor(0).getPosition()-posrotor1));
		this.modele.getRotor(1).avancer(this.modele.CONVERT.length-(this.modele.getRotor(1).getPosition()-posrotor2));
		this.modele.getRotor(2).avancer(this.modele.CONVERT.length-(this.modele.getRotor(2).getPosition()-posrotor3));	
	}
	
	/**
	 * Permet de reculer un rotor d'un cran
	 */	
	public void reculerRotor(){
		this.modele.getRotor(0).avancer(this.modele.CONVERT.length-1);
		if(this.modele.getRotor(0).getPosition()==45){
			System.out.println("allo");
			this.modele.getRotor(1).avancer(this.modele.CONVERT.length-1);
			if(this.modele.getRotor(1).getPosition()==45){
				this.modele.getRotor(2).avancer(this.modele.CONVERT.length-1);
			}
		}

	}
	
	/**
	 * Met en couleur la lettre sortante du clavier revelateur
	 * 
	 * @param lettre
	 * 			La lettre que l'on veut mettre en couleur
	 */	
	public void allumeLettre(char lettre){
		for(int i=0; i<this.modele.CONVERT.length;i++){
			if(this.modele.CONVERT[i]==lettre){
				this.vue.getCases()[i].setOpaque(true);
				this.vue.getCases()[i].setBackground(Color.YELLOW);
				break;
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {


		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	
}
