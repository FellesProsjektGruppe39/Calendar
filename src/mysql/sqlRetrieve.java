package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class sqlRetrieve {
	
    ResultSet rs = null;
    String [][]table;

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
            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();
            table = new String[rows][columns];
            int i = 0;
            while (rs.next()) {
            	for (int j = 0; j < (columns-1); j++) {
            		 table[i][j] = rs.getString((j+1));
            		 
            	}
            	i++;
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
    
    public String[][] getQuery() {
    	return this.table;
    }
    
}