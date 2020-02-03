package MST;

public class Kvertex {
	private String Kparent;
	private int krank;
	
	/**
	 * Initialize each value
	 * @param Kparent
	 * @param krank
	 */
	Kvertex(String Kparent,int krank){
		this.krank=krank;
		this.Kparent=Kparent;
	}
	
	/**
	 * get the parent of vertex.
	 * @return
	 */
	public String getKparent(){
		return Kparent;
	}
	
	/**
	 * get the rank of the vertex.
	 * @return
	 */
	public int getKrank(){
		return krank;
	}
	
	/**
	 * return result. 
	 */
	public String toString(){
		return "Kparent:"+getKparent()+" Krank:"+getKrank();
	}
	

}
