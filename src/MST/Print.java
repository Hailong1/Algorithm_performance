package MST;

import java.io.File;

import javax.swing.JOptionPane;

public class Print {
	   
	   MSTk mk=new MSTk();
       MSTp mp=new MSTp();
       
	/**
	 * get the finally result and print to two ".txt" document,one is for Prim's and anther is for Kruskal's
	 * And the "graph.in.txt" is put in the "MSTproject/bin". 
	 */	
	public void Result()
	{
	     mk.getGraph("./graph.in.txt");	    
         mp.getGraph("./graph.in.txt");
         print();
   	}
    
	/** 
	 * both result will put in document called MST that will be new built 
	 * are located in the same place. 
	 */
	public void print()
	{
		mk.getKresult();//print to the result into console.
		mp.getPresult();//print to the result into console.
        String path="./MST";
        getR(path);
        mp.printTo(path+"/Prim.out.txt"); //print to the result into "Prim.out.txt".
        mk.print(path+"/kruskal.out.txt"); //print to the result into "Kruskal.out.txt".
        String message="please find the export in the current directory that called MST ";	
	    JOptionPane.showMessageDialog(null,message,"successful",
	    			 JOptionPane.INFORMATION_MESSAGE );
	}
	

	/**
	 * build a new document.
	 * @param directory
	 */
	 public void getR(String directory){
   	  File f=new File(directory);
   	  if(!f.exists()){
     	    f.mkdirs();
     	   }
     }
	
	
}
