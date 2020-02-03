package MST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





public class Pgraph {
	private int totalPv;
	private int totalPe;
	private String line;
	private int lineNumber;
	private Map<Integer,Pedge>Pmapedge;
	private ArrayList<Pvertex> Pvertexin;
	private String  []Str;
	private ArrayList<String> Plinkvertex;
	private Map<String, String> Padj;
	private ArrayList<String> Psinglevertex;
	private int w;
 	private String firstV;
 	private String secondV;
 	private Map<String,Integer>numbermap;
 	private ArrayList<String>listedge;
	
 	/**
	 *Initialize each value 
	 */
	public Pgraph(){
		numbermap=new HashMap<String,Integer>();
		secondV=null;
		firstV=null;
		w=0;
		listedge=new ArrayList<String>();
		totalPv=0;
		totalPe=0;
		line=null;
		lineNumber=1;
		Pmapedge=new HashMap<Integer,Pedge>();
		Pvertexin=new ArrayList<Pvertex>();
		Plinkvertex=new ArrayList<String>();
		Padj=new HashMap<String,String>();
		Psinglevertex=new ArrayList<String>();
	}
	
	/**
	 * load the graph information
	 * @param filename
	 */
	public void loadPgraph(String filename){
		
		try {
			 URL file=Kgraph.class.getResource(filename);
		     file.getFile();
		     InputStream f= Kgraph.class.getResourceAsStream(filename); 
			 BufferedReader br=new BufferedReader(new  InputStreamReader(f));
			while((line=br.readLine())!=null){
				if(lineNumber==2){
					totalPv= Integer.parseInt(line);
				}
				if(lineNumber==3){
					 Str=line.split(",");
					 addsingle(Str);
					
				}
				if(lineNumber==4){
		           totalPe=Integer.parseInt(line);	           
		           
				}
				if(lineNumber>=5&&lineNumber<=(5+totalPe-1)){
					String []spl=line.split("=");
				    w=Integer.parseInt(spl[1]);
					String []sd=line.split(",|=");
				    sd[0]=sd[0].replace("(","");
				    sd[1]=sd[1].replace(")","");
				    firstV=sd[0];
				    secondV=sd[1];
                    addPedge(firstV,secondV,w);
                    listedge.add(line);
                    Plinkvertex.add(spl[0]);
                    }   
				lineNumber++;
			}
			br.close();
			LinkVertex();
			addvertex();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	/**
     * find all the vertex.
     * @param str
     */
	public void addsingle(String []str){
		
		for(int i=0;i<str.length;i++)
		{
			Psinglevertex.add(str[i]);
		}
	}

	/**
	 * add the information of the vertex
	 * and numbermap is used for record the number adj of a vertex.
	 */
	public void addvertex(){
		for (Map.Entry<String, String> entry : Padj.entrySet())
		{
			String []str=entry.getValue().split(",");
			for(int i=0;i<str.length;i++)
			{
			Pvertex pv=new Pvertex(entry.getKey(),str[i],0,"",true);
		    Pvertexin.add(pv);
			}
			numbermap.put(entry.getKey(), str.length);
		}
         
	 }

	/**
	 * add the information of the edge
	 * @param PedgeId
	 * @param firstV
	 * @param secondV
	 * @param w
	 */
	public void addPedge(String firstV,String secondV,int w){
		Pedge Pe=new Pedge(firstV,secondV,w);
		Pmapedge.put(lineNumber,Pe);	
    	}
	
	/**
	 * add the adj information of the vertex to the list.
	 * @return
	 */
	public ArrayList<String> LinkVertex(){
		ArrayList<String>list=new ArrayList<String>();
		StringBuilder sb=new StringBuilder();
		int i;
		String first,second,s,singlevertex;
		for(int j=0;j<Psinglevertex.size();j++){
			    singlevertex=Psinglevertex.get(j);
			    i=0;
			    while(i<Plinkvertex.size()){
					String []sd=Plinkvertex.get(i).split(",|=");
				    sd[0]=sd[0].replace("(","");
				    sd[1]=sd[1].replace(")","");
				    first=sd[0];
				    second=sd[1];
			        s=Plinkvertex.get(i);
			    	if(s.contains(singlevertex)){
			            if(singlevertex.equals(first)){
			               sb.append(second+",");
			            }
			            if(singlevertex.equals(second)){
			            	sb.append(first+",");
			            }
			          
			            }
			    	i++;
			        }
			    list.add(sb.toString());
			    sb.setLength(0);
			    }
		addlinkvertex(list);
		return list;
	}
	
	/**
	 * add the adj of vertex to the map
	 * @param list
	 */
	public void addlinkvertex(ArrayList<String> list){
		int j=Psinglevertex.size();
		for(int i=0;i<j;i++){
            Padj.put(Psinglevertex.get(i),list.get(i));			
		}
		
	}
	

	/**
	 * get the number of  vertex
	 * @return
	 */
	public int getTotalPv(){
		return totalPv;
	}

	/**
	 * get the number edge
	 * @return
	 */
	public int getTotalPe(){
		return totalPe;
	}

	/**
	 * get the the information of vertex.
	 * @return
	 */
	
	public ArrayList<Pvertex> getPvertex(){
		return Pvertexin;
	}

	
	/**
	 * get the information of edge in detailed.
	 * @return
	 */
	public Map<Integer,Pedge> getPedge(){
		return Pmapedge;
	}
	
	/**
	 * get the single vertex list.
	 * @return
	 */
	public ArrayList<String> getPsinglevertex(){
		return Psinglevertex;
	}
  
	/**
     * get the adj information of the vertex
     * @return
     */
    public Map<String,String> getPlinkvertex(){
    	return Padj;
    }
    /**
     * get the number adj of a vertex.
     * @return
     */
    public Map<String,Integer> getNumbermap(){
    	return numbermap;
    } 
    /**
     * get the information of the edge.
     * @return
     */
    public ArrayList<String> getEdge(){
    	return listedge;
    }
	

}
