package fellesp;

import java.sql.Date;
import java.sql.Time;

public class MeetingRoom {
	private int RoomNumber;
	private int capacity;
	
	public MeetingRoom(int Roomnumber, int capacity){
		this.RoomNumber=Roomnumber;
		this.capacity=capacity;
	}
	
	public int getRoomNumber(){
		return this.RoomNumber;
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void setRoomNumber(int roomNumber){
		this.RoomNumber=roomNumber;
	}
	
	public void setCapacity(int capacity){
		this.capacity=capacity;
	}

	public boolean isAvailable(Date date, Time startTime, Time endTime) {
		// TODO Auto-generated method stub
		return false;
	}
}
