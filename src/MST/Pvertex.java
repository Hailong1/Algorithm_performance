package MST;



public class Pvertex {
	
	private int Pvkey;
	private String Pvparent;
	private boolean flag;
	private String node;
    private String  adj;
    
    /**
     * Initialize each value
     * @param node
     * @param adj
     * @param Pvkey
     * @param Pvparent
     * @param flag
     */
	Pvertex(String node,String adj,int Pvkey,String Pvparent,boolean flag){
        this.node=node;
	    this.flag=flag;
		this.Pvkey=Pvkey;
		this.Pvparent=Pvparent;
	    this.adj=adj;
		
	}
	
	/**
	 * get the node.
	 * @return
	 */
	public String getNode(){
		return node;
	}
	
	/**
	 * get the key of the node.
	 * @return
	 */
	public int getPkey(){
		return Pvkey;
	}
	
	/**
	 * get the parent of the node 
	 * @return
	 */
	public String getPparent(){
		return Pvparent;
	}
	
	/**
	 * get the flag to judge whether the vertex is belong to queue. 
	 * @return
	 */
	public boolean getFlag(){
		return flag;
	}
	
	/**
	 * get the adj of the vertex.
	 * @return
	 */
	public String getAdj(){
		return adj;
	}
    
	
	/**
	 * return the result. 
	 */
	public String toString(){
		return "node:"+getNode()+"\nadj:"+adj+
				"\nkey:"+getPkey()+"\n"+"Pparent:"+getPparent()+"\n"+"flag:"+getFlag()+"\n";
	}
	

}
