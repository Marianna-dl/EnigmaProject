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
    Checkbox box;
    //on pose le tableau de labels qui va nous permetre de colorer la lettre cryptée
    private JLabel cases[];
    
    
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
        this.clairLabel=new JLabel("Texte en clair");
        this.crypteLabel=new JLabel("Texte crypté");
        this.rotor1=new JLabel("");
        this.rotor2=new JLabel("");
        this.rotor3=new JLabel("");
        
        
        this.positionActuelle=new JLabel("Positions Actuelle des Rotors");
        this.posAcRotor1=new JLabel("");
        this.posAcRotor2=new JLabel("");
        this.posAcRotor3=new JLabel("");
        this.clavier=new JLabel("LE CLAVIER REVELATEUR");
        
        //on initialise le checkbox pour différencier les cryptages avec parametres connus ou inconnus
        this.box=new Checkbox("Parametres inconnus",false);
        
        //on initialise le tableau de label du clavier révélateur
        cases = new JLabel[41];
        cases[0]=new JLabel("A");cases[1]=new JLabel("B");cases[2]=new JLabel("C");cases[3]=new JLabel("D");
        cases[4]=new JLabel("E");cases[5]=new JLabel("F");cases[6]=new JLabel("G");cases[7]=new JLabel("H");
        cases[8]=new JLabel("I");cases[9]=new JLabel("J");cases[10]=new JLabel("K");cases[11]=new JLabel("L");
        cases[12]=new JLabel("M");cases[13]=new JLabel("N");cases[14]=new JLabel("O");cases[15]=new JLabel("P");
        cases[16]=new JLabel("Q");cases[17]=new JLabel("R");cases[18]=new JLabel("S");cases[19]=new JLabel("T");
        cases[20]=new JLabel("U");cases[21]=new JLabel("V");cases[22]=new JLabel("W");cases[23]=new JLabel("X");
        cases[24]=new JLabel("Y");cases[25]=new JLabel("Z");
        
        cases[26]=new JLabel("0");cases[27]=new JLabel("1");cases[28]=new JLabel("2");
        cases[29]=new JLabel("3");cases[30]=new JLabel("4");cases[31]=new JLabel("5");
        cases[32]=new JLabel("6");cases[33]=new JLabel("7");cases[34]=new JLabel("8");
        cases[35]=new JLabel("9");
        
        cases[36]=new JLabel("Espace");cases[37]=new JLabel(".");cases[38]=new JLabel(",");
        cases[39]=new JLabel("?");cases[40]=new JLabel("!");
        
        /*code pour changer de couleur, ici il s'agit de la lettre A
         cases[0].setOpaque(true);
         cases[0].setBackground(Color.yellow); */
        
        
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
        
        
        this.c.add(this.initialisePanelBas());
        this.c.add(this.initialisePanelGauche());
        this.c.add(this.initialisePanelHaut());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }
    
    public JPanel initialisePanelBas(){
        
        this.panelAffichage2=new JPanel(new GridLayout(3,1,4,2));
        this.panelAffichage2.setBounds(380, 380, 990, 400);
        JPanel ptext = new JPanel(new GridLayout(1,1,5,2));
        ptext.add(this.crypteLabel);ptext.add(this.clairLabel);
        
        panelAffichage2.add(ptext);
        
        JPanel ptext1 = new JPanel(new GridLayout(1,1,5,2));
        ptext1.add(this.textCrypte);ptext1.add(this.textClair);
        
        panelAffichage2.add(ptext1);
        
        
        
        JPanel ptext2 = new JPanel();
        
        ptext2.add(this.box);ptext2.add(this.decrypter);
        
        panelAffichage2.add(ptext2);
        
        Border bord1=BorderFactory.createCompoundBorder(
                                                        BorderFactory.createTitledBorder("Commandes"),
                                                        BorderFactory.createEmptyBorder(20,10,3,2));
        panelAffichage2.setBorder(bord1);
        
        
        
        return (panelAffichage2);
        
    }
    
    public JPanel initialisePanelHaut(){
        
        
        this.panelAffichage3=new JPanel();
        
        JPanel j=new JPanel(new GridLayout(2,2,100,1));
        
        Border bord2=BorderFactory.createCompoundBorder(
                                                        BorderFactory.createTitledBorder("Clavier révélateur"),
                                                        BorderFactory.createEmptyBorder(20,10,3,2));
        
        j.setBorder(bord2);
        j.setBounds(300, 400,10,10);
        
        JPanel j1=new JPanel(new GridLayout(4,7,5,3));
        JPanel j2=new JPanel(new GridLayout(3,4,5,3));
        JPanel j3=new JPanel(new GridLayout(1,1,5,3));
        
        
        
        
        j1.add(cases[0]);
        j1.add(cases[1]);
        j1.add(cases[2]);
        j1.add(cases[3]);
        j1.add(cases[4]);
        j1.add(cases[5]);
        j1.add(cases[6]);
        j1.add(cases[7]);
        j1.add(cases[8]);
        j1.add(cases[9]);
        j1.add(cases[10]);
        j1.add(cases[11]);
        j1.add(cases[12]);
        j1.add(cases[13]);
        j1.add(cases[14]);
        j1.add(cases[15]);
        j1.add(cases[16]);
        j1.add(cases[17]);
        j1.add(cases[18]);
        j1.add(cases[19]);
        j1.add(cases[20]);
        j1.add(cases[21]);
        j1.add(cases[22]);
        j1.add(cases[23]);
        j1.add(cases[24]);
        j1.add(cases[25]);
        
        j2.add(cases[26]);
        j2.add(cases[27]);
        j2.add(cases[28]);
        j2.add(cases[29]);
        j2.add(cases[30]);
        j2.add(cases[31]);
        j2.add(cases[32]);
        j2.add(cases[33]);
        j2.add(cases[34]);
        j2.add(cases[35]);
        
        j3.add(cases[36]);
        j3.add(cases[37]);
        j3.add(cases[38]);
        j3.add(cases[39]);
        j3.add(cases[40]);
        
        j.add(j1);
        j.add(j2);
        j.add(j3);
        
        
        this.panelAffichage3.add(j);
        
        
        return (this.panelAffichage3);
        
    }
    
    public JPanel initialisePanelGauche(){
        
        this.panelAffichage=new JPanel(new GridLayout(6,1,4,2));
        this.panelAffichage.setBounds(30,5,350,775);
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
        panelAffichage.add(new JLabel("PLUGBOARD"));
        Border bord=BorderFactory.createCompoundBorder(
                                                       BorderFactory.createTitledBorder("Réglages de la machine enigma"),
                                                       BorderFactory.createEmptyBorder(20,10,3,2));
        panelAffichage.setBorder(bord);
        
        return (panelAffichage);
        
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
