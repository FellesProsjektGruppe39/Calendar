package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sqlExecute {

    public void execute(String query) {

        Connection con = null;
        Statement st = null;

        String url = "jdbc:mysql://mysql.stud.ntnu.no/braged_FellesProsjekt";
        String user = "braged_demo";
        String password = "brage";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            st.execute(query);


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(sqlExecute.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(sqlExecute.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        
    }
    
}