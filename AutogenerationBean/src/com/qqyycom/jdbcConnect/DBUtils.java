package com.qqyycom.jdbcConnect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	public static Connection getDBConnection(String url, String userName, String password) throws SQLException{
		Connection conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}
	
	/**
	 *  ��ȡһ�������
	 * @param conn
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet getTableResultSet(DatabaseMetaData dbmd, String tableName) throws SQLException {
		
		ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
		// ����һ��������Ľ����
		// RowSetFactory factory = RowSetProvider.newFactory();
		// CachedRowSet crs = factory.createCachedRowSet();
		// crs.populate(rs);
		return rs;
	}
	
	// �ر�statement��Rowset
	@Deprecated
	public static void closeRelativeResource(ConnectResource resource) throws Exception{
		resource.rs.close();
		resource.s.close();
	}
	
	public static class ConnectResource{
		private Statement s;
		private ResultSet rs;
		
		public ConnectResource(Statement s, ResultSet rs) {
			this.s = s;
			this.rs = rs;
		}

		public Statement getStatement() {
			return s;
		}

		public ResultSet getResultSet() {
			return rs;
		}
		
		
	}
}
