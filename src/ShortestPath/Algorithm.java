package ShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * This is for the find result. 
 * @author Hailong
 */

public class Algorithm {
 
	  private Travel tr;
	  private Print pl;
	  private ArrayList<Store>resultList;
	  private Map<String,Integer> edgeMap;
	  private Map<String,String>ipMap;
	  private Map<String,Integer> optimalMtuMap;
	  private Map<String,ArrayList<String>>optimalBeforList;
	  private int optimalMTU;
	  private Map<String,ArrayList<String>>optimalBeforeAndIpMap;
	  
	  Algorithm()
	  {
		  tr=new Travel();
		  pl=new Print();
		  resultList=new ArrayList<Store>();
		  edgeMap=new HashMap<String,Integer>();
		  optimalMtuMap=new HashMap<String,Integer>(); // store the minimum MTU for every node pair
		  ipMap=new HashMap<String,String>();
		  optimalBeforList=new HashMap<String,ArrayList<String>>();  // store before node. 
		  optimalMTU=Integer.MAX_VALUE;
		  optimalBeforeAndIpMap=new HashMap<String,ArrayList<String>>(); // store before node and its IP. 
	  }	
	  /**
	   * this method is used for initial all configuration 
	   * and start use the algorithm to get the final result.
	   * The algorithm is below method (except the print method
	   * which is used for print the result, and we should call this method to 
	   * start our algorithm.)
	   * and the method in the class Travel.jave, which will contain how to find the result.
	   * @param startNode
	   */
	  public void initialAndStart(String startNode)
	  {
		  resultList=tr.getResult(startNode);	
		  edgeMap=tr.getEdge();
		  ipMap=tr.getAllIP();
		  findAll(startNode);
	  }
	  
