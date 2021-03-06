package fellesp;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class InviteFactory {
  
	private static DBConnection db;
	
	
	public InviteFactory(Properties properties) throws ClassNotFoundException, SQLException
	{
		 db=new DBConnection(properties);
	}
	
	public  Invite  createInvite(String username, int aID) throws ClassNotFoundException, SQLException
	{
		Invite e=new Invite(username, aID);
		String query=String.format("insert into Invite " +
				"(response,username,appointmentID) values (1,'%s', '%d')", username, aID); 
		db.initialize();
		db.makeSingleUpdate(query);
		db.close();
		
		return e;
	}
	
	
	
	public int getInviteResponse(String username, int aID) throws ClassNotFoundException, SQLException
	{
		String query=String.format("SELECT response FROM Invite WHERE username = '%s' AND appointmentID = '%d'",username, aID);
		db.initialize();
		ResultSet rs=db.makeSingleQuery(query);
		int res;

		if (rs.next()) {
		    res = rs.getInt(1);
		}
		else {
			return (Integer) null;
		}
		
		rs.close();
		db.close();
		
		return res;
		
	}
	
	public void deleteInvite(String username) throws ClassNotFoundException, SQLException
	{
		String query = String.format("DELETE FROM Invite WHERE username = '%s'",username);
		db.initialize();
		db.makeSingleUpdate(query);
		db.close();
	}
	
	
	public void updateInviteResponse(String username, int newResponse, int aID) throws ClassNotFoundException, SQLException
	{
		String update = String.format("UPDATE Invite" + " SET response = %d" + " WHERE username = '%s'" + " AND appointmentID = %d", newResponse, username, aID);
		db.initialize();
		db.makeSingleUpdate(update);
		db.close();
	}
	
	public static ArrayList<String> getParticipants(int aID) throws ClassNotFoundException, SQLException{
		ArrayList<String> list = new ArrayList<String>();
		String query = String.format("SELECT username FROM Invite WHERE appointmentID = %d",aID);
		db.initialize();
		ResultSet rs = db.makeSingleQuery(query);
		while (rs.next()){
			list.add(rs.getString("username"));
		}

		db.close();
		

		return list;
	}

}

