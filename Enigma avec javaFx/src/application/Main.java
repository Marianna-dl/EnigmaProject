package application;
	
import controller.EnigmaController;
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
	public Main() {
		Rotor r;
		Rotor[] tab=new Rotor[3];
		int[] entier;

		for(int i=0;i<3;i++){
			entier=new int[46];
			for(int j=0; j<entier.length;j++){
				entier[j]=j;
			}
			r=new Rotor(entier, 0);
			tab[i]=r;
			
		}
		
		Reflecteur ref = new Reflecteur();
		Plugboard p=new Plugboard();
		this.m=new Machine(tab,ref,p);
    }
	
    public Machine getMachine() {
        return m;
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/EnigmaView.fxml"));

            AnchorPane enigma = (AnchorPane) loader.load();
            EnigmaController controller = loader.getController();
            controller.setApp(this);
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
