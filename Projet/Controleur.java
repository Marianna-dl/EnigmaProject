import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
				int posrotor1=Integer.parseInt(this.vue.getRotorInitial1().getText());
				int posrotor2=Integer.parseInt(this.vue.getRotorInitial2().getText());
				int posrotor3=Integer.parseInt(this.vue.getRotorInitial3().getText());
				this.modele.getRotor(0).setPosition(posrotor1);
				this.modele.getRotor(1).setPosition(posrotor2);
				this.modele.getRotor(2).setPosition(posrotor3);
				
				String chDecryptee=this.modele.crypter(this.vue.getTextCrypt().getText());
				this.vue.getTextClear().setText(chDecryptee);
			}
		}
		//Si c'est le bouton appliquer qui est clique, on change les parametres d'Enigma (positions rotors)
		else if(e.getSource()==this.vue.getBoutonAppliquer()){
			int r1=0;
			int r2=0;
			int r3=0;
			try {
			    r1=Integer.parseInt(this.vue.getPosRotor1().getText());
			    r2=Integer.parseInt(this.vue.getPosRotor2().getText());
			    r3=Integer.parseInt(this.vue.getPosRotor3().getText());
			} catch(NumberFormatException nfe) {
			     System.out.println("Les positions doivent etre des entiers");
			}
			if(r1>=1 && r1<=26){
				this.modele.getRotor(0).setPosition(r1);
				this.vue.getRotorInitial1().setText(""+r1);
				//System.out.println(r1);
			}
			if(r2>=1 && r2<=26){
				this.modele.getRotor(1).setPosition(r2);
				this.vue.getRotorInitial2().setText(""+r2);
				//System.out.println(r2);
			}
			if(r3>=1 && r3<=26){
				this.modele.getRotor(2).setPosition(r3);
				this.vue.getRotorInitial3().setText(""+r3);
				//System.out.println(r3);
			}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {


		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	@Override
	/**
	 * Event declenche apres un avoir relache une touche du clavier
	 */
	public void keyReleased(KeyEvent e) {
		for(int i=1;i<this.vue.getCases().length;i++){
			this.vue.getCases()[i].setOpaque(false);
			this.vue.getCases()[i].repaint();
		}
		if(e.getSource()==this.vue.getTextClear()){// Si le texte tape est un texte en clair, on le crypte
			char chaine=Character.toLowerCase(e.getKeyChar());
			int trouve=-1;
			for(int i=0;i<this.modele.CONVERT.length;i++){ //on Regarde si la lettre est bien une lettre qu'on peut crypter
				if(this.modele.CONVERT[i]==chaine){
					trouve=i;
					break;
				}
			}
			if(!(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) && trouve>=0){
				char lettreCryptee=this.modele.crypter(chaine);
				this.vue.getTextCrypt().setText(this.vue.getTextCrypt().getText()+lettreCryptee);
				this.vue.getCases()[trouve].setOpaque(true);
				this.vue.getCases()[trouve].setBackground(Color.YELLOW);

			}
			else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				String ch;
				if(this.vue.getTextClear().getText().length()!=0){
					ch=this.vue.getTextCrypt().getText().substring(0, this.vue.getTextCrypt().getText().length()-1);
					this.vue.getTextCrypt().setText(ch);
				}
				else{
					 ch=this.vue.getTextCrypt().getText().substring(0, 0);	
					 this.vue.getTextCrypt().setText(ch);
				}
			}
		}
		if(e.getSource()==this.vue.getTextCrypt()){ // Si c'est un texte crypte, on vide le textarea clair
			if(this.vue.getTextClear().getText().length()>0){
				this.vue.getTextClear().setText("");
				this.vue.getTextCrypt().setText(""+e.getKeyChar());
			}
			/*if(e.getKeyChar()!=' ' && !(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
				this.vue.getTextCrypt().setText(this.vue.getTextCrypt().getText()+e.getKeyChar());
			}*/
		}
		
	}


}
