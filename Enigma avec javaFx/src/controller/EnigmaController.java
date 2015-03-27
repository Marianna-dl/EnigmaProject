package controller;


import model.Decrypt;


import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JLabel;

import model.Machine;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import application.Main;

public class EnigmaController implements Observer{
	
	@FXML
	Button crypterButton;
	@FXML
	Button decrypterButton;
	@FXML
	TextArea texteCrypte;
	@FXML
	TextArea texteDecrypte;
	@FXML
	private VBox rotorsInitiaux;
	@FXML
	private VBox newPositions;
	@FXML
	private VBox positionsActuelles;
	@FXML
	private GridPane listeCouples;
	@FXML
	private TextField faireCouple;
	@FXML
	private TextField defaireCouple;
	@FXML
	private StackPane clavier;
	@FXML
	private Pane caseClavier;
	@FXML
	private GridPane numberCase;
	@FXML
	private GridPane symbolCase;
	@FXML
	CheckBox paramInconnus;
	
	@FXML
	private GridPane letterCase;
	
	private Main app;
	
	private Machine model;
	
    private String tabCouple[];
    private Decrypt decryptage;
    
	public EnigmaController(){
		 this.tabCouple=new String[10];
	}

	@FXML
	private void initialize() {
		for(int i=0;i<rotorsInitiaux.getChildren().size();i++){
			((TextField)rotorsInitiaux.getChildren().get(i)).setText("0");
			((TextField)newPositions.getChildren().get(i)).setText("0");
			((TextField)positionsActuelles.getChildren().get(i)).setText("0");
		}
		Label couple;
		for(int i=0;i<listeCouples.getColumnConstraints().size();i++){
			for(int j=0;j<listeCouples.getRowConstraints().size();j++){
				couple=new Label("");
				GridPane.setHalignment(couple, HPos.CENTER);
				listeCouples.add(couple,i,j);
			}
		}
		this.faireCouple.setText("");
		this.defaireCouple.setText("");
		Text text;
		StackPane stack;
		for(int i=0;i<letterCase.getColumnConstraints().size();i++){
			for(int j=0;j<letterCase.getRowConstraints().size();j++){
				stack=new StackPane();
				text= new Text("");
				text.setFill(Color.WHITE);
				text.setFont(Font.font(null, FontWeight.BOLD, 16));
				Circle circle = new Circle();
				circle.setRadius(13.0);
				circle.setFill(Color.TRANSPARENT);
				circle.setStroke(Color.BLACK);
				stack.getChildren().addAll(circle,text);
				GridPane.setHalignment(stack, HPos.CENTER);
				symbolCase.add(stack,0,0);
				numberCase.add(stack,i,j);
				letterCase.add(stack,i,j);
			}
		}
	}
	
	public void updateClavier(){
		letterCase.getChildren().clear();
		numberCase.getChildren().clear();
		symbolCase.getChildren().clear();
		Text text;
		StackPane stack;
		int x=0;
		for(int i=0;i<numberCase.getRowConstraints().size();i++){
			for(int j=0;j<numberCase.getColumnConstraints().size();j++){
				stack=new StackPane();
				if(x<10){
					text= new Text((""+this.model.CONVERT[x]).toUpperCase());
					text.setFill(Color.WHITE);
					text.setFont(Font.font(null, FontWeight.BOLD, 16));
					Circle circle = new Circle();
					circle.setRadius(13.0);
					circle.setFill(Color.TRANSPARENT);
					circle.setStroke(Color.BLACK);
					stack.getChildren().addAll(circle,text);
					GridPane.setHalignment(stack, HPos.CENTER);
					numberCase.add(stack,j,i);
					x++;
				}
			
			}
		}	
		x=10;
		for(int i=0;i<letterCase.getRowConstraints().size();i++){
			for(int j=0;j<letterCase.getColumnConstraints().size();j++){
				stack=new StackPane();
				if(x<36){
					text= new Text((""+this.model.CONVERT[x]).toUpperCase());
					text.setFill(Color.WHITE);
					text.setFont(Font.font(null, FontWeight.BOLD, 16));
					Circle circle = new Circle();
					circle.setRadius(13.0);
					circle.setFill(Color.TRANSPARENT);
					circle.setStroke(Color.BLACK);
					stack.getChildren().addAll(circle,text);
					GridPane.setHalignment(stack, HPos.CENTER);
					letterCase.add(stack,j,i);
					x++;
				}
			
			}
		}
		x=36;
		for(int i=0;i<symbolCase.getRowConstraints().size();i++){
			for(int j=0;j<symbolCase.getColumnConstraints().size();j++){
				stack=new StackPane();
				if(x<this.model.CONVERT.length){
					text= new Text((""+this.model.CONVERT[x]).toUpperCase());
					text.setFill(Color.WHITE);
					text.setFont(Font.font(null, FontWeight.BOLD, 16));
					Circle circle = new Circle();
					circle.setRadius(13.0);
					circle.setFill(Color.TRANSPARENT);
					circle.setStroke(Color.BLACK);
					stack.getChildren().addAll(circle,text);
					GridPane.setHalignment(stack, HPos.CENTER);
					symbolCase.add(stack,j,i);
					x++;
				}
			
			}
		}

	}
	
