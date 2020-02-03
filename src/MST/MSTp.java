package MST;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class MSTp {
     private Map<Integer,Pedge> Pedgemap;
     private ArrayList<Pvertex> Plistvertex;
     private Map<String,Integer>Plinkadj;
     private ArrayList<String> Pvertex;
	 private ArrayList<String>u;
	 private  int w;
	 private  int key;
	 private ArrayList<String>weight;
	 private Map<String,String>result;
	 private String v;
	 private ArrayList<Presult>text;
	 private int number;
	 private ArrayList<Presult>finalresult;
	 private String print;
     
	 /**
      * Initialize each value
      */
     public MSTp(){
    	 finalresult=new ArrayList<Presult>();
    	 text=new ArrayList<Presult>();
    	 number=0;
    	 weight=new ArrayList<String>();
    	 u=new ArrayList<String>();
    	 result=new HashMap<String,String>();
    	 Pedgemap=new HashMap<Integer,Pedge>();
    	 Plistvertex=new ArrayList<Pvertex>();
    	 Plinkadj=new HashMap<String,Integer>();
    	 Pvertex=new ArrayList<String>();
     }
    
     /**
      * get the information of the Graph 
      * @param filename
      */
     public void getGraph(String filename){
    	 Pgraph pg=new Pgraph();
    	 pg.loadPgraph(filename);
    	 Pvertex=pg.getPsinglevertex();
    	 Pedgemap=pg.getPedge();
    	 Plinkadj=pg.getNumbermap();
    	 Plistvertex=pg.getPvertex();
    	 weight=pg.getEdge();
     	 MSTPgraph(Pvertex.get(0));
     	 getR();
     }
    
      /**
       * start the Prim by using the varable r as the root.
       * @param r
       */
      
      public void MSTPgraph(String r){
    	  Heap hp=new Heap();
    	  for(int i=0;i<Pvertex.size();i++){
    		  hp.buildMinheap(Plistvertex, r);
    	  }
    	  for (Map.Entry<Integer, Pedge> e : Pedgemap.entrySet())
    	  {
    			if(e.getValue().getFirstVertex().equals(r))
    			{	
    		    w=e.getValue().getWeight();  
    			break;
    			}
       	  }

    	  while(!Plistvertex.isEmpty())
    	  {
                 Plistvertex=hp.extractMin(Plistvertex);
                 v=Plistvertex.get(0).getNode();
                 int position=0;
                 int time=0;
                 for (Map.Entry<String, Integer> e : Plinkadj.entrySet())
                 {
                	 if(e.getKey().equals(v))
                	 {
                	 time=e.getValue();
                	 }
                 }
                 for(int m=1;m<=time;m++)
                 {
   
                	   if(m==1)
                	   {
                       for(int l=0;l<Plistvertex.size();l++)
                        {
                           	 if(Plistvertex.get(l).getNode().equals(v))
                           	 {
                           		 u.add(Plistvertex.get(l).getAdj());
                           	 }
                        } 
                       }
            			
                	    for(int we=0;we<weight.size();we++)
                	    {
                	    	String []spl=weight.get(we).split("=");
                	    	String []sd=weight.get(we).split(",|=");
        				    sd[0]=sd[0].replace("(","");
        				    sd[1]=sd[1].replace(")","");
        				    String first=sd[0];
        				    String second=sd[1];
                	    	if(v.equals(first)&&u.get(0).equals(second))
                	    	{
                	    		int temp=Integer.parseInt(spl[1]);
                	    		w=temp;
                	    	}
                	    	if(v.equals(second)&&u.get(0).equals(first))
                	    	{
                	    		int temp=Integer.parseInt(spl[1]);
                	    		w=temp;
                	    	}

                	    }
                	    for(int j=0;j<Plistvertex.size();j++)
                	    {	
                	    	
                	       if(Plistvertex.get(j).getNode().equals(u.get(0)))
                    		  {
                    			key=Plistvertex.get(j).getPkey();
                     			position=j;
                       			break;
                     		  }
                      	  }
    
                		  if(Plistvertex.get(position).getFlag()==true&&(w<key))
                		  {
                	    	  
                			  Plistvertex=changelist(Plistvertex);
                   			  Plistvertex=hp.decreaseKey(Plistvertex);
                   		  }  
                		  u.remove(0);
    	             }
    	        
                 for(int i=0;i<Plistvertex.size();i++)
                 {
                	 
                	 
                	 if(Plistvertex.get(i).getNode().equals(v))
                	 {
                		 Plistvertex.remove(i);
               		      i--;
                	 }
                 }                  
    
    	     } 
      
       }
   
      /**
       * make the parent of vertex and key of vertex as new value.
       * @param list
       */
      public ArrayList<Pvertex> changelist(ArrayList<Pvertex> list){
      
    	  for(int i=0;i<list.size();i++)
    	  {
    	      if(list.get(i).getNode().equals(u.get(0)))
    	      {
    		  Pvertex pv=new Pvertex(u.get(0),list.get(i).getAdj(),w,v,true);
    		  list.set(i,pv);
    		  result.put(u.get(0), v);
    		  String s=u.get(0)+","+v;
    		  Presult pr=new Presult(number,s,w);
     		  text.add(pr);
    		  number++;
    		  break;
    	      }
    	      
      	  }
    	  return list;
        }
     
      /**
       * get the print.
       * @return
       */
      public ArrayList<Presult> getProperR(){
    	  ArrayList<Presult> listtext =new ArrayList<Presult>();
    	
    	  for (Map.Entry<String, String> e : result.entrySet())
    	  {
    		  for(int i=0;i<text.size();i++)
    		  {
    			  String []sd=text.get(i).getEdge().split(",");
    			  String first=sd[0];
    			  String second=sd[1];
    			 if(first.equals(e.getKey())&&second.equals(e.getValue())) 
    			  {
    				  listtext.add(text.get(i));
    				  break;
    			  }
      		  }
    	  }
    	  Collections.sort(listtext,new PresultComparator());
       	  return listtext;
      }

      /**
       * get the result 	       
       * @param filename
       */
      public void getR()
      {
    	  finalresult=getProperR();
    	  StringBuilder br=new StringBuilder();
    	  String number=Integer.toString(finalresult.size());
    	  br.append("Prim.out:"+"\r\n");
    	  br.append(number+"\r\n");
    	  for(int i=0;i<finalresult.size();i++)
    	  {
    			String []sd=finalresult.get(i).getEdge().split(",");
			    String first=sd[0];
			    String second=sd[1]; 
    		  String weight=Integer.toString(finalresult.get(i).getWeight());
    		  String s="("+first+","+second+")="+weight;
    		  br.append(s+"\r\n");
    	  }
    	  print=br.toString();
    	  br.setLength(0);  	  
      }
    
      /**
       * print to the final result.
       * @param filaname
       */
      public void printTo(String filename)
      {
    	try{
    	File fl=new File(filename);
  	    if(!fl.exists())
  	    {
  	    	fl.createNewFile();
  	    	
  	    }
  		FileWriter filewriter = new FileWriter(fl);
	    PrintWriter writer=new PrintWriter(filewriter);
	    writer.write(print);
	  	writer.flush();
	  	writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	   
      }
     
      /**
       * get the result for Prim's and print into Console.
       * @return
       */
      public void getPresult()
      {
    	  System.out.println(print);
      }
 
     
      
}   
  
     
     

