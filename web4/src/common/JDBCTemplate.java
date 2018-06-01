package common;

import java.sql.*;

public class JDBCTemplate {
	
	public static Connection conn()
	{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"Member_mgr", "Member_mgr");
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn)
	{
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt)
	{
		try {
			if(stmt!=null && stmt.isClosed())
			{
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rset)
	{
		try {
			if(rset!=null && rset.isClosed())
			{
				rset.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn)
	{
		try {
			if(conn!=null && !conn.isClosed())
			{
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn)
	{
		try {
			if(conn!=null && !conn.isClosed())
			{
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
