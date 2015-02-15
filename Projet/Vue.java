import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Observer;
import java.util.Observable;

public class Vue extends JFrame implements Observer{
	
	
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
	private JPanel panelAffichage2;
	private JPanel panelAffichage3;
	
	private JLabel clavier;
	
	
	private JLabel positionActuelle;
	private JLabel posAcRotor1;
	private JLabel posAcRotor2;
	private JLabel posAcRotor3;
	private Machine m;
	

	
	
	public Vue(int w, int h, Machine ma){
		
		
		this.m=ma;
		m.addObserver(this);

		this.decrypter=new JButton("decrypter");
		this.appliquer=new JButton("appliquer");
		this.textClair=new JTextArea(10,30);
		this.textCrypte=new JTextArea(10,30);
		this.posRotor1=new JTextField(2);
		this.posRotor2=new JTextField(2);
		this.posRotor3=new JTextField(2);
		this.clairLabel=new JLabel("txt Clair");
		this.crypteLabel=new JLabel("txt crypte");
		this.rotor1=new JLabel("");
		this.rotor2=new JLabel("");
		this.rotor3=new JLabel("");
		
		
		this.positionActuelle=new JLabel("Positions Actuelle des Rotors");
		this.posAcRotor1=new JLabel("");
		this.posAcRotor2=new JLabel("");
		this.posAcRotor3=new JLabel("");
		this.clavier=new JLabel("LE CLAVIER REVELATEUR");
		
		this.initialise();
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

	public void initialise()	{
		this.c=this.getContentPane();
		this.panelAffichage=new JPanel(new GridLayout(3,1,4,2));
		this.panelAffichage2=new JPanel(new GridLayout(2,1,4,2));
		this.panelAffichage3=new JPanel();
		
		
		JPanel ptext = new JPanel(new GridLayout(1,2,500,2));
		ptext.add(this.clairLabel);ptext.add(this.crypteLabel);
		
		panelAffichage2.add(ptext);
		
		JPanel ptext1 = new JPanel(new GridLayout(1,2,5,2));
		ptext1.add(this.textClair);ptext1.add(this.decrypter);ptext1.add(this.textCrypte);
		
		panelAffichage2.add(ptext1);
		
	


		JPanel pRot = new JPanel(new GridLayout(1,3,4,2));
		Border bordTemps=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Configurations Rotors Initiale"),BorderFactory.createEmptyBorder(20,10,3,2));
		pRot.setBorder(bordTemps);
		Border bordMM=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordSS=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordCC=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
		this.rotor1.setBorder(bordMM);
		this.rotor2.setBorder(bordSS);
		this.rotor3.setBorder(bordCC);
		
		pRot.add(this.rotor1);pRot.add(this.rotor2);
		pRot.add(this.rotor3);
		panelAffichage.add(pRot);
		
		

		
		JPanel pRot1 = new JPanel(new GridLayout(1,3,4,2));
		Border bordTemps1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Positions Actuelle"),BorderFactory.createEmptyBorder(20,10,3,2));
		pRot1.setBorder(bordTemps1);
		Border bordMM1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordSS1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordCC1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
		this.posAcRotor1.setBorder(bordMM1);
		this.posAcRotor2.setBorder(bordSS1);
		this.posAcRotor3.setBorder(bordCC1);
		
		pRot1.add(this.posAcRotor1);pRot1.add(this.posAcRotor2);
		pRot1.add(this.posAcRotor3);
		

		panelAffichage.add(pRot1);
		
		

		
		JPanel pRot2 = new JPanel(new GridLayout(1,3,4,2));
		Border bordTemps2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Positionner vos Rotors"),BorderFactory.createEmptyBorder(20,10,3,2));
		pRot2.setBorder(bordTemps2);
		Border bordMM2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordSS2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
		Border bordCC2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
		this.posRotor1.setBorder(bordMM2);
		this.posRotor2.setBorder(bordSS2);
		this.posRotor3.setBorder(bordCC2);
		
		pRot2.add(this.posRotor1);pRot2.add(this.posRotor2);
		pRot2.add(this.posRotor3);pRot2.add(this.appliquer);
		panelAffichage.add(pRot2);

		this.panelAffichage3.add(this.clavier);
		this.c.add(this.panelAffichage2,BorderLayout.SOUTH);
		this.c.add(this.panelAffichage,BorderLayout.WEST);
		this.c.add(this.panelAffichage3,BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		

		

		
		
		Border bord=BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Réglages de la machine enigma"),
				BorderFactory.createEmptyBorder(20,10,3,2));
		panelAffichage.setBorder(bord);
		
		Border bord1=BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Commandes"),
				BorderFactory.createEmptyBorder(20,10,3,2));
		panelAffichage2.setBorder(bord1);
		
		
		Border bord2=BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Lettre cryptée"),
				BorderFactory.createEmptyBorder(20,10,3,2));
		panelAffichage3.setBorder(bord2);
	}

	public void centreEcran(int w, int h){
		this.setBounds((w/2)/2,(h/2)/2, w/2, h/2);
		this.setSize(w,h);
}
	public void update(Observable o,Object ob){
		this.rotor1.setText(m.getPosRotor(0));
		this.rotor2.setText(m.getPosRotor(1));
		this.rotor3.setText(m.getPosRotor(2));
		this.posAcRotor1.setText(m.getPosRotor(1));
		this.posAcRotor2.setText(m.getPosRotor(2));
		this.posAcRotor3.setText(m.getPosRotor(3));
		this.repaint();
		
}
}
