package fellesp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class UserFactory {
static DBConnection db;
	
	
	public UserFactory(Properties properties) throws ClassNotFoundException, SQLException
	{
		 db=new DBConnection(properties);
	}
	
	public static  User  createUser(String userName, String password) throws ClassNotFoundException, SQLException
	{
		User e=new User(userName, password);
		String query=String.format("INSERT INTO User " +
				"values ('%s', '%s');", userName, password); 
		db.initialize();
		db.makeSingleUpdate(query);
		db.close();
		
		return e;
	}
	
	public static User getUser(String userName) throws ClassNotFoundException, SQLException
	{
		String query=String.format("SELECT password FROM User WHERE username = '%s';",userName);
		db.initialize();
		ResultSet rs=db.makeSingleQuery(query);
		rs.beforeFirst();
		rs.next();
		String password= rs.getString(1);
		User e=new User(userName,password);
		rs.close();
		db.close();
		
		return e;
	
		
	}
	
	public static void deleteUser(String userName) throws ClassNotFoundException, SQLException
	{
		String query = String.format("DELETE FROM User WHERE username= '%s';",userName);
		db.initialize();
		db.makeSingleUpdate(query);
		db.close();
	}
	public static void updateUser(String userName, String newPassword) throws ClassNotFoundException, SQLException
	{
		String update = String.format("UPDATE User" +
				" SET password = '%s' WHERE username = '%s';", newPassword, userName);
		db.initialize();
		db.makeSingleUpdate(update);
		db.close();
	}
	
	public static ArrayList<Integer> getUnansweredInvites(String username) throws ClassNotFoundException, SQLException{
		ArrayList<Integer> list = new ArrayList<Integer>();
		String query = String.format("select appointmentID from Invite where username='%s' and where response =1;", username);
		db.initialize();
		ResultSet rs=db.makeSingleQuery(query);
		int response;
		while (rs.next()){
			response= rs.getInt(1);
			list.add(response);
		}
		return list;
	}

}
