/**
 * 
 */
package ShortestPath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * process the context from "File.txt" including read from process and write.
 * path and path name is get from below as an example:
 * 1. "D:\\Users\\ThinkPad\\workspace\\Work\\workDescribe.txt", which means get the whole directory.	
 * 2. "./workDescribe.txt",  . means get the current directory
 *
 */
public class ProcessFromTxt {
		
	/**
	 * first give a path where the "file.txt" is located.
	 * and then read the information from the "file.txt"
	 * store the finally into an arrayList and each one element represent one line.	
	 * return this arrayList.
	 */
	public ArrayList<String> readTxt(String pathname)
	{	
		ArrayList<String> context=new ArrayList<String>(); 			
		try {
			 File file=new File(pathname);
			 InputStreamReader read=new InputStreamReader(new FileInputStream(file));
			 BufferedReader br=new BufferedReader(read);
			 String line="";			
			 while ((line=br.readLine())!=null)
			{
			 //   context.add(line+"\n");
			      context.add(line);
			} 
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("read error :" + e);
		}		
		return context; 
	}
	
	/**
	 * first give a path where the "file.txt" want to save. 
	 * and then write to the "file.txt".
	 * the second parameter has an ArrayList, each element represents 
	 * one line in the "file.txt"
	 */	
	public void Writetxt(String path, ArrayList<String>text)
	{		
		try {
			File file=new File(path);
			OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream(file));
			BufferedWriter bw=new BufferedWriter(out);
			for(String textname:text)
			{
				bw.write(textname+"\n");
				
			}
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("write error :" + e);
		}	
	}
	
	
}
