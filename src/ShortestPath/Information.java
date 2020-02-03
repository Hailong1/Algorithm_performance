package ShortestPath;

public class Information {

	 private String adj;
	 private int mtu;
	 
	 Information(String adj,int mtu)
	 {
		 this.adj=adj;
		 this.mtu=mtu;
	 }
	 
	 /**
	  * get the  MTU value that connected to this node
	  * 
	  * @return
	  */
	 public int getMtu()
	 {
		 return mtu;
	 }
	 
	 /**
	  * get  the adjecent node  that connected to this node. 
	  * @return
	  */
	 public String getAdj()
	 {
		 return adj;
	 }
	 
	 /**
	  * return the result. 
	  */
	 public String toString(){
		   return "Adj:"+getAdj()+"\n"+"MTU:"+getMtu()+"\n";
	   }
	
}
