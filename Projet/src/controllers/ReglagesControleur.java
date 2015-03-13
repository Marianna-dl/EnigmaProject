package controllers;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import model.Machine;

import vue.*;

public class ReglagesControleur extends Controleur implements ActionListener, KeyListener {

	public ReglagesControleur(Vue v, Machine m){
		super(v,m);
		initListener();
		
	}
	public void initListener(){
		this.vue.getBoutonAppliquer().addActionListener(this);
		
	}
	public  void actionPerformed(ActionEvent e){
		if(e.getSource()==this.vue.getBoutonAppliquer()){
			updatePosRotor();
			/*verifie si defaire n'est pas vide alors on défait si on peut*/
			/*verifie si faire n'est pas vide alors on fait si on peut */
			/**/
			JLabel[] couples=this.vue.getCouples();
			String couple=this.vue.getDestroyCouple().getText().toUpperCase();
			
			if(verifieCouple(couple)){
				defaireCouple(couples, couple );
			}
			couple=this.vue.getMakeCouple().getText().toUpperCase();
			if(verifieCouple(couple) && compteElement(couples)<10 && coupleExiste(couples, couple) && couple.charAt(0)!=couple.charAt(1)){
				int emplacement=calculEmplacementLibre(couples);
				int premiere=-1;
				for(int i=0;i<this.modele.CONVERT.length;i++){
					if(this.modele.CONVERT[i]==Character.toLowerCase(couple.charAt(0))){
						premiere=i;
						}
					}
					int deuxieme=premiere+((int)couple.charAt(1)-(int)couple.charAt(0));
					this.modele.getPlugboard().setLetter(premiere, deuxieme);
					couples[emplacement].setText(couple);		
				}
				if(compteElement(couples)!=10){
					afficheErreur("Il doit y avoir 10 couples", true);
					enableDisableTextInput(false);
				}
				else{
					afficheErreur("",false);	
					enableDisableTextInput(true);
				}		
			}
	}
	
	public boolean verifieCouple(String couple){
		int i=0;
		do{
			if(couple.length()!=2){
				return false;
			}
			else if((int)couple.charAt(i)<65 || (int)couple.charAt(i)>90){
				return false;
			}
			i++;
		}while(i<couple.length());
		return true;
		
	}
	
	public void afficheErreur(String message, boolean bool){
		this.vue.getMessageErreur().setText(message);
		this.vue.getMessageErreur().setVisible(bool);	
	}
	
	
	public void enableDisableTextInput(boolean bool){
		if(bool){
			this.vue.getTextClear().setEnabled(true);
			this.vue.getTextCrypt().setEnabled(true);
			this.vue.getTextCrypt().setBackground(Color.white);
			this.vue.getTextClear().setBackground(Color.white);
		}
		else{
			this.vue.getTextClear().setEnabled(false);
			this.vue.getTextClear().setBackground(Color.gray);
			this.vue.getTextCrypt().setEnabled(false);
			this.vue.getTextCrypt().setBackground(Color.gray);
		}
	}
	
	
	
	public int compteElement(JLabel[] t){
		int count=0;
		for(int i=0;i<t.length;i++){
			if(t[i].getText()!=""){
				count++;
			}
		}
		return count;
	}
	
	public boolean coupleExiste(JLabel[] tabCouples, String couple){
		int i=0;
		do{
			if(tabCouples[i].getText()!=""){
				for(int j=0; j<couple.length();j++){
					if(tabCouples[i].getText().charAt(0)==couple.charAt(j) || tabCouples[i].getText().charAt(1)==couple.charAt(j)){
						return false;
					}
				}
			}
			i++;
		}while(i<tabCouples.length);
		return true;
	}
	
	public int getIndiceCouple(JLabel[] tabCouples, String couple){
		for(int i=0;i<tabCouples.length;i++){
			if(tabCouples[i].getText().equals(couple)){
				return i;
			}
		}
		return -1;
	}
	
	public void defaireCouple(JLabel[] tabCouples, String couple){
		int i=getIndiceCouple(tabCouples,couple);
		if(i != -1){
		for(int j=0; j<this.modele.CONVERT.length;j++){
				if(Character.toLowerCase(couple.charAt(0))==this.modele.CONVERT[j]){
					this.modele.getPlugboard().defaireCouple(j);
					tabCouples[i].setText("");
					break;
					}
				}	
			}	
		}
	
	public int calculEmplacementLibre(JLabel[] couples){
		for(int i=0;i<couples.length;i++){
			if(couples[i].getText()==""){
				return i;
			}
		}
		return -1;
	}
	
	public void updatePosRotor(){
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
	}
	public  void keyTyped(KeyEvent e){
		
	}
	public  void keyPressed(KeyEvent e){
		
	}
	public  void keyReleased(KeyEvent e){
		
	}
}
