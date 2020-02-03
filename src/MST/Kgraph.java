package MST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class Kgraph {
	private int totalKv;
	private int totalKe;
	private String line;
	private int lineNumber;
	private ArrayList<String> Ksinglevertex;
	private ArrayList<Kedge> Klistedge;
	private String []str;
	
	/**
	 * Initialize each value
	 */
	
	public Kgraph(){
		totalKv=0;
		totalKe=0;
		line=null;
		lineNumber=1;
		Ksinglevertex=new ArrayList<String>();
		Klistedge=new ArrayList<Kedge>();
		}
	
	/**
	 * load the information of the Graph
	 * @param filename
	 */
	public void loadKgraph(String filename){
		try {

	        URL file=Kgraph.class.getResource(filename);
	        file.getFile();
	        InputStream f= Kgraph.class.getResourceAsStream(filename); 
			BufferedReader br=new BufferedReader(new InputStreamReader(f));
			while((line=br.readLine())!=null){
				if(lineNumber==2){
					totalKv= Integer.parseInt(line);
				}
				if(lineNumber==3){
					str=line.split(",");
					addKsinglevertex(str);
				}
				if(lineNumber==4){
				   totalKe=Integer.parseInt(line);
				}
				if(lineNumber>=5&&lineNumber<=(5+totalKe-1)){	
					String []spl=line.split("=");
					int w=Integer.parseInt(spl[1]);
					String edge=spl[0];
					String []sd=line.split(",|=");
				    sd[0]=sd[0].replace("(","");
				    sd[1]=sd[1].replace(")","");
				    String firstV=sd[0];
				    String SecondV=sd[1];
				    addKedge(w,firstV,SecondV,edge);
				}
				lineNumber++;
			}
			br.close();
			f.close();
			sortedge();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * stored the information of edge by using the increasing order by weight of the edge.
	 * @param w
	 * @param firstV
	 * @param secondV
	 * @param edge
	 */
	public void addKedge(int w,String firstV,String secondV,String edge){
		  
		  Kedge ke=new Kedge(firstV,secondV,w,edge);
		  Klistedge.add(ke);		 
	}
	
	/**
	 * sort the weight of edge in increasing order by using quicksort.
	 */
	public void sortedge()
	{
		 Klistedge=new Quicksort().Kquicksort(Klistedge,1,Klistedge.size());
	}
	
	/**
	 * stored the vertex to the list.
	 * @param str
	 */
	public void addKsinglevertex(String []str){
		for(int i=0;i<str.length;i++){
			Ksinglevertex.add(str[i]);
		}
	}

	/**
	 * get total number of the vertex
	 * @return
	 */
	public int getTotalPv(){
		return totalKv;
	}
	
	/**
	 * get the total number of edge
	 * @return
	 */
	public int getTotalPe(){
		return totalKe;
	}
	
	/**
	 * get the information of the edge.
	 * @return
	 */
    public ArrayList<Kedge> getListedge(){
    	return Klistedge;
    }
    
    /**
     * get the single vertex. 
     * @return
     */
    public ArrayList<String> getSinglevertex(){
    	return Ksinglevertex;
    }

}
