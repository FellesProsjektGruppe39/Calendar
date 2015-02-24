package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class CreateUser {
	
	static int AnsattNr;
	static String fornavn;
	static String etternavn;
	static int TlfNr;
	static String stilling;
	
	CreateUser(int AnsattNr, String fornavn, String etternavn, int TlfNr, String stilling){
		this.AnsattNr = AnsattNr;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.TlfNr = TlfNr;
		this.stilling = stilling;
	}

    public static void main(String[] args) {

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
            st.execute("INSERT INTO bruker (Ansattnr,fornavn,etternavn,tlfnr,stilling) VALUES ('" + AnsattNr + "','" + fornavn + "','" + etternavn + "','" + TlfNr + "','" + stilling + "'");
            ste = (PreparedStatement) con.prepareStatement("SELECT * FROM bruker");
            rs = ste.executeQuery();

            while (rs.next()) {
            	
            	System.out.print(rs.getInt(1));
            	System.out.print(": ");
            	System.out.println(rs.getString(2)); 
            
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DeleteUser.class.getName());
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
                Logger lgr = Logger.getLogger(DeleteUser.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
    }
    
}