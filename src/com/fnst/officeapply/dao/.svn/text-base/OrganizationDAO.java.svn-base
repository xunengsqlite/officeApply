package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Organization;
import com.fnst.officeapply.exception.OfficeException;

public class OrganizationDAO extends BaseDAO {

	public OrganizationDAO() throws DBPoolException, OfficeException {
	}

	public List<Organization> splitPage(int pageNum, int count, int type) throws OfficeException, DBPoolException {
		List<Organization> orgs = new ArrayList<Organization>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql1 = "select * from Department limit ?,?;";
		String sql2 = "select * from SectionOffice limit ?,?;";
		String sql3 = "select * from ProjectGroup limit ?,?;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setInt(1, (pageNum-1)*count);
			state.setInt(2, count);
			rs = state.executeQuery();
			while (rs.next()) {
				Organization org = new Organization(rs.getInt(1),rs.getString(2));
				orgs.add(org);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return orgs;
	}

	public int getCount(int type) throws DBPoolException, OfficeException {
		int count = 0;
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql1 = "select count(*) from Department;";
		String sql2 = "select count(*) from SectionOffice;";
		String sql3 = "select count(*) from ProjectGroup;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return count;
	}

	public Organization getOrgz(int orgzID, int type) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		Organization orgz = null;
		String sql1 = "select * from Department where id=?;";
		String sql2 = "select * from SectionOffice where id=?;";
		String sql3 = "select * from ProjectGroup where id=?;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setInt(1, orgzID);
			rs = state.executeQuery();
			if (rs.next()) {
				orgz = new Organization(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return orgz;
	}
	
	public Organization getOrgz(String orgzName, int type) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		Organization orgz = null;
		String sql1 = "select * from Department where departmentName=?;";
		String sql2 = "select * from SectionOffice where officeName=?;";
		String sql3 = "select * from ProjectGroup where groupName=?;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setString(1, orgzName);
			rs = state.executeQuery();
			if (rs.next()) {
				orgz = new Organization(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return orgz;
	}
	
	public boolean add(String orgzName, int type) throws OfficeException, DBPoolException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		String sql1 = "insert into Department values(?,?);";
		String sql2 = "insert into SectionOffice values(?,?);";
		String sql3 = "insert into ProjectGroup values(?,?);";
		String sql = getSQL(type, sql1, sql2, sql3);
		if(getOrgz(orgzName, type) != null){
			return false;
		}
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setString(1, null);
			state.setString(2, orgzName);
			int num = state.executeUpdate();
			if(num > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}

	public boolean update(String oldOrgzName, Organization orgz, int type) throws DBPoolException, OfficeException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		String orgzName = orgz.getName();
		int orgzID = orgz.getId();
		String sql1 = "update Department set departmentName=? where id=?;";
		String sql2 = "update SectionOffice set officeName=? where id=?;";
		String sql3 = "update ProjectGroup set groupName=? where id=?;";
		String sql = getSQL(type, sql1, sql2, sql3);
		if(!ComUtil.isEqual(oldOrgzName, orgzName)){
			if(getOrgz(orgzName, type) != null){
				return false;
			}
		}
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setString(1, orgzName);
			state.setInt(2, orgzID);
			int num = state.executeUpdate();
			if(num > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}
	
	public boolean delete(int orgzID, int type) throws DBPoolException, OfficeException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		ManageUserDAO Userdao = new ManageUserDAO();
		HistoryQueryDAO queryDao = new HistoryQueryDAO();
		if(Userdao.isUserExist(orgzID, type)){
			return false;
		}
		if(type == 2){
			if(queryDao.isHisRecListExist(orgzID)){
				return false;
			}
		}
		String sql1 = "delete from Department where id=?;";
		String sql2 = "delete from SectionOffice where id=?;";
		String sql3 = "delete from ProjectGroup where id=?;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setInt(1, orgzID);
			int num = state.executeUpdate();
			if(num > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}
	
	public List<Organization> orgzList(int type) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		List<Organization> lists = new ArrayList<Organization>();
		String sql1 = "select * from Department;";
		String sql2 = "select * from SectionOffice;";
		String sql3 = "select * from ProjectGroup;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			rs = state.executeQuery();
			while (rs.next()) {
				Organization auth = new Organization(rs.getInt(1),rs.getString(2));
				lists.add(auth);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return lists;
	}
	
	
	
}
