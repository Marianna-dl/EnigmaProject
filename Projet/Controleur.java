import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controleur implements ActionListener, KeyListener{
	private Machine modele;
	private Vue vue;
	
	public Controleur(Vue v, Machine m){
		this.modele=m;
		this.vue=v;
		initListener();
	}
	
	public void initListener(){
		this.vue.getTextClear().addKeyListener(this);
		this.vue.getTextCrypt().addKeyListener(this);
		this.vue.getBoutonDecrypte().addActionListener(this);
		this.vue.getBoutonAppliquer().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==this.vue.getBoutonDecrypte()){
			String ch=this.vue.getTextCrypt().getText();
			String decryptee=this.modele.decrypter(ch);
			this.vue.getTextCrypt().setText("");
			this.vue.getTextClear().setText(decryptee);
			
		}
		else if(e.getSource()==this.vue.getBoutonAppliquer()){
			int r1=0;
			int r2=0;
			int r3=0;
			try {
			    r1=Integer.parseInt(this.vue.getPosRotor1().getText());
			    r2=Integer.parseInt(this.vue.getPosRotor2().getText());
			    r3=Integer.parseInt(this.vue.getPosRotor3().getText());
			} catch(NumberFormatException nfe) {
			     System.out.println("Les positions doivent etre des entiers");
			}
			if(r1>=1 && r1<=26){
				//this.modele.getRotor(0).setPosition(r1);
				System.out.println(r1);
			}
			if(r2>=1 && r2<=26){
				//this.modele.getRotor(1).setPosition(r2);
				System.out.println(r2);
			}
			if(r3>=1 && r3<=26){
				//this.modele.getRotor(2).setPosition(r3);
				System.out.println(r3);
			}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {


		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource()==this.vue.getTextClear()){
			char chaine=e.getKeyChar();
			if(e.getKeyChar()!=' ' && !(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
				char lettreCryptee=this.modele.crypter(chaine);
				this.vue.getTextCrypt().setText(this.vue.getTextCrypt().getText()+lettreCryptee);
			}
			else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				String ch;
				if(this.vue.getTextClear().getText().length()!=0){
					ch=this.vue.getTextCrypt().getText().substring(0, this.vue.getTextCrypt().getText().length()-1);
					this.vue.getTextCrypt().setText(ch);
				}
				else{
					 ch=this.vue.getTextCrypt().getText().substring(0, 0);	
					 this.vue.getTextCrypt().setText(ch);
				}
			}
		}
		if(e.getSource()==this.vue.getTextCrypt()){
			if(this.vue.getTextClear().getText().length()>0){
				this.vue.getTextClear().setText("");
				this.vue.getTextCrypt().setText(""+e.getKeyChar());
				System.out.println("yo");
			}
			/*if(e.getKeyChar()!=' ' && !(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)){
				this.vue.getTextCrypt().setText(this.vue.getTextCrypt().getText()+e.getKeyChar());
				System.out.println("yop");
			}*/
		}
		
	}


}
