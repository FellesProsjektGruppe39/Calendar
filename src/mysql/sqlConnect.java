package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.PreparedStatement;

public class sqlConnect {

    public Connection connection() {

        Connection con = null;

        String url = "jdbc:mysql://mysql.stud.ntnu.no/braged_FellesProsjekt";
        String user = "braged_demo";
        String password = "brage";

            try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(sqlConnect.class.getName());
	            lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
            
            return con;
           
    }
    
}