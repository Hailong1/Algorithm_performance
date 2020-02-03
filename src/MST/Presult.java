package MST;

public class Presult {
      
	  private int number;
	  private String edge;
	  private int weight;
	  
	  /**
	   * Initialize each value
	   * @param number
	   * @param edge
	   * @param weight
	   */

	  Presult(int number,String edge,int weight)
	  {
		  this.number=number;
		  this.edge=edge;
		  this.weight=weight;
	  }
	 
	  /**
	   * get the number, the number is the time of the algorithm add the edge to MST. 
	   * @return
	   */
	  public int getNumber()
	  {
		  return number;
	  }
	
	  /**
	   * get the result weight.
	   * @return
	   */
	  public int getWeight()
	  {
		  return weight;
	  }
	 
	  /**
	   * get the result edge.
	   * @return
	   */
	  public String getEdge()
	  {
		  return edge;
	  }
	  
	  /**
	   * return result.
	   */
	  public String toString()
	  {
		  return "number: "+getNumber()+"\nedge: "+getEdge()+"\nweight:"+getWeight()+"\n";
		  
	  }
	  
}