	public void decrypteChar(KeyEvent e){
		 resetColor();
		String textClear=this.texteDecrypte.getText();
		String textCrypt=this.texteCrypte.getText();

		if(!(e.getCode()==KeyCode.BACK_SPACE)){
			char chaine=(traiteChaine(e.getText())).charAt(0);
			if(lettreExiste(chaine)){
				char lettreCryptee=this.model.crypter(chaine);
				this.texteDecrypte.setText(textClear+lettreCryptee);
			//	allumeLettre(lettreCryptee); // On colorie en jaune la lettre sortante sur le clavier revelateur
			}
		}
		else if(!textCrypt.equals("") && e.getCode()==KeyCode.BACK_SPACE){
			String ch;
			if(textCrypt.length()!=0){
				ch=textClear.substring(0,textClear.length()-1);
				this.texteDecrypte.setText(ch);
			}
			else{
				 ch=textClear.substring(0, 0);	
				 this.texteDecrypte.setText(ch);
			}
			reculerRotor();
		}
		if(textCrypt.equals("")){
			rotorInitial();
			this.texteDecrypte.setText("");
		}
		
	}
	/**
	 * Met en couleur la lettre sortante du clavier revelateur
	 * 
	 * @param lettre
	 * 			La lettre que l'on veut mettre en couleur
	 */	
	public void allumeLettre(char lettre){
		StackPane stack;
		for(int i=0; i<this.model.CONVERT.length;i++){
			
			if(this.model.CONVERT[i]==lettre){
				if(i>=0 && i<10){
					stack=((StackPane)numberCase.getChildren().get(i));
					((Text)stack.getChildren().get(1)).setFill(Color.RED);
					break;
				}
				else if(i>=10 && i<36){
					stack=((StackPane)letterCase.getChildren().get(i-10));
					((Text)stack.getChildren().get(1)).setFill(Color.RED);
					break;
				}
				else{
					stack=((StackPane)symbolCase.getChildren().get(i-36));
					((Text)stack.getChildren().get(1)).setFill(Color.RED);
					break;
				}
				
			}
		}
	}
	public void resetColor(){
		StackPane stack;
		for(int i=0;i<10;i++){
			stack=((StackPane)numberCase.getChildren().get(i));
			((Text)stack.getChildren().get(1)).setFill(Color.WHITE);
		}
		for(int i=10;i<36;i++){
			stack=((StackPane)letterCase.getChildren().get(i-10));
			((Text)stack.getChildren().get(1)).setFill(Color.WHITE);
		}
		for(int i=36;i<this.model.CONVERT.length;i++){
			stack=((StackPane)symbolCase.getChildren().get(i-36));
			((Text)stack.getChildren().get(1)).setFill(Color.WHITE);
		}
	}
	public void crypteChar(KeyEvent e){
		resetColor();
		String textClear=this.texteDecrypte.getText();
		String textCrypt=this.texteCrypte.getText();
		if(!(e.getCode()==KeyCode.BACK_SPACE  )){
			char chaine=(traiteChaine(e.getText())).charAt(0);
			if( lettreExiste(chaine)){
				char lettreCryptee=this.model.crypter(chaine);
				this.texteCrypte.setText(textCrypt+lettreCryptee);
				allumeLettre(lettreCryptee);
			}
		}
		else if(!textClear.equals("") && e.getCode()==KeyCode.BACK_SPACE){
			String ch;
			if(textClear.length()!=0){
				ch=textCrypt.substring(0, textCrypt.length()-1);
				this.texteCrypte.setText(ch);
			}
			else{
				 ch=textCrypt.substring(0, 0);	
				 this.texteCrypte.setText(ch);
			}
			reculerRotor();	
		}
		if(textClear.equals("")){
			rotorInitial();
			this.texteCrypte.setText("");
		}	
	
	}
	
	/**
	 * Permet de reculer un rotor d'un cran
	 */	
	public void reculerRotor(){
		this.model.getRotor(0).avancer(this.model.CONVERT.length-1);
		if(this.model.getRotor(0).getPosition()==45){
			this.model.getRotor(1).avancer(this.model.CONVERT.length-1);
			if(this.model.getRotor(1).getPosition()==45){
				this.model.getRotor(2).avancer(this.model.CONVERT.length-1);
			}
		}

	}
	
	
	
