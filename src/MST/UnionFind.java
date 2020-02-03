package MST;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
	private Map<String,Kvertex> Kmapvertex;
	private int xrank;
	private int yrank;
	private String s;
   
	/**
     * Initialize each value
     */
	public UnionFind(){
		Kmapvertex=new HashMap<String,Kvertex>();
		xrank=0;
		yrank=0;
	}
	
	/**
	 * build the vertex.
	 * @param vertex
	 */
	public void MakeSet(String vertex){
		Kvertex kv=new Kvertex(vertex,0);
		Kmapvertex.put(vertex,kv);
		
	}
	
	/**
	 * judge the vertex is belonging to the same tree or not.
	 * @param vertex
	 * @return
	 */
	public String FindSet(String vertex){
		s=Kmapvertex.get(vertex).getKparent();
		if(!(vertex.equals(s))){
			s=FindSet(s);
		}
		return s; 

	}
	
	/**
	 * link the two vertex.
	 * @param vertexX
	 * @param vertexY
	 */
	public void Link(String vertexX,String vertexY){
		if(xrank>yrank){
		    Kmapvertex.put(vertexY, new Kvertex(vertexX,yrank));
		}
		else{
			Kmapvertex.put(vertexX,new Kvertex(vertexY,xrank));
		}
		if(xrank==yrank){
		    Kmapvertex.put(vertexY,new Kvertex(vertexY,(yrank+1)));
			
		}
	}
	
	/**
	 * union the two vertex into one tree
	 * @param vertexX
	 * @param vertexY
	 */
	public void Union(String vertexX,String vertexY){
		Link(FindSet(vertexX),FindSet(vertexY));
	}
	
	
    

	
}
