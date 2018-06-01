package file.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.JDBCTemplate;
import file.model.vo.DataFile;
import file.model.vo.DataFile2;
import file.model.dao.FileDao;

public class FileService {

	public int uploadFile(DataFile df) {
		Connection conn = JDBCTemplate.conn();
		int result = new FileDao().uploadFile(conn, df);
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int uploadFile2(DataFile2 df2) {
		Connection conn= JDBCTemplate.conn();
		int result = new FileDao().uploadFile2(conn,df2);
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;	
	}
	public ArrayList<DataFile> selsetAll(){
		Connection conn= JDBCTemplate.conn();
		ArrayList<DataFile> list = new FileDao().selsetAll(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	public ArrayList<DataFile2> selsetAll2(){
		Connection conn= JDBCTemplate.conn();
		ArrayList<DataFile2> list = new FileDao().selsetAll2(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	public DataFile fileSelectDownload(String fileName, Timestamp uploadTime) {
		Connection conn= JDBCTemplate.conn();
		DataFile df = new FileDao().fileSelectDownload(conn,fileName,uploadTime);
		JDBCTemplate.close(conn);
		return df;		
	}

	public DataFile2 fileSelectDownload2(String fileName, Timestamp uploadTime) {
		Connection conn= JDBCTemplate.conn();
		DataFile2 df2 = new FileDao().fileSelectDownload2(conn,fileName,uploadTime);
		JDBCTemplate.close(conn);
		return df2;
	}

	public int deleteFile(String fileName, Timestamp uploadTime) {
		Connection conn= JDBCTemplate.conn();
		int result = new FileDao().deleteFile(conn, fileName, uploadTime);
		JDBCTemplate.close(conn);
		return result;
	}

	public DataFile2 selectOne(String fileName, Timestamp uploadTime) {
		Connection conn= JDBCTemplate.conn();
		DataFile2 df2 = new FileDao().selectOne(conn, fileName, uploadTime);
		JDBCTemplate.close(conn);
		return df2;
	}
	

}
