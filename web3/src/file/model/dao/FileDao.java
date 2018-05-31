package file.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;

public class FileDao {

	public int uploadFile(Connection conn, DataFile df) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into fileTbl values(?,?,?,?,?)";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, df.getFileName());
			pstmt.setString(2, df.getFilePath());
			pstmt.setLong(3, df.getFileSize());
			pstmt.setString(4, df.getFileUser());
			pstmt.setTimestamp(5, df.getUploadTime());			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;	
	}

	public ArrayList<DataFile> selsetAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<DataFile> list = new ArrayList<DataFile>();
		String query = "select * from filetbl";
		try {
			stmt = conn.createStatement();
			rset= stmt.executeQuery(query);
			while(rset.next())
			{
				DataFile df = new DataFile();
				df.setFileName(rset.getString("filename"));
				df.setFilePath(rset.getString("filepath"));
				df.setFileSize(rset.getLong("filesize"));
				df.setFileUser(rset.getString("fileuser"));
				df.setUploadTime(rset.getTimestamp("uploadtime"));
				list.add(df);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public DataFile fileSelectDownload(Connection conn, String fileName, Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from filetbl where fileName=? and uploadTime=?";
		DataFile df = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setTimestamp(2, uploadTime);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				df = new DataFile();
				df.setFileName(rset.getString("filename"));
				df.setFilePath(rset.getString("filePath"));
				df.setFileSize(rset.getInt("filesize"));
				df.setFileUser(rset.getString("fileuser"));
				df.setUploadTime(rset.getTimestamp("uploadtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return df;
	}

	public int uploadFile2(Connection conn, DataFile2 df2) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into filetbl2 values(?,?,?,?,?,?)";
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, df2.getBeforeFileName());
			pstmt.setString(2, df2.getAfterFileName());
			pstmt.setString(3, df2.getFilePath());
			pstmt.setLong(4, df2.getFileSize());
			pstmt.setString(5, df2.getFileUser());
			pstmt.setTimestamp(6, df2.getUploadTime());			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;	
	}

	public DataFile2 fileSelectDownload2(Connection conn, String fileName, Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from filetbl2 where afterFileName=? and uploadTime=?";
		DataFile2 df2 = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setTimestamp(2, uploadTime);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				df2 = new DataFile2();
				df2.setBeforeFileName(rset.getString("beforeFileName"));
				df2.setAfterFileName(rset.getString("afterFileName"));
				df2.setFilePath(rset.getString("filePath"));
				df2.setFileSize(rset.getInt("filesize"));
				df2.setFileUser(rset.getString("fileuser"));
				df2.setUploadTime(rset.getTimestamp("uploadtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return df2;
	}

	public ArrayList<DataFile2> selsetAll2(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<DataFile2> list = new ArrayList<DataFile2>();
		String query = "select * from filetbl2";
		try {
			stmt = conn.createStatement();
			rset= stmt.executeQuery(query);
			while(rset.next())
			{
				DataFile2 df2 = new DataFile2();
				df2.setBeforeFileName(rset.getString("beforeFileName"));
				df2.setAfterFileName(rset.getString("afterFileName"));
				df2.setFilePath(rset.getString("filepath"));
				df2.setFileSize(rset.getLong("filesize"));
				df2.setFileUser(rset.getString("fileuser"));
				df2.setUploadTime(rset.getTimestamp("uploadtime"));
				list.add(df2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public int deleteFile(Connection conn, String fileName, Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from filetbl2 where afterfilename=? and uploadTime=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setTimestamp(2, uploadTime);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public DataFile2 selectOne(Connection conn, String fileName, Timestamp uploadTime) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select *  from filetbl2 where afterfilename=? and uploadTime=?";
		DataFile2 df2 = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileName);
			pstmt.setTimestamp(2, uploadTime);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				df2 = new DataFile2();
				df2.setBeforeFileName(rset.getString("beforeFileName"));
				df2.setAfterFileName(rset.getString("afterFileName"));
				df2.setFilePath(rset.getString("filepath"));
				df2.setFileSize(rset.getLong("filesize"));
				df2.setFileUser(rset.getString("fileuser"));
				df2.setUploadTime(rset.getTimestamp("uploadtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return df2;
	}

}