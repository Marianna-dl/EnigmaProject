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
    private JButton crypter;
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
    private Machine model;
    private JScrollPane scrollClair;
    private JScrollPane scrollCrypte;
    Checkbox box;
    //on pose le tableau de labels qui va nous permetre de colorer la lettre cryptee
    private JLabel cases[];
    
    
    public Vue(int w, int h, Machine m){
        
        this.model=m;
        this.model.addObserver(this);
        for (int i=0; i<3;i++){
            this.model.getRotor(i).addObserver(this);
        }
        this.model.getPlugboard().addObserver(this);
        //on initialise le checkbox pour diffŽrencier les cryptages avec parametres connus ou inconnus
        this.box=new Checkbox("Parametres inconnus",false);
        
        //on initialise le tableau de label du clavier revelateur
        this.cases = new JLabel[this.model.CONVERT.length];
        for(int i=0; i<this.model.CONVERT.length;i++){
            this.cases[i]=new JLabel((" "+this.model.CONVERT[i]+" ").toUpperCase());
        }
        
        
        /*code pour changer de couleur, ici il s'agit de la lettre A
         cases[0].setOpaque(true);
         cases[0].setBackground(Color.yellow); */
        
        this.initialise();
        this.centreEcran(w, h);
        this.show();
    }
    
    public JLabel[] getCases(){
        return this.cases;
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
    public JButton getBoutonCrypte(){
        return this.crypter;
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
    public JLabel getRotorInitial3(){
        return this.rotor3;
    }
    public JLabel getRotorInitial1(){
        return this.rotor1;
    }
    public JLabel getRotorInitial2(){
        return this.rotor2;
    }
    public boolean getStateCheckbox(){
        return this.box.getState();
    }
    //ajout de tout les panel au contener
    public void initialise()	{
        this.c=this.getContentPane();
        this.c.add(this.initialisePanelBas());
        this.c.add(this.initialisePanelGauche());
        this.c.add(this.initialisePanelHaut());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    //gestionnaire du panel bas
    public JPanel initialisePanelBas(){
        this.clairLabel=new JLabel("Texte en clair");
        this.crypteLabel=new JLabel("Texte crypte");
        this.textClair=new JTextArea(10,30);
        this.textCrypte=new JTextArea(10,30);
        textClair.setWrapStyleWord(true);
        textCrypte.setWrapStyleWord(true);
        textClair.setLineWrap(true);
        textCrypte.setLineWrap(true);
        this.scrollCrypte= new JScrollPane(textCrypte,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollClair= new JScrollPane(textClair,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.decrypter=new JButton("decrypter");
        this.crypter=new JButton("crypter");
        this.panelAffichage2=new JPanel(new GridLayout(3,1,4,2));
        this.panelAffichage2.setBounds(380, 380, 990, 400);
        JPanel ptext = new JPanel(new GridLayout(1,1,5,2));
        ptext.add(this.crypteLabel);ptext.add(this.clairLabel);
        this.panelAffichage2.add(ptext);

        JPanel ptext1 = new JPanel(new GridLayout(1,1,5,2));
       // ptext1.add(this.textCrypte);ptext1.add(this.textClair);
        textClair.setEditable(true);
        textCrypte.setEditable(true);
        ptext1.add(this.scrollCrypte);
        ptext1.add(this.scrollClair);
        this.panelAffichage2.add(ptext1);
        JPanel ptext2 = new JPanel();
        ptext2.add(this.box, BorderLayout.WEST);
        ptext2.add(this.decrypter, BorderLayout.CENTER);
        ptext2.add(this.crypter,BorderLayout.EAST);
        panelAffichage2.add(ptext2);
        Border bord1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Commandes"),
                                                        BorderFactory.createEmptyBorder(20,10,3,2));
        this.panelAffichage2.setBorder(bord1);
        return (this.panelAffichage2);
        
    }
    //gestionnaire du panel haut
    public JPanel initialisePanelHaut(){
        this.clavier=new JLabel("LE CLAVIER REVELATEUR");
        this.panelAffichage3=new JPanel();
        JPanel j=new JPanel(new GridLayout(2,2,100,1));
        Border bord2=BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Clavier revelateur"),
                                                        BorderFactory.createEmptyBorder(20,10,3,2));
        j.setBorder(bord2);
        //placement manuel du panel j
        j.setBounds(300, 400,10,10);
        JPanel j1=new JPanel(new GridLayout(4,7,5,3));
        JPanel j2=new JPanel(new GridLayout(3,4,5,3));
        JPanel j3=new JPanel(new GridLayout(1,1,5,3));
        
        //boucle qui permet d'ajouter au panel j2 les valeur du tableau alant de la case 1 ˆ 10
        
        for(int i=0; i<10;i++){
            j2.add(cases[i]);
        }
        //boucle qui permet d'ajouter au panel j1 les valeur du tableau alant de la case 11 ˆ 36
        for(int i=10;i<37;i++){
            j1.add(cases[i]);
        }
        
        //boucle qui permet d'ajouter au panel j3 les valeur du tableau alant de la case 37 ˆ la fin du tableau
        for(int i=37;i<this.cases.length;i++){
            j3.add(cases[i]);
        }
        j.add(j1);
        j.add(j2);
        j.add(j3);
        this.panelAffichage3.add(j);
        return (this.panelAffichage3);
    }
    //gestionnaire du panel gauche
    public JPanel initialisePanelGauche(){
        this.posRotor1=new JTextField(2);
        this.posRotor2=new JTextField(2);
        this.posRotor3=new JTextField(2);
        this.rotor1=new JLabel("0");
        this.rotor2=new JLabel("0");
        this.rotor3=new JLabel("0");
        this.positionActuelle=new JLabel("Positions Actuelle des Rotors");
        this.posAcRotor1=new JLabel(""+this.model.getRotor(0).getPosition());
        this.posAcRotor2=new JLabel(""+this.model.getRotor(1).getPosition());
        this.posAcRotor3=new JLabel(""+this.model.getRotor(2).getPosition());
        this.appliquer=new JButton("appliquer");
        this.panelAffichage=new JPanel(new GridLayout(6,1,4,2));
        this.panelAffichage.setBounds(30,5,350,775);
        
        //sous panel qui g�re la position initiale des rotors
        JPanel pRot = new JPanel(new GridLayout(1,3,4,2));
        Border bordTemps=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Configurations Rotors Initiale"),BorderFactory.createEmptyBorder(20,10,3,2));
        pRot.setBorder(bordTemps);
        Border borderRot1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderRot2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderRot3=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
        this.rotor1.setBorder(borderRot1);
        this.rotor2.setBorder(borderRot2);
        this.rotor3.setBorder(borderRot3);
        
        pRot.add(this.rotor1);
        pRot.add(this.rotor2);
        pRot.add(this.rotor3);
        this.panelAffichage.add(pRot);
        
        //sous panel qui g�re la position actuelle des rotors
        JPanel pRot1 = new JPanel(new GridLayout(1,3,4,2));
        Border bordTemps1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Positions Actuelle"),BorderFactory.createEmptyBorder(20,10,3,2));
        pRot1.setBorder(bordTemps1);
        Border borderRotAc1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderRotAc2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderRotAc3=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
        this.posAcRotor1.setBorder(borderRotAc1);
        this.posAcRotor2.setBorder(borderRotAc2);
        this.posAcRotor3.setBorder(borderRotAc3);
        pRot1.add(this.posAcRotor1);pRot1.add(this.posAcRotor2);
        pRot1.add(this.posAcRotor3);
        this.panelAffichage.add(pRot1);
        
        //sous panel permettant de rentrer les posotions initiale des rotors
        JPanel pRot2 = new JPanel(new GridLayout(1,3,4,2));
        Border bordTemps2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Positionner vos Rotors"),BorderFactory.createEmptyBorder(20,10,3,2));
        pRot2.setBorder(bordTemps2);
        Border borderPosRot1=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor1"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderPosRot2=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor2"),BorderFactory.createEmptyBorder(20,10,3,2));
        Border borderPosRot3=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Rotor3"),BorderFactory.createEmptyBorder(20,10,4,2));
        this.posRotor1.setBorder(borderPosRot1);
        this.posRotor2.setBorder(borderPosRot2);
        this.posRotor3.setBorder(borderPosRot3);
        pRot2.add(this.posRotor1);pRot2.add(this.posRotor2);
        pRot2.add(this.posRotor3);pRot2.add(this.appliquer);
        this.panelAffichage.add(pRot2);
        this.panelAffichage.add(new JLabel("PLUGBOARD"));
        Border bord=BorderFactory.createCompoundBorder(
                                                       BorderFactory.createTitledBorder("Reglages de la machine enigma"),
                                                       BorderFactory.createEmptyBorder(20,10,3,2));
        this.panelAffichage.setBorder(bord);
        
        return (this.panelAffichage);
        
    }
    //fonction permettant de g�rer l'affichage de l'Žcran
    public void centreEcran(int w, int h){
        this.setBounds((w/2)/2,(h/2)/2, w/2, h/2);
        this.setSize(w,h);
        
        /*this.setSize(h,w/2);
         this.setLocationRelativeTo(null) ;*/
    }
    
    //permet d'actualiser la vue en fonction des changement d'Žtat des objets observŽs
    public void update(Observable o,Object ob){
        this.posAcRotor1.setText(String.valueOf(this.model.getRotor(0).getPosition()));
        this.posAcRotor2.setText(String.valueOf(this.model.getRotor(1).getPosition()));
        this.posAcRotor3.setText(String.valueOf(this.model.getRotor(2).getPosition()));
        this.repaint();
        
    }
}
