/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author lejla
 */
public class SimpleDBConnect {
    public SimpleDBConnect(){
       // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String msAccDB = "..//guiDB_initial.accdb"; // path to the DB file
        String dbURL = "jdbc:ucanaccess://" + msAccDB;

        // Step 1: Loading or registering JDBC driver class
        try {
           // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        // Step 2: Opening database connection
        try {
            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL);

            // Step 2.B: Creating JDBC Statement
            statement = connection.createStatement();

            // Step 2.C: Executing SQL &amp; retrieve data into ResultSet
            resultSet = statement.executeQuery("SELECT * FROM Department");

            // hardcoded header
            System.out.println("#\tName\t\t\tManager\tStart");
            System.out.println("=====\t=========\t=======\t=======");

            // processing returned data and printing into console
            // Step 2.D: use data from ResultSet
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" +
                        resultSet.getString(2) + "\t\t" +
                        resultSet.getString(3) + "\t" +
                        resultSet.getString(4));
            }

        }
        catch(SQLException sqlex){
            System.err.println(sqlex.getMessage());
        }
        finally {

        // Step 3: Closing database connection
        try {
            if(null != connection) {
                // cleanup resources, once after processing
                resultSet.close();
                statement.close();
                // and then finally close connection
                connection.close();
            }
        }
        catch (SQLException sqlex) {
            System.err.println(sqlex.getMessage());
        }
        }
    } // End of Constructor
    public static void main(String args[]){
        new SimpleDBConnect();
    }
} // End of Class
