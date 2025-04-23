package _Connect_SSMS_with_ECLIPSE._Connection_Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MSSQL_Server_Connection_Manager implements JDBC_Connection_Manager
{
	public static Connection connect;
	
	public static final String URL = "jdbc:sqlserver://DESKTOP-2ROPMHT;databaseName=JDBC_Connects_Eclipse_and_SSMS;integratedSecurity=true;encrypt=false";
	
	/* Method to establish and return a connection to the MSSQL Server database */
	@Override
	public Connection get_Connection() throws SQLException 
	{
	    Connection connect = null;
		
		if((connect == null) || (connect.isClosed())) /* Check if the connection is closed or no connection. */
		{
			try
			{
				/* Let Java Load DLL automatically */
				connect = DriverManager.getConnection(URL); /* Passing URL to make Connection with SSMS. */
				
				try (PreparedStatement prep = connect.prepareStatement("SELECT 1"))
				{
					prep.executeQuery();
				}

				System.out.println("\u001B[46m Database Connection has been Established Successfully 😁 \u001B[0m\n");
			}
			catch (SQLException e)
			{
				e.printStackTrace();

				System.err.println("Failed to Connect to Database 😥");

				throw e;
			}
		}

		return connect; /* Returns connection */
	}

	/* Method to close the connection with MSSQL Server */
	@Override
	public void close_Connection() throws SQLException 
	{
		if(connect != null && !connect.isClosed()) /* Checks if the connection is still established. */
		{
			connect.close(); /* Closes the existing Connection. */

			System.out.println("Database Connection Closed Successfully.");
		}
	}
	

	/* a getter method for access to connection if needed */
    public static Connection getConnectionInstance() 
    {
        return connect;
    }

}
