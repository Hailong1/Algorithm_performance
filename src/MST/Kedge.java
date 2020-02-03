package MST;

public class Kedge {
    private String FirstVertex;
    private String SecondVertex;
    private int weight;
    private String edge;
  
    /**
     * Initialize each value    
     * @param FirstVertex
     * @param SecondVertex
     * @param weight
     * @param edge
     */
    Kedge(String FirstVertex,String SecondVertex,int weight,String edge){
    	this.FirstVertex=FirstVertex;
    	this.SecondVertex=SecondVertex;
    	this.weight=weight;
    	this.edge=edge;
	}
  
    /**
     * get the first vertex of edge
     * @return
     */
    public String getFirstvertex(){
    	return FirstVertex;
    }
    
    /**
     * get the second vertex of edge
     * @return
     */
    public String getSecondVertex(){
       return SecondVertex;	
    }
   
    /**
     * get the weight of edge
     * @return
     */
    public int getWeight(){
    	return weight;
    }
    
    /**
     * get the edge information.
     * @return
     */
    public String getEdge(){
    	return edge;
    }

    /**
     * return result.
     */
   public String toString(){
	   return "FirstVertex:"+getFirstvertex()+"\n"+"SecondVertex:"+getSecondVertex()+"\n"+getEdge()+"="+getWeight()+"\n";
   }
   
}
