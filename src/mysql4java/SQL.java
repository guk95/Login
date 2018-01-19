package mysql4java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class SQL extends funciones {

    private final String DATABASE_URL = "jdbc:mysql://nanosoft.tech:3306/nanosoft_ulatina";
    private final String USERNAME = "nanosoft_ulatina";
    private final String PASSWORD = "guido17";

    private Connection connection;
    private Properties properties;

    public SQL() {
        super();
        this.connect();
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (this.connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.connection = DriverManager.getConnection(this.DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public HashMap<String, Object> SELECT(String preparedQuery, ArrayList<Object> objs) {
        HashMap<String, Object> hmap = new HashMap<>();
        
        PreparedStatement ps;
        try {
            ps = this.connection.prepareStatement(preparedQuery);

            Iterator it = objs.iterator();
            int poc = 1;
            while (it.hasNext()) {
                Object element = it.next();
                if (this.isNumeric(element)) {
                    ps.setInt(poc, Integer.parseInt(element.toString()));
                } else {
                    ps.setString(poc, element.toString());
                }
                poc++;
            }

            try (ResultSet rs = ps.executeQuery()) 
            {
                while (rs.next()) 
                {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) 
                    {
                        hmap.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            hmap = null;
        }
        return hmap;
    }
    public int  executeUpdate(String update, ArrayList<Object> objs){

        PreparedStatement ps;
       try {
            ps = this.connection.prepareStatement(update);

            Iterator it = objs.iterator();
            int poc = 1;
            while (it.hasNext()) {
                Object element = it.next();
                if (this.isNumeric(element)) {
                    ps.setInt(poc, Integer.parseInt(element.toString()));
                } else {
                    ps.setString(poc, element.toString());
                }
                poc++;
            }
            
               ps.executeUpdate(update);
            
          

        
        }  catch (SQLException ex ) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 1;
    }
}
