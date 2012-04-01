package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Authority;
import com.fnst.officeapply.exception.OfficeException;

public class AuthorityDAO extends BaseDAO {

	public AuthorityDAO() throws DBPoolException, OfficeException {
	}

	public List<Authority> permissionList() throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Authority> lists = new ArrayList<Authority>();
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select * from Authority where permission < 2;");
			rs = state.executeQuery();
			while (rs.next()) {
				Authority auth = new Authority(rs.getInt(1),rs.getInt(2),rs.getString(3));
				lists.add(auth);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return lists;
	}
	
	public Authority getAuth(int perm, Connection conn, int type) throws DBPoolException, OfficeException {
		Authority auth = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		String sql1 = "select * from Authority where id=?;";
		String sql2 = "select * from Authority where permission=?;";
		String sql = this.getSQL(type, sql1, sql2);
		try {
			state = conn.prepareStatement(sql);
			state.setInt(1, perm);
			rs = state.executeQuery();
			if (rs.next()) {
				auth = new Authority(rs.getInt(1),rs.getInt(2),rs.getString(3));
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			this.close(new PreparedStatement[]{state}, new ResultSet[]{rs});
		}
		return auth;
	}
	
}
