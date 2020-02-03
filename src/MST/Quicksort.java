package MST;

import java.util.ArrayList;

public class Quicksort {
	
	int q,i;
	
	/**
	 * use the quicksort to sort the edge as a increasing order for Kruskal algorithm.
	 * @param list
	 * @param p
	 * @param r
	 */
	public ArrayList<Kedge> Kquicksort(ArrayList<Kedge> list,int p,int r)
	{
		if(p<r)
		{
			q=Kparitition(list,p,r);
			Kquicksort(list,p,q-1);
			Kquicksort(list,q+1,r);
		}
		
		return list;
	}
	
	
	/**
	 * use the quicksort to sort the edge as a increasing order for Kruskal algorithm.
	 * @param list
	 * @param p
	 * @param r
	 * @return
	 */
	public int Kparitition(ArrayList<Kedge>list,int p,int r)
	{
		int x=list.get(r-1).getWeight();
		i=p-1;
		for(int j=p;j<=r-1;j++)
		{
			if(list.get(j-1).getWeight()<=x)
			{
				i=i+1;
				int temp;
				String f,s,e;
				temp=list.get(i-1).getWeight();
				f=list.get(i-1).getFirstvertex();
				s=list.get(i-1).getSecondVertex();
				e=list.get(i-1).getEdge();
				Kedge kr=new Kedge(list.get(j-1).getFirstvertex(),list.get(j-1).getSecondVertex()
						,list.get(j-1).getWeight(),list.get(j-1).getEdge());
				list.set(i-1, kr);
				Kedge ks=new Kedge(f,s,temp,e);
				list.set(j-1, ks);
			}
		}
		int temp;
		String f,s,e;
		temp=list.get(i).getWeight();
		f=list.get(i).getFirstvertex();
		s=list.get(i).getSecondVertex();
		e=list.get(i).getEdge();
		Kedge kt=new Kedge(list.get(r-1).getFirstvertex(),list.get(r-1).getSecondVertex(),
				list.get(r-1).getWeight(),list.get(r-1).getEdge());
		list.set(i, kt);
		Kedge kr=new Kedge(f,s,temp,e);
		list.set(r-1,kr);
		return i+1;
		
	}
	
	
	/**
	 * use the quicksort for the minimum heap.
	 * @param list
	 * @param p
	 * @param r
	 */
	public ArrayList<Pvertex> Pquicksort(ArrayList<Pvertex> list,int p,int r)
	{
		if(p<r)
		{
			q=Ppartition(list,p,r);
			Pquicksort(list,p,q-1);
			Pquicksort(list,q+1,r);
		}
		
		return list;
	}
   
	/**
     * use the quicksort for the minimum heap.
     * @param list
     * @param p
     * @param r
     * @return
     */
	public int Ppartition(ArrayList<Pvertex>list,int p,int r)
	{
		int x=list.get(r-1).getPkey();
		i=p-1;
		for(int j=p;j<=r-1;j++)
		{
			if(list.get(j-1).getPkey()<=x)
			{
				i=i+1;
				int temp=list.get(i-1).getPkey();
				String n=list.get(i-1).getNode();
				String par=list.get(i-1).getPparent();
				String a=list.get(i-1).getAdj();
				boolean f=list.get(i-1).getFlag();
				Pvertex ps=new Pvertex(list.get(j-1).getNode(),list.get(j-1).getAdj(),list.get(j-1).getPkey(),
						list.get(j-1).getPparent(),list.get(j-1).getFlag());
				list.set(i-1, ps);
				Pvertex pv=new Pvertex(n,a,temp,par,f);
				list.set(j-1, pv);
				}
		}
		int temp=list.get(i).getPkey();
		String n=list.get(i).getNode();
		String par=list.get(i).getPparent();
		String a=list.get(i).getAdj();
		boolean f=list.get(i).getFlag();
		Pvertex pq=new Pvertex(list.get(r-1).getNode(),list.get(r-1).getAdj(),list.get(r-1).getPkey(),
				list.get(r-1).getPparent(),list.get(r-1).getFlag());
		list.set(i, pq);
		Pvertex pt=new Pvertex(n,a,temp,par,f);
		list.set(r-1, pt);
		return i+1;
	}
}
