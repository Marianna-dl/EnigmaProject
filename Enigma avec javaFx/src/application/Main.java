package application;
	
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import controller.EnigmaController;
import model.Decrypt;
import model.Machine;
import model.Plugboard;
import model.Reflecteur;
import model.Rotor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Machine m;
	private Decrypt dec;
	public Main() throws IOException {
		Rotor r;
		Rotor[] tab=new Rotor[3];
		int[] entier;

		Integer[] array = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45};
		Collections.shuffle(Arrays.asList(array));
		
		for(int j=0; j<3;j++){
			entier=new int[46];
			Collections.shuffle(Arrays.asList(array));
			for(int i=0;i<array.length;i++){
				entier[i]=Integer.valueOf(array[i]);
			}
			r=new Rotor(entier, 0);
			tab[j]=r;
		}

		
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		this.m=new Machine(tab,ref,p);
	
		this.dec=new Decrypt(m,"dico.txt");

    }
	
    public Machine getMachine() {
        return m;
    }
    public Decrypt getMachineDecrypt(){
    	return dec;
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/EnigmaView.fxml"));

            AnchorPane enigma = (AnchorPane) loader.load();
            EnigmaController controller = loader.getController();
            controller.setApp(this);
            controller.setDecryptage();
            Scene scene = new Scene(enigma);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
		
		
	}
}
