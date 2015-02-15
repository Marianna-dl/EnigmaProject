import java.awt.Dimension;
import java.awt.Toolkit;


public class Main {
	public static void main(String[] args) {
		Toolkit aTK= Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int large=dim.width;
		int haut=dim.height;
		Machine m=new Machine();
		Vue v=new Vue(large, haut,m);

		Controleur controleur=new Controleur(v,m);
	}
}
