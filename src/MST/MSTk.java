package MST;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MSTk {
    private int Resultedge;
	private ArrayList<String> Kvertex;
	private ArrayList<Kedge> Kedge;
	private ArrayList<String> Kresult;
	private Map<String,Integer> mapresult;
	private String result;
	
	/**
	 * Initialize each value
	 */
	
	MSTk(){
	 Kvertex=new ArrayList<String>();
	 Kedge=new ArrayList<Kedge>();
	 Kresult=new ArrayList<String>();
	 mapresult=new HashMap<String,Integer>();
	}
	
	/**
	 * get all information of Graph.
	 * @param filename
	 */
	
	public void getGraph(String filename){
		Resultedge=0;
		Kgraph kg=new Kgraph();
		kg.loadKgraph(filename);
		Kvertex=kg.getSinglevertex();
		Kedge=kg.getListedge();
		MSTkgraph();
	}
	
	/**
	 * get result pair.
	 */
	public void MSTkgraph(){
		UnionFind uf=new UnionFind();
		for(int i=0;i<Kvertex.size();i++){
			uf.MakeSet(Kvertex.get(i));
		}
		for(int i=0;i<Kedge.size();i++){
		    String first=Kedge.get(i).getFirstvertex();
			String second=Kedge.get(i).getSecondVertex();
			String edge=Kedge.get(i).getEdge();
			if(!(uf.FindSet(first).equals(uf.FindSet(second)))){
				Kresult.add(edge);
				uf.Union(first, second);
				Resultedge++;
			}
		}
		getMap();
	}
   
    /**
     * store the result into map.
     */
	public void getMap(){
		int j;
		for(int i=0;i<Kresult.size();i++){
			j=0;
			while(j<Kedge.size()){
				if(Kedge.get(j).getEdge().contains(Kresult.get(i))){
					mapresult.put(Kresult.get(i),Kedge.get(j).getWeight());
				}
				j++;
			}
		}
		getR();
      }
	
	/**
	 * get the proper result.
	 * 
	 */
	public void getR(){
		StringBuilder br=new StringBuilder();
		br.append("Kruskal.out:"+"\r\n");
		br.append(Resultedge+"\r\n");
		for(int i=0;i<Kresult.size();i++){
			for (Map.Entry<String, Integer> e : mapresult.entrySet()){
				if(Kresult.get(i).equals(e.getKey())){
					br.append(Kresult.get(i)+"="+e.getValue()+"\r\n");
				}
			}
		}
	    result=br.toString();
		br.setLength(0);

	}
	
	/**
	 * print to the final result.
	 * @param filename
	 */
	public void print(String filename)
	{
	 try{
		 File file=new File(filename);
		 if(!file.exists()){
		
			file.createNewFile();
	
		 }
		FileWriter filewriter=new FileWriter(file);
		PrintWriter out=new PrintWriter(filewriter);
		out.write(result);
		out.flush(); 
		out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get the final result of Kruskal's and print into the Console.
	 * @return
	 */
	public void getKresult()
	{
		System.out.println(result);
	}
	
	
}
