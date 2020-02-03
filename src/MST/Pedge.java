package MST;

public class Pedge {
	
	private String FirstVertex;
	private String SecondVertex;
	private int weight;
	
	/**
	 * Initialize each value 
	 * @param FirstVertex
	 * @param SecondVertex
	 * @param weight
	 */
    Pedge(String FirstVertex,String SecondVertex,int weight){
    	this.FirstVertex=FirstVertex;
    	this.SecondVertex=SecondVertex;
    	this.weight=weight;
		
	}
  
    /**
     * get the first vertex of one edge.
     * @return
     */
    public String getFirstVertex(){
    	return FirstVertex;
    }
    
    /**
     * get the second vertex of one edge 
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
     * return result.
     */
   public String toString(){
	   return "FirstVertex:"+getFirstVertex()+"\n"+"SecondVertex:"+getSecondVertex()+"\n"+"weight:"+getWeight()+"\n";
   }


}
