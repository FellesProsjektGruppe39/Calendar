package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class sqlGet {

    public sqlGet() {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://mysql.stud.ntnu.no/braged_FellesProsjekt";
        String user = "braged_demo";
        String password = "brage";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = (PreparedStatement) con.prepareStatement("SELECT * FROM bruker");
            rs = st.executeQuery();

            while (rs.next()) {
            	
            	System.out.print(rs.getInt(1));
            	System.out.print(": ");
            	System.out.println(rs.getString(2)); 
            
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
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
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
    }
    
}