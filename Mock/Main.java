import java.awt.Dimension;
import java.awt.Toolkit;


public class Main {
	public static void main(String[] args) {
		Toolkit aTK= Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int large=dim.width;
		int haut=dim.height;
	
		Vue v=new Vue(large, haut);
		Machine m=new Machine();
		Controleur controleur=new Controleur(v,m);
	}
}
