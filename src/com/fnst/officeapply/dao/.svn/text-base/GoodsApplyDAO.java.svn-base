package com.fnst.officeapply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.CurrentRecord;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class GoodsApplyDAO extends BaseDAO {
	
	public GoodsApplyDAO() throws DBPoolException, OfficeException{
	}

	public List<CurrentRecord> CurrRecByGroup(String groupName, String orderBy) throws DBPoolException,
			OfficeException {
		List<CurrentRecord> crgoods = new ArrayList<CurrentRecord>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			pstmt = conn.prepareStatement("select g.goodsName,u.userName,c.count,u.userRealName from CurrentRecord c,UserInfo u,Goods g,ProjectGroup p where c.userID=u.id and c.goodsID=g.id and u.groupID=p.id and p.groupName=? order by "+orderBy+";");
			pstmt.setString(1, groupName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CurrentRecord cr = new CurrentRecord();
				cr.setGoodsName(rs.getString("goodsName"));
				cr.setUserName(rs.getString("userName"));
				cr.setUserRealName(rs.getString("userRealName"));
				cr.setGroupName(groupName);
				cr.setCount(rs.getInt("count"));
				crgoods.add(cr);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, rs);
		}
		return crgoods;
	}
	
	public int getRecordCount(String groupName, String conditionName, String conditionVal) throws OfficeException, DBPoolException {

		PreparedStatement state = null;
		ResultSet rs = null;
		int number = 0;
		Connection conn = manager.getConnection(DB_POOL_NAME);
		try {
			state = conn.prepareStatement("select count(*) from CurrentRecord c,UserInfo u,Goods g,ProjectGroup p where c.userID=u.id and c.goodsID=g.id and u.groupID=p.id and p.groupName=? and " + conditionName + "=?;");
			state.setString(1, groupName);
			state.setString(2, conditionVal);
			rs = state.executeQuery();
			if (rs.next()) {
				number = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
		return number;
	}
	
	
	public List<CurrentRecord> getUserNameRecs(String userName) throws DBPoolException, OfficeException{
		List<CurrentRecord> userNameRecord=new ArrayList<CurrentRecord>();
		PreparedStatement state = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			state = conn.prepareStatement("select c.goodsID,c.userID,g.goodsName,c.count from CurrentRecord c,UserInfo u,Goods g where c.userID=u.id and c.goodsID=g.id and u.userName=?;");
			state.setString(1, userName);
			rs = state.executeQuery();			
			while (rs.next()){
		        CurrentRecord currRec=new CurrentRecord();
		        currRec.setUserName(userName);
		        currRec.setGoodsID(rs.getInt("goodsID"));
		        currRec.setUserID(rs.getInt("userID"));
		        currRec.setGoodsName(rs.getString("goodsName"));
		        currRec.setCount(rs.getInt("count"));
		        userNameRecord.add(currRec);
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, state, rs);
		}
        return userNameRecord;
	}
	
	public boolean addCurrRec(int goodsID, int userID, int count)
			throws OfficeException, DBPoolException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			pstmt = conn
					.prepareStatement("insert into CurrentRecord values (?,?,?,?);");
			pstmt.setString(1, null);
			pstmt.setInt(2, goodsID);
			pstmt.setInt(3, userID);
			pstmt.setInt(4, count);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, null);
		}
		return flag;
	}
	
	public boolean addCurrRec(List<CurrentRecord> currRecs, Connection conn) throws OfficeException, DBPoolException {
		if(currRecs == null || currRecs.size() == 0){
			return false;
		}
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("insert into CurrentRecord values (?,?,?,?);");
			for (CurrentRecord currRec : currRecs) {
				pstmt.setString(1, null);
				pstmt.setInt(2, currRec.getGoodsID());
				pstmt.setInt(3, currRec.getUserID());
				pstmt.setInt(4, currRec.getCount());
				pstmt.addBatch();
			}
			int[] num = pstmt.executeBatch();
			flag = ComUtil.isExecSucc(num);
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			close(new Statement[]{pstmt}, null);
		}
		return flag;
}
	
	public boolean updateCurrRec(List<CurrentRecord> currRecs, Connection conn)
			throws OfficeException, DBPoolException {
		if(currRecs == null || currRecs.size() == 0){
			return false;
		}
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("update CurrentRecord set count=? where userID=? and goodsID=?;");
			for (CurrentRecord currRec : currRecs) {
				pstmt.setInt(1, currRec.getCount());
				pstmt.setInt(2, currRec.getUserID());
				pstmt.setInt(3, currRec.getGoodsID());
				pstmt.addBatch();
			}
			int[] num = pstmt.executeBatch();
			flag = ComUtil.isExecSucc(num);
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			close(new Statement[]{pstmt}, null);
		}
		return flag;
	}
	
	public boolean updateCurrRec(int goodsID, int userID, int count)
			throws OfficeException, DBPoolException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			pstmt = conn.prepareStatement("update CurrentRecord set count=? where userID=? and goodsID=?;");
			pstmt.setInt(1, count);
			pstmt.setInt(2, userID);
			pstmt.setInt(3, goodsID);
			int num = pstmt.executeUpdate();
			if (num > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, null);
		}
		return flag;
	}
	
	public boolean delCurrRec(List<CurrentRecord> currRecs, Connection conn) throws DBPoolException,
			OfficeException {
		if(currRecs == null || currRecs.size() == 0){
			return false;
		}
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement("delete from CurrentRecord where userID=? and goodsID=?;");
			for (CurrentRecord currRec : currRecs) {
				pstmt.setInt(1, currRec.getUserID());
				pstmt.setInt(2, currRec.getGoodsID());
				pstmt.addBatch();
			}
			int[] num = pstmt.executeBatch();
			flag = ComUtil.isExecSucc(num);
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			close(new Statement[]{pstmt}, null);
		}
		return flag;
	}
	
	public boolean delCurrRec(int goodsID, int userID)
			throws DBPoolException, OfficeException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			pstmt = conn.prepareStatement("delete from CurrentRecord where userID=? and goodsID=?;");
			pstmt.setInt(1, userID);
			pstmt.setInt(2, goodsID);
			int num = pstmt.executeUpdate();
			if(num > 0){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, null);
		}
		return flag;
	}
	
	public boolean applyGoods(List<CurrentRecord> delCurrRecs,List<CurrentRecord> updateCurrRecs, List<CurrentRecord> addCurrRecs)
			throws DBPoolException, OfficeException {
		Connection conn = null;
		boolean autoCommit = true;
		boolean isSetAutoCommit = false;
		boolean isDel = false;
		boolean isUpdate = false;
		boolean isAdd = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			autoCommit = conn.getAutoCommit();
			isSetAutoCommit = true;
			conn.setAutoCommit(false);
			isDel = this.delCurrRec(delCurrRecs, conn);
			isUpdate = this.updateCurrRec(updateCurrRecs, conn);
			isAdd = this.addCurrRec(addCurrRecs, conn);
			conn.commit();
			
			return isDel || isUpdate || isAdd;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new OfficeException("rollback fail:" + e);
			}
			throw new OfficeException(e);
		}finally{
			try {
				// 因为是连接池，必须把conn提交方式变回原样,非常重要!!!!
				if (isSetAutoCommit) {
					conn.setAutoCommit(autoCommit);
				}
			} catch (SQLException e) {
				throw new OfficeException("setAutoCommit fail:" + e);
			}
			manager.freeConnection(DB_POOL_NAME, conn, null, null);
		}
		
	}
	
	public boolean isCurrRecExisted(Connection conn, int id, int type) throws DBPoolException, OfficeException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = null;
		switch(type){
		case 0:
			sql = "select id from CurrentRecord where userID=?;";
			break;
		case 1:
			sql = "select id from CurrentRecord where goodsID=?;";
			break;
		default:
			sql = "select id from CurrentRecord where userID=?;";
			break;
		}
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			close(new Statement[] { pstmt }, new ResultSet[] { rs });
		}
		return flag;
	}
	
	public boolean enableApplyStep1(String note, int groupID, String[] goodsNameList) throws DBPoolException, OfficeException {
		Date d = new Date();
		String date = ComUtil.formatDate(d);
		Connection conn = null;
		boolean autoCommit = true;
		boolean isSetAutoCommit = false;
		PreparedStatement pstmt = null;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			autoCommit = conn.getAutoCommit();
			isSetAutoCommit = true;
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement("insert into HistoryRecordList values (?,?,?,?)");
			pstmt.setString(1, null);
			pstmt.setString(2, date);
			pstmt.setInt(3, groupID);
			pstmt.setString(4, note);
			int num = pstmt.executeUpdate();
			boolean flag1 = false;
			if(num > 0){
				flag1 = true;
			}
			boolean flag2 = enableApplyStep2(groupID, goodsNameList, conn);
			//提交
			conn.commit();
			return flag1 || flag2;
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new OfficeException("rollback fail:"+e);
			}
			throw new OfficeException(e);
		} finally {
			try {
				//因为是连接池，必须把conn提交方式变回原样,非常重要!!!!
				if(isSetAutoCommit){
					conn.setAutoCommit(autoCommit);
				}
			} catch (SQLException e) {
				throw new OfficeException("setAutoCommit fail:"+e);
			}
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, null);
		}
	}
	
	public boolean enableApplyStep2(int groupID, String[] goodsNameList, Connection conn) throws DBPoolException, OfficeException{
		Statement state = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		try {
			pstmt3 = conn.prepareStatement("select c.goodsID,c.userID,g.goodsName,u.userName,u.userRealName,c.count from CurrentRecord c, UserInfo u,Goods g where c.userID=u.id and c.goodsID=g.id and u.groupID=? and g.goodsName=?");
			pstmt1 = conn.prepareStatement("insert into HistoryRecord  values (?,?,?,?,?,?)");
			pstmt2 = conn.prepareStatement("delete from CurrentRecord where goodsID=? and userID=?;");
			state = conn.createStatement();
//			rs = state.executeQuery("select * from HistoryRecordList order by historyRecordListID desc;");
			rs = state.executeQuery("select max(historyRecordListID) from HistoryRecordList;");
			int historyRecordListID=0;
			if(rs.next()){
				historyRecordListID=rs.getInt(1);
			}
			for(String goodsName : goodsNameList){
				pstmt3.setInt(1, groupID);
				pstmt3.setString(2, goodsName);
				rs=pstmt3.executeQuery();
				while(rs.next()){
					String userName = rs.getString("userName");
					pstmt1.setString(1, null);
			        pstmt1.setInt(2,historyRecordListID);
			        pstmt1.setString(3,rs.getString("goodsName"));
			        pstmt1.setString(4,userName);
			        pstmt1.setString(5, rs.getString("userRealName"));
			        pstmt1.setInt(6,rs.getInt("count"));
			        pstmt1.addBatch();
			        pstmt2.setInt(1, rs.getInt("goodsID"));
			        pstmt2.setInt(2, rs.getInt("userID"));
			        pstmt2.addBatch();
				}
			}
			int[] num1 = pstmt1.executeBatch();
			int[] num2 = pstmt2.executeBatch();
			boolean flag1 = ComUtil.isExecSucc(num1);
			boolean flag2 = ComUtil.isExecSucc(num2);
			
			return flag1 || flag2;
		}
		catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			close(new Statement[]{state, pstmt1, pstmt2, pstmt3},new ResultSet[]{rs});
		}
	}

	public boolean delOwnGoods(UserInfo user) throws OfficeException, DBPoolException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			conn = manager.getConnection(DB_POOL_NAME);
			pstmt = conn.prepareStatement("delete from CurrentRecord where userID=?");
			pstmt.setInt(1, user.getUserID());
			int num = pstmt.executeUpdate();
			if (num > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new OfficeException(e);
		} finally {
			manager.freeConnection(DB_POOL_NAME, conn, pstmt, null);
		}
		return flag;
	}
}
