package ShortestPath;

/**
 * Storing all immediately result during the algorithm.
 * And it store for the information of one node during the algorithm process. 
 *  @author hailong
 *
 */
public class Store {
	
     /**
      * the node itself the same as the nid in the Node. 	
      */
     private String nid; 
	 /**
	  * beforenode store the node, which we can find current node from this node during the fragments 
	  * travel to the destination. It is mean that in the project, we find the next hop IP address.
	  */
	 private String beforeNode;
	 
	 /**
	  * curretntMTU store the current minimum MTU from the start node to current node, 
	  * in our criteria, we choose the route as the following way, 
	  * from start node to the destination node, we choose one route that have the minimum total MTU, 
	  * and choosing minimum one MTU of above route as the fragmentation. 
	  * And it will cause the minimum extra waste of the MTU. 
	  */
	 private int currentMTU;	 
	 Store (String nid,String beforeNode, int currentMTU)
	 {
		 this.nid=nid;
		 this.beforeNode=beforeNode;
		 this.currentMTU=currentMTU;
	 }
	 
	 /**
	  * get the value of the node.
	  */
	 public String GetNode()
	 {
		 return nid;
	 }
	 /**
	  * get the value of the beforeNode. 
	  */
	 public String getBefore()
	 {
		 return beforeNode;
	 }
	 
	 /**
	  * get the value of the currentMTU
	  */
	 public int getCMTU()
	 {
		 return currentMTU;
	 }	 
	 /**
	  * return the result. 
	  */
     public String toString()
     {
			   return "node:"+GetNode()+"\n beforeNode:"+getBefore()+"\n"+"CurrentMTU:"+getCMTU()+"\n";
     }
	
}