	  /**
	   * find the minimum MTU and add it into the optimalMtuMap map. 
	   * firstly we make optimalMTU is positive infinity number 
	   * And for every time, we find the one link,
	   * if this link is smaller than optimalMTU.
	   * we will change the optimalMTU equals to this edge.
	   */	  
	  public void findMinMtu(String startNode,String bNode, String nextNode,String destionation)
	  {	   		  
		   for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) 
		   {
			   String temp1= bNode+"-"+nextNode;
			   if(temp1.equals(entry.getKey()))
			   {
					   int tempMTU=entry.getValue();
					   if(optimalMTU>tempMTU)
					   {
						   optimalMTU=tempMTU;
					   }
					   String temp=startNode+"-"+destionation;
					   optimalMtuMap.put(temp,optimalMTU);
					   break; 
			   }
	      }  // end for for edgeMap circulation.	   
	  }
	  
	  /**
	   * find the before node and the minimum MTU. 
	   * @param startNode
	   * @param destion
	   */
	 public void storeInfor(String startNode,String destion)
	 {
		   String beforenode="test"; // store the before node of current node,at first it has a arbitrary value
		                             // in oder to get through while test, except to set the value "null",
		                             // because it is the end condition for the while circulation.
		   
		   String dest=destion;      // at the first one, it store the destination node, and then it will
		                             // store the before node of the before destination 
		   
		   ArrayList <String> list= new ArrayList<String>();
		   // the list for the optimalBeforList, it only have before node without the IP
		   ArrayList <String> listiP=new ArrayList<String>();
		  //  the listiP for the optimalBeforeAndIpMap, it has node with the IP. 	   
		   /**
			 *  as the below, the circulation will stop if the before node of current node is null
			 *  because only the before node of the start node is null after we doing the process.
			 *  so we have already find the path from the destination node to the start node.
			 *  and at the same time we find the corresponding IP. 
			 */
		   while(!(beforenode.equals("null")))
		   {
			   /**
			    * find the beforeNode. 
			    * every time we find the before node of the current node
			    * and we make current node equals to the before node for the next circulation
			    * and in this time, we add the optimalMTU to corresponding map
			    * if the before node is null, we should stop.
			    */
			   for(int i=0;i<resultList.size();i++)
			   {
				   if(dest.equals(resultList.get(i).GetNode()))
				   {
					    beforenode=resultList.get(i).getBefore();
					    findMinMtu(startNode, beforenode,dest,destion);	// call this method to find minimum MTU. 
					    findIP(dest,listiP); // call this method to find the node IP
					    dest=beforenode;// destination will equal to the before node of current node					    
					    if(!(beforenode.equals("null")))
					    {
					    	list.add(beforenode);
					    }
				   }			   
			   } // end for resultList			   
		   }  // end for while circulation. 		
		   String temp=startNode+"-"+destion;
		   optimalBeforList.put(temp, list);
		   // put the path to this map for future use to find IP and go print the path. 
		   optimalBeforeAndIpMap.put(temp, listiP);
		 // put the path and IP and node into the map.   
		 
   	 }	 
	/**
	 * find all path and MTU from the given start node.
	 * in this method, we will finish all result 
	 * from the start node to every other node
	 */	 
	 public void findAll(String startNode)
	 {
		 String destion;
		 /**
		  * first we need to find the right position of start node 
		  * in the resultList.
		  */
		 int position=-1; // mark the position of the start node in the resultList.
		 for(int j=0;j<resultList.size();j++)
		 {
			  if(startNode.equals(resultList.get(j).GetNode()))
			  {
				  position=j;
				  break;
			  }
		 }

		 pl.pln("the start position is: " +(position+1));
		 
		 for(int i=0;i<resultList.size();i++)
		 {			 
			 /**
			  * first if this circulation the value of this time is equal to the position 
			  * we do nothing ,just do the next circulation.
			  */
			 if(i==position) // as the startNode
			 {
				 pl.pln("it is the the startNode, we do nothing, just do next circulation");
				 continue;
			 }
			destion=resultList.get(i).GetNode();			
	    	pl.pln("the "+(i+1)+" number destion node is "+destion);	    	
		    storeInfor(startNode,destion);
		    /**
		     *  after above process we finish from start node to one of node.
		     *  as do the next find, we need to make the optimalMTU is MAX_value. 
		     */
		    optimalMTU=Integer.MAX_VALUE;		    			
		    			 
       	 }	 
	 }
	 
	 /**
	  * According to the optimalBeforList and ipMap
	  * we will find all IP of node 
	  * and put into a optimalBeforeAndIpMap Map.
	  */
	 public void findIP(String dest,ArrayList<String>list) 
	 {
		 
		 for(Map.Entry<String, String> entry : ipMap.entrySet())
		 {
			  if(dest.equals(entry.getKey()))
			  {
				  String ip=entry.getValue(); // find corresponding IP
				  String temp=dest+"(IP:"+ip+")";
				  list.add(temp);
				  break;
			  }
		 }

	 }	 
	  /**
	   * this method,we use to formal a format for future to print the result
	   * the first line is the start node and the second line is finally node
	   * and the list one is optimalMTU that we choose in our algorithm 
	   */ 
	  public String formatForoptimalMtu()
	  {
		     StringBuffer sb = new StringBuffer();
		     sb.append("\nfrom the start node to the destination, the optimalMTU is below: \n");
		     sb.append("------------------------------------------------------\n");
		     sb.append("start node\t destination node\t optimalMtu\n");
	         for(Map.Entry<String, Integer> entry : optimalMtuMap.entrySet())
		     {
		    	  String []node;
		    	  node=entry.getKey().split("-");
		    	  int mtu=entry.getValue();
		    	  sb.append("    "+node[0]+"\t\t "+"       "+node[1]+"\t\t "+"    "+mtu+"\n");
		     }
		     sb.append("------------------------------------------------------");
		     return sb.toString();
		  
	  }
	 
	  /**
	   * this method,we use to formal a format for future to print the result
	   * the first line is the start node and the second line is finally node
	   * and the list one is nextHop IP that we choose in our algorithm 
	   */ 
	  public String formatNextHop()
	  {
		  StringBuffer sb = new StringBuffer();
		  sb.append("\nfrom the start node to the destination, the travel path as the below: \n");
		  sb.append("---------------------------------------------------------------------\n");
		  sb.append("start node\t destination node\t travel path\n");
		  int time=0;
		  for(Map.Entry<String, ArrayList<String>> entry : optimalBeforeAndIpMap.entrySet())
		  {
			   time++;
			   String []node;
			   node= entry.getKey().split("-");
			   sb.append("    "+node[0]+"\t\t "+"         "+"\t\t ");
			   for(int i=entry.getValue().size()-1;i>=0;i--)
			   {
				   String path=entry.getValue().get(i);
				   if(i==entry.getValue().size()-1)
				   {
				   sb.append("start: "+path+"\n");
				   }
				   if(i==0)
				   {
				   sb.append("      "+"\t\t "+"       "+node[1]+"\t\t ");   
				   sb.append("final: "+path+"\n");
				   }
				   if((i!=0)&&(i!=entry.getValue().size()-1))
				   {	   
				   sb.append("      "+"\t\t "+"         "+"\t\t ");  
				   sb.append("next : "+path+"\n");
				   }
			   }
			if(time!=optimalBeforeAndIpMap.size() )
			{
				sb.append("---------------------------------------------------------------------\n");
			    sb.append("start node\t destination node\t travel path\n");	
			}		   
		  }	
		  sb.append("---------------------------------------------------------------------\n");
		  return sb.toString();
	  }
	  
	  /**
		* print the result. 
		*/
		
	 public void print(String startNode,String choice)
	{
			 initialAndStart(startNode);
			 if(choice.equals("1"))
			 {
			 pl.pln(formatForoptimalMtu());
			 }
			 if(choice.equals("2"))
			 {
			 pl.pln(formatNextHop());
			 }
			 if(choice.equals("3"))
			 {
				 pl.pln(formatForoptimalMtu());
				 pl.pln(formatNextHop());
				 
			 }
			
	 }
	  
	  
	  	 
}
