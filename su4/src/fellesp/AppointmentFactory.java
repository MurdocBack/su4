package fellesp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Properties;

public class AppointmentFactory {
	
	DBConnection db;
	
	public AppointmentFactory(Properties properties) throws ClassNotFoundException, SQLException
	{
		 db = new DBConnection(properties);
	}
	
	public Appointment createAppointment(Date date, Time startTime, Time endTime, String description, int type, String owner) throws ClassNotFoundException, SQLException{
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Time sqlStartTime = new java.sql.Time(startTime.getTime());
		java.sql.Time sqlEndTime = new java.sql.Time(endTime.getTime());
		
		int id = getNextId();
		
		Appointment a= new Appointment(id, date, startTime, endTime, description, owner, type);
		String query=String.format("insert into appointment " + "(date, startTime, endTime, description, type, owner) values (" + id + "," + sqlDate + "," + sqlStartTime +"," + sqlEndTime +"," + description + "," 
		+ type + "," + owner + ");" );
		
		db.initialize();
		db.makeSingleUpdate(query);
		db.close();
		return a;
	}
	
	public Appointment getAppointment(int id) throws SQLException, ClassNotFoundException{
		String query = String.format("select * from Appointmet where id =" + id);
		db.initialize();
		ResultSet rs = db.makeSingleQuery(query);
		Date date = null;
		Time startTime = null;
		Time endTime = null;
		String description = null;
		int type = 1; // "1" avtale og "2" er m�te
		String owner = null;
		
		while (rs.next()){
			date = rs.getDate(1);
			startTime = rs.getTime(2);
			endTime = rs.getTime(3);
			description = rs.getString(4);
			type = rs.getInt(5);
			owner = rs.getString(6);
		}
		
		Appointment a= new Appointment(id, date, startTime, endTime, description, owner, type);

		db.close();
		
		return a;
	}
	
	public int getNextId() throws ClassNotFoundException, SQLException{
		String query= "select max(appointmentID) from Appointment";
		db.initialize();
		ResultSet rs = db.makeSingleQuery(query);
		int nextId = 0;
		while( rs.next() ){
			nextId = rs.getInt(0);
		}
		rs.close();
		db.close();
		
		return nextId++;
	}
	
	public void deleteAppointment(String owner) throws ClassNotFoundException, SQLException{
		
		String query =String.format("delete from Appintment where owner =" + owner);
		db.initialize();
		ResultSet rs = db.makeSingleQuery(query);
		rs.close();
		db.close();
		
	}
	
	
}
