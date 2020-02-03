package ShortestPath;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * start begin the program 
 * first please put one node as the student 
 * and the choose one way to show the result. 
 * it is mean what you want to show. 
 * @author Hailong
 *
 */
public class Begin {

	   private Configuration cf;
	   private Print pl;
	   private Algorithm al;
	   private Scanner input;
	   private String choice;
	   
	   Begin()
	   {
		   cf=new Configuration();
		   pl=new Print();
		   al=new Algorithm();
		   input=new Scanner(System.in);
		   choice="test";// put the choice
	   }
	   
	   /**
	    * start running the program.
	    */
	   public void startProgram()
	   {
		   ArrayList<String> list=new ArrayList<String>();
		   list=cf.getAllNode();
		   StringBuffer sb=new StringBuffer();
		   sb.append("Note: 1. please enter below one as the start node to run the program.\n");
		   sb.append("if enter one character or number not below one, it will repeat until you enter the right one.\n");
		   sb.append("enter the character q to quit.\n");
		   sb.append("2. please enter 1,2,and 3 to show which result want to see\n");
		   sb.append("     1, represent want to show the optimalMTU.\n");
		   sb.append("     2, represent want to show the nextIP\n");
		   sb.append("     3, represent both 1 and 2\n");
		   for(int i=0;i<list.size();i++)
		   {
			   sb.append(list.get(i)+" ,");
		   }
		   sb.append("\n please enter you node : ");
           pl.pln(sb.toString());		  
           enterStartAndChoice(list);
	   }
	   
	   /**
	    * enter the startNode for running the program. 
	    * And enter the choice to determine which result want to show. 
	    */
	   
	   public void enterStartAndChoice(ArrayList<String>list)
	   {
    	   String StartNode="test";
		   StartNode.trim();
		   choice.trim();
		   String flag="true";
		   /**
		    * below while circulation 
		    * first one it should check the enter of node is correct or not 
		    * if it correct it will check the enter of the number is correct or not. 
		    */
		   while(flag.equals("true"))
		   {
			   
			  StartNode=input.next(); // enter the node	 
			   
			     for(int i=0;i<list.size();i++)
               {
   				    judgeChoice(StartNode,list.get(i));
   				    
   				    if(!(choice.equals("test")))
   				   {
   					   flag="false"; // find the right number. 
   				   }
            
               }  // end for for 
             
			   if(StartNode.equals("q"))
			   {
				   pl.pln("we stopped the program.");
				   break;
			   } 
			   
			   if(choice.equals("0"))
			   {
				   pl.pln("we stopped the program.");
				   break;
			   }
			   
			   if(flag.equals("false"))
			   {
				
				   al.print(StartNode,choice);
			   }
			   
		   }	
		   
		   input.close();
		   
	   }
	   
	   /**
	    * judge the input number. 
	    * @return
	    */
	   
	   public void judgeChoice(String startnode,String node)
	   {
		   
		   if(startnode.equals(node))
     	  {
			  pl.pln("successfully enter the Startnode, please enter the number from 1,2,3 and 0 to quit");
			  
			 while(true)  
			{
     	         choice=input.next(); 
     	         
     		     if(choice.equals("1"))
     		     {
     			     break;
     		      }
     		     if(choice.equals("2"))
     		     {
     			     break;
     		     }
     		  
     		     if(choice.equals("3"))
     		     {
     			     break;
     		     }
     		     if(choice.equals("0"))
     		     {
     			     break;
     		     }
     		  
			  } // end while while
	
       	  }  // end if
		   
          
	   }
	
	
	
}
