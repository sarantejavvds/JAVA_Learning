package _Connect_SSMS_with_ECLIPSE._Connection_Utilities;

import java.sql.Connection;
import java.sql.SQLException;

/* 
 * These methods are only responsible for connecting to the database and disconnecting from the database.
 * To reuse across any other service or DAO class that needs SQL access.
 */

public interface JDBC_Connection_Manager 
{

	public Connection get_Connection() throws SQLException;
	
	public void close_Connection() throws SQLException;
	
}
