import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Vue extends JFrame{

    
	private Container c;
	
	private JButton decrypter ; 
	private JButton appliquer ; 
	
	private JTextArea textClair;
	private JTextArea textCrypte;	
	
	private JTextField posRotor1;
	private JTextField posRotor2;
	private JTextField posRotor3;
	
	private JLabel clairLabel;
	private JLabel crypteLabel;
	private JLabel rotor1;
	private JLabel rotor2;
	private JLabel rotor3;
    
	private JPanel panelReglages;

	private JPanel panelTexte;
	private JPanel panelAffichage;
	
    public Vue(int w, int h){
    	this.c=this.getContentPane();
    	this.decrypter=new JButton("decrypter");
    	this.appliquer=new JButton("appliquer");   
    	this.textClair=new JTextArea(10,30);
    	this.textCrypte=new JTextArea(10,30);   	
    	this.posRotor1=new JTextField(2);
       	this.posRotor2=new JTextField(2);
       	this.posRotor3=new JTextField(2);
       	
       	this.clairLabel=new JLabel("txt Clair");
       	this.crypteLabel=new JLabel("txt crypte");
       	this.rotor1=new JLabel("Pos R1");
       	this.rotor2=new JLabel("Pos R2");
       	this.rotor3=new JLabel("Pos R3");
       	
       	this.panelAffichage=new JPanel();
       	this.panelAffichage.add(this.decrypter,BorderLayout.CENTER);
       	this.panelAffichage.add(this.appliquer);
       	this.panelAffichage.add(this.textClair);
       	this.panelAffichage.add(this.textCrypte);
       	this.panelAffichage.add(this.posRotor1);
       	this.panelAffichage.add(this.posRotor2);
       	this.panelAffichage.add(this.posRotor3);
       	this.panelAffichage.add(this.clairLabel);
       	this.panelAffichage.add(this.crypteLabel);
       	this.panelAffichage.add(this.rotor1);
       	this.panelAffichage.add(this.rotor2);
       	this.panelAffichage.add(this.rotor3);
       	
       	this.c.add(this.panelAffichage,BorderLayout.CENTER);
    	
    	
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
    	this.centreEcran(w, h);
		this.show();
     
    }
    
    public JTextArea getTextClear(){
    	return this.textClair;
    }
    
    public JTextArea getTextCrypt(){
    	return this.textCrypte;
    }
    
    public JButton getBoutonDecrypte(){
    	return this.decrypter;
    }
    
    public JButton getBoutonAppliquer(){
    	return this.appliquer;
    }
    
    
    public JTextField getPosRotor1(){
    	return this.posRotor1;
    }
    public JTextField getPosRotor2(){
    	return this.posRotor2;
    }
    public JTextField getPosRotor3(){
    	return this.posRotor3;
    }
    
    
	public void centreEcran(int w, int h){
		this.setBounds((w/2)/2,(h/2)/2, w/2, h/2);
	}
	 public void metAJour(){
		 //this.couleurPan.setColor(Color.white);
		 this.repaint();
		 //this.affichage.setText(jeu.toString());
	 }
	

}
