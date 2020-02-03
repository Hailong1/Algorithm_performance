package ShortestPath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Store all the information of configuration (we suppose this configuration is a undirected connection graph).
 * we store information into a database for demo my algorithm. 
 * @Hailong 
 *
 */
public class DatabaseHandle {

	Print print=new Print();
	
	/**
	 * connect to the database. 
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	
	public Connection conDatabase() throws ClassNotFoundException, SQLException{
		
		    Class.forName("com.mysql.jdbc.Driver") ;   
		    String []infor; 
		    infor=getInfor("./DataInfor.txt"); // enter corresponding path where the database information locate
		    Connection dbConnection=DriverManager.getConnection(infor[0],infor[1],infor[2]);
				   if(!dbConnection.isClosed()) {         
					   print.pln("Succeeded connecting to the Database!"); 
				   }	
		 return dbConnection;
			
	}
	
	/**
	 *  get the information of corresponding database from the "DataInfor.txt"
	 * @param path
	 * @return three string value. 
	 * the first one store the url
	 * the second one store the username
	 * the third one store the password.     
	 */	
	  public String[] getInfor(String path)
	{
		    ProcessFromTxt pf=new ProcessFromTxt();
		    ArrayList<String> list=new ArrayList<String>();
		    String url="jdbc:mysql://localhost:3306/";
		    list=pf.readTxt(path); 
		    String []infor=new String[3];
		    for(int i=0; i<list.size();i++)
		    {
		    	String []temp=list.get(i).split(":");
		    	if (i==0)
		    		infor[0]=url+temp[1].trim(); // get the url information, temp[1] store the database information. 
		    	if (i<=2&&i>0)
		    	    infor[i]=temp[1].trim();
		    }
		    return infor;
	}
	
	
	/**
	 * this method is something for about update, create and delete operation. 
	 * @param build
	 */
	public void ChangeTable(String build)
	{
	try {
			PreparedStatement sql;
			Connection dbConnection=conDatabase();
			sql=dbConnection.prepareStatement(build);
			sql.executeUpdate();											
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			print.pln("Unable to connect properly to database.");
			System.err.println(e.getMessage());
		}
		
	}
	
	   /**
	    * return Whole value in selection in one column. 
	    * @param select
	    * @param column
	    * @return
	    */
		public ArrayList<String> getOneValue(String select,int column)
		{	  
		  ArrayList<String>list =new ArrayList<String>();
		  try{
			  PreparedStatement sql;
			  Connection dbConnection=conDatabase();
			  ResultSet rs=null;
			  sql=dbConnection.prepareStatement(select);
			  rs=sql.executeQuery();
			  while(rs.next())
			  {
				  list.add(rs.getString(column));
			  }
				
			}
			catch(Exception e)
			{
				// TODO Auto-generated catch block
				print.pln("Unable to connect properly to database.");
				System.err.println(e.getMessage());
			}
		
		   return list;
		}
		
		
		 /**
		    * return one value in selection in one column. 
		    * @param select
		    * @param column
		    * @return
		    */
			public String getValue(String select,int column)
			{
			  String  result=null;
			  try{
				  PreparedStatement sql;
				  Connection dbConnection=conDatabase();
				  ResultSet rs=null;
				  sql=dbConnection.prepareStatement(select);
				  rs=sql.executeQuery();
				  if(rs.next())
				  {
					 result=rs.getString(column);  
				  }
					
				}
				catch(Exception e)
				{
					// TODO Auto-generated catch block
					print.pln("Unable to connect properly to database.");
					System.err.println(e.getMessage());
				}
			
			   return result;
			}
		
}
