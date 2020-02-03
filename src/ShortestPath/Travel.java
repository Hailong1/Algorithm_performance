package ShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The algorithm to find the way (it is used the route that will have the Total minimum MTU) and after that,
 * we store the information into a list. 
 * in this list, we can get the path from start node to every other node using above way.  
 * 
 * @author Hailong
 *
 */
public class Travel {
   	   private Print pl;
	   private Configuration cf;
	   private Map<String,String>nodeIP;
	   private ArrayList<String>allNode;
	   private Map<String,Integer>edge;
	   private ArrayList<Store>storeList;
	   private int numbernode;// get the number of node; 
	   private Map<String,ArrayList<String>>adj; 
	   private ArrayList<String> visitNode;
	   /**
	    *  adjList means we store all adjacent node of the start node. 
	    */	
	   private ArrayList<String> adjList;		   
	   Travel()
	   {
		   pl=new Print();
		   cf=new Configuration(); // it is used for load all information from my database that
		                           // all information about the configuration. 
		   nodeIP=new HashMap<String,String>();
		   allNode=new ArrayList<String>();
		   storeList=new ArrayList<Store>();
		   edge=new HashMap<String,Integer>();
		   numbernode=0;
		   /**
		    * the adj map is means we should use the map to choose the start node.
		    * if this map is empty, we should put all information about the current circulation start node.
		    * And the key of this is the current circulation start node and value is a 
		    * list that store all adjacent of this node. 
		    */
		   adj=new HashMap<String,ArrayList<String>>();
		   visitNode=new ArrayList<String>();
		   adjList =new ArrayList<String>();	
	   }
	   /**
	    *  get the result of this travel. 
	    *  we store in the storeList,
	    *  which contains the name of this node and before node and currentMTU 
	    *  from the minimum MTU starting from start node and this node. 
	    * @return
	    */
	   public ArrayList<Store> getResult(String startNode)
	   {
		   InitialC(); // load all information about the configuration.
		   startFind(startNode);
		   pl.pln("result is"+storeList);
		   return storeList;
	   }	   
	   /**
	    * Initialize all the Configuration for the algorithm. 
	    */
	   public void InitialC()
	   {
		   cf.start();
		   nodeIP=cf.getIP();
		   allNode=cf.getAllNode();
		   edge=cf.getAllEdge();
		   numbernode=cf.getnumberOfnode();
	   }
	   
	   /**
	    *  Initialize the start node for the travel.
	    *  we initialize the start node as the following:
	    *  the currentMTU is 0 and its before node is null  (because it the start node)
	    *  and other node as the following:
	    *  the currentMTU is positive infinity number 
	    *  and its before node is null (because we do not start the travel. )  
	    *  allNode and storeList is the same meaning as the step 1 in algorithm. 
	    */
	   public void InitialNode(String startNode)
	   {
		   String beforeNode;
		   int currentMtu;
		   for(int i=0;i<allNode.size();i++)
		   {
			   if(allNode.get(i).equals(startNode))
			   {  
				  beforeNode="null";
				  currentMtu=0;
				  storeList.add(new Store(allNode.get(i),beforeNode,currentMtu)); 				   
			   }
			   else
			   {
				   beforeNode=null;  
				   currentMtu=Integer.MAX_VALUE;
				   //  currentMtu=Integer.MIN_VALUE;
				   storeList.add(new Store(allNode.get(i),beforeNode,currentMtu));
			   }
		   }
   	   }
	  
