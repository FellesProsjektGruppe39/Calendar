package Room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class CreateRoom {
	
	static String romnavn;
	static int Kapasitet;
	static String Sted;
	static String Beskrivelse;
	
	CreateRoom(String romnavn, int Kapasitet, String Sted, String Beskrivelse){
		this.romnavn = romnavn;
		this.Kapasitet = Kapasitet;
		this.Sted = Sted;
		this.Beskrivelse = Beskrivelse;
		
	}

   public void SetRoom() {

       Connection con = null;
       Statement st = null;
       ResultSet rs = null;
       PreparedStatement ste = null;

       String url = "jdbc:mysql://mysql.stud.ntnu.no/braged_FellesProsjekt";
       String user = "braged_demo";
       String password = "brage";
       

       try {
           con = DriverManager.getConnection(url, user, password);
           st = con.createStatement();
           st.execute("INSERT INTO rom (romnavn,Kapasitet,Sted,Beskrivelse) VALUES ('" + romnavn + "','" + Kapasitet + "','" + Sted + "','" 
           + Beskrivelse + "')");
           ste = (PreparedStatement) con.prepareStatement("SELECT * FROM rom");
           rs = ste.executeQuery();

           while (rs.next()) {
           	
           	System.out.print(rs.getString(1));
           	System.out.print(": ");
           	System.out.println(rs.getString(2)); 
           
           }

       } catch (SQLException ex) {
           Logger lgr = Logger.getLogger(CreateRoom.class.getName());
           lgr.log(Level.SEVERE, ex.getMessage(), ex);

       } finally {
           try {
               if (rs != null) {
                   rs.close();
               }
               if (st != null) {
                   st.close();
               }
               if (con != null) {
                   con.close();
               }

           } catch (SQLException ex) {
               Logger lgr = Logger.getLogger(CreateRoom.class.getName());
               lgr.log(Level.WARNING, ex.getMessage(), ex);
           }
       }
       
   }
   
}