	 public void initCouples(){
	        int x=0;
	        char lettre1;
	        char lettre2;
	        String couple;
	        int occurences;
	      	for(int j=10;j<10+26;j++){
	        		occurences=0;
	        		 lettre1=Character.toUpperCase(Machine.CONVERT[j]);
	    			 lettre2=Character.toUpperCase(Machine.CONVERT[this.model.getPlugboard().getLetter(j)]);
	    			 couple=""+lettre1+lettre2;
	    			 for(int i=0;i<tabCouple.length;i++){
	    				 if(this.tabCouple[i]!=null && this.tabCouple[i].equals(""+lettre2+lettre1)){
	    					 occurences++;
	    				 }  			
	    			 }
	        		if(this.model.getPlugboard().getLetter(j)!=j && occurences==0){
	        			this.tabCouple[x]=couple;
	        			x++;
	    			}
	      	}

	    }
	 
	public void crypterTexte(){
		String chaine=traiteChaine(this.texteDecrypte.getText());
		
		if(lettreExiste(chaine)){
			String cryptee=this.model.crypter(chaine);
			this.texteCrypte.setText(cryptee);
		}
	
	}
	
	/**
	 * Verifie qu'une lettre est cryptable
	 * @param c
	 * 			Le char qu'on veut verifier
	 * @return true si la lettre est bien dans le tableau des lettres cryptables, false sinon
	 */	
	public boolean lettreExiste(char c){
		for(int i=0;i<this.model.CONVERT.length;i++){ //on Regarde si la lettre est bien une lettre qu'on peut crypter
			if(this.model.CONVERT[i]==c){	
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
			for(int i=0;i<this.model.CONVERT.length;i++){ 
				if(this.model.CONVERT[i]==s.charAt(j)){	
					occurences++;
				}
			}
			if(occurences==s.length()){
				return true;
			}
		}
		return false;
	}
	
	public void checkParamInconnus(){
		if(this.paramInconnus.isSelected()){
			for(int i=0;i<3;i++){
			((TextField)rotorsInitiaux.getChildren().get(i)).setText("0");
			this.model.getRotor(i).avancer(this.model.CONVERT.length-(this.model.getRotor(i).getPosition()));	
			}
			this.texteDecrypte.setText("");
		}
	}
	
	
	public void decrypterTexte(){
		if(this.paramInconnus.isSelected()){ // On verifie si les parametres des rotors sont connus
			String ch=this.texteCrypte.getText();
			ch=traiteChaine(ch);
			//ch=ch.replaceAll(" |'|:|,|\\.", "");
			//this.texteDecrypte.setText(decryptage.decrypter(ch));
			this.texteDecrypte.setText(decryptage.decrypterIc(ch));
		}
		else{ // Si ils sont connus, on remet les rotors dans leur position initiale et on decrypte
			rotorInitial();
			String chDecryptee=this.model.crypter(traiteChaine(this.texteCrypte.getText()));
			this.texteDecrypte.setText(chDecryptee);
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
	
	public void rotorInitial(){
		int posrotor1=Integer.parseInt(((TextField)rotorsInitiaux.getChildren().get(0)).getText());
		int posrotor2=Integer.parseInt(((TextField)rotorsInitiaux.getChildren().get(1)).getText());
		int posrotor3=Integer.parseInt(((TextField)rotorsInitiaux.getChildren().get(2)).getText());
		this.model.getRotor(0).avancer(this.model.CONVERT.length-(this.model.getRotor(0).getPosition()-posrotor1));
		this.model.getRotor(1).avancer(this.model.CONVERT.length-(this.model.getRotor(1).getPosition()-posrotor2));
		this.model.getRotor(2).avancer(this.model.CONVERT.length-(this.model.getRotor(2).getPosition()-posrotor3));	
	}
	
	public void updatePosRotor(){
		System.out.println("rotor");
		int r1=-1;
		int r2=-1;
		int r3=-1;
		try {
		    r1=Integer.parseInt(((TextField)newPositions.getChildren().get(0)).getText());
		    r2=Integer.parseInt(((TextField)newPositions.getChildren().get(1)).getText());
		    r3=Integer.parseInt(((TextField)newPositions.getChildren().get(2)).getText());
		} catch(NumberFormatException nfe) {
		     System.out.println("Les positions doivent etre des entiers");
		}
		if(r1>=0 && r1<this.model.CONVERT.length){
			this.model.getRotor(0).avancer(46-(this.model.getRotor(0).getPosition()-r1));
			((TextField)rotorsInitiaux.getChildren().get(0)).setText(""+r1);
		}
		if(r2>=0 && r2<this.model.CONVERT.length){
			this.model.getRotor(1).avancer(46-(this.model.getRotor(1).getPosition()-r2));
			((TextField)rotorsInitiaux.getChildren().get(1)).setText(""+r2);
		}
		if(r3>=0 && r3<this.model.CONVERT.length){
			this.model.getRotor(2).avancer(46-(this.model.getRotor(2).getPosition()-r3));
			((TextField)rotorsInitiaux.getChildren().get(2)).setText(""+r3);
		}
	}
	
    public void setApp(Main app) {
        this.app = app;
    	this.model=app.getMachine();
        this.model.addObserver(this);
        for (int i=0; i<3;i++){
            this.model.getRotor(i).addObserver(this);
        }
        this.model.getPlugboard().addObserver(this);
		initCouples();
		updateCouple();
		updateClavier();

    }
    
    public void setDecryptage() {
    	this.decryptage=this.app.getMachineDecrypt();
    }
    
    public void updateCouple(){
    	listeCouples.getChildren().clear();
		Label couple;
		int x=0;
		for(int i=0;i<listeCouples.getColumnConstraints().size();i++){
			for(int j=0;j<listeCouples.getRowConstraints().size();j++){
				couple=new Label(this.tabCouple[x]);
				couple.setFont(Font.font(null, FontWeight.BOLD, 13));
				couple.setTextFill(Color.BROWN);
				GridPane.setHalignment(couple, HPos.CENTER);
				listeCouples.add(couple,i,j);
				x++;
			}
		}
		
    }
    
    public void reglagescouples(){
    	String couple=this.defaireCouple.getText().toUpperCase();
		
		if(verifieCouple(couple)){
			defaireCouple(couple );
		}
		couple=this.faireCouple.getText().toUpperCase();
		if(verifieCouple(couple) && compteElement(this.tabCouple)<10 && !coupleExiste(couple) && couple.charAt(0)!=couple.charAt(1)){
			int emplacement=calculEmplacementLibre(this.tabCouple);
			int premiere=-1;
			for(int i=0;i<this.model.CONVERT.length;i++){
				if(this.model.CONVERT[i]==Character.toLowerCase(couple.charAt(0))){
					System.out.println(this.model.CONVERT[i]);
					premiere=i;
					}
				}
				int deuxieme=premiere+((int)couple.charAt(1)-(int)couple.charAt(0));
				this.model.getPlugboard().setLetter(premiere, deuxieme);
				this.tabCouple[emplacement]=couple;		
			}
		updateCouple();
		if(compteElement(this.tabCouple)<10){
			this.texteCrypte.setDisable(true);
			this.texteDecrypte.setDisable(true);
		}
		else{
			this.texteCrypte.setDisable(false);
			this.texteDecrypte.setDisable(false);
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

	public void defaireCouple(String couple){
		int i=getIndiceCouple(couple);
		if(i != -1){
		for(int j=0; j<this.model.CONVERT.length;j++){
				if(Character.toLowerCase(couple.charAt(0))==this.model.CONVERT[j]){
					this.model.getPlugboard().defaireCouple(j);
					this.tabCouple[i]="";
					break;
					}
				}	
			}	
		}
	
	public int getIndiceCouple(String couple){
		for(int i=0;i<this.tabCouple.length;i++){
			if(this.tabCouple[i].equals(couple)){
				return i;
			}
		}
		return -1;
	}
	
	public int compteElement(String[] t){
		int count=0;
		for(int i=0;i<t.length;i++){
			if(t[i]!=""){
				count++;
			}
		}
		return count;
	}
	public boolean coupleExiste(String couple){
		int i=0;
		do{
			if(this.tabCouple[i]!=""){
				for(int j=0; j<couple.length();j++){
					if(this.tabCouple[i].charAt(0)==couple.charAt(j) || this.tabCouple[i].charAt(1)==couple.charAt(j)){
						System.out.println(this.tabCouple[i]);
						return true;
					}
				}
			}
			i++;
		}while(i<this.tabCouple.length);
		return false;
	}
	public int calculEmplacementLibre(String[] couples){
		for(int i=0;i<couples.length;i++){
			if(couples[i]==""){
				return i;
			}
		}
		return -1;
	}

	@Override
    public void update(Observable o,Object ob){
		((TextField)positionsActuelles.getChildren().get(0)).setText(String.valueOf(this.model.getRotor(0).getPosition()));
		((TextField)positionsActuelles.getChildren().get(1)).setText(String.valueOf(this.model.getRotor(1).getPosition()));
		((TextField)positionsActuelles.getChildren().get(2)).setText(String.valueOf(this.model.getRotor(2).getPosition()));

    }


}