	   /**
	    * start the the travel
	    */    
	   public void startFind(String startNode)
	   {
		   InitialNode(startNode);//call the initiallNode method initial all node 
		   putadjmap(startNode);  // call the put adj map method. 		   
		   /**
		    * below circulation is used to visit all node
		    * each circulation we choose one node as the start node,
		    * first one start node that is input the data.
		    * and then we should choose one of the adjacent node of current start node as the the next start node
		    * if all of adjacent node of  current start node have been chosen as the next start node.
		    * we should choose arbitrary one adjacent node of the before visited node.
		    * and then we repeat above operation until we have been visited all node. 
		    */
		   for(int i=0;i<numbernode;i++) 
		   {
			   pl.pln("startnode:"+startNode);			  
			   /**
			    * below circulation is used to circulate all edges that is belonged in the configuration.
			    * edge is the same meaning as step 1 in algorithm. 
			    */
               boolean flag=true; // fix current node MTU flag.
			   for (Map.Entry<String, Integer> entry : edge.entrySet())   
			   {     
				     String []temp;
                     temp=entry.getKey().split("-");// the first one that is temp[0] is for this one is the start node,
                                                    // the second one that is temp[1] is for this one is its adjacent
                     
                     if(temp[0].equals(startNode))
                     {
                   	   if(flag)
                           {
                          	 findAll(startNode); 
                          	 flag=false;
                           }
    
                    	     if(adj.isEmpty())
                    	   {
                    	    	adjList.add(temp[1]);
                    	   }
                           ChangeStore(temp[0],temp[1],entry.getValue());
                      }
                    		
			   }//end of the map
			   
			   /**
			    * put the new key and value into map 
			    * for the next circulation. 
			    */
			   if(adj.isEmpty())
			   {
				   adj.put(startNode,adjList);
			   }
               
			   /**
			    * below circulation is used for choosing a node as 
			    * the next start node. 
			    */
			   for (Map.Entry<String, ArrayList<String>> entryadj : adj.entrySet()) 
			   {
                   int time=entryadj.getValue().size();
                   // time is the number of adjacent of this node.
                   /**
                    * below circulation is used for find the next start node
                    * if it find one node we should drop this circulation.
                    */                   
                   boolean flagempty=false; 
                   // if the flag empty is true, we should choose another start node 
                   // because the next start node will be as the same one 
                   // and we do not visit a node twice, so it should choose another one.
                   while(time>0)
                  {
                	  if(Testadj(entryadj.getValue().get(0)))
                     {
                   	   changeAllnode(startNode);
                	   show();
                	   for(int f=0;f<visitNode.size();f++)
                	   {
                		   if(entryadj.getValue().get(0).equals(visitNode.get(f)))
                		   {
                			   entryadj.getValue().remove(0);
                		   }
                	   }
                	   startNode=entryadj.getValue().get(0);
    				   entryadj.getValue().remove(0);
    				   if(entryadj.getValue().isEmpty())
    				   {   					   
    					   adj.clear();  					   
    					   pl.pln("in the if condition the "+entryadj.getKey()+" is clear");    					   
    					   for(int f=0;f<visitNode.size();f++)
                    	   {
                    		   if(startNode.equals(visitNode.get(f)))
                    		   {
                    			   flagempty=true;
                    	       }
                    	   }   					   
    				   }
    				   break;    // break the while circulation. 
                    }
                    else // test adj is not true.
                    {
                	   entryadj.getValue().remove(0);
                	   time--;     
                 	   pl.pln(time+": choosing next adjacent node");               	   
                	   if(entryadj.getValue().isEmpty())
                	   {
                		   adj.clear();              		   
                		   pl.pln("in the else condition the "+entryadj.getKey()+" is clear");               		   
                  	   }            	 
                    }
                	   
                  }// end for while                  
                   if(adj.isEmpty())
                   {
                	   /**
                	    * below if condition is used for 
                	    * choosing another start node
                	    * because all the adjacent node of current start node have
                	    * been chosen as the start node.
                	    * so we should choose another one as the strategy that we mentioned before.  
                	    * and if flagempty is true we also need to choose another one.
                	    */
    				   if(time==0||flagempty)
    				   {
       					   String sNode=findAdj(startNode);	 
  					          if(!(sNode.equals("false")))
  					         {					    	    
  					        	putadjmap(startNode);
  					    	    changeAllnode(startNode);
  					    	    startNode=sNode;
  					    	    entryadj.getValue().remove(0); 					    	 
  					         }
  					          else
  					          {
    					       startNode=anotherStartnode(startNode);
    				       // call method anotherStartnode to choose the start node, it is defined below.
  					          } 					     
       				   } 
    				   else
    				   {	   
                   	   pl.pln("The last one of about node as the start node");
    				   }
                   }     	
			   }  //end for choosing a node as start node circulation. 			   
		   } //end of the list circulation. 		  
	   }
	   /**
	    * whether it has the adjacent node that are in the allNode list. 
	    * if it has we should use this adjacent node as the start node
	    * and put the node into adj map.
	    */
	   public String findAdj(String startNode)
	   {
		   String flag="false";
		   boolean flagupMap=false;		   
		   for  (Map.Entry<String, Integer> entry : edge.entrySet()) 
		   {
			   String []temp;
               temp=entry.getKey().split("-");
			   if(temp[0].equals(startNode))
               {
                      for(int i=0;i<allNode.size();i++)
                      {
                    	  if(temp[1].equals(allNode.get(i)))
                    	  {
                    		  flag=temp[1];
                    		  flagupMap=true;
                    	  }
                      }
               } 
			   if(flagupMap) break;			  
		   }		   
		   return flag;
	   }	
	   /**
	    * add value in the adj map.
	    */
	   public void putadjmap(String startNode)
	   {		   
		   /**
		    * below circulation is used for to find the adjacent node of the start node.
		    * and store it into the adjList list. 
		    */
		   for  (Map.Entry<String, Integer> entry : edge.entrySet()) 
		   {
			   String []temp;
               temp=entry.getKey().split("-");
			   if(temp[0].equals(startNode))
               {
                      adjList.add(temp[1]);                                      		          
               } 
		   }
		   adj.put(startNode, adjList); // this is put a pair of start node and its adjacent node into a map. 
	   }	   
	   /**
	    * text whether  the adjacent of this node has in the allNode list
	    * if the answer is true, we should choose this node as the start node 
	    * because it can not be visited before.
	    * if the answer is false, we should not choose this node as the start node
	    * because it can be visited before. 
	    */	   
	   public boolean Testadj(String s)
	   {
		   boolean flag=false;		   
		   for(int i=0;i<allNode.size();i++)
		   {
			   if(s.equals(allNode.get(i)))
			   {
				   flag=true;
			   }
		   }		   
		   return flag;
	   }	   
	   /**
	    * after doing this we will make the currentMTU of this node is the right value at this moment. 
	    */
	   public void Changefirst(String firstnode,String adjacent, int mtu)
	   {
		   int firstMTU=-1,adjacentMTU=-1;
		   String firstbefore=null;	  		
		   /**
		    *  find the information about first node and his one adjacent
		    *  from the storeList, about from start point to this node, we have before node ,and current minimum MTU.
		    */
		   for(int i=0;i<storeList.size();i++)
		   {	
			   if(firstnode.equals(storeList.get(i).GetNode()))
			   {
				   firstMTU=storeList.get(i).getCMTU();		
				   firstbefore=storeList.get(i).getBefore();
			   }   
			   if(adjacent.equals(storeList.get(i).GetNode()))
			   {
				   adjacentMTU=storeList.get(i).getCMTU();	   
			   }			   
			   if((adjacentMTU!=-1)&&(firstMTU!=-1))
			   {			   
				   //comparing the minimum MTU and restore the new value in adjacentMTU and firstMTU. 
				   if(adjacentMTU!=Integer.MAX_VALUE)
				   
		//		     if(adjacentMTU!=Integer.MIN_VALUE)	   
				   {
					   if(firstMTU>adjacentMTU+mtu)
					   
		//	           if(firstMTU<adjacentMTU+mtu)			   
					   {
						   firstMTU=adjacentMTU+mtu;
						   firstbefore=adjacent;
					   }					
				   }
				   break;     
			   }			   
		   }//end first for circulation.		   
		   /**
		    *  we need to update the result into the storeList. 
		    */
		   for(int i=0;i<storeList.size();i++)
		   {
			   if(firstnode.equals(storeList.get(i).GetNode()))
			   {
				   storeList.set(i, new Store(firstnode,firstbefore,firstMTU));	
				   break; 
			   }			   
		   } //end  second for circulation
	   }    
	   /**
        * make all startNode's currentMTU is right value at this moment. 
        * @param StartNode
        */
	   public void findAll(String StartNode)
	   {		
		   for (Map.Entry<String, Integer> entry : edge.entrySet()) 				   
		      {        String []temp;
				    temp=entry.getKey().split("-");
				    if(temp[0].equals(StartNode))
				    {
				    	  Changefirst(temp[0],temp[1],entry.getValue());
				    }
			   } // end for the map. 
		   
	   }
	  
