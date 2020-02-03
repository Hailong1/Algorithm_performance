package ShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * store all the information into corresponding map or Arraylist or others from the database. 
 * 
 * @author hailong 
 *
 */
public class Configuration {

	/**
	 * load all information of the node. It is means load all information of the graph (configuration of the network)
	 * except the IP.
	 */
	private ArrayList<Node>nodeList;
	private DatabaseHandle db;
	
	/**
	 * store ip into a map, a pattern like (N1,188.0.0.1), it means the iP of N1 is 188.0.0.1
	 */
	private Map<String,String>ip;
	/**
	 * store one node as the key and its corresponding adjacent node. 
	 */
   
	Configuration()
	{
           nodeList=new ArrayList<Node>();
           ip=new HashMap<String,String>();
	       db=new DatabaseHandle();
	}
	
  
   /**
     * we call the loadNode method to get the information about configuration. 
     */
	
    public void start()
    {
    	loadNode();
    }
    
	
	/**
	 * load all of information about the node 
	 * nid and adj and mtu are stored in the nodeList.
	 * and iP are stored in the ip map. 
	 */
	public void loadNode()
	{
        ArrayList<String> nodeinfo=new ArrayList<String>();
        ArrayList<String> adjinfo=new ArrayList<String>();
        ArrayList<String> Mtuinfo=new ArrayList<String>();
        ArrayList<String> Ipinfo=new ArrayList<String>();
        String select=null;
        select= "select * from node natural join infor;";
        nodeinfo=db.getOneValue(select, 1);
        Ipinfo=db.getOneValue(select, 2);
        adjinfo=db.getOneValue(select, 3);
        Mtuinfo=db.getOneValue(select, 4);
        for(int i=0; i<adjinfo.size();i++)
        {
        	addNode(nodeinfo.get(i),adjinfo.get(i),Mtuinfo.get(i));
        	ip.put(nodeinfo.get(i),Ipinfo.get(i));
        }
      
        
	}
	
	/**
	 * add the information
	 * @param nid
	 * @param adj
	 * @param mtu
	 */
	public void addNode(String nid, String adj, String mtu)
	{
		int Mtu=Integer.parseInt(mtu);
		Information im=new Information(adj,Mtu);
		Node nd=new Node(nid,im);
		nodeList.add(nd);
	}

	/**
	 * return all information in the list except ip.
	 * the list like this (the name of the node,one adjacent node,it mtu).
	 * @return
	 */
    public ArrayList<Node> getNode()
    {
    	return nodeList;
    }
    
    /**
     * return the ip for corresponding node.
     * the map is this (the name of the node, its ip).
     * @return
     */
    public Map<String,String>getIP()
    {
    	return ip;
    }
    
    /**
     * get all node in the graph. 
     * @return
     */
    public ArrayList<String>getAllNode()
    {
    	String select=null;
    	select="select distinct(nid) from node natural join infor;";
    	return db.getOneValue(select, 1);
    }
	
    /**
     * get all edge in the graph.
     * @return
     */
    public Map<String, Integer> getAllEdge()
    {
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	String temp1,temp2,temp;

    	int mtu;
    	for(int i=0;i<nodeList.size();i++)
    	{
    		temp1=nodeList.get(i).getNodeId();
    		temp2=nodeList.get(i).getInfor().getAdj();
    		temp=temp1+"-"+temp2;
    		mtu=nodeList.get(i).getInfor().getMtu();
    		temp.trim();// drop space from leftmost one and rightmost one. 
    		map.put(temp, mtu);
    		
    	}
    	return map;
    }
    
    /**
     * get the number of the node
     * @return
     */ 
    public int getnumberOfnode()
    {
       	String select=null;
    	select="select count(distinct nid) from node natural join infor;";
    	int number= Integer.parseInt(db.getValue(select, 1));
    	return number;	
    }
    
   
   
}
