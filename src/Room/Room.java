package Room;

import mysql.sqlExecute;

public class Room {
	
	sqlExecute sql = new sqlExecute();

	
	private int romNavn;
	
	
	public int getRomNavn(){
		return this.romNavn;
	}
	
	public void setRomNavn(int roomnum){
		this.romNavn = roomnum;
	}
	
	public void createRoom(String romnavn, int Kapasitet, String Sted, String Beskrivelse){
		sql.execute("INSERT INTO rom (romnavn,Kapasitet,Sted,Beskrivelse) VALUES ('" + romnavn + "','" + Kapasitet + "','" + Sted + "','" 
		           + Beskrivelse + "')");
	}
	
	public void bookRoom() {
		
	}

	public static void main(String[] args) {
		Room room = new Room();
		room.createRoom("Galtvort", 10, "Gloeshaugen", "Et VELDIG stort rom");
	}
	
}