	   /**
	    * It is that we use the minimum MTU of the path as the fragmentation. 
	    * And mtu is the MTU of link between the first node and adjacent. 
	    * And after do this the StoreList will store all information we need. 
	    */
	   public void ChangeStore(String firstnode,String adjacent, int mtu)
	   {
		   int firstMTU=-1,adjacentMTU=-1;
		   String adjacentbefore=null;	   		
		   /**
		    *  find the information about first node and his one adjacent
		    */
		   for(int i=0;i<storeList.size();i++)
		   {
			  // find the MTU (the minimum from start to this node) of the first node.
			   if(firstnode.equals(storeList.get(i).GetNode()))
			   {
				   firstMTU=storeList.get(i).getCMTU();		
			   }   			   
			   // find the MTU (the minimum from start to this node) of one of the adjacent node of current start node.
			   if(adjacent.equals(storeList.get(i).GetNode()))
			   {
				   adjacentMTU=storeList.get(i).getCMTU();
				   adjacentbefore=storeList.get(i).getBefore();			   
			   }
			   if((adjacentMTU!=-1)&&(firstMTU!=-1))
			   {			   				  
				   //comparing the minimum MTU.
				   // and restore the new value in adjacentMTU and firstMTU.
				   
				     if(adjacentMTU>firstMTU+mtu)
				//     if(adjacentMTU<firstMTU+mtu) 	   
				   {
					   adjacentMTU=firstMTU+mtu;
					   adjacentbefore=firstnode;
					   break; 
				   }
			   }			   
		   }//end first for circulation.		   
		   /**
		    *  we need to update the result into the storeList. 
		    */
		   for(int i=0;i<storeList.size();i++)
		   {
			   /**
			    * update the Storelist. 
			    */
			   if(adjacent.equals(storeList.get(i).GetNode()))
			   {
				   storeList.set(i, new Store(adjacent,adjacentbefore,adjacentMTU));	
				   break; 
			   }			   
		   } //end  second for circulation		   
	    }
	   
