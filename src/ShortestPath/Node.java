package ShortestPath;

public class Node {
	
	private  String nodeId;
	private  Information infor;
	
	Node (String nodeId,  Information infor)
	{
		this.nodeId= nodeId;
		this.infor=infor;
	}
	
	
	/**
	 * get the value of the node. 
	 * @return
	 */
	public String getNodeId()
	{
		return nodeId;
	}
	
	
	/**
	 * get all the information about this node, like adjacent node and corresponding MTU. 
	 * @return
	 */
	public Information getInfor()
	{
		return infor;
	}
	
	/**
	 * return the result. 
	 */
	 public String toString(){
		   return "nodeId:"+getNodeId()+"\n"+"infor:\n"+getInfor()+"\n";
	   }

}
