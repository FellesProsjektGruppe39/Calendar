package Room;

import mysql.sqlExecute;

public class Room {
	
	sqlExecute sql = new sqlExecute();

	
	private int Roomnumber;
	
	
	private int getRoomnumber(){
		return this.Roomnumber;
	}
	
	private void setRoomnumber(int roomnum){
		this.Roomnumber = roomnum;
	}
	
	public void createRoom(String romnavn, int Kapasitet, String Sted, String Beskrivelse){
		sql.execute("INSERT INTO rom (romnavn,Kapasitet,Sted,Beskrivelse) VALUES ('" + romnavn + "','" + Kapasitet + "','" + Sted + "','" 
		           + Beskrivelse + "')");
	}

	public static void main(String[] args) {
		Room room = new Room();
		room.createRoom("Galtvort", 10, "Gloeshaugen", "Et VELDIG stort rom");
	}
	
}