	   /**
	    * add or remove to the allNode list.
	    * after finish the travel the allNode list will be empty,
	    * and visitedNode list will be as the same as the allNode list that is before the travel.
	    * so it is means that all node have been visited.
	    */
	   public void changeAllnode(String startNode)
	   {
		   for(int p=0;p<allNode.size();p++)
			   {
				    if(startNode.equals(allNode.get(p)))
				    {
				    	visitNode.add(allNode.get(p));
				    	allNode.remove(p);
				    }
			   }
	   }	  
	   /**
	    * print to the console about the allNode list and the visitedNode
	    * after finish the travel the allNode list will be empty,
	    * and visitedNode list will be as the same as the allNode list that is not be doing the travel.
	    * so it is means that all node have been visited.
	    */
	   public void show()
	   {
		   pl.pln("allNode is "+allNode);
		   pl.pln("visited node is "+visitNode);
	   }
	   
	   /**
	    * this method we use to choose another start node 
	    * if all of the adjacent node of the current start node 
	    * have been chosen as the start node 
	    * we use this method to choose anther start node.  
	    */	   
	   public String anotherStartnode(String startNode)
	   {
		   changeAllnode(startNode);
    	   show();
    	   for (Map.Entry<String, Integer> entry2 : edge.entrySet()) 
    	   {
    		   String []temp3=entry2.getKey().split("-");
    		   boolean flag=false;
    		   for(int k=0;k<visitNode.size();k++ )
    		   {
    			   for(int z=0;z<allNode.size();z++)
    			   {
    				    if(visitNode.get(k).equals(temp3[0]))
    				    {
    				    	if(temp3[1].equals(allNode.get(z)))
    				    	{
    				    		startNode=temp3[1];
    				    		pl.p("we should choose another one as the start node. And ");
    				    		pl.pln("this new startNode is "+startNode);
    				    		flag=true;
    				    	}
    				    }
    			   }
    		   
    		   }
    		   if(flag) break;      
    	   }// end map circulation    	   
    	   return startNode;
	   }	
	   /**
	    * get all of the information of the edge for future to get path. 
	    */
	   
	   public Map<String,Integer> getEdge()
	   {
		   return edge;
	   }	   
	   /**
	    * get all IP information for future get path.
	    */
	   
	   public Map<String,String> getAllIP()
	   {
		   return nodeIP;
	   }	   
}
