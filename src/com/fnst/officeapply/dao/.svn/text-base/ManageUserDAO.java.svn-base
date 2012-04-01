package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Authority;
import com.fnst.officeapply.entity.Organization;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class ManageUserDAO extends BaseDAO {
	
	public ManageUserDAO() throws DBPoolException, OfficeException{
	}
	
	/**
	 * 分页显示记录
	 */
	public List<UserInfo> userList4Page(int pageNum, int count, String quserRealName, String groupName, int type) throws OfficeException, DBPoolException {
		
		String sql1 = "select u.id,u.username,u.password,u.userRealName,u.userMail,a.permission,a.permissionName,d.departmentName," +
		"o.officeName,p.groupName from UserInfo u,Authority a,Department d,SectionOffice o," +
		"ProjectGroup p where u.permissionID=a.id and u.departmentID=d.id and u.officeID=o.id and u.groupID=p.id and p.groupName=? and u.userRealName like ? and a.permission<2 limit ?,?;";
		
		String sql2 = "select u.id,u.username,u.password,u.userRealName,u.userMail,a.permission,a.permissionName,d.departmentName," +
		"o.officeName,p.groupName from UserInfo u,Authority a,Department d,SectionOffice o," +
		"ProjectGroup p where u.permissionID=a.id and u.departmentID=d.id and u.officeID=o.id and u.groupID=p.id and p.groupName like ? and u.userRealName like ? and a.permission<2 limit ?,?;";
		
		String sql = getSQL(type, sql1, sql2);
		
		List<UserInfo> users = new ArrayList<UserInfo>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			if(type == 1){
				state.setString(1, "%" + groupName + "%");
				state.setString(2, "%" + quserRealName + "%");
				state.setInt(3, (pageNum - 1) * count);
				state.setInt(4, count);
			}else{
				state.setString(1, groupName);
				state.setString(2, "%" + quserRealName + "%");
				state.setInt(3, (pageNum - 1) * count);
				state.setInt(4, count);
			}
			rs = state.executeQuery();
			UserInfo user = null;
			while (rs.next()) {
				user = new UserInfo();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setMail(rs.getString("userMail"));
				user.setPermission(rs.getInt("permission"));
				user.setPermissionName(rs.getString("permissionName"));
				user.setDepartmentName(rs.getString("departmentName"));
				user.setOfficeName(rs.getString("officeName"));
				user.setGroupName(rs.getString("groupName"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state ,rs);
		}
		return users;
	}
	
	public int getUserCount(String quserRealName, String groupName, int type) throws OfficeException, DBPoolException {
		
		int count = 0;
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql1 = "select count(*) from userInfo u,Authority a,ProjectGroup p where u.permissionID=a.id and u.groupID=p.id and a.permission<2 and p.groupName=? and u.userRealName like ?;";
		String sql2 = "select count(*) from userInfo u,Authority a,ProjectGroup p where u.permissionID=a.id and u.groupID=p.id and a.permission<2 and p.groupName like ? and u.userRealName like ?;";
		String sql = getSQL(type, sql1, sql2);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			if(type == 1){
				state.setString(1, "%" + groupName + "%");
				state.setString(2, "%" + quserRealName + "%");
			}else {
				state.setString(1, groupName);
				state.setString(2, "%" + quserRealName + "%");
			}
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
	
	/**
	 * 根据用户名查询用户信息
	 */
	public UserInfo getUser(String userName) throws DBPoolException, OfficeException {

		PreparedStatement state = null;
		ResultSet rs = null;
		UserInfo user = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(SHOW_USER_BY_NAME);
			state.setString(1, userName);
			rs = state.executeQuery();
			while (rs.next()) {
				user = new UserInfo();
				user.setUserID(rs.getInt("id"));
				user.setUserName(rs.getString("userName"));
				//解密
				String psd = DesEncrypter.decrypt(rs.getString("password"), DesEncrypter.KEY_2);
				user.setPassword(psd);
				user.setUserRealName(rs.getString("userRealName"));
				user.setMail(rs.getString("userMail"));
				user.setPermissionID(rs.getInt("permissionID"));
				user.setPermission(rs.getInt("permission"));
				user.setPermissionName(rs.getString("permissionName"));
				user.setDepartmentID(rs.getInt("departmentID"));
				user.setDepartmentName(rs.getString("departmentName"));
				user.setOfficeID(rs.getInt("officeID"));
				user.setOfficeName(rs.getString("officeName"));
				user.setGroupID(rs.getInt("groupID"));
				user.setGroupName(rs.getString("groupName"));
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);

		}
		return user;
	}

	/**
	 * 添加用户
	 */
	public boolean insertUser(UserInfo user, int type) throws DBPoolException, OfficeException {
		
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		AuthorityDAO authDao = new AuthorityDAO();
		OrganizationDAO orgzDao = new OrganizationDAO();
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(INSERT_USER);
			// 判断数据库中是否已经有该用户及权限是否符合。
			if (this.isUserExist(user.getUserName(), conn)) {
				return false;
			}
			if(type == 0){
				Authority auth = authDao.getAuth(user.getPermissionID(), conn, 0);
				if(auth == null || auth.getPermission() >= 2){
					return false;
				}
				state.setInt(6, user.getPermissionID());
				
			}else if(type == 1){
				int permission = 1;
				Authority auth = authDao.getAuth(permission, conn, 1);
				if(auth == null){
					return false;
				}
				Organization dep = orgzDao.getOrgz(user.getDepartmentID(), 0);
				if(dep == null){
					return false;
				}
				Organization sec = orgzDao.getOrgz(user.getOfficeID(), 1);
				if(sec == null){
					return false;
				}
				Organization proj = orgzDao.getOrgz(user.getGroupID(), 2);
				if(proj == null){
					return false;
				}
				state.setInt(6, auth.getPermissionID());
			}
			
			state.setString(1, null);
			state.setString(2, user.getUserName());
			state.setString(3, user.getPassword());
			state.setString(4, user.getUserRealName());
			state.setString(5, user.getMail());
			state.setInt(7, user.getDepartmentID());
			state.setInt(8, user.getOfficeID());
			state.setInt(9, user.getGroupID());
			int count = state.executeUpdate();
			if(count > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}

	/**
	 * 修改用户
	 * @param user
	 * @return f
	 * @throws OfficeException 
	 * @throws DBPoolException 
	 */
	public boolean updateUser(UserInfo user, int type) throws OfficeException, DBPoolException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		String sql1 = UPDATE_USER;
		String sql2 = "update UserInfo set password=?,userRealName=?,userMail=?,departmentID=?,officeID=?,groupID=? where userName=?;";
		String sql = getSQL(type, sql1, sql2);
		try {
			AuthorityDAO authDao = new AuthorityDAO();
			OrganizationDAO orgzDao = new OrganizationDAO();
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			if(type == 0){
				Authority auth = authDao.getAuth(user.getPermissionID(), conn, 0);
				if (auth == null || auth.getPermission() >= 2) {
					return false;
				}
				state.setInt(4, user.getPermissionID());
				state.setString(5, user.getUserName());
			}else if (type == 1){
				Organization dep = orgzDao.getOrgz(user.getDepartmentID(), 0);
				if(dep == null){
					return false;
				}
				Organization sec = orgzDao.getOrgz(user.getOfficeID(), 1);
				if(sec == null){
					return false;
				}
				Organization proj = orgzDao.getOrgz(user.getGroupID(), 2);
				if(proj == null){
					return false;
				}
				state.setInt(4, user.getDepartmentID());
				state.setInt(5, user.getOfficeID());
				state.setInt(6, user.getGroupID());
				state.setString(7, user.getUserName());
			}
			state.setString(1, user.getPassword());
			state.setString(2, user.getUserRealName());
			state.setString(3, user.getMail());
			
			int count = state.executeUpdate();
			if(count > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}

	/**
	 * 删除用户
	 * @param userName
	 * @return flag
	 * @throws OfficeException 
	 * @throws DBPoolException 
	 */
	public boolean deleteUser(int userID) throws OfficeException, DBPoolException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		GoodsApplyDAO applyDao = new GoodsApplyDAO();
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			if(applyDao.isCurrRecExisted(conn, userID, 0)){
				return false;
			}
			state = conn.prepareStatement(DELETE_USER);
			state.setInt(1, userID);
			int count = state.executeUpdate();
			if(count > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}

	public boolean delAll(int[] userIDArray) throws DBPoolException, OfficeException {
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		boolean autoCommit = true;
		boolean isSetAutoCommit = false;
		GoodsApplyDAO applyDao = new GoodsApplyDAO();
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			autoCommit = conn.getAutoCommit();
			isSetAutoCommit = true;
			conn.setAutoCommit(false);
			state = conn.prepareStatement(DELETE_USER);
			for (int i = 0; i < userIDArray.length; i++) {
				if (applyDao.isCurrRecExisted(conn, userIDArray[i], 0)) {
					continue;
				}
				state.setInt(1, userIDArray[i]);
				state.addBatch();
			}
			int[] num = state.executeBatch();
			flag = ComUtil.isExecSucc(num);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new OfficeException("rollback fail:" + e);
			}
			throw new OfficeException(e);
		} finally {
			try {
				// 因为是连接池，必须把conn提交方式变回原样,非常重要!!!!
				if (isSetAutoCommit) {
					conn.setAutoCommit(autoCommit);
				}
			} catch (SQLException e) {
				throw new OfficeException("setAutoCommit fail:" + e);
			}
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}
	
	/**
	 * @author xuneng 判断数据库中是否已经存在该用户
	 * @param userName
	 * @return flag
	 * @throws OfficeException 
	 * @throws DBPoolException 
	 */
	public boolean isUserExist(String userName, Connection conn) throws OfficeException, DBPoolException {
		boolean flag = false;
		PreparedStatement state = null;
		ResultSet rs = null;
		try {
			state = conn.prepareStatement("select id from userInfo where userName=?;");
			state.setString(1, userName);
			rs = state.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			this.close(new PreparedStatement[]{state}, new ResultSet[]{rs});
		}
		return flag;
	}

	public boolean isUserExist(int orgzID, int type) throws DBPoolException, OfficeException {
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		boolean flag = false;
		String sql1 = "select id from userInfo where departmentID=? limit 0,1;";
		String sql2 = "select id from userInfo where officeID=? limit 0,1;";
		String sql3 = "select id from userInfo where groupID=? limit 0,1;";
		String sql = getSQL(type, sql1, sql2, sql3);
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(sql);
			state.setInt(1, orgzID);
			rs = state.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return flag;
	}
	
	/**
	 * 显示用户列表
	 * 
	 * @return users
	 * @throws DBPoolException 
	 * @throws OfficeException 
	 */
	public List<UserInfo> userList() throws DBPoolException, OfficeException {
		List<UserInfo> users = new ArrayList<UserInfo>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement(SHOW_USER);
			rs = state.executeQuery();
			while (rs.next()) {
				UserInfo user = new UserInfo();
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setUserRealName(rs.getString("userRealName"));
				user.setMail(rs.getString("userMail"));
				user.setPermission(rs.getInt("permission"));
				user.setPermissionName(rs.getString("permissionName"));
				user.setDepartmentName(rs.getString("departmentName"));
				user.setOfficeName(rs.getString("officeName"));
				user.setGroupName(rs.getString("groupName"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return users;
	}

	public List<String> namesList(String queryWord) throws DBPoolException, OfficeException {
		List<String> names = new ArrayList<String>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select u.userRealName from userinfo u,Authority a " +
					"where u.permissionID=a.id and u.userName like ? and a.permission<2 limit 10;");
			state.setString(1, "%" + queryWord + "%");
			rs = state.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				names.add(name);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return names;
	}
	
	public List<String> namesList(String queryWord,String groupName) throws DBPoolException, OfficeException {
		List<String> names = new ArrayList<String>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select u.userRealName from userinfo u,Authority a,ProjectGroup p " +
					"where u.permissionID=a.id and u.groupID=p.id and p.groupName=? and u.userName like ? and a.permission<2 limit 10;");
			state.setString(1, groupName);
			state.setString(2, "%" + queryWord + "%");
			rs = state.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				names.add(name);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return names;
	}
	
	public boolean isUserExist(String userName,String password) throws DBPoolException, OfficeException{
		boolean flag = false;
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select * from UserInfo where userName=? and password =?");
			state.setString(1, userName);
			state.setString(2, password);
			rs = state.executeQuery();
			if (rs.next()) {
				flag = true;
			}	
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return flag;
	}
	
	public boolean modifyPassword(String newpsd,String oldpsd,String userName) throws DBPoolException, OfficeException{
		if(!isUserExist(userName,oldpsd)){
			return false;
		}
		boolean flag = false;
		PreparedStatement state = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("update UserInfo set password =? where username=?");
			state.setString(1, newpsd);
			state.setString(2, userName);
			int count =state.executeUpdate();
			if(count > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, null);
		}
		return flag;
	}
	
	public String getUserRealName(String userName) throws DBPoolException, OfficeException {
		String userRealName = null;
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select userRealName from UserInfo where userName=?;");
			state.setString(1, userName);
			rs = state.executeQuery();
			if (rs.next()) {
				userRealName = rs.getString(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return userRealName;
	}

	public List<String> getGroupMails(int groupID) throws DBPoolException, OfficeException {
		List<String> mailAddrs = new ArrayList<String>();
		PreparedStatement state = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select userMail from userInfo where groupID=?;");
			state.setInt(1, groupID);
			rs = state.executeQuery();
			while(rs.next()){
				String mailAddr = rs.getString("userMail");
				mailAddrs.add(mailAddr);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return mailAddrs;
	}

}
