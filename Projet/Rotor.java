
public class Rotor {
	private int[] parcoursAller;
	private int[] parcoursRetour;
	private int position;
	
	public Rotor(int[] tab,int pos){
		this.parcoursAller=tab;
		this.parcoursRetour= new int[47];
		this.createMirror();
		this.avancer(pos);
		this.position=pos;
	}
	public void createMirror(){
		for(int i=0;i<parcoursAller.length;i++){
			parcoursRetour[parcoursAller[i]]=i;
		}
	}
	public int getCorrespondance(int i, boolean b){
		if(b){
			return parcoursAller[i];
		}
		else{
			return parcoursRetour[i];
		}
	}
	public int getPosition(){
		return position;
	}
	public void setPosition(int i){
		this.position=i;
	}
	public void avancer(int i){
		int k=0;
		while(k<i){
			int temp1,temp2;
			temp1=parcoursAller[1];
			parcoursAller[1]=parcoursAller[0];
			for(int j=1;j<parcoursAller.length-1;j++){
				temp2=parcoursAller[j+1];
				parcoursAller[j+1]=temp1;
				temp1=temp2;
			}
			parcoursAller[0]=temp1;
			k++;
		}
	}
}
