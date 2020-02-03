package MST;


import java.util.ArrayList;




public class Heap {
	
	private ArrayList<Pvertex>heaplist;
	
	public  Heap(){
	heaplist=new ArrayList<Pvertex>();
	}
   
	/**
     * build the minimum heap of by using the key of vertex.
     * @param list
     * @param root
     */
    public void buildMinheap(ArrayList<Pvertex>list,String root){
    	      String p="NIL";
    	      int k=(int) Double.POSITIVE_INFINITY;
    	      for(int i=0;i<list.size();i++){
    	    		  if(list.get(i).getNode().equals(root))
    	    		  {
    	    			  Pvertex pv=new Pvertex(root,list.get(i).getAdj(),0,p,true);
    	    			  list.set(i, pv);
    	    		  }
    	    		  else {
    	    			  Pvertex pv=new Pvertex(list.get(i).getNode(),list.get(i).getAdj(),k,p,true);
    	    			  list.set(i, pv);
    	    		  }
       	      }
    	      minHeapify(list);
      }
    
     /**
      * get the minimum of heap.
      * @param list
      * @return
      */
     public ArrayList<Pvertex> extractMin(ArrayList<Pvertex>list){
         minHeapify(list);
         for(int i=0;i<list.size();i++)
   	     {
   	      while(list.get(i).getNode().equals(list.get(0).getNode()))
   	      {
          Pvertex pv=new Pvertex(list.get(i).getNode(),list.get(i).getAdj(),list.get(i).getPkey(),
        		  list.get(i).getPparent(),false);
          list.set(i,pv);
          break;
   	      }
       	  }
    	 return list;
       }
     
     /**
      * maintain the character of the minimum heap.
      * @param list
      */
      public void minHeapify(ArrayList<Pvertex>list){
    	  list=new Quicksort().Pquicksort(list, 1, list.size());
  	   }
     
     /**
      * decrease the heap.
      * @param list
      * @return
      */
	public ArrayList<Pvertex> decreaseKey(ArrayList<Pvertex>list){
		          minHeapify(list);
		          for(int i=0;i<list.size();i++)
		    	     {
		    	      while(list.get(i).getNode().equals(list.get(0).getNode()))
		    	      {
		           Pvertex pv=new Pvertex(list.get(i).getNode(),list.get(i).getAdj(),list.get(i).getPkey(),
		         		  list.get(i).getPparent(),true);
		           list.set(i,pv);
		           break;
		    	      }
		    	      
		      	  }
		     	 return list;
	   
	}
	
	/**
	 * return the result
	 * @return
	 */
	public ArrayList<Pvertex> getList(){
		return heaplist;
	}
	
}
