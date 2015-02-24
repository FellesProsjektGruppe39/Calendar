package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class sqlRetrieve {
	
    ResultSet rs = null;

    public sqlRetrieve(String query) {

        Connection con = null;
        PreparedStatement st = null;

        String url = "jdbc:mysql://mysql.stud.ntnu.no/braged_FellesProsjekt";
        String user = "braged_demo";
        String password = "brage";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = (PreparedStatement) con.prepareStatement(query);
            rs = st.executeQuery();
i = 0
            while (rs.next()) {
            	
            	if (rs.get)
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(sqlRetrieve.class.getName());
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
                Logger lgr = Logger.getLogger(sqlRetrieve.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
    }
    
    public ResultSet getQuery() {
    	return this.rs;
    }
    
}